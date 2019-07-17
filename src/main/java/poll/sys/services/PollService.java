package poll.sys.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poll.sys.dto.PollDTO;
import poll.sys.models.Poll;

import java.security.Principal;
import java.util.List;

public interface PollService
{
        PollDTO findOneByCode ( String code );

        Page<PollDTO> findAllPublic ( Pageable pageable );

        PollDTO create ( PollDTO poll );

        Page<PollDTO> findByUser ( Pageable pageable );
}
