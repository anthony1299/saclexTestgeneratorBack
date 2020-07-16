package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Question;
import org.saclex.demo.entities.Reponse;
import org.saclex.demo.service.CategorieService;
import org.saclex.demo.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class QuestionController {
    private final QuestionService questionService;
    private final CategorieService categorieService;

    public QuestionController(QuestionService questionService, CategorieService categorieService) {
        this.questionService = questionService;
        this.categorieService=categorieService;
    }


    @GetMapping("listerQuestions")
    public List<Question> getAllQuestion(){
        return questionService.getAllQuestions();
    }

    //retourne tous les objets reponses associés à une question
    @GetMapping("listerReponses/{Idquestion}")
    public List<Reponse> getReponse(@PathVariable Long Idquestion ){
       Question question= questionService.findById(Idquestion);
       return question.getReponses();
    }
    //liste des questions par rapport à une catégorie précise
    @GetMapping("questionCategorie/{idcategorie}")
    public List<Question> getquestionCategorie(@PathVariable Long idcategorie ){
        Optional<Categorie> categorie=categorieService.findById(idcategorie);
        List<Question> questions= questionService.findByCategorie(categorie.get());
       return questions;
    }

    //Creation d'une question
    @PostMapping("creerQuestion")
    public Question createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }

    //Modification d'une question
    @PutMapping("modifierQuestion")
    public Question updateQuestion(@RequestBody Question question) throws Exception {
        if(question.getIdQuestion() == null){
            throw new Exception("Question inexistante");
        }

        return questionService.updateQuestion(question);
    }

    //Suppression d'une question à travers l'Id
    @DeleteMapping("supprimerQuestion/{idQuestion}")
    public void deleteQuestion(@PathVariable Long idQuestion){
        questionService.deleteQuestion(idQuestion);
    }
}
