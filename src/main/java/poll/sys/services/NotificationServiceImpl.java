package poll.sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import poll.sys.models.Notification;
import poll.sys.models.Poll;
import poll.sys.models.User;
import poll.sys.repositories.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service ("notificationService")
public class NotificationServiceImpl implements NotificationService
{
        private final NotificationRepository notificationRepository;

        @Autowired
        public NotificationServiceImpl ( NotificationRepository notificationRepository )
        {
                this.notificationRepository = notificationRepository;
        }

        @Override
        public List<Notification> findByUser ()
        {
                User user;

                if ( SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User )
                {
                        user = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        if ( user != null )
                                return notificationRepository.findAllByPoll_Creator( user );

                }
                return null;
        }

        @Override
        public boolean create ( Poll poll )
        {
                Notification notification = Notification.builder()
                        .createdAt( LocalDateTime.now() )
                        .message( "Someone voted in your poll" )
                        .poll( poll )
                        .read( false )
                        .build();
                notificationRepository.save( notification );
                return true;
        }

        @Override
        public boolean update ()
        {
                return false;
        }
}