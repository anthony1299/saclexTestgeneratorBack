package org.saclex.demo.repositories;

import org.saclex.demo.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
   //rechercher un token par sa valeur
    VerificationToken findByToken(String token);
}