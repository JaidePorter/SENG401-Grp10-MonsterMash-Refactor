/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom.test;

import ServerCom.RemoteTalker;
import data.Player;

/**
 *
 * @author sis13
 */
public class Test {
    public static void main(String[] args) {
        RemoteTalker rt = new RemoteTalker();
        
        Player p = rt.findUser("sindre@downgoat.net");
        if(p != null) {
            System.out.println(p.getUserID());
        } else {
            System.out.println("Not found.");
        }
    }
}
