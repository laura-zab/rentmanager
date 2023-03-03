package com.epf.rentmanager.modele;

public class Vehicle {

    private long id;
    private String constructeur;
    private int nb_places;

    public Vehicle(long id, String constructeur, int nb_places) {
        this.id = id;
        this.constructeur = constructeur;
        this.nb_places = nb_places;
    }

    public Vehicle(Vehicle vehicle) {
        setId(vehicle.getId());
        setConstructeur(vehicle.getConstructeur());
        setNb_places(vehicle.getNb_places());
    }

    public Vehicle() {

    }

    @Override
    public String toString() {
        return "ID : " + id +
                " | Constructeur : " + constructeur +
                " | Nombre de places : " + nb_places + "\n";
    }

    public long getId() {
        return id;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }
}
