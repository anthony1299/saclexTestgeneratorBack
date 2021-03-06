package org.saclex.demo.service;

import org.saclex.demo.entities.EvalQuestRep;
import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.entities.Question;
import org.saclex.demo.repositories.EvalQuestRepRepository;
import org.saclex.demo.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvalQuestRepServiceImpl implements EvalQuestRepService {
final
EvalQuestRepRepository evalQuestRepRepository;
EvaluationRepository evaluationRepository;

    public EvalQuestRepServiceImpl(EvalQuestRepRepository evalQuestRepRepository, EvaluationRepository evaluationRepository) {
        this.evalQuestRepRepository=evalQuestRepRepository;
        this.evaluationRepository=evaluationRepository;
    }

    @Override
    public EvalQuestRep createEvalQuestRep(EvalQuestRep evalQuestRep) {
        return evalQuestRepRepository.save(evalQuestRep);
    }

    @Override
    public List<EvalQuestRep> findByStatu(Evaluation.statuEval statuEval) {
        return evalQuestRepRepository.findByStatut(statuEval);
    }

    @Override
    public EvalQuestRep findByEvalAndQuest(Evaluation eval, Question question) {
        return evalQuestRepRepository.findByEvalAndQuest(eval,question);
    }

    @Override
    public List<EvalQuestRep> findByEval(Long ideval) {
        Evaluation ev=evaluationRepository.findById(ideval).get();
        return ev.getEvalQuest();
    }


    @Override
    public EvalQuestRep updateEvalQuestRep(EvalQuestRep evalQuestRep) {
        return evalQuestRepRepository.save(evalQuestRep);
    }
}
