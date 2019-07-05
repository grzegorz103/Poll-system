package poll.sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poll.sys.models.Poll;
import poll.sys.repositories.PollRepository;
import poll.sys.repositories.VoteRepository;

import javax.transaction.Transactional;
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
        public Page<Poll> findAll ( Pageable pageable)
        {
                return pollRepository.findAllByOrderByPostDateDesc(pageable);
        }

        @Override
        @Transactional
        public Poll create ( Poll poll )
        {
                poll.setPostDate( LocalDateTime.now() );
                return pollRepository.save( poll );
        }
}
