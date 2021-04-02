/* 
 * $HeadURL: https://github.com/DownGoat/MonsterMash/blob/development/G12/src/MonsterMash/FightAccept.java
 * 
 * Copyright (c) 2013 Aberystwyth University
 * All rights reserved. 
 * 
 */

import ServerCom.RemoteTalker;
import data.*;
import database.OtherPersistenceManager;
import database.PersistenceManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 * This servlet is called when a fight request is accepted, runs fight 
 * algorithm and determinates who won and who lost. 
 * 
 * @author $Author: fiz$
 * @version $Id$
 */
public class FightAccept extends HttpServlet {

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
        String fightID = request.getParameter("fightID");
        if(fightID != null){
            OtherPersistenceManager pm = new OtherPersistenceManager();
            FightRequest fr = pm.getFightRequest(fightID);

            if(fr != null){
                Player receiver = pm.getPlayer(fr.getRecieverID());
                RemoteTalker rt = new RemoteTalker();
                String address = rt.getRemoteAddress(fr.getSenderServerID());
                Player sender = rt.getRemotePlayer(fr.getSenderID(), address);
                if(sender != null){
                    Monster opponent = pm.getMonster(fr.getSenderMonsterID(), fr.getSenderServerID());
                    Monster myMonster = pm.getMonster(fr.getReceiverMonsterID(), CONFIG.OUR_SERVER);
                    double opponentHealth = myMonster.fight(opponent);
                    if(opponentHealth > 0){
                        rt.wonRemoteFight(fr, fr.getSenderServerID(), opponent);
                        
                        receiver.addNotification(new Notification("The battle is lost!", "Your enemy fights with no honor and has won the battle. You lost your pet monster <b>" + myMonster.getName()
                                + "</b> against the demonic. <b>" + sender.getUsername() + "</b> is now enjoing the spoils of war.", receiver));
                        pm.storeNotifications(receiver);

                    } else {
                        rt.lostRemoteFight(fr, fr.getSenderServerID());
                        receiver.addNotification(new Notification("Fight won!", "Congratulations! You are the winner of the epic battle between you and <b>" + fr.getSenderID() + "</b>. The bards will songs about this heroic battle for thousands of years to come.", receiver));
                        
                        pm.storeNotifications(receiver);
                    }
                    pm.removeFightRequest(fr);
                    pm.checkIfAnyMonsterDies();
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
