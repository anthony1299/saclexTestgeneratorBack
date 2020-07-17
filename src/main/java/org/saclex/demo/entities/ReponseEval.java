package org.saclex.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "reponse_user")
public class ReponseEval {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn (name = "evalId")
    private EvalQuestRep evalId;

    @ManyToOne
    @JoinColumn(name="rep_user")
    private Reponse rep;


    public ReponseEval() {
    }

    public ReponseEval(EvalQuestRep evalId, Reponse rep) {
        this.evalId = evalId;
        this.rep = rep;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EvalQuestRep getEvalId() {
        return evalId;
    }

    public void setEvalId(EvalQuestRep evalId) {
        this.evalId = evalId;
    }

    public Reponse getRep() {
        return rep;
    }

    public void setRep(Reponse rep) {
        this.rep = rep;
    }
}
