package org.saclex.demo.repositories;

import org.saclex.demo.entities.Question;
import org.saclex.demo.entities.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Long>,ReponseRepositoryCustom {

}
