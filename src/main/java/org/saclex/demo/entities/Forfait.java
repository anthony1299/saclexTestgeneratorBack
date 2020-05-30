package org.saclex.demo.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@Table(name = "forfait")
public class Forfait implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_forfait")
    private Long idForfait;

    @Column(name = "libelle")
    private String libele;

    @Column(name = "description")
    private String description;

    @Column(name = "montant")
    private int montant;

    @Column(name = "duree")
    private int duree;

    @OneToMany(mappedBy ="forfait",fetch=FetchType.LAZY)
    List<Utilisateur> utilisateurs = new ArrayList<>();

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Forfait() {
    }

    public Forfait(String libele, String description, int montant, int duree) {
        this.libele = libele;
        this.description = description;
        this.montant = montant;
        this.duree = duree;
    }

    public Long getIdForfait() {
        return idForfait;
    }

    public void setIdForfait(Long idForfait) {
        this.idForfait = idForfait;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}

