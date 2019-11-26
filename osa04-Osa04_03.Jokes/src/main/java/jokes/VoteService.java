
package jokes;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Lotta
 */
@Controller
public class VoteService {
    
    @Autowired
    private VoteRepository voteRepository;
    
    @Transactional
    public void saveVote(Long id, String value){
        Vote vote = findVoteByJokeId(id);
        
        if ("up".equals(value)) {
            vote.setUpVotes(vote.getUpVotes() + 1);
        } else if ("down".equals(value)) {
            vote.setDownVotes(vote.getDownVotes() + 1);
        }
    }
    
    @Transactional
    public Vote findVoteByJokeId(Long jokeId) {
        Vote vote = voteRepository.findByJokeId(jokeId);
        if (vote == null) {
            vote = new Vote(jokeId, 0, 0);
            voteRepository.save(vote);
        }
        return vote;
    }
    
    
}
