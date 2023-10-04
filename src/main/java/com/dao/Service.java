package com.dao;

import com.model.Address;
import com.model.User;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

public interface Service {

     void saveUser(User user);
     void saveAddress(Address address);

     String getRoles(String email , String password);

       boolean verifyEmailandDob(String email, String dob);

     void updatePassword(Object email, Object dob, String newPassword);



    List<User> getAllRegistrations();

    List<User>  getUserByEmail(String email);

    List<Address>  findAllAddressesById(int user_id);

    public void updateUserByAdmin(int userId, String firstName, String lastName, String contactNumber, String role, String dob, Blob profilePicture, String email);



    public void updateAddressByAddressId(int id, String street, String apartment, String city, String pincode, String state, String country);
    boolean checkEmailExists(String email);

     void deleteAddressById(int addressId);

     void deleteUser(int userId);

     void closeHibernate();
}
