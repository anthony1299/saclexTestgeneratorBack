package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.repositories.EvaluationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class EvaluationController {
    private final EvaluationRepository evaluationRepository;

    public EvaluationController(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @GetMapping("listerEvaluations")
    public List<Evaluation> getAllEvaluation(){
        return evaluationRepository.findAll();
    }

    @PostMapping("creerEvaluation")
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation){
        return evaluationRepository.save(evaluation);
    }

    @PutMapping("modifierEvaluatiion")
    public Evaluation updateEvaluation(@RequestBody Evaluation evaluation) throws Exception {
        if(evaluation.getIdEvaluation() == null){
            throw new Exception("Evaluation non existante");
        }

        return evaluationRepository.save(evaluation);
    }

    @DeleteMapping("supprimerEvaluation/{idEvaluation}")
    public void deleteEvaluation(@PathVariable Long idEvaluation){
        evaluationRepository.deleteById(idEvaluation);
    }
}
