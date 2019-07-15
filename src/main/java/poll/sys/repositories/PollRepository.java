package poll.sys.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import poll.sys.models.Poll;
import poll.sys.models.User;
import poll.sys.models.Vote;

public interface PollRepository extends JpaRepository<Poll, Long>
{
        Poll findByVotes ( Vote vote );

        Page<Poll> findAllByNonPublicFalseOrderByPostDateDesc ( Pageable pageable );

        Page<Poll> findAllByCreatorOrderByPostDateDesc ( Pageable pageable, User currentUser );

        Poll findByCode(String code);
}
