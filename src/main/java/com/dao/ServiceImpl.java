package com.dao;

import com.model.Address;
import com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class ServiceImpl implements Service {
    private static SessionFactory factory=  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private static   Session session = factory.openSession();
    private static  Transaction tx = session.beginTransaction();

    public void saveUser(User user) {
       session.save(user);
    }
    public void saveAddress(Address address) {
        session.save(address);
    }

    @Override

        public String getRoles(String email, String password) {
            String role = null;
            try {
                // Use HQL (Hibernate Query Language) instead of SQL
                String hql = "SELECT u.role FROM User u WHERE u.email = :email AND u.password = :password";
                role = (String) session.createQuery(hql)
                        .setParameter("email", email)
                        .setParameter("password", password)
                        .uniqueResult();
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return role;
        }

    @Override
    public boolean verifyEmailandDob(String email, String dob) {
        try {
            String hql = "FROM User WHERE email = :email AND dob = :dob";
            List<User> users = session.createQuery(hql, User.class)
                    .setParameter("email", email)
                    .setParameter("dob", dob)
                    .list();
            tx.commit();
            return !users.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Handle the exception appropriately in your application
        }
    }

     @Override
    public void updatePassword(Object email, Object dob, String newPassword) {
        try  {
            String hql = "UPDATE User SET password = :newPassword WHERE email = :email AND dob = :dob";
            session.createQuery(hql)
                    .setParameter("newPassword", newPassword)
                    .setParameter("email", email)
                    .setParameter("dob", dob)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


@Override
    public List<User> getAllRegistrations() {
        try {
            String hql = "FROM User WHERE role = :role";
            List<User> users = session.createQuery(hql, User.class)
                    .setParameter("role", "user")
                    .list();

            for (User user : users) {
                Blob profilePictureBlob = user.getProfilePicture();
                if (profilePictureBlob != null) {
                    // Convert Blob to byte array
                    byte[] profilePictureBytes = profilePictureBlob.getBytes(1, (int) profilePictureBlob.length());
                    // Encode byte array to Base64
                    String base64ProfilePicture = Base64.getEncoder().encodeToString(profilePictureBytes);
                    // Set the Base64-encoded image data in the user object
                    user.setBase64ProfilePicture(base64ProfilePicture);
                }
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  @Override
    public List<User> getUserByEmail(String email) {
        try {
            String hql = "FROM User WHERE email = :email";

            List<User> user=  session.createQuery(hql, User.class)
                    .setParameter("email", email)
                    .list();

            for (User users : user) {
                Blob profilePictureBlob = users.getProfilePicture();
                if (profilePictureBlob != null) {
                    // Convert Blob to byte array
                    byte[] profilePictureBytes = profilePictureBlob.getBytes(1, (int) profilePictureBlob.length());
                    // Encode byte array to Base64
                    String base64ProfilePicture = Base64.getEncoder().encodeToString(profilePictureBytes);
                    // Set the Base64-encoded image data in the user object
                    users.setBase64ProfilePicture(base64ProfilePicture);
                }
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
   @Override
    public List<Address> findAllAddressesById(int userId) {
        try {
            String hql = "FROM Address WHERE user_id = :userId";
            return session.createQuery(hql, Address.class)
                    .setParameter("userId", userId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
            return Collections.emptyList();
        }
    }

    @Override
    public void updateUserByAdmin(int userId, String firstName, String lastName, String contactNumber, String role, String dob, Blob profilePicture, String email) {
        try {
            // Load the User entity by its ID
            User userToUpdate = session.get(User.class, userId);

            if (userToUpdate != null) {
                // Update the user attributes
                userToUpdate.setFirst_name(firstName);
                userToUpdate.setLast_name(lastName);
                userToUpdate.setContact_number(contactNumber);
                userToUpdate.setRole(role);
                userToUpdate.setDob(dob);
                userToUpdate.setEmail(email);
                userToUpdate.setProfilePicture(profilePicture);

                session.update(userToUpdate);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
        }
    }
@Override
public void updateAddressByAddressId(int id, String street, String apartment, String city, String pincode, String state, String country) {
    try  {
        // Load the Address entity by its ID
        Address addressToUpdate = session.get(Address.class, id);

        if (addressToUpdate != null) {
            // Update the address attributes
            addressToUpdate.setStreet(street);
            addressToUpdate.setApartment(apartment);
            addressToUpdate.setCity(city);
            addressToUpdate.setPincode(pincode);
            addressToUpdate.setState(state);
            addressToUpdate.setCountry(country);

            session.update(addressToUpdate);
        }

    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception appropriately in your application
    }
}

    @Override
    public boolean checkEmailExists(String email) {
        try {
            String hql = "SELECT 1 FROM User WHERE email = :email";
            Query<?> query = session.createQuery(hql);
            query.setParameter("email", email);

            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
            return false;
        }
    }


    public void deleteAddressById(int addressId) {
        try  {
            // Load the Address entity by its ID
            Address addressToDelete = session.load(Address.class, addressId);

            // Check if the entity exists before deleting it
            if (addressToDelete != null) {
                session.delete(addressToDelete);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
        }
    }

    public void deleteUser(int userId) {
        try {

            // Load the User entity by its ID
            User userToDelete = session.load(User.class, userId);
            // Check if the entity exists before deleting it
            if (userToDelete != null) {
                session.delete(userToDelete);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
        }
    }
    public void closeHibernate(){
        session.close();
        factory.close();
    }

}
