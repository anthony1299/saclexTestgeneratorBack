package org.saclex.demo.repositories;

import org.saclex.demo.entities.Theme;
import org.saclex.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ThemeRepository extends JpaRepository<Theme,Long> {
    //lister les themes par rapport à leur createur
    List<Theme> findByUtilisateur(Utilisateur utilisateur);

    //Recuperer un theme grace a son libelle
    Theme findByLibelle(String libelle);
}
