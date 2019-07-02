package poll.sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poll.sys.models.Poll;
import poll.sys.repositories.PollRepository;

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
        public List<Poll> findAll ()
        {
                return pollRepository.findAll();
        }

        @Override
        public Poll create ( Poll poll )
        {
                return pollRepository.save( poll );
        }
}
