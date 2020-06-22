package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.TypeEvaluation;
import org.saclex.demo.repositories.TypeEvaluationRepository;
import org.saclex.demo.service.TypeEvaluationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(name = "typeevaluation/")
@CrossOrigin("*")
public class TypeEvaluationController {

    private final TypeEvaluationService typeEvaluationService;

    public TypeEvaluationController(TypeEvaluationRepository typeEvaluationRepository, TypeEvaluationService typeEvaluationService) {
        this.typeEvaluationService = typeEvaluationService;
    }

    @GetMapping("listerTypeEvaluation")
    public List<TypeEvaluation> getAllTypeEvaluation(){
        return typeEvaluationService.getAllTypeEvaluations();
    }

    @PostMapping("creerTypeEvaluation")
    public TypeEvaluation createTypeEvaluation(@RequestBody TypeEvaluation typeEvaluation){
        return typeEvaluationService.createTypeEvaluation(typeEvaluation);
    }

    @PutMapping("modifierTypeEvaluation")
    public TypeEvaluation updateTypeEvaluation(@RequestBody TypeEvaluation typeEvaluation) throws Exception {
        if(typeEvaluation.getIdTypeE() == null){
            throw new Exception("Type evaluation non existant");
        }

        return typeEvaluationService.updateTypeEvaluation(typeEvaluation);
    }

    @DeleteMapping("supprimerTypeEvaluation/{idTypeEvaluation}")
    public void deleteTypeEvaluation(@PathVariable Long idTypeEvaluation){
       typeEvaluationService.deleteTypeEvaluation(idTypeEvaluation);
    }
}
