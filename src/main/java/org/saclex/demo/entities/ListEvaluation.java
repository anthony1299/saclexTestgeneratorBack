package org.saclex.demo.entities;

import java.util.List;

public class ListEvaluation {
    private List<Evaluation> evaluationList;
    private int totalPage;
    private int numPage;

    public ListEvaluation() {
    }

    public ListEvaluation(List<Evaluation> evaluationList, int totalPage, int numPage) {
        this.evaluationList = evaluationList;
        this.totalPage = totalPage;
        this.numPage = numPage;
    }

    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }
}
