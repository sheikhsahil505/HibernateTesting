package com.controller;

import com.dao.Service;
import com.dao.ServiceImpl;
import com.model.Address;
import com.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.util.Base64;

@MultipartConfig
public class SaveUser extends HttpServlet {
    private static final long serialVersionUID= 3051159175703178152L;
    private transient Logger logger = Logger.getLogger(SaveUser.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
try {
    // Retrieve form data here
    String firstName = request.getParameter("Fname");
    String lastName = request.getParameter("Lname");
    String email = request.getParameter("email");
    String contactNumber = request.getParameter("contactNumber");
    String password = request.getParameter("pass");
    String role = request.getParameter("role");
    String dob = request.getParameter("dob");
    String PasswordEncoder = Base64.getEncoder().encodeToString(password.getBytes(Charset.forName("UTF-8")));
    Part profilePicturePart = request.getPart("profilePicture");
    InputStream inputStream = profilePicturePart.getInputStream();
    byte[] bytes = new byte[inputStream.available()];
    inputStream.read(bytes);

    // Create a Blob from the byte array
    Blob profilePictureBlob = new SerialBlob(bytes);



    User user = new User();
    user.setFirst_name(firstName);
    user.setLast_name(lastName);
    user.setEmail(email);
    user.setPassword(PasswordEncoder);
    user.setRole(role);
    user.setDob(dob);
    user.setContact_number(contactNumber);
    user.setProfilePicture(profilePictureBlob);


    Service service = new ServiceImpl();
    service.saveUser(user);

    String[] streets = request.getParameterValues("streets");
    if (streets != null && streets.length > 0) {
        String[] apartments = request.getParameterValues("apartments");
        String[] cities = request.getParameterValues("cities");
        String[] pincodes = request.getParameterValues("pincodes");
        String[] states = request.getParameterValues("states");
        String[] countries = request.getParameterValues("countries");

        // Loop through the data for each address and save them individually
        for (int i = 0; i < streets.length; i++) {
            Address address = new Address();
            address.setStreet(streets[i]);
            address.setApartment(apartments[i]);
            address.setPincode(pincodes[i]);
            address.setState(states[i]);
            address.setCity(cities[i]);
            address.setCountry(countries[i]);
            address.setUser(user);

            service.saveAddress(address);
        }
        logger.info("saved user with name " + email);
    }

        request.setAttribute("Done", "You Are Register Successfully.");
        request.getRequestDispatcher("index.jsp").forward(request, response);


}catch (Exception e){
    logger.error("error happened in SaveUser Servlet  "+e);
e.printStackTrace();
}
    }

}
