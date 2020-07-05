package org.saclex.demo.repositories;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Theme;
import org.saclex.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    List<Categorie> findByTheme(Theme theme);
    List<Categorie> findByRespCat(Utilisateur utilisateur);

}
