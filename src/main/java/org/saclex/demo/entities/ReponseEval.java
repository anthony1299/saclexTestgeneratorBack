package org.saclex.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "reponse_user")
public class ReponseEval {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
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

    @Override
    public String toString() {
        return "ReponseEval{" +
                "id=" + id +
                ", evalId=" + evalId +
                ", rep=" + rep +
                '}';
    }

    public void setRep(Reponse rep) {
        this.rep = rep;
    }
}
