package poll.sys.services;

import poll.sys.models.Poll;

import java.util.List;

public interface PollService
{
        Poll findOne ( Long id );

        List<Poll> findAll ();

        Poll create ( Poll poll );
}
