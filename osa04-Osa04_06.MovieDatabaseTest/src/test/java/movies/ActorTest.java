/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ActorTest extends org.fluentlenium.adapter.junit.FluentTest {
    
    @LocalServerPort
    private Integer port;
    
    @Autowired
    ActorRepository actorRepo;
    
    @Test
    public void canAddAndDeleteActor(){
        
        goTo("http://localhost:" + port + "/actors");
        
        Assert.assertFalse(pageSource().contains("Uuno Turhapuro"));
       
        find("#name").fill().with("Tauno Palo");
        find("form").first().submit();
        
        find("#name").fill().with("Uuno Turhapuro");
        find("form").first().submit();
        
        Assert.assertTrue(pageSource().contains("Uuno Turhapuro"));
        
        
        find("#remove-" + actorRepo.findByName("Uuno Turhapuro").getId()).click();
        
        Assert.assertFalse(pageSource().contains("Uuno Turhapuro"));
        
        
    
    }
    
}
