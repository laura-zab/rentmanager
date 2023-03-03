package com.epf.rentmanager.modele;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Client {

    private long id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate naissance;


    public Client(long id, String nom, String prenom, String email, LocalDate naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.naissance = naissance;
    }

    public Client(Client client) {
        setId(client.getId());
        setNom(client.getNom());
        setPrenom(client.getPrenom());
        setEmail(client.getEmail());
        setNaissance(client.getNaissance());
    }

    public Client() {

    }

    @Override
    public String toString() {
        return "ID : " + id +
                " | Nom : " + nom +
                " | Prénom : " + prenom +
                " | Email : " + email +
                " | Naissance : " + naissance + "\n";
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }
}
