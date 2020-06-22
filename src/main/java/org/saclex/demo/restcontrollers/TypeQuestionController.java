package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.TypeQuestion;
import org.saclex.demo.repositories.TypeQuestionRepository;
import org.saclex.demo.service.TypeQuestionService;
import org.saclex.demo.service.TypeQuestionServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(name = "typequestion/")
@CrossOrigin("*")
public class TypeQuestionController {

    private final TypeQuestionService typeQuestionService;

    public TypeQuestionController(TypeQuestionRepository typeQuestionRepository) {
        typeQuestionService = new TypeQuestionServiceImpl();
    }

    @GetMapping("listerTypeQuestion")
    public List<TypeQuestion> getAllTypeQuestion(){
        return typeQuestionService.getAllTypeQuestion();
    }

    @PostMapping("creerTypeQuestion")
    public TypeQuestion createTypeEvaluation(@RequestBody TypeQuestion typeQuestion){
        return typeQuestionService.createTypeQuestion(typeQuestion);
    }

    @PutMapping("modifierTypeQuestion")
    public TypeQuestion updateTypeQuestion(@RequestBody TypeQuestion typeQuestion) throws Exception {
        if(typeQuestion.getIdTypeQ() == null){
            throw new Exception("Type question inexistant");
        }

        return typeQuestionService.updateTypeQuestion(typeQuestion);
    }

    @DeleteMapping("supprimerTypeQuestion/{idTypeQuestion}")
    public void deleteTypeEvaluation(@PathVariable Long idTypeQuestion){
       typeQuestionService.deleteTypeQuestion(idTypeQuestion);
    }
}
