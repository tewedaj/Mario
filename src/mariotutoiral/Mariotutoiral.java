/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariotutoiral;

import javax.swing.JFrame;

/**
 *
 * @author Tewedaj
 */
public class Mariotutoiral {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         JFrame fr=new JFrame("Mario");
        Enviroment ev=new Enviroment();
        fr.add(ev);
        fr.setSize(700,500);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setVisible(true);
    }
    
}
