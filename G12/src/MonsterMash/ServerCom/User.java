/**
 * @HeadUrl https://github.com/DownGoat/MonsterMash/blob/development/G12/src/MonsterMash/ServerCom/User.java
 * 
 * Copyright (c) 2013 Aberystwyth University
 * All rights reserved.
 */
package ServerCom;

import data.Player;
import database.OtherPersistenceManager;
import database.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.reference.DefaultEncoder;

/**
 * Servlet implementing the API used to get users from this server.
 * If the url /users is requested from our server server the servlet will return
 * a json array containing all the users registerd on the server. If the client
 * wants a specific user only it can be requested by sending a request to 
 * /users?userID=xxx where xxx is the ID of the user.
 * 
 * @author $Author sis13 $
 * 
 * @see data.Player
 */
@WebServlet(name = "users", urlPatterns = {"/users"})
public class User extends HttpServlet {
    Encoder encoder = new DefaultEncoder();
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        System.out.println("IP: " + request.getRemoteAddr() +
                ", Port: " + request.getRemotePort() +
                ", Host: " + request.getRemoteHost());
        OtherPersistenceManager pm = OtherPersistenceManager.getInstance();
        String userIDString = request.getParameter("userID");
        System.out.println(userIDString);

        if (userIDString != null) {
            userIDString = encoder.encodeForSQL(new OracleCodec(), userIDString);
            Player player = null;
            player = pm.getPlayerSafe(userIDString);

            if (player != null) {
                PrintWriter out = response.getWriter();
                out.write(JSONManager.jsonPlayer(player).toString());
                System.out.println("doGet"+JSONManager.jsonPlayer(player).toString());
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found");
            }
        } else {
            PrintWriter out = response.getWriter();
            out.write(JSONManager.jsonUsers(pm.getPlayers()));
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
