package poll.sys.models;

import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "notification")
public class Notification
{
        @Id
        @GeneratedValue (strategy = GenerationType.AUTO)
        private Long id;

        @Column (name = "message")
        private String message;

        @Column (name = "is_read")
        private boolean read;

        @Column (name = "created_at", columnDefinition = "TIMESTAMP NULL")
        private LocalDateTime createdAt;

        @ManyToOne
        @JoinColumn (name = "user_id")
        private User user;
}
