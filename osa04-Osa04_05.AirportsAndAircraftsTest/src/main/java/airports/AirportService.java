package airports;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> list() {
        return airportRepository.findAll();
    }

    public void create(String identifier, String name) {
        if(airportRepository.findByName(name)==null){
            Airport a = new Airport();
            a.setIdentifier(identifier);
            a.setName(name);

            airportRepository.save(a);
        }
    }
}
