package org.saclex.demo.service;

import org.saclex.demo.entities.TypeQuestion;
import org.saclex.demo.repositories.TypeQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeQuestionServiceImpl implements TypeQuestionService {

    private final TypeQuestionRepository typeQuestionRepository;

    public TypeQuestionServiceImpl(TypeQuestionRepository typeQuestionRepository) {
        this.typeQuestionRepository = typeQuestionRepository;
    }

    @Override
    public List<TypeQuestion> getAllTypeQuestion() {
        return typeQuestionRepository.findAll();
    }

    @Override
    public TypeQuestion createTypeQuestion(TypeQuestion typeQuestion) {
        return typeQuestionRepository.save(typeQuestion);
    }

    @Override
    public TypeQuestion updateTypeQuestion(TypeQuestion typeQuestion) {
        return typeQuestionRepository.save(typeQuestion);
    }

    @Override
    public void deleteTypeQuestion(Long idTypeQuestion) {
        typeQuestionRepository.deleteById(idTypeQuestion);
    }
}
