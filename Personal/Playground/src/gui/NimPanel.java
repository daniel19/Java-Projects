package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class NimPanel extends JPanel{
   
    private JPanel currentPanel;

    public NimPanel(){
        this.setLayout(new FlowLayout());
        this.currentPanel = new StartPanel();
        add(currentPanel);
        this.setBackground(Color.black);

    }

    public void switchPanels(){
        if(this.currentPanel instanceof StartPanel){
            this.remove(this.currentPanel);
            this.currentPanel = new NimGamePanel();
            this.add(currentPanel);
            this.revalidate();
            this.repaint();
        }else if (this.currentPanel instanceof NimGamePanel){
            this.remove(this.currentPanel);
            this.currentPanel = new StartPanel();
            this.add(currentPanel);
            this.revalidate();
            this.repaint();
        }   
    }

    class StartPanel extends JPanel{
        private JLabel startLabel; 
        private JButton startButton;

        public StartPanel(){
            this.setLayout(new GridLayout(2,1));
            
            this.startLabel = new JLabel("Welcome to the game of NIM!");
            this.startButton = new JButton("Start Game");
            configureComponents();

            add(startLabel);
            add(startButton);
        }

        private void configureComponents(){
            if(this.startLabel != null && this.startButton != null){
                this.startButton.addActionListener(new AbstractAction(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        System.out.println("Switching");
                        NimPanel.this.switchPanels();
                    }
                });    
            }
        }
    }

    class NimGamePanel extends JPanel{
        private JPanel playerPanel;
        private JPanel opponentPanel;
        private JPanel mainPanel;

        public NimGamePanel(){
            this.setLayout(new GridLayout(3,1));
            this.setBackground(Color.CYAN);
            System.out.println("GamePanel created");
            configurePanels();

        }


        private void configurePanels(){
            this.playerPanel = new JPanel(new FlowLayout());
            this.opponentPanel= new JPanel(new FlowLayout());
            this.mainPanel = new JPanel(new FlowLayout());
            
            this.mainPanel.setBackground(Color.RED);
            this.playerPanel.setBackground(Color.BLUE);
            this.opponentPanel.setBackground(Color.GREEN);

            addButtons();

            this.add(opponentPanel);
            this.add(mainPanel);
            this.add(playerPanel);
        }

        private void addButtons(){
            JButton quitButton = new JButton("QUIT");
            quitButton.addActionListener(new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e){
                    System.exit(0);
                }
            });
            
            
            this.playerPanel.add(quitButton);

        }

    }
}

