import gui.*;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public  class PlaygroundDriver{
    public static void main(String[] args){
        System.out.println("Starting Simulation");
       // playNim();
       playPong();
    }   

    static void playNim(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex){
            System.out.println(ex.getMessage());
        }

        NimPanel panel = new NimPanel();
        NimGUI gui = new NimGUI(panel);
        gui.setResizable(false);
        gui.setVisible(true);
    }
   
    static void playPong(){
        GamePanel gp = new GamePanel(Color.BLUE);
        ForceGUI gui = new ForceGUI(gp);
        gui.setVisible(true);
        while(true){
            gp.gameAction();
            gp.repaint();
            try{
                Thread.sleep(gp.getSpeed(), gp.getSpeed()/1000);
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
   } 
}
