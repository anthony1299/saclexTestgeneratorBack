package org.saclex.demo.entities;

import java.util.List;

public class QuestionReponses {
    private Question question;
    private List<Reponse> reponses;

    public QuestionReponses() {
    }

    public QuestionReponses(Question question, List<Reponse> reponses) {
        this.question=question;
        this.reponses=reponses;
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
