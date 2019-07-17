package poll.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poll.sys.models.Notification;
import poll.sys.models.User;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long>
{
        List<Notification> findAllByPoll_CreatorOrderByCreatedAtDesc ( User user );
}
