package org.saclex.demo.service;

import org.saclex.demo.entities.Evaluation;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EvaluationService {

    //Fonction qui permet de lister toutes les evaluations
     List<Evaluation> getAllEvaluations();

    //Fonction qui permet de sauvegarder une evaluation en BD
     Evaluation createEvaluation(Evaluation evaluation);

    //FOnction qui permet de modifier une evaluation
     Evaluation updateEvaluation( Evaluation evaluation) ;

    //Fonction qui permet de supprimer une evaluation
     void deleteEvaluation(Long idEvaluation);

     Evaluation findById(Long idEvaluation);

     Evaluation lastEval(Long idUser,Long IdCategorie);

     Evaluation lastEval(Long idUser,Long IdCategorie,String niveau);

    List<Evaluation> findByIdUser(Long idUser);

}
