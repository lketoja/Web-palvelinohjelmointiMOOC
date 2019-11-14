package examsandquestions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {
    
    @Autowired
    private QuestionRepository questionRepo;

    @GetMapping("/questions")
    public String list(Model model) {
        model.addAttribute("questions", questionRepo.findAll());
        return "questions";
    }

    @PostMapping("/questions")
    public String addQuestion(@RequestParam String title, @RequestParam String content) {
        questionRepo.save(new Question(title, content));
        return "redirect:/questions";
    }

}
