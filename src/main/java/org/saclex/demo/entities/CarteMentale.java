package org.saclex.demo.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@Table(name = "carteMentale")
public class CarteMentale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_carte")
    private Long idCarte;

    @Lob
    @Column(name = "media")
    private byte[] media;

    @OneToOne
    @MapsId
    @JoinColumn(name = "question_id")
    private Question quest;

    public CarteMentale() {
    }

    public CarteMentale(byte[] media, Question quest) {
        this.media = media;
        this.quest = quest;
    }

    public Long getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(Long idCarte) {
        this.idCarte = idCarte;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public Question getQuest() {
        return quest;
    }

    public void setQuest(Question quest) {
        this.quest = quest;
    }
}
