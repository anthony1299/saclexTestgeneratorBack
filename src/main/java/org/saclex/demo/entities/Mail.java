package org.saclex.demo.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@Table(name = "mail")
public class Mail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMail")
    private Long idMail;

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "destinataire")
    private String destinataire;

    @Column(name = "emetteur")
    private String emetteur;

    @Column(name = "objet")
    private String objet;

    @OneToOne(mappedBy = "mail")
    private EnvoiMail envoiMail;

    public Mail() {
    }

    public Mail(String contenu, String destinataire, String emetteur, String objet) {
        this.contenu = contenu;
        this.destinataire = destinataire;
        this.emetteur = emetteur;
        this.objet = objet;
    }

    public Long getIdMail() {
        return idMail;
    }

    public void setIdMail(Long idMail) {
        this.idMail = idMail;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(String emetteur) {
        this.emetteur = emetteur;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public EnvoiMail getEnvoiMail() {
        return envoiMail;
    }

    public void setEnvoiMail(EnvoiMail envoiMail) {
        this.envoiMail = envoiMail;
    }
}
