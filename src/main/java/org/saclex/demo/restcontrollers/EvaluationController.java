package org.saclex.demo.restcontrollers;

import net.sf.jasperreports.engine.JRException;
import org.saclex.demo.entities.*;
import org.saclex.demo.service.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
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
    private final ReportService reportService;

    public EvaluationController(EvaluationService evaluationService , QuestionService questionService , CategorieService categorieService , ThemeService themeService , ReponseService reponseService , ReponseEvalService reponseEvalService , EvalQuestRepService evalQuestRepService , UtilisateurService utilisateurService , ReportService reportService) {
        this.evaluationService = evaluationService;
        this.questionService = questionService;
        this.categorieService = categorieService;
        this.themeService = themeService;
        this.reponseService=reponseService;
        this.reponseEvalService=reponseEvalService;
        this.evalQuestRepService = evalQuestRepService;
        this.utilisateurService = utilisateurService;
        this.reportService = reportService;
    }

    //Fonction qui liste toutes les evaluations
    @GetMapping("listerEvaluations")
    public List<Evaluation> getAllEvaluation() {
        return evaluationService.getAllEvaluations();
    }

    //Fonction qui liste toutes les evaluations
    @GetMapping("print/{ideval}")
    public void printEvaluation(@PathVariable("ideval") Long ideval) throws SQLException, IOException, JRException {
        reportService.getPdfEval( ideval );
    }

    //Fonction qui permet de retrouver toutes les evaluations d'un utilisateur
    @GetMapping("evaluationByUser/{idUser}")
    public ListEvaluation getEvaluationsByUser(@PathVariable Long idUser, @RequestParam int page){
        return evaluationService.findByUser(idUser, page);
    }
    @GetMapping("evaluationByUserGraph/{idUser}/{intitule}")
    public List<Evaluation> EvaluationsGraph(@PathVariable("idUser") Long idUser, @PathVariable("intitule") String intitule){
        return evaluationService.findByIntituleAndUer( intitule,idUser );
    }
    @PostMapping("nbEval/{idUser}")
    public ListEntier nBEvaluations(@RequestBody List<Categorie> lcat, @PathVariable("idUser") Long idUser ){
            ListEntier lent =new ListEntier( );
            List<Integer> listNumber= new ArrayList<>( );
        for( Categorie c:lcat
              ) {
             listNumber.add( evaluationService.findByIntituleAndUer(c.getLibelle() ,idUser ).size());
        }
        lent.setListNumber( listNumber );

        return lent;
    }
    //Creation d'une evaluation
    @PostMapping("creerEvaluation/{idUser}/{idCategorie}/{nbreQuestions}")
    public List < QuestionReponses > createEvaluation(@RequestBody Evaluation evaluation , @PathVariable("idUser") Long idUser , @PathVariable("idCategorie") Long idCategorie , @PathVariable("nbreQuestions") int nbreQuestions) {

        //Creation de l'objet evaluation
        evaluation.setDateCreation( new Date() );
        evaluation.setDateModification( new Date() );
        evaluation.setNiveauEval( Question.Difficulte.Mixte );
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
        if( questions_de_la_categorie.size() <= nbreQuestions ){

            for( Question v : questions_de_la_categorie
            ) {
                EvalQuestRep evalQuestRep = new EvalQuestRep();
                evalQuestRep.setEval( eval );
                evalQuestRep.setQuest( v );
                tempsEval = tempsEval + v.getDuree();
                evalQuestRepService.createEvalQuestRep( evalQuestRep );
                System.out.println( "question categorie "+evalQuestRep.getId() );
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
            // List < EvalQuestRep > listEchec = evalQuestRepService.findByStatu( Evaluation.statuEval.Echoue );
            List < Question > questionsRates = new ArrayList <>();
            List < Question > questionsReussi = new ArrayList <>();
            //Recuperation de la derniere evaluation
            Evaluation lastEval =evaluationService.lastEval( idUser,idCategorie );
            if( lastEval!=null ){
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
                questionEval.addAll(questionsRates);
            }


            //Construction de la liste des questions non repondues pour cette categorie
            List < Question > listNonRepondu = new ArrayList <>();
            for( Question q : questions_de_la_categorie
            ) {
                if( !((questionsRates.contains( q )) || (questionsReussi.contains( q ))) ){
                    listNonRepondu.add( q );
                }
            }

            //Collections.shuffle( listNonRepondu , new Random( 2 ) );

            //Ajout des questions non répondues à la liste des questions de l'évaluation
            Collections.shuffle( listNonRepondu );
            /*int i=0;
            if (questionEval.size()<nbreQuestions){
                int d =nbreQuestions-questionEval.size();
                while(i< nbreQuestions - questionEval.size() && i<listNonRepondu.size())
                {
                    questionEval.add( listNonRepondu.get( i ) );
                    i=i+1;
                }
            }*/

            questionEval.addAll(listNonRepondu);
            Collections.shuffle( questionEval );
            if( questionEval.size()< nbreQuestions ){
                questionEval.addAll( questionsReussi );
            }
            Collections.shuffle( questionEval );
            int c=0;
            while (c< nbreQuestions && c<questionEval.size()){

                EvalQuestRep evalQuestRep = new EvalQuestRep();
                evalQuestRep.setEval( eval );
                evalQuestRep.setTempsMis( 0 );
                evalQuestRep.setQuest( questionEval.get( c ));
                tempsEval = tempsEval + questionEval.get( c ).getDuree();
                evalQuestRepService.createEvalQuestRep( evalQuestRep );
                System.out.println( "eqr "+evalQuestRep.getId() );

                leqr.add( evalQuestRep );
                c=c+1;

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

    @GetMapping("lasteval/{iduser}/{idcat}")
    public Evaluation getlast(@PathVariable("iduser") Long iduser,@PathVariable("idcat") Long idCat){
        return evaluationService.lastEval( iduser,idCat );
    }

    @PostMapping("correction")
    public ResultatEval correctionEval(@RequestBody List<QuestionReponses> questionReponses){
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

            for(Reponse r:reponseList
            ){
                r2.add(r.getIdReponse());


            }
            Collections.sort(r1);
            Collections.sort(r2);
            if(r1.equals(r2)){

                q.getEqr().setStatut(Evaluation.statuEval.Reussi);
            }else{
                q.getEqr().setStatut(Evaluation.statuEval.Echoue);
            }

            evalQuestReps.add(evalQuestRepService.updateEvalQuestRep(q.getEqr()));



        }
        int totalObtenu=0;
        int total=0;
        int tempsMis=0;
        int tempsEval=0;
        int seuil;
        for(EvalQuestRep e:evalQuestReps
        ){
            total=total+e.getQuest().getScore();
            tempsMis=tempsMis+e.getTempsMis();
            tempsEval=tempsEval+e.getQuest().getDuree();
            if(e.getStatut()==Evaluation.statuEval.Reussi){
                totalObtenu=totalObtenu+e.getQuest().getScore();}
        }
        Evaluation evaluation=(evalQuestReps.get(0).getEval());
        evaluation.setTotal(total);
        evaluation.setTotalObtenu(totalObtenu);
        evaluation.setTempsEvaluation( tempsEval );
        evaluation.setTempsApprenant( tempsMis );
        seuil=evalQuestReps.get(0).getQuest().getCategorie().getSeuil();
        if((totalObtenu/total)*100<seuil){
            evaluation.setStatut(Evaluation.statuEval.Echoue);
        }else {
            evaluation.setStatut(Evaluation.statuEval.Reussi);
        }

        evaluationService.updateEvaluation(evaluation);
        ResultatEval rev=new ResultatEval();
        rev.setEvalQuestReps( evalQuestReps );
        rev.setEvaluation( evaluation );
        return rev;
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

    @GetMapping("nombre")
    public ListEntier getNumber(){
        ListEntier listEntier = new ListEntier(  );
        List<Integer> listNumber= new ArrayList<>( );
        listNumber.add( questionService.getAllQuestions().size() );
        listNumber.add( utilisateurService.getAllUtilisateurs().size() );
        listNumber.add( categorieService.getAllCategories().size() );
        listNumber.add( evaluationService.getAllEvaluations().size() );

        listEntier.setListNumber( listNumber );
        return listEntier;
    }
}