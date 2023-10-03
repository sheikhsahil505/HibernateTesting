package com.model;


import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "user_information")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String first_name;
    private String last_name;
    @Column(unique = true)
    private String email;
    private String contact_number;
    private String password;
    private String role;
    private String dob;

    @Lob
    private Blob profilePicture;
    @Transient
    private String base64ProfilePicture;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addressList;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Blob getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Blob profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBase64ProfilePicture() {
        return base64ProfilePicture;
    }

    public void setBase64ProfilePicture(String base64ProfilePicture) {
        this.base64ProfilePicture = base64ProfilePicture;
    }

    public List<Address> getAddressDaoList() {
        return addressList;
    }

    public void setAddressDaoList(List<Address> addressList) {
        this.addressList = addressList;
    }
}