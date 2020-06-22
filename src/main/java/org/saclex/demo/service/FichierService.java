package org.saclex.demo.service;

import org.saclex.demo.entities.Fichier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FichierService {

    //Fonction qui permet de lister toutes les fichiers
     List<Fichier> getAllFichiers();

    //Fonction qui permet de sauvegarder un fichier en BD
     Fichier createFichier(Fichier fichier);

    //FOnction qui permet de modifier un fichier
     Fichier updateFichier( Fichier fichier) ;

    //Fonction qui permet de supprimer un fichier
     void deleteFichier(Long idFichier);

}
