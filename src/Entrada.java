import java.awt.EventQueue;

import javax.swing.*;
public class Entrada {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	
             
                    VentanaInicio frame = new VentanaInicio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });}

}