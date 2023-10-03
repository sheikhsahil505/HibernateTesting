package com.model;

import javax.persistence.*;
@Entity
@Table(name = "user_addresses")
public class Address {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int address_id;
    private String street;
    private String  apartment;
    private String city;
    private String pincode;
    private String state;
    private String country;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int i) {
        this.address_id = address_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
