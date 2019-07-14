package poll.sys.services;

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

@Service
public class PollServiceImpl implements PollService
{
        private final PollRepository pollRepository;

        @Autowired
        public PollServiceImpl ( PollRepository pollRepository )
        {
                this.pollRepository = pollRepository;
        }


        @Override
        public Poll findOne ( Long id )
        {
                return pollRepository.findById( id ).get();
        }

        @Override
        public Page<Poll> findAll ( Pageable pageable )
        {
                return pollRepository.findAllByNonPublicFalseOrderByPostDateDesc( pageable );
        }

        @Override
        @Transactional
        public Poll create ( @NotNull Poll poll )
        {
                if ( SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User )
                {
                        User currentUser = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        poll.setCreator( currentUser );
                }
                poll.setPostDate( LocalDateTime.now() );
                return pollRepository.save( poll );
        }

        @Override
        public Page<Poll> findByUser ( Pageable pageable )
        {
                User currentUser = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return pollRepository.findAllByCreator( pageable, currentUser );
        }
}
