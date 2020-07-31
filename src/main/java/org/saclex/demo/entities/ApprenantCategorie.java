package org.saclex.demo.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@XmlRootElement
@Table(name = "appprenant_cat")
public class ApprenantCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur user;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie cat;


    @Column(name = "date_creation")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;


    @Column(name = "active",nullable = false)
    private Boolean valeur;

    public ApprenantCategorie() {
    }

    public ApprenantCategorie(Utilisateur user , Categorie cat , Date dateCreation) {
        this.user = user;
        this.cat = cat;
        this.dateCreation = new Date(  );
        this.valeur=false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getValeur() {
        return valeur;
    }

    public void setValeur(Boolean valeur) {
        this.valeur = valeur;
    }
}
