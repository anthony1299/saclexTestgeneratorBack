package org.saclex.demo.service;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.EvalQuestRep;
import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.repositories.CategorieRepository;
import org.saclex.demo.repositories.EvaluationRepository;
import org.saclex.demo.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class EvaluationServiceImpl implements EvaluationService {

final
EvaluationRepository evaluationRepository;
UtilisateurRepository utilisateurRepository;
EvalQuestRepService evalQuestRepService;
CategorieService categorieService;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository, UtilisateurRepository utilisateurRepository,EvalQuestRepService evalQuestRepService,CategorieService categorieService) {
        this.evaluationRepository=evaluationRepository;
        this.utilisateurRepository=utilisateurRepository;
        this.evalQuestRepService=evalQuestRepService;
        this.categorieService =categorieService;
    }

    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    @Override
    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation updateEvaluation(Evaluation evaluation) {
        return  evaluationRepository.save(evaluation);
    }

    @Override
    public void deleteEvaluation(Long idEvaluation) {
        evaluationRepository.deleteById(idEvaluation);
    }

    @Override
    public Evaluation findById(Long idEvaluation) {
        return evaluationRepository.findById(idEvaluation).get();
    }

    @Override
    public Evaluation lastEval(Long idUser , Long IdCategorie) {
        List<Evaluation>lev=findByIdUser( idUser );
        Categorie c = categorieService.findById( IdCategorie ).get();
        List< EvalQuestRep >levq=new ArrayList <>();
        List<Evaluation> evalCat=new ArrayList <>();
        for( Evaluation e:lev
              ) {
                    levq=evalQuestRepService.findByEval(e.getIdEvaluation());
                    if(levq!=null){
                        for(EvalQuestRep eva:levq
                              ) {
                            if( c.getQuestions().contains( eva.getQuest() ) ){
                                evalCat.add( e );
                            }
                        }

                        }

        }
        System.out.println("eval cat "+evalCat);
        Collections.sort( evalCat , new Comparator < Evaluation >() {
            @Override
            public int compare(Evaluation o1 , Evaluation o2) {
                return o1.getDateCreation().compareTo( o2.getDateCreation() );
            }
        } );
        if( evalCat.size()!=0 ){
            Collections.reverse(evalCat);
            return evalCat.get( 0 );
        }
        else
        return null ;
    }

    @Override
    public List<Evaluation> findByIdUser(Long idUser) {
        Utilisateur u = utilisateurRepository.findById(idUser).get();

        return u.getEvaluations();
    }
}
