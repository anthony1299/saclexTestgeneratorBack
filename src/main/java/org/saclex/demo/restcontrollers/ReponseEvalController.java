package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.EvalQuestRep;
import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.entities.Question;
import org.saclex.demo.entities.ReponseEval;
import org.saclex.demo.service.EvalQuestRepService;
import org.saclex.demo.service.EvaluationService;
import org.saclex.demo.service.QuestionService;
import org.saclex.demo.service.ReponseEvalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reponseEval/")
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class ReponseEvalController {

    private final ReponseEvalService reponseEvalService ;
    private final EvalQuestRepService evalQuestRepService ;
    private final EvaluationService evaluationService ;
    private final QuestionService questionService ;

    public ReponseEvalController(ReponseEvalService reponseEvalService, EvalQuestRepService evalQuestRepService, EvaluationService evaluationService, QuestionService questionService) {
        this.reponseEvalService = reponseEvalService;
        this.evalQuestRepService=evalQuestRepService;
        this.evaluationService=evaluationService;
        this.questionService=questionService;
    }


    @GetMapping("listerReponseEval")
    public List<ReponseEval> getAllReponse(){
        return reponseEvalService.reponsesEval();
    }

    @PostMapping("creerReponse/{idEvaluation}/{idQuestion}")
    public ReponseEval createReponse(@RequestBody ReponseEval reponse,@PathVariable("idEvaluation") Long idEvalation,@PathVariable("idQuestion") Long idQuestion){
        Evaluation evaluation = evaluationService.findById(idEvalation);
        Question question =questionService.findById(idQuestion);
        EvalQuestRep evalQuestRep = evalQuestRepService.findByEvalAndQuest(evaluation,question);
        reponse.setEvalId(evalQuestRep);
        return reponseEvalService.saveRepEval(reponse);
    }


}
