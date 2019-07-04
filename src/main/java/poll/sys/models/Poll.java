package poll.sys.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

        @Column(name="multiple_answer")
        private boolean multipleAnswer;
}
