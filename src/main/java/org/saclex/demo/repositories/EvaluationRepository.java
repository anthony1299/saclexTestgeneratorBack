package org.saclex.demo.repositories;

import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RepositoryRestResource
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    Page <Evaluation> findByUser(Utilisateur utilisateur, Pageable pageable);

    @Query(value = "SELECT evaluation.id_evaluation,evaluation.date_modification,evaluation.date_creation,evaluation.intitule,evaluation.statut,evaluation.total,evaluation.total_obtenu,\n" +
            "evaluation.type_evaluation,evaluation.apprenant,evaluation.temps_apprenant,evaluation.temps_evaluation,evaluation.niveau_eval\n" +
            "from evaluation join categorie on evaluation.intitule=categorie.libelle\n" +
            "where categorie.id_categorie=?2\n" +
            "and evaluation.apprenant=?1\n" +
            "ORDER by evaluation.date_creation DESC\n" +
            "LIMIT 1",nativeQuery = true)
    Evaluation getLastEval(Long idUser,Long idCat);

    //Fonction qui récupère les évaluations d'un user
    @Query("select e from Evaluation e join e.user user where user.id=?1 ")
    List<Evaluation> getEvalsByUser(Long idUser);

    //Fonction qui récupère toutes les évaluations sur une certaine période
    @Query(value = "SELECT * from evaluation \n" +
            "WHERE TIMESTAMPDIFF(DAY,CAST(evaluation.date_creation AS Date),CURRENT_DATE) BETWEEN 0 and ?1",nativeQuery = true)
    List<Evaluation> getEvalsByDate(int duree);




}
