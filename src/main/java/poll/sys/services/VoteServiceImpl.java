package poll.sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poll.sys.models.Vote;
import poll.sys.repositories.VoteRepository;

@Service
public class VoteServiceImpl implements VoteService
{
        private final VoteRepository voteRepository;

        @Autowired
        public VoteServiceImpl ( VoteRepository voteRepository )
        {
                this.voteRepository = voteRepository;
        }

        @Override
        public void vote ( Long id )
        {
                Vote vote = voteRepository.getOne( id );
                vote.setVoteCount( vote.getVoteCount() + 1 );
                voteRepository.save( vote );
        }
}
