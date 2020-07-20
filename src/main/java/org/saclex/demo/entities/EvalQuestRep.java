package org.saclex.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Null;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
@Table(name = "eval_quest_rep")
public class EvalQuestRep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "evaluation")
    private Evaluation eval;

    @ManyToOne
    @JoinColumn(name = "question")
    private Question quest;


    @Column(name="temps_reponse")
    private Integer tempsMis;

    @Column(name = "date_creation")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "etat")
    @Enumerated(EnumType.STRING)
    private Evaluation.statuEval statut;

    @OneToMany(mappedBy = "evalId",fetch = FetchType.LAZY)
    private List<ReponseEval> repEval = new ArrayList<>();

    public List<ReponseEval> getRepEval() {
        return repEval;
    }

    public void setRepEval(List<ReponseEval> repEval) {
        this.repEval=repEval;
    }

    public EvalQuestRep() {
        this.tempsMis=0;
        this.dateCreation=new Date();
    }

    public EvalQuestRep(Evaluation eval, Question quest) {
        this.eval = eval;
        this.quest = quest;
    }

    public EvalQuestRep(Evaluation eval, Question quest, int tempsMis) {
        this.eval=eval;
        this.quest=quest;
        this.tempsMis=tempsMis;
    }

    public void setTempsMis(Integer tempsMis) {
        this.tempsMis = tempsMis;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getTempsMis() {
        return tempsMis;
    }

    public void setTempsMis(int tempsMis) {
        this.tempsMis=tempsMis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evaluation getEval() {
        return eval;
    }

    public void setEval(Evaluation eval) {
        this.eval = eval;
    }

    public Question getQuest() {
        return quest;
    }

    public void setQuest(Question quest) {
        this.quest = quest;
    }

    public Evaluation.statuEval getStatut() {
        return statut;
    }

    public void setStatut(Evaluation.statuEval statut) {
        this.statut = statut;
    }
}

