package org.saclex.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "active",nullable = false)
    private Boolean valeur;

    public ApprenantCategorie() {
    }

    public ApprenantCategorie(Utilisateur user , Categorie cat) {
        this.user = user;
        this.cat = cat;
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
}
