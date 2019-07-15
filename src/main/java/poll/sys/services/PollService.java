package poll.sys.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poll.sys.models.Poll;

import java.security.Principal;
import java.util.List;

public interface PollService
{
        Poll findOneByCode ( String code );

        Page<Poll> findAllPublic ( Pageable pageable );

        Poll create ( Poll poll );

        Page<Poll> findByUser ( Pageable pageable );
}
