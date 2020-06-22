package org.saclex.demo.service;

import org.saclex.demo.entities.CarteMentale;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CarteMentaleService {

    //Fonction qui permet de lister les cartes mentales
     List<CarteMentale> cartes();

    //Fonction qui permert d'enregistrer une carte mentale en BD
     CarteMentale saveCarte( CarteMentale carteMentale);

    //Fonction qui permet de modifier une carte mentale
     CarteMentale updateCarte( CarteMentale carteMentale);

    //Fonction qui permet de supprimer une carte
     void deleteCarte( Long idCarte);

}
