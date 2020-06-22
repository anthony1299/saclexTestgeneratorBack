package org.saclex.demo.repositories;

import org.saclex.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    //Rechercher utilisateur par son login
    Utilisateur findByLogin(String login);

    //rechercher un utilisateur par son email independement de la casse
    Utilisateur findByEmailIgnoreCase (String email);

    //liste des utilisateurs par role
    List<Utilisateur> findByRole(Utilisateur.Role role);
}
