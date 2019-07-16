package poll.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import poll.sys.models.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long>
{
        List<Notification> findAllByUser ( User user );
}
