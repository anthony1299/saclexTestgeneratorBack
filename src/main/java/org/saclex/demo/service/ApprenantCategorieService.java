package org.saclex.demo.service;

import org.saclex.demo.entities.ApprenantCategorie;
import org.saclex.demo.entities.Categorie;

import java.util.List;

public interface ApprenantCategorieService {

    //créer un enregistrement qui permet d'inscrirze un étudiant à une catégorie
    ApprenantCategorie createAppCategorie(ApprenantCategorie apprenantCategorie);

    ApprenantCategorie getAppCategorieByUserAndCat(Long idUser, Long idCat);

    //mettre à jour un enregistrement d'apprenantCategorie
    ApprenantCategorie updateAppCategorie( ApprenantCategorie apprenantCategorie) ;

    List < ApprenantCategorie > getUserCats(Long iduser) ;

    //List des apprenants qui n'ont pas encore été activé pour une catégorie
    List < ApprenantCategorie > getAppCatNotActive() ;

    List < Categorie > getCatsNotSelect(Long idUser);

}
