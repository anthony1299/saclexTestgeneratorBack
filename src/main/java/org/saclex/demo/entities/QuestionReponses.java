package org.saclex.demo.entities;

import java.util.List;

public class QuestionReponses {
    private List<EvalQuestRep> evalQuestRep;
    private Question question;
    private List<Reponse> reponses;

    public QuestionReponses() {
    }

    public QuestionReponses(List<EvalQuestRep> evalQuestRep, Question question, List<Reponse> reponses) {
        this.evalQuestRep = evalQuestRep;
        this.question = question;
        this.reponses = reponses;
    }

    public List<EvalQuestRep> getEvalQuestRep() {
        return evalQuestRep;
    }

    public void setEvalQuestRep(List<EvalQuestRep> evalQuestRep) {
        this.evalQuestRep = evalQuestRep;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question=question;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses=reponses;
    }
}
