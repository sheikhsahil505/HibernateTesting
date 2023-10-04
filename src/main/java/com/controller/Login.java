package com.controller;

import com.model.Address;
import com.model.User;
import com.dao.Service;
import com.dao.ServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;

public class Login extends HttpServlet {
    private static final long serialVersionUID= 3051159175703178152L;
    private transient Logger logger = Logger.getLogger(Login.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service service = new ServiceImpl();

    try {

//    get email and password from the jsp page .
    String username = request.getParameter("username");
    String password = request.getParameter("password");


//    service.connectDB();
//    convert into encode password.
        String encoder = Base64.getEncoder().encodeToString(password.getBytes(Charset.forName("UTF-8")));
//    get role from the database with the help of email and password.
    String roles = service.getRoles(username, encoder);

    if (roles != null) {
        logger.info("login user with username "+username);
//            it stores username and password into session
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("role", roles);
//            this method get all users form the database
            List<User> allRegistrations = service.getAllRegistrations();

//            this method show all the information of login user s
         List<User> userByEmail = service.getUserByEmail(username);

//            this loop find user_id from user information
            for (User user : userByEmail) {
                int userId = user.getUser_id();
                Blob profilePicture = user.getProfilePicture();
                System.out.println(profilePicture);
//                this store user id into session for admin can not delete himself.
                session.setAttribute("userId", userId);
                if("admin".equals(roles)){
                    session.setAttribute("admin_id",userId);

                }
//                this is get all address of admin by user_id and show to admin page .
                List<Address> allAddressesById = service.findAllAddressesById(userId);
                request.setAttribute("addresses", allAddressesById);
                request.setAttribute("profile", userByEmail);
                request.setAttribute("registrations", allRegistrations);
                request.getRequestDispatcher("/jsp/View.jsp").forward(request, response);

          }

    } else{
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}catch (Exception e){
        logger.error("Error in login page");
e.printStackTrace();
}
    }
}
