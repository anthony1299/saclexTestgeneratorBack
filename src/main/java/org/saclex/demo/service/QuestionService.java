package org.saclex.demo.service;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Question;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuestionService {


    //Fonction qui permet de lister toutes les questions
     List<Question> getAllQuestions();

    //Fonction qui permet de sauvegarder une question en BD
     Question createQuestion(Question question);

    //FOnction qui permet de modifier une question
     Question updateQuestion( Question question) ;

    //Fonction qui permet de supprimer une question
     void deleteQuestion(Long idQuestion);

     Question findById(Long idQuestion);

    //liste des questions en fonction d'une categorie
     List<Question> findByCategorie(Categorie categorie);

}
