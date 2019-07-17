package poll.sys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poll.sys.models.Vote;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollDTO
{
        private List<Vote> votes;
        private String name;
        private boolean multipleAnswer;
        private LocalDateTime postDate;
        private boolean allowSameIp;
        private boolean nonPublic;
        private String code;
}
