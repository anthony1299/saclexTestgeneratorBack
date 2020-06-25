package org.saclex.demo.service;

import org.saclex.demo.entities.Theme;
import org.saclex.demo.entities.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ThemeService {

    //Fonction qui permet de lister tous les themes
   List<Theme> getAllThemes();

    //Fonction qui permet de sauvegarder un theme en BD
    Theme createTheme(Theme theme);

    Theme findById(Long idtheme);

    //FOnction qui permet de modifier un theme
     Theme updateTheme( Theme theme) ;

    //Fonction qui permet de supprimer un theme
    void deleteTheme(Long idReponse);

    //lister themme en fonction du createur
    List<Theme> findByUser(Utilisateur utilisateur);

    Theme findByLibelle(String Libelle);


}
