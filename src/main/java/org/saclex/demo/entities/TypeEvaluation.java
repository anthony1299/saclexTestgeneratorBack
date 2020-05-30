package org.saclex.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@XmlRootElement
@Table(name = "type_evaluation")
public class TypeEvaluation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_type_eval")
    private Long idTypeE;

    @Column(name = "libelle")
    private String libelle;

    @JsonIgnore
    @OneToMany(mappedBy = "typeEvaluation",fetch=FetchType.LAZY)
    List<Evaluation> evaluations = new ArrayList<>();

    public TypeEvaluation() {
    }

    public TypeEvaluation(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdTypeE() {
        return idTypeE;
    }

    public void setIdTypeE(Long idTypeE) {
        this.idTypeE = idTypeE;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
