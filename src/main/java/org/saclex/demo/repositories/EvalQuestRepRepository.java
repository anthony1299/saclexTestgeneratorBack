package org.saclex.demo.repositories;

import org.saclex.demo.entities.EvalQuestRep;
import org.saclex.demo.entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvalQuestRepRepository extends JpaRepository<EvalQuestRep,Long> {

    //List des evalquestRep en fonction du statut c'est a dire echoue ou reussi
     List<EvalQuestRep> findByStatut(Evaluation.statuEval statuEval);

}
