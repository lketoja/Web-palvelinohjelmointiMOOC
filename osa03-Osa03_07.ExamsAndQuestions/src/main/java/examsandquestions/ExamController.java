package examsandquestions;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExamController {
    
    @Autowired
    private ExamRepository examRepo;
    
    @Autowired
    private QuestionRepository questionRepo;


    @GetMapping("/exams")
    public String listExams(Model model) {
        Pageable pageable = PageRequest.of(0,20, Sort.by("examDate").descending());
        model.addAttribute("exams", examRepo.findAll(pageable));
        return "exams";
    }

    @GetMapping("/exams/{id}")
    public String viewExam(Model model, @PathVariable Long id) {
        model.addAttribute("exam", examRepo.getOne(id));
        model.addAttribute("questions", questionRepo.findAll());
        return "exam";
    }

    @PostMapping("/exams")
    public String addExam(@RequestParam String subject,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate examDate) {
        examRepo.save(new Exam(subject, examDate));
        return "redirect:/exams";
    }

    @PostMapping("/exams/{examId}/questions/{questionId}")
    @Transactional
    public String addQuestionToExam(@PathVariable Long examId, @PathVariable Long questionId) {
        Exam exam = examRepo.getOne(examId);
        Question question = questionRepo.getOne(questionId);
        question.getExams().add(exam);
        questionRepo.save(question);
        return "redirect:/exams/" + examId;
    }
}
