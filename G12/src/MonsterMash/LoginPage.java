/* 
 * $HeadURL: https://github.com/DownGoat/MonsterMash/blob/development/G12/src/MonsterMash/LoginPage.java
 * 
 * Copyright (c) 2013 Aberystwyth University
 * All rights reserved. 
 * 
 */

import data.Player;
import database.PersistenceManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.reference.DefaultEncoder;

/**
 * Servlet used for the login page. 
 * 
 * @author $Author: sjk4$
 * @version $Id$
 */
public class LoginPage extends HttpServlet {

    /**
     * Encode password using MD5.
     * @param md5 password
     * @return encoded password
     */
    public String MD5(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
         }
         return null;
     }
    
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if(session != null && session.getAttribute("user") != null){
            // If user logged, redirect to main page
            response.sendRedirect("main");
        }else{
            // If not load login page
            request.getRequestDispatcher("/WEB-INF/login_page.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Encoder encoder = new DefaultEncoder();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(email.length() < 1 || password.length() < 1){
            request.setAttribute("errorMessage", "Please enter both your email and password.");
            request.getRequestDispatcher("/WEB-INF/login_page.jsp").forward(request, response);
        }else{
            PersistenceManager pm = new PersistenceManager();
            password = this.MD5(encoder.encodeForSQL(new OracleCodec(), password));
            Player selected = pm.doLogin(encoder.encodeForSQL(new OracleCodec(), email), password);
            if(selected != null){
                // If player exists save object to the session called "user"
                HttpSession session = request.getSession(true);
                session.setAttribute("user", selected);
                response.sendRedirect("main");
            }else{
                // If null, there's no player with this email and password
                request.setAttribute("errorMessage", "Password or email address is incorrect.");
                request.getRequestDispatcher("/WEB-INF/login_page.jsp").forward(request, response);
            }
        }
    }
}
