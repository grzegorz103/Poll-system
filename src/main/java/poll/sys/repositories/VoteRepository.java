package poll.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poll.sys.models.Vote;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long>
{
        Optional<Vote> findByCode ( String code );
}
