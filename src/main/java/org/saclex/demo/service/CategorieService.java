package org.saclex.demo.service;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Theme;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CategorieService {

    //Fonction qui permet de lister toutes les categories
     List<Categorie> getAllCategories();

    //Fonction qui permet de sauvegarder une categorie en BD
     Categorie createCategorie(Categorie categorie);

    //FOnction qui permet de modifier une categorie
     Categorie updateCategorie( Categorie categorie) ;

    //Fonction qui permet de supprimer une categorie
     void deleteCategorie(Long idCategorie);

     //Liste categories par theme
     List<Categorie> findByTheme(Theme theme);

     //Fonction qui retourne une categorie en fonction de son id
    Optional<Categorie> findById(Long id);
}
