package org.saclex.demo.service;

import org.saclex.demo.entities.EvalQuestRep;
import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.repositories.EvalQuestRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvalQuestRepServiceImpl implements EvalQuestRepService {
final
EvalQuestRepRepository evalQuestRepRepository;

    public EvalQuestRepServiceImpl(EvalQuestRepRepository evalQuestRepRepository) {
        this.evalQuestRepRepository = evalQuestRepRepository;
    }

    @Override
    public EvalQuestRep createEvalQuestRep(EvalQuestRep evalQuestRep) {
        return evalQuestRepRepository.save(evalQuestRep);
    }

    @Override
    public List<EvalQuestRep> findByStatu(Evaluation.statuEval statuEval) {
        return evalQuestRepRepository.findByStatut(statuEval);
    }
}