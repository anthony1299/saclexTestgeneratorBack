package org.saclex.demo.service;

import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EvaluationServiceImpl implements EvaluationService {


    EvaluationRepository evaluationRepository;
    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    @Override
    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation updateEvaluation(Evaluation evaluation) {
        return  evaluationRepository.save(evaluation);
    }

    @Override
    public void deleteEvaluation(Long idEvaluation) {
        evaluationRepository.deleteById(idEvaluation);
    }
}
