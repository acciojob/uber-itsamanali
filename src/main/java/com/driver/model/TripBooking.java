package com.driver.model;

import javax.persistence.*;

@Entity
public class TripBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripBookingId;
    private String fromLocation;
    private String toLocation;
    private TripStatus tripstatus;
    private int distanceInKilometer;
    private int bill;

    public TripBooking() {
    }

    public TripBooking(int tripBookingId, String fromLocation, String toLocation, TripStatus tripstatus, int distanceInKilometer, int bill, Driver driver, Customer customer) {
        this.tripBookingId = tripBookingId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.tripstatus = tripstatus;
        this.distanceInKilometer = distanceInKilometer;
        this.bill = bill;
        this.driver = driver;
        this.customer = customer;
    }

    public int getTripBookingId() {
        return tripBookingId;
    }

    public void setTripBookingId(int tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public TripStatus getTripstatus() {
        return tripstatus;
    }

    public void setTripstatus(TripStatus tripstatus) {
        this.tripstatus = tripstatus;
    }

    public int getDistanceInKilometer() {
        return distanceInKilometer;
    }

    public void setDistanceInKilometer(int distanceInKilometer) {
        this.distanceInKilometer = distanceInKilometer;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn
    Driver driver;

    @ManyToOne
    @JoinColumn
    Customer customer;
}