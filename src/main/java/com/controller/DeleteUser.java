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

public class DeleteUser extends HttpServlet {

    private static final long serialVersionUID= 3051159175703178152L;
    private transient Logger logger = Logger.getLogger(DeleteUser.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
try {
//    this code is for logout
    HttpSession session = request.getSession(false);
    if (session.getAttribute("username") != null) {

//  this is get user id and email from the jsp page
        String idStr = request.getParameter("user_id");
        int user_id = Integer.parseInt(idStr);

        String email = request.getParameter("email");

        Service service = new ServiceImpl();
//        service.connectDB();
//        get user id from the session
        Object attribute = session.getAttribute("userId");
        int adminId = ((Integer) attribute).intValue();


// this code for delete particular user.
            service.deleteUser(user_id);
            logger.info("delete user with user id "+user_id);
            List<User> allRegistrations = service.getAllRegistrations();
            List<User> userByEmail = service.getUserByEmail(email);
            List<Address> allAddressesById = service.findAllAddressesById(adminId);
            request.setAttribute("profile", userByEmail);
            request.setAttribute("addresses", allAddressesById);
            request.setAttribute("registrations", allRegistrations);
            request.getRequestDispatcher("/jsp/View.jsp").forward(request, response);

    } else {
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }


}catch (Exception e){
    logger.error("error happened in DeleteUser servlet  ");
e.printStackTrace();
}
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { /* compiled code */


    }
}


