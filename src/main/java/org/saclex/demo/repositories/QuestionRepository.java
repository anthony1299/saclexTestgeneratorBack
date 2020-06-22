package org.saclex.demo.repositories;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    //retrouver une question par son Id
     Question findByIdQuestion(Long idQuestion);

     //Liste des questions d'une catégorie
    List<Question> findByCategorie(Categorie categorie);
}
