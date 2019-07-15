package poll.sys.services;

import java.util.List;

public interface VoteService
{
        void vote ( Long id );

        void voteAll ( List<String> voteCodes, String ip );
}
