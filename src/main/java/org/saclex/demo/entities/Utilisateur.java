package org.saclex.demo.entities;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@Entity
@XmlRootElement
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom",nullable = false)
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "email",nullable = false)
    private String email;


    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @Column(name = "description")
    private String description;

    @Column(nullable = false)
    private boolean isActive;

    public enum Role {
        ADMINISTRATEUR,APPRENANT,RESPONSABLE_THEME,RESPONSABLE_CATEGORIE
    }

    public enum Sexe {
        masculin,feminin
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Transient
    @OneToMany(mappedBy = "utilisateur",fetch=FetchType.LAZY)
    private List <EnvoiMail> envoiMails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "forfait")
    private Forfait forfait;

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Evaluation> evaluations = new ArrayList<>();

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<ApprenantCategorie> apprenantCategories = new ArrayList<>();

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "respCat",fetch = FetchType.LAZY)
    private List<Categorie> cat = new ArrayList<>();


    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String email, Date dateNaissance, String login, String password, String description, Role role, Sexe sexe, Forfait forfait) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.login = login;
        this.password = password;
        this.description = description;
        this.role = role;
        this.sexe = sexe;
        this.forfait = forfait;
    }
    public Utilisateur(String nom, String prenom, String email, Date dateNaissance, String login, String password,Sexe sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.login = login;
        this.password = password;
        this.sexe = sexe;
    }

    public List < ApprenantCategorie > getApprenantCategories() {
        return apprenantCategories;
    }

    public void setApprenantCategories(List < ApprenantCategorie > apprenantCategories) {
        this.apprenantCategories = apprenantCategories;
    }

    public List<Categorie> getCat() {
        return cat;
    }

    public void setCat(List<Categorie> cat) {
        this.cat=cat;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public List<EnvoiMail> getEnvoiMails() {
        return envoiMails;
    }

    public void setEnvoiMails(List<EnvoiMail> envoiMails) {
        this.envoiMails = envoiMails;
    }

    public Forfait getForfait() {
        return forfait;
    }

    public void setForfait(Forfait forfait) {
        this.forfait = forfait;
    }
}

