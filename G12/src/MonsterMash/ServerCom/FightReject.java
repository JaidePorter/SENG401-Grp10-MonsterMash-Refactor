/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.FightRequest;
import data.Notification;
import data.Player;
import database.OtherPersistenceManager;
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
 *
 * @author FZajac
 */
@WebServlet(name = "FightReject", urlPatterns = {"/fight/reject"})
public class FightReject extends HttpServlet {
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
        String fightID = encoder.encodeForSQL(new OracleCodec(), request.getParameter("fightID"));
        
        if(fightID != null) {
            OtherPersistenceManager pm = OtherPersistenceManager.getInstance();
            FightRequest fr = pm.getFightRequest(fightID);
            
            if(fr != null) {
                Player sender = pm.getPlayer(fr.getSenderID());
                
                if(sender != null) {
                    sender.addNotification(new Notification(
                            "Battle rejected",
                            "The craven "+fr.getRecieverID()+" has rejected your offer for battle.",
                            sender
                            ));
                    pm.storeNotifications(sender);
                    pm.removeFightRequest(fr);
                    
                    response.sendRedirect("/MonsterMash/main");
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown player in fight request.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown fightID.");
            }
            
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request, invalid parameters for fight reject.");
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
