package org.saclex.demo.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@Table(name = "fichier")
public class Fichier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fichier")
    private Long idFichier;

    @Lob
    @Column(name = "media")
    private byte[] media;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Fichier() {
    }

    public Fichier(byte[] media, Question question) {
        this.media = media;
        this.question = question;
    }

    public Long getIdFichier() {
        return idFichier;
    }

    public void setIdFichier(Long idFichier) {
        this.idFichier = idFichier;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
