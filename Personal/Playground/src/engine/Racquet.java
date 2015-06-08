package engine;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;


import gui.GamePanel;

@SuppressWarnings("serial")
public class Racquet extends JComponent{
    private final int WIDTH = 10;
    private final int HEIGHT = 60;
    private int y = 400;

    private int x = 0;
    private int deltaX = 0;
    private GamePanel gamePanel;

    public Racquet(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void move() {
        if (x + deltaX > 0 && x + deltaX < gamePanel.getWidth()- WIDTH)
            x = x + deltaX;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(x, y, HEIGHT, WIDTH);
    }
    public void keyReleased(KeyEvent e) {
        deltaX = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            deltaX = -1;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            deltaX = 1;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getMyY(){
        return y;
    }
}
