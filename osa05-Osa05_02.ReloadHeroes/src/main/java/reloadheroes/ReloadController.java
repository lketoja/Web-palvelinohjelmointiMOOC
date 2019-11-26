package reloadheroes;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {
        String username = (String) session.getAttribute("name");
        ReloadStatus reloadStatus = null;
       
        //jos käyttäjä on jo vieraillut sivulla, haetaan ReloadStatus tietokannasta
        if(username != null){ 
            reloadStatus = reloadStatusRepository.findByName(username);
        }
        
        //uudelle käyttäjälle generoidaan käyttäjätunnus ja tallennetaan se 
        //HttpSession-olioon. Luodaan uusi ReloadStatus.
        if(username == null){ 
           username = RandomStringUtils.randomAlphabetic(10);
           session.setAttribute("name", username);
           reloadStatus = new ReloadStatus(username, 0);
        }
        
        model.addAttribute("status", reloadStatus);
        
        //haetaan tietokannasta ne 5 käyttäjää, jotka ovat tehneet eniten 
        //uudelleenlatauksia
        Pageable pageable = PageRequest.of(0, 5, Sort.by("reloads").descending());
        model.addAttribute("scores", reloadStatusRepository.findAll(pageable));
        
        return "index";

    }
}
