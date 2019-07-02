package poll.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poll.sys.models.Poll;

public interface PollRepository extends JpaRepository<Poll, Long>
{
}
