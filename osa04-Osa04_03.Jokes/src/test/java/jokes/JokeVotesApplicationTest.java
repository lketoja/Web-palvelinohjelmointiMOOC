package jokes;

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.lang.reflect.Field;
import java.util.Random;
import javax.transaction.Transactional;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import jokes.JokeVotesApplicationTest.TestConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Points("04-03")
public class JokeVotesApplicationTest {

    @Configuration
    @Import(RefactoringApplication.class)
    public static class TestConfig {

        @Bean
        public JokeService jokeService() {
            return new JokeService() {
                @Override
                public Joke getRandomJoke() {
                    return findJokeById(new Long(new Random().nextInt(50) + 1));
                }

                @Override
                public Joke findJokeById(Long id) {
                    Joke joke = new Joke();
                    joke.setId(id);
                    joke.setJoke("Chuck Norris can make fire using two ice cubes: " + id);
                    return joke;
                }

            };
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VoteRepository voteRepository;

    @Test
    public void voteRepositoryNotInJokeController() {
        boolean found = false;
        for (Field field : Reflex.reflect(JokeController.class).getReferencedClass().getDeclaredFields()) {
            if (VoteRepository.class.equals(field.getType())) {
                found = true;
            }
        }

        assertFalse("The class JokeController should not contain VoteRepository.", found);
    }

    @Test
    public void jokeReturnedFromController() throws Throwable {
        MvcResult res = mockMvc.perform(get("/jokes")).andReturn();
        assertTrue("Jokes should be displayed at /jokes even after the modification.", res.getResponse().getContentAsString().contains("Chuck Norris can make fire using two ice cubes"));
    }

    @Test
    @Transactional
    public void canVoteAJoke() throws Throwable {
        Vote v = new Vote();
        v.setDownVotes(0);
        int ups = new Random().nextInt(50) + 1;
        v.setUpVotes(ups);
        int jokeId = 100 + new Random().nextInt(50) + 1;
        v.setJokeId((long) jokeId);
        long id = voteRepository.save(v).getId();

        mockMvc.perform(post("/jokes/" + jokeId + "/vote").param("value", "up"));

        int upVotesAfter = voteRepository.getOne(id).getUpVotes();

        assertTrue("When a joke is voted, it's vote count should change.", ups == upVotesAfter - 1);
    }

}
