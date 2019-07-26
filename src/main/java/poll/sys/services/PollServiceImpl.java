package poll.sys.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import poll.sys.dto.PollDTO;
import poll.sys.mappers.PollMapper;
import poll.sys.models.Poll;
import poll.sys.models.User;
import poll.sys.repositories.PollRepository;
import poll.sys.repositories.VoteRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PollServiceImpl implements PollService
{
        private final PollRepository pollRepository;

        private Random random = new Random();

        private final int POLL_CODE_LENGTH = 8;

        private final int VOTE_CODE_LENGTH = 10;

        private final NotificationService notificationService;

        private final PollMapper pollMapper;

        private final VoteRepository voteRepository;

        @Autowired
        public PollServiceImpl ( PollRepository pollRepository, NotificationService notificationService, PollMapper pollMapper, VoteRepository voteRepository )
        {
                this.pollRepository = pollRepository;
                this.notificationService = notificationService;
                this.pollMapper = pollMapper;
                this.voteRepository = voteRepository;
        }


        @Override
        public PollDTO findOneByCode ( String code )
        {
                return pollMapper.pollToDTO( pollRepository.findByCode( code ) );
        }

        @Override
        public Page<PollDTO> findAllPublic ( Pageable pageable )
        {
                return pollRepository.findAllByNonPublicFalseOrderByPostDateDesc( pageable )
                        .map( pollMapper::pollToDTO );
        }

        @Override
        @Transactional
        public PollDTO create ( @NotNull PollDTO poll )
        {
                Poll pollToSave = pollMapper.pollDTOtoPoll( poll );
                boolean userLogged = false;
                if ( SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User )
                {
                        userLogged = true;
                        User currentUser = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        pollToSave.setCreator( currentUser );
                }
                pollToSave.setCode( generateRandomString( POLL_CODE_LENGTH ) );
                pollToSave.setPostDate( LocalDateTime.now() );
                pollToSave.getVotes().forEach( e -> e.setCode( generateRandomString( VOTE_CODE_LENGTH ) ) );

                Poll savedPoll = pollRepository.save( pollToSave );
                //  if ( userLogged )
                //            notificationService.create( savedPoll );
                return pollMapper.pollToDTO( savedPoll );
        }

        @Override
        public Page<PollDTO> findByUser ( Pageable pageable )
        {
                User currentUser = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return pollRepository.findAllByCreatorOrderByPostDateDesc( pageable, currentUser )
                        .map( pollMapper::pollToDTO );
        }

        @Override
        public List<PollDTO> updateMany ( @NotNull List<PollDTO> pollList )
        {
                User currentUser = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                List<Poll> polls = pollList.stream()
                        .map( e -> pollRepository.findByCode( e.getCode() ) )
                        .filter( e -> !Objects.equals(e.getCreator().getUsername(), currentUser.getUsername() ) )
                        .collect( Collectors.toList() );

                if ( !polls.isEmpty() )
                        throw new RuntimeException( "Permission denied" );

                return pollList.stream()
                        .map( this::update )
                        .collect( Collectors.toList() );
        }

        @Override
        public PollDTO update ( @NotNull PollDTO pollDTO )
        {
                if ( pollRepository.existsByCode( pollDTO.getCode() ) )
                {
                        Poll pollFromDB = pollRepository.findByCode( pollDTO.getCode() );
                        pollFromDB.setNonPublic( pollDTO.isNonPublic() );
                        pollFromDB.setAllowSameIp( pollDTO.isAllowSameIp() );
                        pollFromDB.setMultipleAnswer( pollDTO.isMultipleAnswer() );
                        return pollMapper.pollToDTO( pollRepository.save( pollFromDB ) );
                }
                return null;
        }

        private String generateRandomString ( int length )
        {
                String str;
                do
                {
                        str = RandomStringUtils.randomAlphanumeric( length );
                } while ( pollRepository.existsByCode( str ) && voteRepository.existsByCode( str ) );
                return RandomStringUtils.randomAlphanumeric( length );
        }

}
