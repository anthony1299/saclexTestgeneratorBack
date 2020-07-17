package org.saclex.demo.repositories;

import org.saclex.demo.entities.ReponseEval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseEvalRepository extends JpaRepository<ReponseEval,Long> {
}
