package poll.sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import poll.sys.models.Poll;
import poll.sys.models.Vote;
import poll.sys.repositories.PollRepository;
import poll.sys.repositories.VoteRepository;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class VoteServiceImpl implements VoteService
{
        private final VoteRepository voteRepository;

        private final PollRepository pollRepository;

        private final BCryptPasswordEncoder encoder;

        @Autowired
        public VoteServiceImpl ( VoteRepository voteRepository, PollRepository pollRepository, BCryptPasswordEncoder encoder )
        {
                this.voteRepository = voteRepository;
                this.pollRepository = pollRepository;
                this.encoder = encoder;
        }

        @Override
        public void vote ( Long id )
        {
                Vote vote = voteRepository.getOne( id );
                Poll poll = pollRepository.findByVotes( vote );
                System.out.println( poll == null );
                if ( poll != null )
                {
                        System.out.println( "CCCCC" );
                        //         if ( !poll.isAllowSameIp() && isAlreadyVoted( poll.getUsersIps() ) )
                        throw new RuntimeException( "User has already voted" );
                        //           poll.getUsersIps().add( getCurrentIP() );
                }
                vote.setVoteCount( vote.getVoteCount() + 1 );
                voteRepository.save( vote );
        }

        @Override
        public void voteAll ( List<Long> votes, String ip )
        {
                if ( votes != null && !votes.isEmpty() )
                {
                        Poll poll = pollRepository.findByVotes( voteRepository.findById( votes.get( 0 ) ).get() );
                        if ( poll != null )
                        {
                                if ( !poll.isAllowSameIp() && isAlreadyVoted( poll.getUsersIps(), ip ) )
                                {
                                        throw new RuntimeException( "User already voted" );
                                }
                                poll.getUsersIps().add( encoder.encode( ip ) );
                        }
                        votes.stream()
                                .map( voteRepository::findById )
                                .map( Optional::get )
                                .forEach( e -> {
                                        e.setVoteCount( e.getVoteCount() + 1 );
                                        voteRepository.save( e );
                                } );
                }
        }

        private boolean isAlreadyVoted ( Set<String> ips, String ip )
        {
                return ips.stream().anyMatch( e -> encoder.matches( ip, e ) );
        }
}
