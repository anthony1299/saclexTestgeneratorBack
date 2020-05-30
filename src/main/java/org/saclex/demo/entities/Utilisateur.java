package org.saclex.demo.entities;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

    private String telephone;

    @Column(name = "date_de_naissance",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDeNaissance;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @Column(nullable = false)
    private boolean isActive;

    public enum Role {
        administrateur,apprenant,responsable_theme
    }

    public enum Sexe {
        masculin,feminin
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    @OneToMany(mappedBy = "utilisateur",fetch=FetchType.LAZY)
    private List <EnvoiMail> envoiMails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "forfait")
    private Forfait forfait;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String email, String telephone, Date dateDeNaissance, String adresse, String login, String password, boolean isActive, Role role, Sexe sexe, Forfait forfait) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.login = login;
        this.password = password;
        this.isActive = false;
        this.role = role;
        this.sexe = sexe;
        this.forfait = forfait;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

