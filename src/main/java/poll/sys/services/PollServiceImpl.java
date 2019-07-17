package poll.sys.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import poll.sys.models.Poll;
import poll.sys.models.User;
import poll.sys.repositories.PollRepository;
import poll.sys.repositories.VoteRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class PollServiceImpl implements PollService
{
        private final PollRepository pollRepository;

        private Random random = new Random();

        private final int POLL_CODE_LENGTH = 8;

        private final int VOTE_CODE_LENGTH = 10;

        private final NotificationService notificationService;

        @Autowired
        public PollServiceImpl ( PollRepository pollRepository, NotificationService notificationService )
        {
                this.pollRepository = pollRepository;
                this.notificationService = notificationService;
        }


        @Override
        public Poll findOneByCode ( String code )
        {
                return pollRepository.findByCode( code );
        }

        @Override
        public Page<Poll> findAllPublic ( Pageable pageable )
        {
                return pollRepository.findAllByNonPublicFalseOrderByPostDateDesc( pageable );
        }

        @Override
        @Transactional
        public Poll create ( @NotNull Poll poll )
        {
                boolean userLogged = false;
                if ( SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User )
                {
                        userLogged = true;
                        User currentUser = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        poll.setCreator( currentUser );
                }
                poll.setCode( generateRandomString( POLL_CODE_LENGTH ) );
                poll.setPostDate( LocalDateTime.now() );
                poll.getVotes().forEach( e -> e.setCode( generateRandomString( VOTE_CODE_LENGTH ) ) );

                Poll savedPoll = pollRepository.save( poll );
              //  if ( userLogged )
            //            notificationService.create( savedPoll );
                return savedPoll;
        }

        @Override
        public Page<Poll> findByUser ( Pageable pageable )
        {
                User currentUser = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return pollRepository.findAllByCreatorOrderByPostDateDesc( pageable, currentUser );
        }

        private String generateRandomString ( int length )
        {
                return RandomStringUtils.randomAlphanumeric( length );
        }

}
