package org.saclex.demo.repositories;

import org.saclex.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByLogin(String login);
    Boolean findByLoginAndPassword(String login, String password);
    Utilisateur findByEmailIgnoreCase (String email);
}
