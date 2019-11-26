/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airports;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Lotta
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {
    
    @Autowired
    private AirportService airportServ;
    
    @Autowired
    private AirportRepository airportRepo;
    
    @Test
    public void savingAirportToDBWorks(){
        airportServ.create("FOU", "Fourier");
        System.out.println(airportRepo.findByName("Fourier"));
        Assert.assertTrue(airportRepo.findByName("Fourier")!= null); 
    }
    
    @Test
    public void methodListWorks(){
        airportRepo.save(new Airport("TKU", "Turku"));
        airportRepo.save(new Airport("TRE", "Tampere"));
        airportRepo.save(new Airport("ROV", "Rovaniemi"));
        
        List<Airport> serviceList = airportServ.list();
        List<Airport> repositorioList = airportRepo.findAll();
        
        Assert.assertEquals(serviceList.size(), repositorioList.size());
    }
    
    @Test
    public void cantCreateTwoAirportsWithSameName(){
        airportServ.create("KOT", "Kotka");
        Long airportCountBefore = airportRepo.count();
        airportServ.create("KOT", "Kotka");
        Long airportCountAfter = airportRepo.count();
        Assert.assertEquals(airportCountBefore, airportCountAfter);
        
    }
    
}
