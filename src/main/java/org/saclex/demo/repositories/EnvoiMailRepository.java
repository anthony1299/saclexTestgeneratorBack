package org.saclex.demo.repositories;

import org.saclex.demo.entities.EnvoiMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvoiMailRepository extends JpaRepository<EnvoiMail,Long> {
}
