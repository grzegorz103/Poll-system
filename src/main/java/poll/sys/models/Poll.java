package poll.sys.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "polls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poll
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        private List<Vote> votes;

        @Column (name = "name")
        private String name;

        @Column (name = "multiple_answer")
        private boolean multipleAnswer;

        @Column (name = "post_date")
        private LocalDateTime postDate;

        @Column (name = "allow_same_ip")
        private boolean allowSameIp;

        @Column (name = "ips")
        @ElementCollection (targetClass = String.class)
        private Set<String> usersIps;
}
