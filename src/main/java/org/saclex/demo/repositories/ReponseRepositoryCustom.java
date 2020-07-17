package org.saclex.demo.repositories;

import org.saclex.demo.entities.Question;
import org.saclex.demo.entities.Reponse;

import java.util.List;

public interface ReponseRepositoryCustom {
    List<Reponse> findByQuestion_associeeAndValeurIsTrue(Question q);
}
