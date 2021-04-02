/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.CONFIG;
import data.Friend;
import data.Player;
import database.OtherPersistenceManager;
import database.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.reference.DefaultEncoder;

/**
 *
 * @author sis13
 */
public class FriendRequest extends HttpServlet {
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
        Friend friend = null;
        String friendID = encoder.encodeForSQL(new OracleCodec(), request.getParameter("friendID"));
        String localUserID = encoder.encodeForSQL(new OracleCodec(), request.getParameter("localUserID"));
        String remoteUserID = encoder.encodeForSQL(new OracleCodec(), request.getParameter("remoteUserID"));
        String remoteServerNumber = encoder.encodeForSQL(new OracleCodec(), request.getParameter("remoteServerNumber"));
        
        if (friendID == null || localUserID == null || remoteServerNumber == null || remoteUserID == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request, invalid parameters for friend request.");
        } else {
            OtherPersistenceManager pm = new OtherPersistenceManager();
            Player player = pm.getPlayer(localUserID);

            friend = new Friend(friendID, localUserID, remoteUserID, Integer.parseInt(remoteServerNumber), CONFIG.OUR_SERVER, "N");
            
            if(player != null) {
                System.out.println("Player exists on my server.");
                pm.addFriend(friend);
                pm.storeNotifications(player);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found.");
            }
            
            //response.sendRedirect("/MonsterMash/main");
        }
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
        System.out.println("asd");
        processRequest(request, response);
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
