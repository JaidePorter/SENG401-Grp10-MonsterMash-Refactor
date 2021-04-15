/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.Monster;
import data.NameGenerator;
import data.Notification;
import data.Player;
import database.OtherPersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class BuyServlet extends HttpServlet {
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
        String monsterID = encoder.encodeForSQL(new OracleCodec(), request.getParameter("monsterID"));
        
        if(monsterID != null) {
            System.out.println("Buy request for monster"+monsterID);
            OtherPersistenceManager pm = OtherPersistenceManager.getInstance();
            Monster monster = pm.getMonster(monsterID);
            
            if(monster != null ){
                Player player = pm.getPlayer(monster.getUserID());
                
                if(player != null) {
                    player.setMoney(player.getMoney()+monster.getSaleOffer());
                    player.addNotification(new Notification(
                            "<b>"+monster.getName()+"</b> has been sold.",
                            "Your monster "+monster.getName()+" has been sold for "+monster.getSaleOffer()+"$.",
                            player
                            ));
                    
                    pm.storeNotifications(player);
                    pm.updateMoney(player);
                    pm.removeMonster(monsterID);
                    
                    if(player.getMonsters().size() == 1) {
                       ArrayList<Monster> monsters = new ArrayList<Monster>();
                       
                       monsters.add(new Monster(NameGenerator.getName(), player.getUserID()));
                       player.setMonsters(monsters);
                       
                       pm.storeMonsters(player);
                   }
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid monster id.");
            }
            
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters for buy request.");
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
