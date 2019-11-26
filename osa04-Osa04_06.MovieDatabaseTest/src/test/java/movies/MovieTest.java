/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies;

import static org.fluentlenium.core.filter.FilterConstructor.withText;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Lotta
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieTest extends org.fluentlenium.adapter.junit.FluentTest{
    
    @LocalServerPort
    private Integer port;
    
    @Test
    public void canAddMovieAndAddActorToMovie(){
    
        goTo("http://localhost:" + port + "/movies");
        
        Assert.assertFalse(pageSource().contains("Uuno Espanjassa"));
        Assert.assertFalse(pageSource().contains("Uuno Turhapuro"));
        
        find("#name").fill().with("Uuno Espanjassa");
        find("#lengthInMinutes").fill().with("92");
        find("form").first().submit();
        
        Assert.assertTrue(pageSource().contains("Uuno Espanjassa"));
        Assert.assertFalse(pageSource().contains("Uuno Turhapuro"));
        
        goTo("http://localhost:" + port + "/actors");
        
        Assert.assertFalse(pageSource().contains("Uuno Turhapuro"));
        find("#name").fill().with("Uuno Turhapuro");
        find("form").first().submit();
        
        Assert.assertTrue(pageSource().contains("Uuno Turhapuro"));
    
        find("a", withText().contains("Uuno Turhapuro")).click();
        
        find("#add-to-movie").click();
        
        goTo("http://localhost:" + port + "/movies");
        
        Assert.assertTrue(pageSource().contains("Uuno Espanjassa"));
        Assert.assertTrue(pageSource().contains("Uuno Turhapuro"));
        
        
        
        
        
        
        
        
    
    }
    
    
}
