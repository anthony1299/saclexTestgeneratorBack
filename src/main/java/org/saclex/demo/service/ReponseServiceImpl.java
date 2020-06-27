package org.saclex.demo.service;

import org.saclex.demo.entities.Reponse;
import org.saclex.demo.repositories.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseServiceImpl implements ReponseService {
    final
    ReponseRepository reponseRepository;

    public ReponseServiceImpl(ReponseRepository reponseRepository) {
        this.reponseRepository = reponseRepository;
    }

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

    @Override
    public void saveAllReponse(List<Reponse> reponses) {
        for (Reponse r: reponses
             ) {
            reponseRepository.save(r);
        }
    }
}
