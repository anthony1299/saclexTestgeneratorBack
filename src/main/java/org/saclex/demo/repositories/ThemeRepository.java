package org.saclex.demo.repositories;

import org.saclex.demo.entities.Theme;
import org.saclex.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme,Long> {
    //lister les themes par rapport à leur createur
    List<Theme> findByUtilisateur(Utilisateur utilisateur);

    //Recuperer un theme grace a son libelle
    Theme findByLibelle(String libelle);
}
