package org.saclex.demo.service;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Question;
import org.saclex.demo.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {

    final
    QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long idQuestion) {
        questionRepository.deleteById(idQuestion);

    }

    @Override
    public Question findById(Long idQuestion) {
        return questionRepository.findByIdQuestion(idQuestion);
    }

    @Override
    public List<Question> findByCategorie(Categorie categorie) {
        return questionRepository.findByCategorie(categorie);
    }
}
