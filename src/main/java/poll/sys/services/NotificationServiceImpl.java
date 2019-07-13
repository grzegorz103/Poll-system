/*package poll.sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poll.sys.models.Notification;
import poll.sys.repositories.NotificationRepository;

import java.util.List;

@Service ("notificationService")
public class NotificationServiceImpl implements NotificationService
{
        @Autowired
        private NotificationRepository notificationRepository;

        @Override
        public List<Notification> findByUser (int limit)
        {
                return notificationRepository.findAllByUser( user )
                        .stream()
                        .sorted((o1, o2 -> o1.))
        }

        @Override
        public boolean create ()
        {
                return false;
        }

        @Override
        public boolean update ()
        {
                return false;
        }
}
*/