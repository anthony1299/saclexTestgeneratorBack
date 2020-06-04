package org.saclex.demo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
public class VerificationToken  {
    private static final int Expiration = 60*24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Utilisateur.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private Utilisateur utilisateur;

    private Date dateExpiration;

    private Date calculDateExpiration(int tempsExpirationEnMinutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE,tempsExpirationEnMinutes);
        return new Date(cal.getTime().getTime());
    }

    public VerificationToken() {
    }

    public VerificationToken(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        this.token= UUID.randomUUID().toString();
        this.dateExpiration=new Date();
    }


    public static int getExpiration() {
        return Expiration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
}

