package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.repositories.EvaluationRepository;
import org.saclex.demo.service.EvaluationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("evaluation/")
@CrossOrigin("*")
public class EvaluationController {
    private final EvaluationServiceImpl evaluationService;

    public EvaluationController(EvaluationRepository evaluationRepository) {
        evaluationService = new EvaluationServiceImpl();
    }

    //Fonction qui liste toutes les evaluations
    @GetMapping("listerEvaluations")
    public List<Evaluation> getAllEvaluation(){
        return evaluationService.getAllEvaluations();
    }

    //Creation d'une evaluation
    @PostMapping("creerEvaluation")
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation){
        return evaluationService.createEvaluation(evaluation);
    }

    //Fonction de modification d'une évaluation mais qui n'est pas utilisée
    @PutMapping("modifierEvaluatiion")
    public Evaluation updateEvaluation(@RequestBody Evaluation evaluation) throws Exception {
        if(evaluation.getIdEvaluation() == null){
            throw new Exception("Evaluation non existante");
        }

        return evaluationService.updateEvaluation(evaluation);
    }

    //fonction de suppression d'une evaluation mais qui n'est pas utilisée
    @DeleteMapping("supprimerEvaluation/{idEvaluation}")
    public void deleteEvaluation(@PathVariable Long idEvaluation){
        evaluationService.deleteEvaluation(idEvaluation);
    }
}
