package org.saclex.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@Table(name = "type_question")
public class TypeQuestion implements Serializable {
    public enum type_question{

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_type_q")
    private Long idTypeQ;

    @Column(name = "libelle")
    private String libelle;

    @JsonIgnore
    @OneToMany(mappedBy = "typeQuestion",fetch=FetchType.LAZY)
    List<Question> questions = new ArrayList<>();

    public TypeQuestion() {
    }

    public TypeQuestion(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdTypeQ() {
        return idTypeQ;
    }

    public void setIdTypeQ(Long idTypeQ) {
        this.idTypeQ = idTypeQ;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
