package org.saclex.demo.service;

import org.saclex.demo.entities.ReponseEval;
import org.saclex.demo.repositories.ReponseEvalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseEvalServiceImpl implements ReponseEvalService {

    final ReponseEvalRepository reponseEvalRepository;

    public ReponseEvalServiceImpl(ReponseEvalRepository reponseEvalRepository) {
        this.reponseEvalRepository = reponseEvalRepository;
    }

    @Override
    public List<ReponseEval> reponsesEval() {
        return reponseEvalRepository.findAll();
    }

    @Override
    public ReponseEval saveRepEval(ReponseEval reponse) {
        return reponseEvalRepository.save(reponse);
    }

    @Override
    public List<ReponseEval> saveAllReponse(List<ReponseEval> reponsesuser) {
       return reponseEvalRepository.saveAll(reponsesuser);
    }
}
