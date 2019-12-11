package weatherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationServ;
    
    @Autowired
    private LocationRepository locationRepo;


    @GetMapping("/locations")
    public String list(Model model) {
        model.addAttribute("locations", locationServ.readAll());
        return "locations";
    }

    @GetMapping("/locations/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("location", locationServ.readOne(id));
        return "location";
    }
    
    @GetMapping("/flushcaches")
    public String flushcaches(){
        locationServ.flush();
        return "locations";
    }

    @PostMapping("/locations")
    public String add(@ModelAttribute Location location) {
        locationServ.saveLocation(location);
        return "redirect:/locations";
    }
}
