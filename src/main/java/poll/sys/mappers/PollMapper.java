package poll.sys.mappers;

import org.mapstruct.Mapper;
import poll.sys.dto.PollDTO;
import poll.sys.models.Poll;

@Mapper(componentModel = "spring")
public interface PollMapper
{
        Poll pollDTOtoPoll ( PollDTO pollDTO );

        PollDTO pollToDTO ( Poll poll );
}
