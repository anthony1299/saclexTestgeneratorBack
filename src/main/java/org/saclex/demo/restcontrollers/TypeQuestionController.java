package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.TypeQuestion;
import org.saclex.demo.repositories.TypeQuestionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/responsable/")
@CrossOrigin
public class TypeQuestionController {

    private final TypeQuestionRepository typeQuestionRepository;

    public TypeQuestionController(TypeQuestionRepository typeQuestionRepository) {
        this.typeQuestionRepository = typeQuestionRepository;
    }

    @GetMapping("listerTypeQuestion")
    public List<TypeQuestion> getAllTypeQuestion(){
        return typeQuestionRepository.findAll();
    }

    @PostMapping("creerTypeQuestion")
    public TypeQuestion createTypeEvaluation(@RequestBody TypeQuestion typeQuestion){
        return typeQuestionRepository.save(typeQuestion);
    }

    @PutMapping("modifierTypeQuestion")
    public TypeQuestion updateTypeQuestion(@RequestBody TypeQuestion typeQuestion) throws Exception {
        if(typeQuestion.getIdTypeQ() == null){
            throw new Exception("Type question non existante");
        }

        return typeQuestionRepository.save(typeQuestion);
    }

    @DeleteMapping("supprimerTypeQuestion/{idTypeQuestion}")
    public void deleteTypeEvaluation(@PathVariable Long idTypeQuestion){
        typeQuestionRepository.deleteById(idTypeQuestion);
    }
}
