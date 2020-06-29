package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.*;
import org.saclex.demo.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("evaluation/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
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
    public List<Evaluation> getAllEvaluation() {
        return evaluationService.getAllEvaluations();
    }

    //Creation d'une evaluation
    @PostMapping("creerEvaluation/{idCategorie}/{nbreQuestions}")
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation, @PathVariable("idCategorie") Long idCategorie, @PathVariable("nbreQuestions") int nbreQuestions) {
        evaluation.setDateCreation(new Date());
        evaluation.setDateModification(new Date());
        Evaluation eval = evaluationService.createEvaluation(evaluation);
        Categorie categorie = categorieService.findById(idCategorie).get();
        //Liste des questions de la categorie
        List<Question> questions_de_la_categorie = categorie.getQuestions();
        Collections.shuffle(questions_de_la_categorie, new Random(3));
        //objet qui contient la liste des questions pour l'evaluation en cours
        List<Question> questionEval = new ArrayList<>();
        //Si le nombre de questions demandé est superieur aux nombre de questions disponibles on lui renvoi toutes les questions disponible
        if (questions_de_la_categorie.size() <= nbreQuestions) {

            for (Question q : questions_de_la_categorie) {
                EvalQuestRep evalQuestRep = new EvalQuestRep();
                evalQuestRep.setEval(eval);
                evalQuestRep.setQuest(q);
                evalQuestRepService.createEvalQuestRep(evalQuestRep);
            }

        } else {
            int compteur = 0;


            //Recuperation de la liste des questions ratées pour cette categorie
            List<EvalQuestRep> listEchec = evalQuestRepService.findByStatu(Evaluation.statuEval.Echoue);
            List<Question> questionsRates = new ArrayList<>();
            for (EvalQuestRep e : listEchec) {
                if (e.getQuest().getCategorie().getIdCategorie() == idCategorie) {
                    questionsRates.add(e.getQuest());
                }
            }
            Set<Question> set = new LinkedHashSet<>(questionsRates);
            questionsRates = new ArrayList<>(set);
            Collections.shuffle(questionsRates, new Random(2));
            for (Question q:questionsRates
                 ) {
                questionEval.add(q);

            }

            //Recuperation de la liste des questions reussie pour cette categorie
            List<EvalQuestRep> listReussi = evalQuestRepService.findByStatu(Evaluation.statuEval.Reussi);
            List<Question> questionsReussi = new ArrayList<>();
            for (EvalQuestRep e : listReussi) {
                if (e.getQuest().getCategorie().getIdCategorie() == idCategorie) {
                    questionsReussi.add(e.getQuest());
                }
            }
            set = new LinkedHashSet<>(questionsReussi);
            questionsReussi = new ArrayList<>(set);


            //Generation de la liste des questions non repondues pour cette categorie
            List<Question> listNonRepondu = new ArrayList<>();
            for (Question q : questions_de_la_categorie
            ) {
                if (!((questionsRates.contains(q))||(questionsReussi.contains(q)))) {
                    listNonRepondu.add(q);
                }
            }

            Collections.shuffle(listNonRepondu, new Random(2));
            for (Question q:listNonRepondu
            ) {
                questionEval.add(q);

            }

            //test des listes
            System.out.println("questions ratees");
            for (Question q : questionsRates
            ) {
                System.out.println(q.getIdQuestion() + "-" + q.getLibelle() + "/n");

            }
            System.out.println("questions reussies");
            for (Question q : questionsReussi
            ) {
                System.out.println(q.getIdQuestion() + "-" + q.getLibelle() + "/n");

            }
            System.out.println("questions non repondues");
            for (Question q : listNonRepondu
            ) {
                System.out.println(q.getIdQuestion() + "-" + q.getLibelle() + "/n");

            }
            //Compteur qui permet de verifier si le nombre de questions sollicité par l'utilisateur est respecté
            System.out.println(questionEval.size());
            System.out.println(nbreQuestions);
            for (Question q : questions_de_la_categorie) {
                if (questionEval.size()<nbreQuestions){
                    for (int i = 0; i <nbreQuestions-questionEval.size() ; i++) {
                        int u=new Random().nextInt(questionsReussi.size());
                        questionEval.add(questionsReussi.get(u));
                        questionsReussi.remove(u);
                    }

                    for (Question question:questionEval
                         ) {
                        EvalQuestRep evalQuestRep = new EvalQuestRep();
                        evalQuestRep.setEval(eval);
                        evalQuestRep.setQuest(question);
                        evalQuestRepService.createEvalQuestRep(evalQuestRep);
                    }
                }else {
                    Collections.shuffle(questionEval);
                    for (Question v:questionEval
                         ) {
                        if (compteur != nbreQuestions) {

                            EvalQuestRep evalQuestRep = new EvalQuestRep();
                            evalQuestRep.setEval(eval);
                            evalQuestRep.setQuest(v);
                            evalQuestRepService.createEvalQuestRep(evalQuestRep);
                            compteur = compteur + 1;

                        }

                    }
                    }

            }


        }
        return eval;
    }

        //Fonction de modification d'une évaluation mais qui n'est pas utilisée
        @PutMapping("modifierEvaluatiion")
        public Evaluation updateEvaluation (@RequestBody Evaluation evaluation) throws Exception {
            if (evaluation.getIdEvaluation() == null) {
                throw new Exception("Evaluation non existante");
            }

            return evaluationService.updateEvaluation(evaluation);
        }

        //fonction de suppression d'une evaluation mais qui n'est pas utilisée
        @DeleteMapping("supprimerEvaluation/{idEvaluation}")
        public void deleteEvaluation (@PathVariable Long idEvaluation){
            evaluationService.deleteEvaluation(idEvaluation);
        }
    }

