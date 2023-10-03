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
import java.util.List;

public class Home extends HttpServlet {
    private static final long serialVersionUID= 3051159175703178152L;
    private transient Logger logger = Logger.getLogger(Home.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {

// this is for logout
           HttpSession session1 = request.getSession(false);
           if (session1.getAttribute("username") != null) {

               Service service = new ServiceImpl();
//               service.connectDB();
// get email from the jsp page
               HttpSession session = request.getSession(false);
               Object username = (String) session.getAttribute("username");
//              get all user list from the database
               logger.info("Home button hit with username "+username);
               List<User> allRegistrations = service.getAllRegistrations();
//               get all information of login user and show to the home page
               List<User> userByEmail = service.getUserByEmail((String) username);
//                 find id from the login user list
               for (User user : userByEmail) {
                   int userId = user.getUser_id();
//                   this session id set for user can not  delete himself
                   session.setAttribute("userId", userId);
//                 this is show all the information of admin
                   List<Address> allAddressesById = service.findAllAddressesById(userId);
                   request.setAttribute("addresses", allAddressesById);
                   request.setAttribute("profile", userByEmail);
                   request.setAttribute("registrations", allRegistrations);
                   request.getRequestDispatcher("/jsp/View.jsp").forward(request, response);
                   session.removeAttribute("oldEmail");

               }
           } else {
               request.getRequestDispatcher("index.jsp").forward(request, response);
           }

       }catch (Exception e){
           logger.error("Error happened in Home Servlet "+e);
       e.printStackTrace();
       }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
