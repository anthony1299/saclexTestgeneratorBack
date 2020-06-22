package org.saclex.demo.service;

import org.saclex.demo.entities.Reponse;
import org.saclex.demo.repositories.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseServiceImpl implements ReponseService {
    @Autowired
    ReponseRepository reponseRepository;
    @Override
    public List<Reponse> getAllReponses() {
        return reponseRepository.findAll();
    }

    @Override
    public Reponse createReponse(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    @Override
    public Reponse updateReponse(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    @Override
    public void deleteReponse(Long idReponse) {
        reponseRepository.deleteById(idReponse);
    }
}
