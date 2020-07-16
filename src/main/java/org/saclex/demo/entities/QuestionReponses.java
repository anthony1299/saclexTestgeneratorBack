package org.saclex.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class QuestionReponses {
    private List<ReponseEval> reponses;
    private EvalQuestRep eqr;

    public QuestionReponses() {
        this.reponses=new ArrayList<>();
    }


    public EvalQuestRep getEqr() {
        return eqr;
    }

    public void setEqr(EvalQuestRep eqr) {
        this.eqr=eqr;
    }
}
