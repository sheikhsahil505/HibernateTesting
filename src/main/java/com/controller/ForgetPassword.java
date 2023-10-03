package com.controller;

import com.dao.Service;
import com.dao.ServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ForgetPassword extends HttpServlet {
    private static final long serialVersionUID= 3051159175703178152L;
    private transient Logger logger = Logger.getLogger(ForgetPassword.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
//     get email and dob form the forget password jsp .
    String email = request.getParameter("email");
    String dob = request.getParameter("dob");

    Service service = new ServiceImpl();
//    service.connectDB();
//    this method check user available or not with this email and dob .
    boolean status = service.verifyEmailandDob(email, dob);
    if (status == true) {
//        if user is available it stored email and dob in session and send request to the next page
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("dob", dob);
        session.setMaxInactiveInterval(60);

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/GenerateNewPassword.jsp");
        rd.forward(request, response);
    } else {
//        if user is not exist it shows message on same jsp page
        request.setAttribute("error", "Invalid email or Date of Birth");
        request.getRequestDispatcher("/jsp/ForgetPassword.jsp").forward(request, response);


    }
}catch (Exception e){
        logger.error("error happened in ForgetPassword Servlet "+e);
e.printStackTrace();
}
    }
}
