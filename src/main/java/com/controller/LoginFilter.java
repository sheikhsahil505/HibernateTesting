package com.controller;

import javax.servlet.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 try{
        // Retrieve form data here

        String email = request.getParameter("username");

        // Regular expressions for validation

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

        if (!Pattern.matches(emailRegex,email))
               {

            request.setAttribute("error", "Invalid Your email ");
            request.getRequestDispatcher("index.jsp").forward(request, response);
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