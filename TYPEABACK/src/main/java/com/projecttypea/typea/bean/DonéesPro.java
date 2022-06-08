package com.projecttypea.typea.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class DonéesPro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String niveau;

    private String grade;

    private String ced;

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @OneToOne
    private Etablissement etablissement;

    private String entiteRecherche;

    private String respoEntite;

    @OneToOne
    private Manifestation manifestation;

    @OneToOne
    private MissionStage missionStage;

    @JsonBackReference(value = "donne")
    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }



    public String getEntiteRecherche() {
        return entiteRecherche;
    }

    public void setEntiteRecherche(String entiteRecherche) {
        this.entiteRecherche = entiteRecherche;
    }

    public String getRespoEntite() {
        return respoEntite;
    }

    public void setRespoEntite(String respoEntite) {
        this.respoEntite = respoEntite;
    }

    public DonéesPro() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}