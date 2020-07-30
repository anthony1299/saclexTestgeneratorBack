package org.saclex.demo.service;

import org.saclex.demo.entities.ApprenantCategorie;
import org.saclex.demo.entities.Categorie;

import java.util.List;

public interface ApprenantCategorieService {

    ApprenantCategorie createAppCategorie(ApprenantCategorie apprenantCategorie);
    ApprenantCategorie updateAppCategorie( ApprenantCategorie apprenantCategorie) ;
    List < ApprenantCategorie > getUserCats(Long iduser) ;
}
