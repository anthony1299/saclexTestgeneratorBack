package org.saclex.demo.service;

import org.saclex.demo.entities.TypeEvaluation;
import org.saclex.demo.repositories.TypeEvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeEvaluationServiceImpl implements TypeEvaluationService {

    private final TypeEvaluationRepository typeEvaluationRepository;

    public TypeEvaluationServiceImpl(TypeEvaluationRepository typeEvaluationRepository) {
        this.typeEvaluationRepository = typeEvaluationRepository;
    }


    @Override
    public List<TypeEvaluation> getAllTypeEvaluations() {
        return typeEvaluationRepository.findAll();
    }

    @Override
    public TypeEvaluation createTypeEvaluation(TypeEvaluation typeEvaluation) {
        return typeEvaluationRepository.save(typeEvaluation);
    }

    @Override
    public TypeEvaluation updateTypeEvaluation(TypeEvaluation typeEvaluation) {
        return typeEvaluationRepository.save(typeEvaluation);
    }

    @Override
    public void deleteTypeEvaluation(Long idTypeEvaluation) {
        typeEvaluationRepository.deleteById(idTypeEvaluation);

    }
}
