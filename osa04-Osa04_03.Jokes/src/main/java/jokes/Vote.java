package jokes;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends AbstractPersistable<Long> {

    private Long jokeId;
    private Integer downVotes = 0;
    private Integer upVotes = 0;

}
