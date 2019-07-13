package poll.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poll.sys.models.User;

public interface UserRepository extends JpaRepository<User, Long>
{
        User findUserByUsername ( String username );
}
