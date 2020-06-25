package org.saclex.demo.service;

import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EvaluationServiceImpl implements EvaluationService {

final
EvaluationRepository evaluationRepository;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

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
