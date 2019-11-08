package persondatabase;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonDatabaseController {

    @Autowired
    private PersonRepository personRepository;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "index";
    }
    
    @PostMapping("/")
    public String post(@RequestParam String name){
        personRepository.save(new Person(name));
        return "redirect:/";
    }

}
