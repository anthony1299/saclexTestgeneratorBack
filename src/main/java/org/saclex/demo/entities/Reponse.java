package org.saclex.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@Table(name = "reponse")
public class Reponse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reponse")
    private Long idReponse;

    @Column(name = "libelle",nullable = false)
    private String libelle;

    @Column(name = "descriptiion")
    private String description;

    @Column(name = "valeur",nullable = false)
    private Boolean valeur;

    @Lob
    @Column(name = "media")
    private byte[] media;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question_associee;

    public Reponse() {
    }

    public Reponse(String libelle, String description, Boolean valeur, byte[] media, Question question_associee) {
        this.libelle = libelle;
        this.description = description;
        this.valeur = valeur;
        this.media = media;
        this.question_associee = question_associee;
    }

    public Long getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(Long idReponse) {
        this.idReponse = idReponse;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getValeur() {
        return valeur;
    }

    public void setValeur(Boolean valeur) {
        this.valeur = valeur;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public Question getQuestion_associee() {
        return question_associee;
    }

    public void setQuestion_associee(Question question_associee) {
        this.question_associee = question_associee;
    }
}
