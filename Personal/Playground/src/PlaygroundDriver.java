import gui.*;
import java.awt.Color;

public  class PlaygroundDriver{
    public static void main(String[] args){
        System.out.println("Starting Simulation");
        GamePanel gp = new GamePanel(Color.BLUE);
        ForceGUI gui = new ForceGUI(gp);
        gui.setVisible(true);
        while(true){
            gp.gameAction();
            gp.repaint();
            try{
                Thread.sleep(gp.getSpeed(), gp.getSpeed()/100);
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }    
}
