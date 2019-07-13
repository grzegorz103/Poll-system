package poll.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poll.sys.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>
{
        UserRole findUserRoleByUserType ( UserRole.UserType userType );
}
