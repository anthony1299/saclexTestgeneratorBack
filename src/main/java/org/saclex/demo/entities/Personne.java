package org.saclex.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Personne implements Serializable {

    public enum Sexe {
        MASCULIN, FEMININ
    }

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email",unique=true,nullable = false)
    private String email;

    @Column(name = "telephone",unique=true,nullable = false)
    private int telephone;

    @Column(name = "date_naissance", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateNaissance;

    @Column(name = "sexe",nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    public Personne() {
    }

    public Personne(String nom, String prenom, String email, int telephone, Date dateNaissance, Sexe sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
}
