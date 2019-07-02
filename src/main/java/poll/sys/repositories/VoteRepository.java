package poll.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poll.sys.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>
{
}
