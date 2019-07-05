package poll.sys.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table (name = "votes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private int voteCount;

}