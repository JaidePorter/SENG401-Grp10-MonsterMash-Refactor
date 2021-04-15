/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.FightRequest;
import data.Monster;
import data.Notification;
import data.Player;
import database.OtherPersistenceManager;
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
public class FightWon extends HttpServlet {
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
        
        Double strength = null;
        Double defence = null;
        Double health = null;
        
        try {
            strength = Double.parseDouble(request.getParameter("strength"));
            defence = Double.parseDouble(request.getParameter("defence"));
            health = Double.parseDouble(request.getParameter("health"));
        } catch(Exception err) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request, invalid parameters for fight won.");
        }
        
        if (fightID != null) {
            OtherPersistenceManager pm = OtherPersistenceManager.getInstance();
            FightRequest fr = pm.getFightRequest(fightID);
            
            if(fr != null) {
                Monster monster = pm.getMonster(fr.getSenderMonsterID());
                Player player = pm.getPlayer(fr.getSenderID());
                
                if(monster != null && player != null) {
                    monster.updateStats(strength, defence, health);
                    pm.updateMonster(monster);
                    player.addNotification(new Notification(
                            "Fight won!",
                            "Congratulations! You are the winner of the epic battle between you and "+
                                fr.getRecieverID()+" and your mongrel pets "+monster.getName()+" and "+
                                fr.getReceiverMonsterID()+". The bards will songs about this heroic battle for thousands of years to come.",
                            player
                            ));
                    
                    pm.storeNotifications(player);
                    pm.removeFightRequest(fr);
                    player.setMoney(fr.getMONEY()+player.getMoney());
                    pm.updateMoney(player);
                    
                    response.sendRedirect("/MonsterMash/main");
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad fight request data."); 
                }     
            } else {
               response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad fight id."); 
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request, invalid parameters for fight won.");
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
