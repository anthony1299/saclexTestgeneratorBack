package org.saclex.demo.repositories;

import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    Page<Evaluation> findByUser(Utilisateur utilisateur, Pageable pageable);

}
