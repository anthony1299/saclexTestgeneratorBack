package org.saclex.demo.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

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

    @Column(name = "reponse")
    private Long reponse;

    @Column(name = "etat")
    @Enumerated(EnumType.STRING)
    private Evaluation.statuEval statut;

    public EvalQuestRep() {
    }

    public EvalQuestRep(Evaluation eval, Question quest) {
        this.eval = eval;
        this.quest = quest;
    }

    public EvalQuestRep(Evaluation eval, Question quest, Long reponse) {
        this.eval = eval;
        this.quest = quest;
        this.reponse = reponse;
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

    public Long getReponse() {
        return reponse;
    }

    public void setReponse(Long reponse) {
        this.reponse = reponse;
    }

    public Evaluation.statuEval getStatut() {
        return statut;
    }

    public void setStatut(Evaluation.statuEval statut) {
        this.statut = statut;
    }
}

