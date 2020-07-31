package org.saclex.demo.service;

import org.saclex.demo.entities.*;
import org.saclex.demo.repositories.CategorieRepository;
import org.saclex.demo.repositories.EvaluationRepository;
import org.saclex.demo.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class EvaluationServiceImpl implements EvaluationService {

private final EvaluationRepository evaluationRepository;
private final UtilisateurRepository utilisateurRepository;
private final EvalQuestRepService evalQuestRepService;
private final CategorieService categorieService;

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
        return evaluationRepository.getLastEval( idUser,IdCategorie );
    }

    @Override
    public Evaluation lastEval(Long idUser , Long IdCategorie , String niveau) {
        List<Evaluation>lev=findByIdUser( idUser );
        Categorie c = categorieService.findById( IdCategorie ).get();
        List< EvalQuestRep >levq;
        List<Evaluation> evalCat=new ArrayList <>();
        List<Evaluation> evalCategorie=new ArrayList <>();
        for( Evaluation e:lev
        ) {
            levq=evalQuestRepService.findByEval(e.getIdEvaluation());
            if(levq!=null){
                for(EvalQuestRep eva:levq
                ) {
                    if( c.getQuestions().contains( eva.getQuest() )){
                        evalCat.add( e );
                    }
                }

            }

        }
        for( Evaluation e:evalCat
              ) {

            if( e.getNiveauEval().toString().equals( niveau )){
                evalCategorie.add( e );
            }
        }

        System.out.println("eval cat "+evalCat);
        Collections.sort( evalCategorie , new Comparator < Evaluation >() {
            @Override
            public int compare(Evaluation o1 , Evaluation o2) {
                return o1.getDateCreation().compareTo( o2.getDateCreation() );
            }
        } );
        if( evalCategorie.size()!=0 ){
            Collections.reverse(evalCategorie);
            return evalCategorie.get( 0 );
        }
        else
            return null ;
    }

    @Override
    public ListEvaluation findByUser(Long idUser,int numPage) {
        Utilisateur u = utilisateurRepository.findById(idUser).get();
        Pageable pageable = PageRequest.of(numPage,10, Sort.by(Sort.Direction.ASC,"dateCreation"));
        Page<Evaluation> evaluationPage = evaluationRepository.findByUser(u, pageable);
        return new ListEvaluation(evaluationPage.getContent(),evaluationPage.getTotalPages(),evaluationPage.getNumber());


        // return u.getEvaluations();
    }

    @Override
    public List<Evaluation> findByIdUser(Long idUser) {
              return evaluationRepository.getEvalsByUser(idUser);
    }

    @Override
    public List < Evaluation > findByIntituleAndUer(String intitule , Long idUser) {
        List < Evaluation > listev1=findByIdUser( idUser );
        List < Evaluation > listev2=new ArrayList <>(  );

        for( Evaluation e:listev1
              ) {
            if( e.getIntitule().equals( intitule ) ){
                listev2.add( e );
            }

        }
        Collections.sort( listev2 , new Comparator < Evaluation >() {
            @Override
            public int compare(Evaluation o1 , Evaluation o2) {
                return o1.getDateCreation().compareTo( o2.getDateCreation() );
            }
        } );

        return listev2;
    }
}
