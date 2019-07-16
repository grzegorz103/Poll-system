package poll.sys.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "notification")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
        @JoinColumn (name = "poll_id")
        private Poll poll;
}
