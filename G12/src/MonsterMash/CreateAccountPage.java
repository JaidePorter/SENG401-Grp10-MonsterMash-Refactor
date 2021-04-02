/* 
 * $HeadURL: https://github.com/DownGoat/MonsterMash/blob/development/G12/src/MonsterMash/CreateAccountPage.java
 * 
 * Copyright (c) 2013 Aberystwyth University
 * All rights reserved. 
 * 
 */

import data.*;
import database.PersistenceManager;
import java.io.IOException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.reference.DefaultEncoder;

/**
 * Servlet for creating a new account. 
 * 
 * @author $Author: sjk4$
 * @version $Id$
 */
public class CreateAccountPage extends HttpServlet {
    /** SET INITIAL MONEY AMOUNT **/
    private final int MONEY_AMOUNT = 10;
    
    /**
     * Check if email address is correct.
     * @param email user's email address
     * @return true when email address is correct
     */
    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException e) {
            result = false;
        }
        return result;
    }
    
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
        request.getRequestDispatcher("/WEB-INF/create_account_page.jsp").forward(request, response);
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
        String email = encoder.encodeForSQL(new OracleCodec(), request.getParameter("email"));
        String username = encoder.encodeForSQL(new OracleCodec(), request.getParameter("username"));
        String monsterName = encoder.encodeForSQL(new OracleCodec(), request.getParameter("monster"));
        String password = encoder.encodeForSQL(new OracleCodec(), request.getParameter("password"));
        String cpassword = encoder.encodeForSQL(new OracleCodec(), request.getParameter("cpassword"));
        String errorMessage = null;
        PersistenceManager pm = new PersistenceManager();
        // Simple validation
        if(email.length() < 1 || !isValidEmailAddress(email)){
            errorMessage = "Please enter correct email address."; 
        }else if(monsterName.length() < 5 || monsterName.length() > 32){
            errorMessage = "Please enter correct monster name.";
        }else if(password.length() < 5 || password.length() > 255){
            errorMessage = "Please enter correct password.";
        }else if(!password.equals(cpassword)){
            errorMessage = "Passwords are not the same.";
        }else if(pm.accountExists(email)){
            errorMessage = "There is already account with this email address.";
        }else{
            password = MD5(password);
            Player tmp = new Player(email, username, password, MONEY_AMOUNT, monsterName);
            // Store player in DB
            pm.storePlayer(tmp);
            // Redirect to login page
            request.setAttribute("message", "Account created successfully. You can sign in now.");
            request.getRequestDispatcher("/WEB-INF/login_page.jsp").forward(request, response);
            return;
        }
        // Display error message
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/WEB-INF/create_account_page.jsp").forward(request, response);
    }
}
