package engine;
import gui.GamePanel;

public class Game{

    private GamePanel gamePanel;

    private Ball ball;
    private Racquet racquet;

    public Game(GamePanel panel){
        gamePanel = panel;
        ball = new Ball(this);
        racquet =  new Racquet(this);
        
        gamePanel.add(ball);
        gamePanel.add(racquet);
    }

    public int getWidth(){
        return gamePanel.getWidth();
    }

    public int getHeight(){
        return gamePanel.getHeight();
    }

}
