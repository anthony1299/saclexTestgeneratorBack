package org.saclex.demo.security;

import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.repositories.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * classe qui implémente UserDetailsService, interface utilisée par
 * spring security pour récupérer les infos d'un utilisateur
 */

@Service
public class DetailsUtilisateurService implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;

    public DetailsUtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    //méthode qui retourne l'interface UserDetails de spring security contenant les infos d'un utisateur, il faut une classe qui implémente cette interface
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByLogin(s);
        if(utilisateur !=null) {
            return new DetailsUtilisateur(utilisateur);
        }else {
            return null;
        }
    }
}
