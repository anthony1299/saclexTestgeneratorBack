package org.saclex.demo.service;

import org.saclex.demo.entities.EvalQuestRep;
import org.saclex.demo.entities.Evaluation;

import java.util.List;

public interface EvalQuestRepService {
    //Fonction qui permet de sauvegarder une evalquest en BD
    EvalQuestRep createEvalQuestRep(EvalQuestRep evalQuestRep);

    //Fonction qui liste les evalQuestRep en fonction du critere de reussite à savoir reussi ou echoue
    List<EvalQuestRep> findByStatu(Evaluation.statuEval statuEval);

}
