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
    private final UtilisateurService utilisateurService;

    public EvaluationController(EvaluationService evaluationService, QuestionService questionService, CategorieService categorieService, ThemeService themeService, EvalQuestRepService evalQuestRepService, UtilisateurService utilisateurService) {
        this.evaluationService = evaluationService;
        this.questionService = questionService;
        this.categorieService = categorieService;
        this.themeService = themeService;
        this.evalQuestRepService = evalQuestRepService;
        this.utilisateurService = utilisateurService;
    }

    //Fonction qui liste toutes les evaluations
    @GetMapping("listerEvaluations")
    public List<Evaluation> getAllEvaluation() {
        return evaluationService.getAllEvaluations();
    }

    //Creation d'une evaluation
    @PostMapping("creerEvaluation/{idUser}/{idCategorie}/{nbreQuestions}")
    public List<QuestionReponses> createEvaluation(@RequestBody Evaluation evaluation,@PathVariable("idUser") Long idUser, @PathVariable("idCategorie") Long idCategorie, @PathVariable("nbreQuestions") int nbreQuestions) {
        evaluation.setDateCreation(new Date());
        evaluation.setDateModification(new Date());
        evaluation.setUser(utilisateurService.findById(idUser));
        Categorie categorie = categorieService.findById(idCategorie).get();
        Evaluation eval = evaluationService.createEvaluation(evaluation);
        int compteur = 0;
        //Liste des questions de la categorie
        List<Question> questions_de_la_categorie = categorie.getQuestions();
        Collections.shuffle(questions_de_la_categorie, new Random(3));
        //objet qui contient la liste des questions pour l'evaluation en cours de creation
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



            //Recuperation de la liste des questions ratées pour cette categorie
            List<EvalQuestRep> listEchec = evalQuestRepService.findByStatu(Evaluation.statuEval.Echoue);
            //On verifie si l'apprenant avait déja raté des questions de cette categorie
            List<Question> questionsRates = new ArrayList<>();
            if (listEchec!=null){
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

            }

            //Recuperation de la liste des questions reussie pour cette categorie
            List<EvalQuestRep> listReussi = evalQuestRepService.findByStatu(Evaluation.statuEval.Reussi);
            //On verifie si il avait déja reussi des questions dans cette categorie
            List<Question> questionsReussi = new ArrayList<>();
            if (listReussi!=null){
                for(EvalQuestRep e: listReussi){
                    if(e.getQuest().getCategorie().getIdCategorie()==idCategorie){
                        questionsReussi.add(e.getQuest());
                    }
                }
                Set<Question> set=new LinkedHashSet<>(questionsReussi);
                questionsReussi=new ArrayList<>(set);

            }

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

            if(listEchec==null && listReussi==null){
                if(questionEval.size() < nbreQuestions){
                    for(int i=0; i < nbreQuestions - questionEval.size(); i++){
                        int u=new Random().nextInt(listNonRepondu.size());
                        questionEval.add(listNonRepondu.get(u));
                        listNonRepondu.remove(u);
                    }
                }
            }else{
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
                    }else {

                    }

                }
            }



        }
        Collections.shuffle(questionEval,new Random(5));
        List<EvalQuestRep> leqr= new ArrayList<>();
        for (Question v:questionEval
        ) {
            if (compteur != nbreQuestions) {

                EvalQuestRep evalQuestRep = new EvalQuestRep();
                evalQuestRep.setEval(eval);
                evalQuestRep.setQuest(v);
                evalQuestRepService.createEvalQuestRep(evalQuestRep);
                System.out.println(evalQuestRep.getId());
                leqr.add(evalQuestRep);
                compteur = compteur + 1;

            }

        }
        List<QuestionReponses> lqp=new ArrayList<>();
        for(Question question:questionEval
            ){
            QuestionReponses qp=new QuestionReponses();
            qp.setEqr(leqr);
            qp.setReponses(question.getReponses());
            lqp.add(qp);

        }
        return lqp;
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

