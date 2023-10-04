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

public class Logout extends HttpServlet {
    private static final long serialVersionUID= 3051159175703178152L;
    private transient Logger logger = Logger.getLogger(Logout.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       try {
           Service service = new ServiceImpl();
           service.closeHibernate();
           HttpSession session = request.getSession(false);
           logger.info("logout successfully");
//           when this servlet is call by the url this is invalid the session .
           session.invalidate();
           request.getRequestDispatcher("index.jsp").forward(request, response);

       }catch (Exception e){
           logger.error("error happened in Logout Servlet "+e);
       e.printStackTrace();
       }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    }
