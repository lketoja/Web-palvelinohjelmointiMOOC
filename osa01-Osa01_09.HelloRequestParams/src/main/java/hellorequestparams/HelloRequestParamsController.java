package hellorequestparams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloRequestParamsController {
    
    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam String param){
        return "Hello " + param;
    }
    
    @GetMapping("/params")
    @ResponseBody
    public String hello(@RequestParam Map<String, String> parametrit){
        Set<String> avaimet = parametrit.keySet();
        String palautus = "";
        for(String avain : avaimet){
            palautus += avain + "=" + parametrit.get(avain) + "\n";
        }
        return palautus;
    }
    
    

}
