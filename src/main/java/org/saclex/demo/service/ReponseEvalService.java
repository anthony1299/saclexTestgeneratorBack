package org.saclex.demo.service;

import org.saclex.demo.entities.ReponseEval;

import java.util.List;

public interface ReponseEvalService {
    //Fonction qui permet de lister les reponses d'un etudiant à une question
    List<ReponseEval> reponsesEval();

    //Fonction qui permert d'enregistrer la reponse d"un etudiant a une question d'une evaluation
    ReponseEval saveRepEval( ReponseEval reponse);

    List<ReponseEval> saveAllReponse( List<ReponseEval> reponsesuser);

}
