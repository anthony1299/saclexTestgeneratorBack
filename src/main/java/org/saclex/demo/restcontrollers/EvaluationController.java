package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.*;
import org.saclex.demo.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("evaluation/")
@CrossOrigin("*")
public class EvaluationController {
    private final EvaluationService evaluationService;
    private final QuestionService questionService;
    private final CategorieService categorieService;
    private final ThemeService themeService;
    private final EvalQuestRepService evalQuestRepService;

    public EvaluationController(EvaluationService evaluationService, QuestionService questionService, CategorieService categorieService, ThemeService themeService, EvalQuestRepService evalQuestRepService) {
        this.evaluationService = evaluationService;
        this.questionService = questionService;
        this.categorieService = categorieService;
        this.themeService = themeService;
        this.evalQuestRepService = evalQuestRepService;
    }

    //Fonction qui liste toutes les evaluations
    @GetMapping("listerEvaluations")
    public List<Evaluation> getAllEvaluation(){
        return evaluationService.getAllEvaluations();
    }

    //Creation d'une evaluation
    @PostMapping("creerEvaluation")
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation){
        Evaluation eval=evaluationService.createEvaluation(evaluation);
        Theme theme= themeService.findByLibelle("ERP");
        List<Categorie> categories = categorieService.findByTheme(theme);
        //objet qui contient la liste des questions pour l'evaluation en cours
        List<Question> questionEval=new ArrayList<>();
        List<List<Question>> list =new ArrayList<>();
        for (Categorie categorie:categories
             ) {list.add(categorie.getQuestions());
        }
        List<EvalQuestRep> listEchec =evalQuestRepService.findByStatu(Evaluation.statuEval.Echoue);
        for (EvalQuestRep e:listEchec
             ) {
            questionEval.add(e.getQuest());
        }

        for (List<Question> lques:list
             ) {
            for (Question q:lques
                 )

            {   questionEval.add(q);
                EvalQuestRep evalQuestRep=new EvalQuestRep();
                evalQuestRep.setEval(eval);
                evalQuestRep.setQuest(q);
                evalQuestRepService.createEvalQuestRep(evalQuestRep);

            }

        }

        return eval;
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
