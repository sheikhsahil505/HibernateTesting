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

public class ViewUserViaAdmin extends HttpServlet {
    private static final long serialVersionUID= 3051159175703178152L;
    private transient Logger logger = Logger.getLogger(ViewUserViaAdmin.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
try {
//    logout
    HttpSession session = request.getSession(false);
    if (session.getAttribute("username") != null) {

// get email and user_id which is viewed by admin
        String idStr = request.getParameter("user_id");
        int id = Integer.parseInt(idStr);
        String email = request.getParameter("email");
        HttpSession session1= request.getSession(true);
        session1.setAttribute("oldEmail",email);
        Service service = new ServiceImpl();

         logger.info("profile viewed by admin of username "+email);
             List<User> allRegistrations = service.getAllRegistrations();
            List<User> userByEmail = service.getUserByEmail(email);
            List<Address> allAddressesById = service.findAllAddressesById(id);
            request.setAttribute("addresses", allAddressesById);
            request.setAttribute("profile", userByEmail);
            request.setAttribute("registrations", allRegistrations);
            request.getRequestDispatcher("/jsp/View.jsp").forward(request, response);


    } else {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}catch (Exception e){
    logger.error("error happened in VewUserViaAdmin Servlet "+e);
e.printStackTrace();
}
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
