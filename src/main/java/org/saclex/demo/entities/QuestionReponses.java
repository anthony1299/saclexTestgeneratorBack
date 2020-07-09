package org.saclex.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class QuestionReponses {
    private List<Reponse> reponses;
    private List<EvalQuestRep> eqr;

    public QuestionReponses() {
        this.reponses=new ArrayList<>();
    }

    public QuestionReponses(List<Reponse> reponses, List<EvalQuestRep> eqr) {
        this.reponses=reponses;
        this.eqr=eqr;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses=reponses;
    }

    public List<EvalQuestRep> getEqr() {
        return eqr;
    }

    public void setEqr(List<EvalQuestRep> eqr) {
        this.eqr=eqr;
    }
}
