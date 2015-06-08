package gui;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import engine.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener{
    private Ball ball;
    private Racquet racquet; 
    private int gameSpeed = 10; // millis of game speed 
    /**
     *Construct basic background of our game.
     */
    public GamePanel(Color backgroundColor){
        this.setBackground(backgroundColor);
        ball = new Ball(this);
        racquet = new Racquet(this);
        this.addKeyListener(this);
        this.setFocusable(true);
    } 
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ball.paintComponent(g);
        racquet.paintComponent(g);
    }

    public void gameAction(){
        ball.move();
        racquet.move();
    }

    public void keyTyped(KeyEvent e){
    }
    
    public void keyPressed(KeyEvent e){
        racquet.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        racquet.keyReleased(e);
    }

    public int getSpeed(){
        return gameSpeed;
    }

    public void setSpeed(int speed){
        gameSpeed = speed; 
    }

    public Racquet getRacquet(){
        return racquet;
    }
}
