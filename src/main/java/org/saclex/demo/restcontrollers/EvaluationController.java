package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.*;
import org.saclex.demo.service.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.*;

@RestController
@RequestMapping("evaluation/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class EvaluationController {
    private final EvaluationService evaluationService;
    private final QuestionService questionService;
    private final CategorieService categorieService;
    private final ThemeService themeService;
    private final ReponseService reponseService;
    private final ReponseEvalService reponseEvalService;
    private final EvalQuestRepService evalQuestRepService;
    private final UtilisateurService utilisateurService;

    public EvaluationController(EvaluationService evaluationService, QuestionService questionService, CategorieService categorieService, ThemeService themeService, ReponseService reponseService, ReponseEvalService reponseEvalService, EvalQuestRepService evalQuestRepService, UtilisateurService utilisateurService) {
        this.evaluationService = evaluationService;
        this.questionService = questionService;
        this.categorieService = categorieService;
        this.themeService = themeService;
        this.reponseService=reponseService;
        this.reponseEvalService=reponseEvalService;
        this.evalQuestRepService = evalQuestRepService;
        this.utilisateurService = utilisateurService;
    }

    //Fonction qui liste toutes les evaluations
    @GetMapping("listerEvaluations")
    public List<Evaluation> getAllEvaluation() {
        return evaluationService.getAllEvaluations();
    }

    //Fonction qui permet de retrouver toutes les evaluations d'un utilisateur
    @GetMapping("evaluationByUser/{idUser}")
    public List<Evaluation> getEvaluationsByUser(@PathVariable Long idUser){
        return evaluationService.findByIdUser(idUser);
    }
    //Creation d'une evaluation
    @PostMapping("creerEvaluation/{idUser}/{idCategorie}/{nbreQuestions}")
    public List < QuestionReponses > createEvaluation(@RequestBody Evaluation evaluation , @PathVariable("idUser") Long idUser , @PathVariable("idCategorie") Long idCategorie , @PathVariable("nbreQuestions") int nbreQuestions) {

        //Creation de l'objet evaluation
        evaluation.setDateCreation( new Date() );
        evaluation.setDateModification( new Date() );
        evaluation.setUser( utilisateurService.findById( idUser ) );
        Categorie categorie = categorieService.findById( idCategorie ).get();
        evaluation.setIntitule( categorie.getLibelle() );
        evaluation.setTypeEvaluation( Evaluation.TypeEval.Formative );
        Evaluation eval = evaluationService.createEvaluation( evaluation );
        List < EvalQuestRep > leqr = new ArrayList <>();
        int tempsEval = 0;
        int compteur = 0;

        //Liste des questions de la categorie
        List < Question > questions_de_la_categorie = categorie.getQuestions();
        Collections.shuffle( questions_de_la_categorie , new Random( 3 ) );

        List < QuestionReponses > lqp = new ArrayList <>();

        //objet qui contient la liste des questions pour l'evaluation en cours de creation
        List < Question > questionEval = new ArrayList <>();

        //Si le nombre de questions demandé est superieur aux nombre de questions disponibles on lui renvoi toutes les questions disponible
        if( questions_de_la_categorie.size() <nbreQuestions ){
            System.out.println("je suis dans if");
            for( Question v : questions_de_la_categorie
            ) {
                EvalQuestRep evalQuestRep = new EvalQuestRep();
                evalQuestRep.setEval( eval );
                evalQuestRep.setQuest( v );
                tempsEval = tempsEval + v.getDuree();
                evalQuestRepService.createEvalQuestRep( evalQuestRep );
                System.out.println( "question categorie "+evalQuestRep.getQuest().getLibelle());
                leqr.add( evalQuestRep );

            }

            for( EvalQuestRep eqr : leqr
            ) {
                QuestionReponses qp = new QuestionReponses();
                qp.setEqr( eqr );
                lqp.add( qp );


            }

        } else {

            //Recuperation de la liste des EQR(eval quest rep) ayant pour statut Echoue pour cette categorie
            List < EvalQuestRep > listEchec = evalQuestRepService.findByStatu( Evaluation.statuEval.Echoue );
            List < Question > questionsRates = new ArrayList <>();
            List < Question > questionsReussi = new ArrayList <>();
            //Recuperation de la derniere evaluation
            Evaluation lastEval =evaluationService.lastEval( idUser,idCategorie );
            if( lastEval!=null ){
                System.out.println("last eval"+lastEval.getIdEvaluation());
                List < EvalQuestRep > listeqrlast=evalQuestRepService.findByEval( lastEval.getIdEvaluation() );

                //Remplissage des listes de questions reussies et ratées
                for( EvalQuestRep e:listeqrlast
                ) {
                    if( e.getStatut()== Evaluation.statuEval.Echoue ){
                        questionsRates.add( e.getQuest());
                    }else{
                        questionsReussi.add( e.getQuest() );
                    }

                }
                //test des listes de creation de l'évaluation
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

                if (questionsRates.size()!=0){
                    for( Question q:questionsRates ){
                        if( compteur<=(nbreQuestions*60)/100 ){
                            int u = new Random().nextInt( questionsRates.size() );
                            if (!questionEval.contains(questionsRates.get(u))){
                                questionEval.add( questionsRates.get( u ) );
                                compteur=compteur+1;
                            }


                        }
                    }
                }

            }


            //Construction de la liste des questions non repondues pour cette categorie
            List < Question > listNonRepondu = new ArrayList <>();
            for( Question q : questions_de_la_categorie
            ) {
                if( !((questionsRates.contains( q )) || (questionsReussi.contains( q ))) ){
                    listNonRepondu.add( q );
                }
            }

            Collections.shuffle( listNonRepondu , new Random( 2 ) );

            System.out.println("questions non repondues");
            for (Question q : listNonRepondu
            ) {
                System.out.println(q.getIdQuestion() + "-" + q.getLibelle() + "/n");

            }

            //Ajout des questions non répondues à la liste des questions de l'évaluation
            for( Question q : listNonRepondu
            ) {
                if( compteur<nbreQuestions )
                questionEval.add( q );
                compteur=compteur+1;

            }
            if (questionEval.size()<nbreQuestions){
                for( int i = 0; i < nbreQuestions - questionEval.size(); i++ ) {
                    int u = new Random().nextInt( questionsReussi.size() );
                    if (!questionEval.contains(questionsReussi.get(u))){
                        questionEval.add( questionsReussi.get( u ) );
                    }
                }
            }




/*
            if(listEchec==null && listReussi==null){
                if(questionEval.size() < nbreQuestions){
                    for(int i=0; i < nbreQuestions - questionEval.size(); i++){
                        int u=new Random().nextInt(listNonRepondu.size());
                        questionEval.add(listNonRepondu.get(u));
                        listNonRepondu.remove(u);
                    }
                }
            }

            else{
                //Compteur qui permet de verifier si le nombre de questions sollicité par l'utilisateur est respecté
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
*/
            Collections.shuffle( questionEval );

            for( Question v : questionEval ) {

                    EvalQuestRep evalQuestRep = new EvalQuestRep();
                    evalQuestRep.setEval( eval );
                    evalQuestRep.setQuest( v );
                    tempsEval = tempsEval + v.getDuree();
                    evalQuestRepService.createEvalQuestRep( evalQuestRep );
                    System.out.println( "eqr "+evalQuestRep.getId() );
                    leqr.add( evalQuestRep );


            }


        }
        eval.setTempsEvaluation( tempsEval );
        evaluationService.updateEvaluation( eval );

        for( EvalQuestRep eqr : leqr
        ) {
            QuestionReponses qp = new QuestionReponses();
            qp.setEqr( eqr );
            lqp.add( qp );


        }
        return lqp;
    }

    @PostMapping("correction")
    public List<EvalQuestRep>correctionEval(@RequestBody List<QuestionReponses> questionReponses){
        System.out.println(questionReponses.get(0).getEqr().getTempsMis());
        System.out.println(questionReponses.get(0).getReponses().toString());
        List<Reponse> reponseList;
        List<ReponseEval> reponseEvals;
        List<EvalQuestRep> evalQuestReps=new ArrayList<>();
        for(QuestionReponses q:questionReponses
            ){
            List<Reponse> reponseuser= new ArrayList<>();
            List<Long> r1= new ArrayList<>();
            List<Long> r2= new ArrayList<>();
            System.out.println(q.getEqr().getId());
            reponseEvals=reponseEvalService.saveAllReponse(q.getReponses());
//terst
            for(ReponseEval rep:reponseEvals
                ){

                reponseuser.add(rep.getRep());
            }
            //System.out.println("reponses user "+reponseuser.size());
            for(Reponse r:reponseuser
                ){
                r1.add(r.getIdReponse());

            }
            System.out.println("r1 "+r1.size());
            reponseList=reponseService.findcorrectAnswer(q.getEqr().getQuest().getIdQuestion());

                System.out.println(reponseList.size());

            for(Reponse r:reponseList
            ){
                r2.add(r.getIdReponse());


            }
            System.out.println("r2 "+r2.size());
            Collections.sort(r1);
            System.out.println(r1);
            Collections.sort(r2);
            System.out.println(r2);
            if(r1.equals(r2)){
                q.getEqr().setStatut(Evaluation.statuEval.Reussi);
            }else{
                q.getEqr().setStatut(Evaluation.statuEval.Echoue);
            }

            evalQuestReps.add(evalQuestRepService.updateEvalQuestRep(q.getEqr()));



        }
        int totalObtenu=0;
        int total=0;
        for(EvalQuestRep e:evalQuestReps
            ){
            total=total+e.getQuest().getScore();
            if(e.getStatut()==Evaluation.statuEval.Reussi){
                totalObtenu=totalObtenu+e.getQuest().getScore();}
        }
        Evaluation evaluation=(evalQuestReps.get(0).getEval());
        evaluation.setTotal(total);
        evaluation.setTotalObtenu(totalObtenu);
        if(totalObtenu<total/2){
            evaluation.setStatut(Evaluation.statuEval.Echoue);
        }else {
            evaluation.setStatut(Evaluation.statuEval.Reussi);
        }
        evaluationService.updateEvaluation(evaluation);
        return evalQuestReps;
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

