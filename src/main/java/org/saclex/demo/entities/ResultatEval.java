package org.saclex.demo.entities;

import java.util.List;

public class ResultatEval {
    private List <EvalQuestRep> evalQuestReps;
    private Evaluation evaluation;

    public ResultatEval() {
    }

    public List < EvalQuestRep > getEvalQuestReps() {
        return evalQuestReps;
    }

    public void setEvalQuestReps(List < EvalQuestRep > evalQuestReps) {
        this.evalQuestReps = evalQuestReps;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
