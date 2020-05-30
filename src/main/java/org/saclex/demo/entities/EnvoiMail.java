package org.saclex.demo.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@XmlRootElement
@Table(name = "envoi_message")
public class EnvoiMail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_envoi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoi;

    public enum Statut{
        SUCCES,ECHEC
    }

    @Column(name="statut")
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @OneToOne
    @JoinColumn(name="mail")
    private Mail mail;

    @ManyToOne
    @JoinColumn(name = "utilisateur")
    private Utilisateur utilisateur;

    public EnvoiMail() {
    }

    public EnvoiMail(Date dateEnvoi, Statut statut, Mail mail, Utilisateur utilisateur) {
        this.dateEnvoi = dateEnvoi;
        this.statut = statut;
        this.mail = mail;
        this.utilisateur = utilisateur;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
