package org.saclex.demo.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
@Table(name = "evaluation")
public class Evaluation implements Serializable {
    public enum statuEval{
        Reussi,Echoue
    }
    public enum TypeEval{
        Formative,Certificative
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_evaluation")
    private Long idEvaluation;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_evaluation")
    private TypeEval typeEvaluation;

    @Column(name="intitule")
    private String intitule;


    @Column(name = "total")
    private Integer total;

    @Column(name="total_obtenu")
    private Integer totalObtenu;


    @Column(name="pourcentage")
    private Integer pourcentage;

    @Column(name="tempsEvaluation")
    private Integer tempsEvaluation;

    @Column(name="tempsApprenant")
    private Integer tempsApprenant;

    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private statuEval statut;

    @Column(name = "date_creation")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "date_modification")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateModification;

    @JsonIgnore
    @OneToMany(mappedBy = "eval",fetch=FetchType.LAZY)
    private List<EvalQuestRep> evalQuest = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "apprenant")
    private Utilisateur user;

    public Evaluation() {
        this.total=0;
        this.totalObtenu=0;
    }

    public Evaluation(TypeEval typeEvaluation, int total, statuEval statut) {
        this.typeEvaluation = typeEvaluation;
        this.total = total;
        this.statut = statut;
    }

    public Evaluation(TypeEval typeEvaluation, int total, statuEval statut, Utilisateur user) {
        this.typeEvaluation = typeEvaluation;
        this.total = total;
        this.statut = statut;
        this.dateCreation = new Date();
        this.dateModification = new Date();
        this.user = user;
    }

    public int getTotalObtenu() {
        return totalObtenu;
    }

    public void setTotalObtenu(int totalObtenu) {
        this.totalObtenu=totalObtenu;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule=intitule;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Evaluation(TypeEval typeEvaluation) {
        this.typeEvaluation = typeEvaluation;
        this.dateCreation = new Date();
        this.dateModification = new Date();
    }

    public Integer getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Integer pourcentage) {
        this.pourcentage=pourcentage;
    }

    public Integer getTempsEvaluation() {
        return tempsEvaluation;
    }

    public void setTempsEvaluation(Integer tempsEvaluation) {
        this.tempsEvaluation=tempsEvaluation;
    }

    public Integer getTempsApprenant() {
        return tempsApprenant;
    }

    public void setTempsApprenant(Integer tempsApprenant) {
        this.tempsApprenant=tempsApprenant;
    }

    public Long getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(Long idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public TypeEval getTypeEvaluation() {
        return typeEvaluation;
    }

    public void setTypeEvaluation(TypeEval typeEvaluation) {
        this.typeEvaluation=typeEvaluation;
    }

    public void setTotal(Integer total) {
        this.total=total;
    }

    public void setTotalObtenu(Integer totalObtenu) {
        this.totalObtenu=totalObtenu;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public statuEval getStatut() {
        return statut;
    }

    public void setStatut(statuEval statut) {
        this.statut = statut;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public List<EvalQuestRep> getEvalQuest() {
        return evalQuest;
    }

    public void setEvalQuest(List<EvalQuestRep> evalQuest) {
        this.evalQuest = evalQuest;
    }
}
