package com.controller;

import javax.servlet.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 try{
        // Retrieve form data here
        String firstName = request.getParameter("Fname");
        String lastName = request.getParameter("Lname");
        String email = request.getParameter("email");
        String contactNumber = request.getParameter("contactNumber");
        String password = request.getParameter("pass");


        // Regular expressions for validation
        String nameRegex = "^[A-Za-z]+$";
        String mobileRegex = "^[0-9]{10}$";
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
        if (!Pattern.matches(nameRegex, firstName)
                || !Pattern.matches(nameRegex, lastName)
                || !Pattern.matches(mobileRegex, contactNumber)
                || !Pattern.matches(emailRegex,email)
                || !Pattern.matches(passwordRegex,password)) {

            request.setAttribute("error", "Invalid Your inputs ");
            request.getRequestDispatcher("/WEB-INF/jsps/NewRegister.jsp").forward(request, response);
        } else {
            // Validation passed; continue processing the request
            chain.doFilter(request, response);
        }
}catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}