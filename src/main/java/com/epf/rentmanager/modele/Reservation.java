package com.epf.rentmanager.modele;

import java.time.LocalDate;

public class Reservation {

    private long id;
    private int clientId;
    private int vehicleId;
    private LocalDate debut;
    private LocalDate fin;

    public Reservation(long id, int clientId, int vehicleId, LocalDate debut, LocalDate fin){
        this.id = id;
        this.clientId = clientId;
        this.vehicleId = vehicleId;
        this.debut = debut;
        this.fin = fin;
    }

    public Reservation(Reservation reservation) {
        setId(reservation.getId());
        setClientId(reservation.getClientId());
        setVehicleId(reservation.getVehicleId());
        setDebut(reservation.getDebut());
        setFin(reservation.getFin());
    }
    public Reservation() {

    }

    @Override
    public String toString() {
        return "ID : " + id +
                " | Client ID : " + clientId +
                " | Vehicle ID : " + vehicleId +
                " | Début : " + debut +
                " | Fin : " + fin + "\n";
    }

    public long getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }
}
