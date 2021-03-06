package org.saclex.demo.repositories;

import org.saclex.demo.entities.ApprenantCategorie;
import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprenantCategorieRepository extends JpaRepository < ApprenantCategorie,Long> {

    @Query("SELECT ap from ApprenantCategorie ap join ap.user user where user.id=?1 and ap.valeur= true ")
     List< ApprenantCategorie> getUserCategories(Long userId);

    @Query("SELECT ap from ApprenantCategorie ap join ap.user user where user.id=?1")
     List< ApprenantCategorie> getUserCategoriInAppCat(Long userId);

    @Query("select ap from ApprenantCategorie ap join ap.user user join ap.cat ctg where user.id=?1 and ctg.idCategorie=?2")
    ApprenantCategorie getApprenantCategorie(Long userId,Long catId);

    @Query("select ap from ApprenantCategorie ap where ap.valeur=false")
    List< ApprenantCategorie> getApprenantCategorieNotActive();

}
