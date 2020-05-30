package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Question;
import org.saclex.demo.repositories.QuestionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/responsable/")
@CrossOrigin
public class QuestionController {
    private final QuestionRepository questionRepository ;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("listerQuestions")
    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    @PostMapping("creerQuestion")
    public Question createQuestion(@RequestBody Question question){
        return questionRepository.save(question);
    }

    @PutMapping("modifierQuestion")
    public Question updateQuestion(@RequestBody Question question) throws Exception {
        if(question.getIdQuestion() == null){
            throw new Exception("Question non existante");
        }

        return questionRepository.save(question);
    }

    @DeleteMapping("supprimerQuestion/{idQuestion}")
    public void deleteQuestion(@PathVariable Long idQuestion){
        questionRepository.deleteById(idQuestion);
    }
}
