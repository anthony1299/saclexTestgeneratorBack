package org.saclex.demo.service;

import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.entities.ListEvaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
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

     //Fonction qui permet de retrouver la dernière évaluation d'un apprenant
     Evaluation lastEval(Long idUser,Long IdCategorie);

     Evaluation lastEval(Long idUser,Long IdCategorie,String niveau);

     List<Evaluation> findByIdUser(Long idUser);
     //liste des évaluations sur un nombre de jour à partir de la date actuelle en reculant
    List<Evaluation> getEvalsByDate(int duree);

     List<Evaluation> findByIntituleAndUer(String intitule,Long idUser);

     ListEvaluation findByUser(Long idUser, int pageable);

}
