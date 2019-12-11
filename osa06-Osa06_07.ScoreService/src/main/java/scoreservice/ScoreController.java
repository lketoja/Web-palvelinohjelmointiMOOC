/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lotta
 */
@RestController
public class ScoreController {
    
    @Autowired
    private GameRepository gameRepo;
    
    @Autowired
    private ScoreRepository scoreRepo;
    
    @PostMapping("/games/{name}/scores")
    public Score postScore(@PathVariable String name, @RequestBody Score score){
        score.setGame(gameRepo.findByName(name));
        return scoreRepo.save(score);
    }
    
    @GetMapping("games/{name}/scores")
    public List<Score> getScores(@PathVariable String name){
        Game game = gameRepo.findByName(name);
        return scoreRepo.findByGame(game);
    }
    
    @GetMapping("/games/{name}/scores/{id}")
    public Score getScore(@PathVariable String name, @PathVariable Long id){
        Game game = gameRepo.findByName(name);
        return scoreRepo.findByGameAndId(game, id);
   
    }
    
    @DeleteMapping("/games/{name}/scores/{id}")
    public Score deleteScore(@PathVariable String name, @PathVariable Long id){
        Game game = gameRepo.findByName(name);
        Score score = scoreRepo.findByGameAndId(game, id);
        scoreRepo.delete(score);
        return score;
    }
    
   
    
    
}
