/* 
 * $HeadURL: https://github.com/DownGoat/MonsterMash/blob/development/G12/src/MonsterMash/MainPage.java
 * 
 * Copyright (c) 2013 Aberystwyth University
 * All rights reserved. 
 * 
 */

import ServerCom.RemoteTalker;
import database.PersistenceManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.*;
import database.OtherPersistenceManager;
import java.util.Collections;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.reference.DefaultEncoder;

/**
 * Servlet used for displaying the main page after logging in. 
 * 
 * @author $Author: sjk4$
 * @version $Id$
 */
public class MainPage extends HttpServlet {

    /**
     * Gets all data from DB, which will be displayed on main screen (list of
     * friends, monsters and notifications).
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void getDataFromDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Redirects when user is not logged in
            response.sendRedirect("");
        } else {
            Player current = (Player) session.getAttribute("user");
            PersistenceManager pm = new PersistenceManager();
            // Check if any monster dies:
            pm.checkIfAnyMonsterDies();
            // Updates player informations
            current = pm.getPlayer(current.getUserID());
            session.setAttribute("user", current);
            // Saves all notifications to attribute
            request.setAttribute("notificationList", current.getNotifications());
            // Saves all friends to attribute
            request.setAttribute("friendList", current.getFriends());
            // Saves all friend requests to attribute
            request.setAttribute("requestList", pm.getFriendRequestList(current.getUserID()));
            // Saves all fight requests to attribute
            request.setAttribute("monsterRequestList", pm.getFightRequests(current.getUserID()));
            // Saves all monsters to attribute
            request.setAttribute("monsterList", pm.getMonsterList(current.getUserID()));
            request.getRequestDispatcher("/WEB-INF/main_page.jsp").forward(request, response);
        }
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
        if(request.getParameter("removeFriend") != null){
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("");
            } else {
                String friendID = request.getParameter("removeFriend");
                Player myUser = (Player)session.getAttribute("user");
                myUser.addNotification(new Notification("Friend "+friendID+" removed from friend list.", "You have removed "+friendID+" from your friend list.", myUser));
                OtherPersistenceManager opm = new OtherPersistenceManager();
                opm.storeNotifications(myUser);
                opm.removeFriendship(friendID, myUser.getUserID());
            }
        }
        this.respondToFriendRequest(request, response);
        this.getDataFromDB(request, response);
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
        this.sendFriendRequest(request, response);
        this.doGet(request, response);
    }

    /**
     * Check if user sent form with new friend request if so process it and send
     * friend request with proper notifications.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void sendFriendRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PersistenceManager pm = new PersistenceManager();
        // Checks if user is logged in
        if (session != null && session.getAttribute("user") != null) {

            // Gets email from POST 
            Encoder encoder = new DefaultEncoder();
            String userID = encoder.encodeForSQL(new OracleCodec(), request.getParameter("email"));
            Player sender = (Player)session.getAttribute("user");
            // Checks if user with this email address exists
            int serverID = pm.getPlayerServerID(userID);
            if(serverID == 0){
                request.setAttribute("alertMessage", "Cannot find user with this email address.");
            }else if(pm.isFriendRequestSent(sender.getUserID(), userID)){
                request.setAttribute("alertMessage", "Cannot send friend request to this player.");
            }else if(sender.getUserID().equals(userID)){
                request.setAttribute("alertMessage", "Cannot send friend request to yourself.");
            }else{
                String message = "Friend request to <b>"+userID+"</b> sent successfully.";
                sender.addNotification(new Notification(message, "You have sent friend request to <b>"+userID+"</b>.", sender));
                pm.storeNotifications(sender);
                pm.sendFriendRequest(sender.getUserID(), userID, serverID);
                if(serverID == CONFIG.OUR_SERVER){
                    //Receiver is on our server
                    Player receiverObject = pm.getPlayer(userID);
                    receiverObject.addNotification(new Notification("Received friend request from <b>"+sender.getUsername()+"</b>", "You have received friend request from <b>"+sender.getUsername()+"</b>.", receiverObject)); 
                    pm.storeNotifications(receiverObject);
                    // Save updated player's object in session
                    session.setAttribute("user", sender);
                    request.setAttribute("alertMessage", message);
                }
            }
        }
    }

    /**
     * Accept or cancel friend request.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void respondToFriendRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        RemoteTalker rt = new RemoteTalker();
        // Check if user is logged in
        if (session != null && session.getAttribute("user") != null) {
            Player logged = (Player) session.getAttribute("user");
            OtherPersistenceManager pm = new OtherPersistenceManager();
            // Check if acceptFriendRequest occured:
            if (request.getParameter("acceptFriendRequest") != null) {
                String friendshipID = request.getParameter("acceptFriendRequest");
                Friend friend = pm.getFriend(friendshipID);
                if (friend != null) {
                    Player sender = rt.findUser(friend.getRemoteUserID());
                    if (sender == null) {
                        System.err.println("Something went wrong with server to server stuff player(s) not found.");
                        return;
                    }

                    if (friend.getRemoteServerID() == CONFIG.OUR_SERVER) {
                        pm.acceptFriendRequest(friend);

                        sender.addNotification(new Notification(
                                "Accepted friend request from <b>" + logged.getUserID() + "</b>.",
                                "<b>" + logged.getUserID() + "</b> has accepted your friend request.",
                                sender));

                        logged.addNotification(new Notification(
                                "Accepted friend request from <b>" + sender.getUserID() + "</b>.",
                                "You have accepted friend request from <b>" + sender.getUserID() + "</b>.",
                                logged));

                        pm.storeNotifications(sender);
                        pm.storeNotifications(logged);

                    } else {
                        pm.acceptFriendRequest(friend);
                        logged.addNotification(new Notification(
                                "Accepted friend request from <b>" + sender.getUserID() + "</b>.",
                                "You have accepted friend request from <b>" + sender.getUserID() + "</b>.",
                                logged));
                        pm.storeNotifications(logged);

                        if (!rt.acceptRemoteFriendRequest(friend)) {
                            System.err.println("Something went wrong with server to server stuff friend request accept request was not sent.");
                        }
                    }
                }

            }
            // Check if cancelFriendRequest occured:
            if (request.getParameter("cancelFriendRequest") != null) {
                String friendshipID = request.getParameter("cancelFriendRequest");

                Friend friend = pm.getFriend(friendshipID);
                if (friend != null) {
                    Player sender = rt.findUser(friend.getRemoteUserID());
                    Player receiver = rt.findUser(friend.getLocalUserID());

                    if (sender == null || receiver == null) {
                        System.err.println("Something went wrong with server to server stuff player(s) not found.");
                        return;
                    }

                    if (friend.getRemoteServerID() == CONFIG.OUR_SERVER) {
                        sender.addNotification(new Notification(
                                "Friend request to<b>" + receiver.getUserID() + "</b> declined.",
                                "<b>" + receiver.getUserID() + "</b> has rejected your friend request.",
                                sender));

                        receiver.addNotification(new Notification(
                                "Request from <b>" + sender.getUserID() + "</b> declined.",
                                "You declined the friend request from <b>" + sender.getUserID() + "</b>.",
                                receiver));

                        pm.storeNotifications(sender);
                        pm.storeNotifications(receiver);
                        
                        pm.rejectFriend(friend);
                    } else {
                        if(friend.getRemoteServerID() == CONFIG.OUR_SERVER) {
                            receiver.addNotification(new Notification(
                                "Request from <b>" + sender.getUserID() + "</b> declined.",
                                "You declined the friend request from <b>" + sender.getUserID() + "</b>.",
                                receiver));
                        
                            pm.storeNotifications(receiver);
                        }
                        
                        if(!rt.rejectRemoteFriendRequest(friend)) {
                            System.err.println("Something went wrong with the remote friend reject request.");
                        }
                        
                        pm.rejectFriend(friend);
                    }
                }
            }
        }
    }
}
