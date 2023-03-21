package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverid;

    public Driver() {
    }

    public Driver(int driverid, String mobile, String password, Cab cab, List<TripBooking> tripBookingList) {
        this.driverid = driverid;
        this.mobile = mobile;
        this.password = password;
        this.cab = cab;
        this.tripBookingList = tripBookingList;
    }

    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }

    private String mobile;
    private String password;


    @OneToOne(mappedBy = "driver" , cascade = CascadeType.ALL)
    Cab cab;

    @OneToMany(mappedBy = "driver" , cascade = CascadeType.ALL)
    List<TripBooking> tripBookingList = new ArrayList<>();


}