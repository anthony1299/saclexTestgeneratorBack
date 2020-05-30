package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.TypeEvaluation;
import org.saclex.demo.repositories.TypeEvaluationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/responsable/")
@CrossOrigin
public class TypeEvaluationController {

    private final TypeEvaluationRepository typeEvaluationRepository;

    public TypeEvaluationController(TypeEvaluationRepository typeEvaluationRepository) {
        this.typeEvaluationRepository = typeEvaluationRepository;
    }

    @GetMapping("listerTypeEvaluation")
    public List<TypeEvaluation> getAllTypeEvaluation(){
        return typeEvaluationRepository.findAll();
    }

    @PostMapping("creerTypeEvaluation")
    public TypeEvaluation createTypeEvaluation(@RequestBody TypeEvaluation typeEvaluation){
        return typeEvaluationRepository.save(typeEvaluation);
    }

    @PutMapping("modifierTypeEvaluation")
    public TypeEvaluation updateTypeEvaluation(@RequestBody TypeEvaluation typeEvaluation) throws Exception {
        if(typeEvaluation.getIdTypeE() == null){
            throw new Exception("Type evaluation non existante");
        }

        return typeEvaluationRepository.save(typeEvaluation);
    }

    @DeleteMapping("supprimerTypeEvaluation/{idTypeEvaluation}")
    public void deleteTypeEvaluation(@PathVariable Long idTypeEvaluation){
        typeEvaluationRepository.deleteById(idTypeEvaluation);
    }
}
