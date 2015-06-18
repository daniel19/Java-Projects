package engine;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

import gui.GamePanel;

@SuppressWarnings("serial")
public class Ball extends JComponent{
    private final int DIAMETER = 30;
    private int x, y;
    private int deltaX, deltaY;
    private GamePanel gamePanel;
    
    public Ball(GamePanel gamePanel){
        x = y = 0;
        deltaX = deltaY =1;
        this.gamePanel = gamePanel;
        this.setBackground(Color.WHITE);
    }

    public void move(){
        if( x + deltaX < 0)
            deltaX =1;
        if( x + deltaX > gamePanel.getWidth() - DIAMETER)
            deltaX = -1;

        if( y + deltaY < 0)
            deltaY = 1;
ea       if( y + deltaY > gamePanel.getHeight() - DIAMETER)
            deltaY = -1;

        if(isCollision()){
            deltaY = -1;
            y = gamePanel.getRacquet().getMyY() - DIAMETER;
            int currentSpeed = gamePanel.getSpeed();
            int newSpeed = currentSpeed - new Double(Math.floor(currentSpeed*0.153)).intValue();
            System.out.println("New: " + newSpeed + " old: " + currentSpeed);
            gamePanel.setSpeed(newSpeed);
        }

        x+= deltaX;
        y+= deltaY;

    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(x,y,DIAMETER, DIAMETER);
        
    }

    private boolean isCollision(){
        return gamePanel.getRacquet().getBounds().intersects(this.getBounds());
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
