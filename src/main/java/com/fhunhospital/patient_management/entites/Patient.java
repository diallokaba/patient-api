package com.fhunhospital.patient_management.entites;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;


@Document(collection = "patients")
public class Patient {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Sexe sexe;
    private Double taille;
    private Double poids;
    private List<Contact> contacts;

    public Patient() {}

    public Patient(String id, String nom, String prenom, LocalDate dateNaissance, Sexe sexe, double taille, double poids, List<Contact> contacts) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.taille = taille;
        this.poids = poids;
        this.contacts = contacts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public enum Sexe {
        HOMME("Homme"),
        FEMME("Femme");

        public final String value;

        Sexe(String value) {
            this.value = value;
        }

        public String value(){
            return value;
        }

        public static Sexe fromValue(String value){
            for(Sexe s: Sexe.values()){
                if(s.value().equals(value)){
                    return s;
                }
            }
            throw new IllegalArgumentException("Valeur invalide pour Sexe " + value);
        }
    }
}
