package org.saclex.demo.service;

import org.saclex.demo.entities.Reponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReponseService {

    //Fonction qui permet de lister toutes les reponses
     List<Reponse> getAllReponses();

    //Fonction qui permet de sauvegarder une reponse en BD
     Reponse createReponse(Reponse reponse);

    //FOnction qui permet de modifier une reponse
     Reponse updateReponse( Reponse reponse) ;

    //Fonction qui permet de supprimer une reponse
     void deleteReponse(Long idReponse);

     //Fonction qui permet d'enregistrer une liste de reponse
    void saveAllReponse( List<Reponse> reponses);

}
