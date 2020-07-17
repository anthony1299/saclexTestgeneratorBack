package org.saclex.demo.service;

import org.saclex.demo.entities.Question;
import org.saclex.demo.entities.Reponse;
import org.saclex.demo.repositories.QuestionRepository;
import org.saclex.demo.repositories.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReponseServiceImpl implements ReponseService {
    final
    ReponseRepository reponseRepository;
    QuestionRepository questionRepository;

    public ReponseServiceImpl(ReponseRepository reponseRepository, QuestionRepository questionRepository) {
        this.reponseRepository=reponseRepository;
        this.questionRepository=questionRepository;
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

    @Override
    public List<Reponse> findcorrectAnswer(Long q) {
        Question qu=questionRepository.findByIdQuestion(q);
        List<Reponse> lr=new ArrayList<>();
        List<Reponse> lrcorreect=new ArrayList<>();
        System.out.println(qu.getLibelle());
        lr=qu.getReponses();
        for(Reponse r:lr
        ){
            System.out.println(r.getLibelle());
            if(r.getValeur()==true){
                lrcorreect.add(r);
            }

        }
        return lrcorreect;
    }
}
