package org.saclex.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
@Table(name = "question")
public class Question implements Serializable {

    public enum Difficulte{
        Facile,Moyen,Difficile,Mixte
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_question")
    private Long idQuestion;

    @Column(name ="libelle")
    private String libelle;

    @Column(name ="score")
    private int score;

    @Column(name = "duree")
    private int duree;

    @ManyToOne
    @JoinColumn(name = "type_question")
    private TypeQuestion typeQuestion;


    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "categorie")
    private Categorie categorie;

    @Enumerated(EnumType.STRING)
    @Column(name="difficulte")
    private Difficulte difficulte;

    @JsonIgnore
    @OneToMany(mappedBy = "question",fetch=FetchType.LAZY)
    private List<Fichier> fichiers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "quest",fetch=FetchType.LAZY)
    private List<EvalQuestRep> questEval = new ArrayList<>();


    @OneToMany(mappedBy = "question_associee",fetch=FetchType.LAZY)
    private List<Reponse> reponses = new ArrayList<>();

    @Column(name = "date_creation")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "date_modification")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateModification;

    public Question() {
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<EvalQuestRep> getQuestEval() {
        return questEval;
    }

    public void setQuestEval(List<EvalQuestRep> questEval) {
        this.questEval = questEval;
    }

    public Question(String libelle, int score, int duree, TypeQuestion typeQuestion, Categorie categorie, Difficulte difficulte, Date dateCreation, Date dateModification) {
        this.libelle=libelle;
        this.score=score;
        this.duree=duree;
        this.typeQuestion=typeQuestion;
        this.categorie=categorie;
        this.difficulte=difficulte;
        this.dateCreation=dateCreation;
        this.dateModification=dateModification;
    }

    public Difficulte getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(Difficulte difficulte) {
        this.difficulte=difficulte;
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public TypeQuestion getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(TypeQuestion typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public List<Fichier> getFichiers() {
        return fichiers;
    }

    public void setFichiers(List<Fichier> fichiers) {
        this.fichiers = fichiers;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
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
}
