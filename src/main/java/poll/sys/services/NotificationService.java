package poll.sys.services;

import poll.sys.models.Notification;
import poll.sys.models.Poll;

import java.util.List;

public interface NotificationService
{
        List<Notification> findByUser ();

        boolean create (Poll poll);

        boolean update ();

        List<Notification> readByUser();
}
