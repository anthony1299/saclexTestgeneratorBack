package org.saclex.demo.service;

import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.repositories.EvaluationRepository;
import org.saclex.demo.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EvaluationServiceImpl implements EvaluationService {

final
EvaluationRepository evaluationRepository;
UtilisateurRepository utilisateurRepository;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository, UtilisateurRepository utilisateurRepository) {
        this.evaluationRepository=evaluationRepository;
        this.utilisateurRepository=utilisateurRepository;
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

    @Override
    public Evaluation findById(Long idEvaluation) {
        return evaluationRepository.findById(idEvaluation).get();
    }

    @Override
    public List<Evaluation> findByIdUser(Long idUser) {
        Utilisateur u = utilisateurRepository.findById(idUser).get();

        return u.getEvaluations();
    }
}
