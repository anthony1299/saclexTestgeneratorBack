package org.saclex.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class QuestionReponses {
    private List<ReponseEval> reponses;
    private EvalQuestRep eqr;

    public QuestionReponses() {
        this.reponses=new ArrayList<>();
    }

    public QuestionReponses(List<ReponseEval> reponses, EvalQuestRep eqr) {
        this.reponses=reponses;
        this.eqr=eqr;
    }

    public EvalQuestRep getEqr() {
        return eqr;
    }

    public List<ReponseEval> getReponses() {
        return reponses;
    }

    public void setReponses(List<ReponseEval> reponses) {
        this.reponses=reponses;
    }

    public void setEqr(EvalQuestRep eqr) {
        this.eqr=eqr;
    }
}
