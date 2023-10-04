package com.controller;

import com.model.Address;
import com.model.User;
import com.dao.Service;
import com.dao.ServiceImpl;
import org.apache.log4j.Logger;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@MultipartConfig(maxFileSize = 16177215)
public class UpdateProfile extends HttpServlet {
    private static final long serialVersionUID= 3051159175703178152L;
    private transient Logger logger = Logger.getLogger(UpdateProfile.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
// logout
            HttpSession session = request.getSession(false);
            if (session.getAttribute("username")!=null){

// get old data of the user and show to the next page.
                String email = request.getParameter("email");
                Service service = new ServiceImpl();
//                service.connectDB();
                List<User> userByEmail = service.getUserByEmail(email);
                for (User user : userByEmail) {
                    int userId = user.getUser_id();
                    List<Address> allAddressesById = service.findAllAddressesById(userId);
                    request.setAttribute("addresses", allAddressesById);
                    request.setAttribute("profiles", userByEmail);

                    request.getRequestDispatcher("/jsp/UpdateProfile.jsp").forward(request, response);

                } }else{
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }}catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
//           logout
            HttpSession session = request.getSession(false);
            if (session.getAttribute("username")!=null){
                Service service = new ServiceImpl();
//           get updated data from the jsp page
                String email = request.getParameter("email");
                String idStr = request.getParameter("user_id");
                int id = Integer.parseInt(idStr);
                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                String contactNumber = request.getParameter("contact_number");
                String role = request.getParameter("role");
                String dob = request.getParameter("dob");
                Part profilePicturePart = request.getPart("profilePicture");
                String submittedFileName = profilePicturePart.getSubmittedFileName();

                InputStream inputStream = profilePicturePart.getInputStream();
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);

                Blob profilePictureBlob = null;
                String oldEmail;
                if (session.getAttribute("oldEmail") != null) {
                    oldEmail = (String) session.getAttribute("oldEmail");

                } else {
                    oldEmail = email;
                }
                List<User> userByEmail1 = service.getUserByEmail(oldEmail);
                for (User user1 : userByEmail1) {
                    Blob profilePicture = user1.getProfilePicture();

//                    this is when image is not selected
                    if (Objects.equals(submittedFileName, "")) {
                        profilePictureBlob = profilePicture;
                    } else {
                        profilePictureBlob = new SerialBlob(bytes);
                    }
                }
                User user = new User();
                user.setUser_id(id);
                user.setFirst_name(firstName);
                user.setLast_name(lastName);
                user.setEmail(email);
                user.setRole(role);
                user.setDob(dob);
                user.setContact_number(contactNumber);
                user.setProfilePicture(profilePictureBlob);

                    String[] addressIds = request.getParameterValues("address_id1");
                    int[] addressId1 = Arrays.stream(addressIds).mapToInt(Integer::parseInt).toArray();

                    String[] streets1 = request.getParameterValues("street1");
                    String[] apartment1 = request.getParameterValues("apartment1");
                    String[] city1 = request.getParameterValues("city1");
                    String[] pincode1 = request.getParameterValues("pincode1");
                    String[] state1 = request.getParameterValues("state1");
                    String[] country1 = request.getParameterValues("country1");

                    String[] removedAddressIdsString = request.getParameter("removedAddressIds").split(",");


//                this loop for get ids from String array
                    for (String Deleteids : removedAddressIdsString) {
//                        this conditions for not delete any address
                        if (!Objects.equals(Deleteids, "")) {
                            int[] deletedAddressIds = new int[removedAddressIdsString.length];
                            for (int i = 0; i < removedAddressIdsString.length; i++) {
                                deletedAddressIds[i] = Integer.parseInt(removedAddressIdsString[i]);
                            }
//                    this loop delete address
                            for (int i = 0; i < deletedAddressIds.length; i++) {
                                service.deleteAddressById(deletedAddressIds[i]);
                            }
                        }
                    }
                    boolean status = service.checkEmailExists(email);
                    List<Address> allAddressesById1 = service.findAllAddressesById(id);

                        if (Objects.equals(oldEmail, email)) {

                            service.updateUserByAdmin( id, firstName, lastName,  contactNumber,  role,  dob,  profilePictureBlob,  email);


                                for (int i = 0; i < streets1.length; i++) {
                                    service.updateAddressByAddressId( addressId1[i],   streets1[i],   apartment1[i],   city1[i],   pincode1[i],   state1[i],   country1[i]);

                                }

                        } else if (status == true) {
                            List<User> userByEmail = service.getUserByEmail(oldEmail);
                            request.setAttribute("addresses", allAddressesById1);
                            request.setAttribute("profiles", userByEmail);
                            request.setAttribute("error", "Email is already taken");
                            request.getRequestDispatcher("/jsp/UpdateProfile.jsp").forward(request, response);
                        } else {

                            service.updateUserByAdmin( id, firstName, lastName,  contactNumber,  role,  dob,  profilePictureBlob,  email);

                            for (int i = 0; i < streets1.length; i++) {
                                service.updateAddressByAddressId(  addressId1[i],   streets1[i],   apartment1[i],   city1[i],   pincode1[i],   state1[i],   country1[i]);
                            }
                        }
                        logger.info("Update profile with username "+oldEmail);

                          // this is for new address
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
                    }
                    List<User> allRegistrations = service.getAllRegistrations();
//            after update all the details this is show update details of the user in next page
                    List<User> userByEmail = service.getUserByEmail(email);
                    List<Address> allAddressesById = service.findAllAddressesById(id);
                    request.setAttribute("addresses", allAddressesById);
                    request.setAttribute("profile", userByEmail);
                    request.setAttribute("registrations", allRegistrations);
                    request.getRequestDispatcher("/jsp/View.jsp").forward(request, response);
                    
            }else{
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }catch (Exception e){
logger.error("Error happened in UpdateProfile Servlet");
           e.printStackTrace();
        }
    }
}