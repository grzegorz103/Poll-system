package poll.sys.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poll.sys.models.Poll;

import java.util.List;

public interface PollService
{
        Poll findOne ( Long id );

        Page<Poll> findAll ( Pageable pageable );

        Poll create ( Poll poll );
}
