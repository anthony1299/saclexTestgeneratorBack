package org.saclex.demo.repositories;

import org.saclex.demo.entities.EvalQuestRep;
import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EvalQuestRepRepository extends JpaRepository<EvalQuestRep,Long> {

    //List des evalquestRep en fonction du statut c'est a dire echoue ou reussi
     List<EvalQuestRep> findByStatut(Evaluation.statuEval statuEval);

     EvalQuestRep findByEvalAndQuest(Evaluation eval, Question question);

     @Query("SELECT e from EvalQuestRep e join e.quest q join q.categorie c where c.idCategorie=?1")
    List<EvalQuestRep> findByCategorie(Long idCategorie);


        List<EvalQuestRep> findByEval(Evaluation e);
}
