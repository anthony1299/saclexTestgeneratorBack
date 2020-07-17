package org.saclex.demo.repositories;

import org.saclex.demo.entities.Question;
import org.saclex.demo.entities.Reponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReponseRepositoryImpl implements ReponseRepositoryCustom {

    @Override
    public List<Reponse> findByQuestion_associeeAndValeurIsTrue(Question q) {
        List<Reponse> lr=new ArrayList<>();
        List<Reponse> lrcorreect=new ArrayList<>();
        System.out.println(q.getLibelle());
        lr=q.getReponses();
        for(Reponse r:lr
            ){
            System.out.println(r.getLibelle());
            if(r.getValeur()==true){
                lrcorreect.add(r);
            }

        }
        return lr;
    }
}
