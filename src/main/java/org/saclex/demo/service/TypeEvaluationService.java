package org.saclex.demo.service;

import org.saclex.demo.entities.TypeEvaluation;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TypeEvaluationService {
    //Fonction qui permet de lister tous les TypeEvaluations
     List<TypeEvaluation> getAllTypeEvaluations();

    //Fonction qui permet de sauvegarder un TypeEvaluation en BD
     TypeEvaluation createTypeEvaluation(TypeEvaluation typeEvaluation);

    //FOnction qui permet de modifier un TypeEvaluation
     TypeEvaluation updateTypeEvaluation( TypeEvaluation typeEvaluation) ;

    //Fonction qui permet de supprimer un TypeEvaluation
     void deleteTypeEvaluation(Long idTypeEvaluation);
}
