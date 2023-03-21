package com.driver.model;

import javax.persistence.*;

@Entity
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int perKmRate;
    private boolean isAvailable;

    @OneToOne
    @JoinColumn
    Driver driver;


    public Cab() {
    }

    public Cab(int id, int perKmRate, boolean isAvailable) {
        this.id = id;
        this.perKmRate = perKmRate;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(int perKmRate) {
        this.perKmRate = perKmRate;
    }

    public boolean getisAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}