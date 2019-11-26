package gifbin;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DefaultController {
    
    @Autowired
    private FileObjectRepository foRepo;
    

    @GetMapping("/")
    public String redirect() {
        return "redirect:/gifs";
    }
    
    @GetMapping("/gifs")
    public String home(){
        return "redirect:/gifs/1";
    }
    
    @GetMapping("/gifs/{id}")
    public String showGifs(Model model, @PathVariable Long id){
        List<FileObject> objectList = foRepo.findAll();
        int size = objectList.size();
        FileObject current = foRepo.getOne(id);
        Long next = null;
        Long previous = null;
        int indexOfCurrent = objectList.indexOf(current);
        if(indexOfCurrent + 1 < size){
            next = objectList.get(indexOfCurrent + 1).getId();
        }
        if(indexOfCurrent - 1 >= 0){
            previous = objectList.get(indexOfCurrent - 1).getId();
        }
        model.addAttribute("count", objectList.size());
        model.addAttribute("next", next);
        model.addAttribute("previous", previous);
        model.addAttribute("current", id);
        return "gifs";
    }
    
    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return foRepo.getOne(id).getContent();
    }
    
    @PostMapping("/gifs")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        if(file.getContentType().equals("image/gif")){
            FileObject fo = new FileObject();
            fo.setContent(file.getBytes());
            System.out.println(file.getContentType());
        
            foRepo.save(fo);
        }
        return "redirect:/gifs";
    }
}
