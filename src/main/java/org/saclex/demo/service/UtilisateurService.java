package org.saclex.demo.service;

import org.saclex.demo.entities.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UtilisateurService {
    //Fonction qui permet de lister tous les utilisateurs
     List<Utilisateur> getAllUtilisateurs();

    //Fonction qui permet de sauvegarder un utilisatuer en BD
     Utilisateur createUtilisateur(Utilisateur utilisateur);

    //FOnction qui permet de modifier un utilisateur
     Utilisateur updateUtilisateur( Utilisateur utilisateur) ;

    //Fonction qui permet de supprimer un utilisateur
     void deleteUtilisateur(Long idUtilisateur);

     List<Utilisateur> findByRole(Utilisateur.Role role);

     Utilisateur findByEmailIgnoreCase(String email);
     Utilisateur findByLogin(String login);
}
