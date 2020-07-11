package org.saclex.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class QuestionReponses {
    private List<Reponse> reponses;
    private EvalQuestRep eqr;

    public QuestionReponses() {
        this.reponses=new ArrayList<>();
    }

    public QuestionReponses(List<Reponse> reponses, EvalQuestRep eqr) {
        this.reponses=reponses;
        this.eqr=eqr;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses=reponses;
    }

    public EvalQuestRep getEqr() {
        return eqr;
    }

    public void setEqr(EvalQuestRep eqr) {
        this.eqr=eqr;
    }
}
