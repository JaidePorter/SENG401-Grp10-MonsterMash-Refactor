/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom.test;

import ServerCom.JSONManager;
import ServerCom.RemoteTalker;
import data.FightRequest;
import data.Friend;
import data.Monster;
import data.Player;
import database.OtherPersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author sis13
 */
public class TalkerTester extends HttpServlet {

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
            throws ServletException, IOException, JSONException {
        String type = request.getParameter("type");
        
        if(type.equals("sendf")) {
            OtherPersistenceManager pm = new OtherPersistenceManager();
            Player p = pm.getPlayer("sindre@downgoat.net");
            RemoteTalker rt = new RemoteTalker();
            rt.remoteFriendRequest(p, "test@test.com", 12);
        }
        
        else if (type.equals("reject")) {
            OtherPersistenceManager pm = new OtherPersistenceManager();
            RemoteTalker rt = new RemoteTalker();
            Friend friend = pm.getFriend(request.getParameter("friendID"));
            rt.rejectRemoteFriendRequest(friend);
        }
        
        else if (type.equals("accept")) {
            OtherPersistenceManager pm = new OtherPersistenceManager();
            RemoteTalker rt = new RemoteTalker();
            Friend friend = pm.getFriend(request.getParameter("friendID"));
            rt.acceptRemoteFriendRequest(friend);
        }
        
        else if (type.equals("monster")) {
            RemoteTalker rt = new RemoteTalker();
            Monster m = rt.getRemoteMonster(request.getParameter("monsterID"), rt.getRemoteAddress(12));
            System.out.println(JSONManager.jsonMonster(m).toString());
        }
        
        else if (type.equals("monsters")) {
            RemoteTalker rt = new RemoteTalker();
            System.out.println(
                    JSONManager.jsonMonsterList(
                        rt.getRemoteUsersMonsters(
                            request.getParameter("userID"),
                            rt.getRemoteAddress(12)
                        )
                    ).toString());
        }
        
        else if (type.equals("user")) {
            RemoteTalker rt = new RemoteTalker();
            Player p = rt.getRemotePlayer(request.getParameter("userID"), rt.getRemoteAddress(12));
            System.out.println(JSONManager.jsonPlayer(p).toString());
        }
        
        else if(type.equals("sendfr")) {
            RemoteTalker rt = new RemoteTalker();
            
            String fightID = request.getParameter("fightID");
            String recieverMonsterID = request.getParameter("localMonsterID");
            String senderMonsterID = request.getParameter("remoteMonsterID");
            String senderID = request.getParameter("senderID");
            String receiverID = request.getParameter("receiverID");
            
            FightRequest fr = new FightRequest(senderID, receiverID, fightID, senderMonsterID, recieverMonsterID, 12, 12);
            
            rt.remoteFightRequest(fr, 12);
         
        }
        
        else if(type.equals("won")) {
            RemoteTalker rt = new RemoteTalker();
            
            String fightID = request.getParameter("fightID");
            String recieverMonsterID = request.getParameter("localMonsterID");
            String senderMonsterID = request.getParameter("remoteMonsterID");
            String senderID = request.getParameter("senderID");
            String receiverID = request.getParameter("receiverID");
            
            FightRequest fr = new FightRequest(senderID, receiverID, fightID, senderMonsterID, recieverMonsterID, 12, 12);
            
            
            
            rt.wonRemoteFight(fr, 12, rt.getRemoteMonster(senderMonsterID, rt.getRemoteAddress(12)));
        }
        
        else if(type.equals("lost")) {
            RemoteTalker rt = new RemoteTalker();
            String fightID = request.getParameter("fightID");
            String recieverMonsterID = request.getParameter("localMonsterID");
            String senderMonsterID = request.getParameter("remoteMonsterID");
            String senderID = request.getParameter("senderID");
            String receiverID = request.getParameter("receiverID");
            
            FightRequest fr = new FightRequest(senderID, receiverID, fightID, senderMonsterID, recieverMonsterID, 12, 12);
            
            rt.lostRemoteFight(fr, 12);
        }
        
        else if(type.equals("rejectfr")) {
            RemoteTalker rt = new RemoteTalker();
            String fightID = request.getParameter("fightID");
            String recieverMonsterID = request.getParameter("localMonsterID");
            String senderMonsterID = request.getParameter("remoteMonsterID");
            String senderID = request.getParameter("senderID");
            String receiverID = request.getParameter("receiverID");
            
            FightRequest fr = new FightRequest(senderID, receiverID, fightID, senderMonsterID, recieverMonsterID, 12, 12);
            
            rt.rejectRemoteFight(fr, 12);
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
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(TalkerTester.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(TalkerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
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
