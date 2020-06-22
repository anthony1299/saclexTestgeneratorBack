package org.saclex.demo.service;

import org.saclex.demo.entities.TypeQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TypeQuestionService {
    //Fonction qui permet de lister tous les typeQuestions
     List<TypeQuestion> getAllTypeQuestion();

    //Fonction qui permet de sauvegarder un typeQuestion en BD
     TypeQuestion createTypeQuestion(TypeQuestion typeQuestion);

    //FOnction qui permet de modifier un typeQuestion
     TypeQuestion updateTypeQuestion( TypeQuestion typeQuestion) ;

    //Fonction qui permet de supprimer un typeQuestion
     void deleteTypeQuestion(Long idTypeQuestion);
}
