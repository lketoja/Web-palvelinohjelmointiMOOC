/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airports;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Lotta
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AircraftControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private AircraftRepository aircraftRepo;
    
    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void modelHasAttributeAircrafts()throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(model().attributeExists("aircrafts")); 
    }
    
    @Test
    public void modelHasAttributeAirports()throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(model().attributeExists("airports")); 
    }
    
    @Test
    public void postRequestSavesTheAircraftToDB() throws Exception {
        
        mockMvc.perform(post("/aircrafts").param("name", "HA-LOL"))
               .andExpect(redirectedUrl("/aircrafts"));
        
        Assert.assertFalse(aircraftRepo.findByName("HA-LOL")==null);
    }
    
    @Test
    public void postRequestSavesTheAircraftAndModelHasItListed() throws Exception {
        
        mockMvc.perform(post("/aircrafts").param("name", "XP-55"))
               .andExpect(redirectedUrl("/aircrafts"));
        
        MvcResult result = mockMvc.perform(get("/aircrafts")).andReturn();
        
        List<Aircraft> aircrafts = (List) result.getModelAndView()
                .getModel()
                .get("aircrafts");
        
        List<String> names = aircrafts.stream()
                .map(aircraft -> aircraft.getName())
                .collect(Collectors.toList());
        
        System.out.println(names);
        
        Assert.assertTrue(names.contains("XP-55"));
    }
    
        
    
}
