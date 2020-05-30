package org.saclex.demo.repositories;

import org.saclex.demo.entities.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichierRepository extends JpaRepository<Fichier,Long> {
}
