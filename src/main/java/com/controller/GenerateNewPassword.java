package com.controller;

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
import java.util.Base64;

public class GenerateNewPassword extends HttpServlet {
    private static final long serialVersionUID= 3051159175703178152L;
    private transient Logger logger = Logger.getLogger(GenerateNewPassword.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     try {

           HttpSession session = request.getSession(false);
//           get email and dob from the session .
           Object email = (String) session.getAttribute("email");
           Object dob = (String) session.getAttribute("dob");
           String Password = request.getParameter("password");

         String encoderPassword = Base64.getEncoder().encodeToString(Password.getBytes(Charset.forName("UTF-8")));
           Service service = new ServiceImpl();
             //connect db and update new password
//           service.connectDB();
           service.updatePassword(email, dob, encoderPassword);
           logger.info("new password Generated with username "+email);
           // redirect to the next page
           request.setAttribute("Done","Your Password Successfully Changed.");
           request.getRequestDispatcher("/jsp/GenerateNewPassword.jsp").forward(request, response);


       }catch (Exception e){
         logger.error("error happened in GeneratedNewPassword Servlet "+e);
       e.printStackTrace();
       }
    }
}
