package gui;

import java.awt.BorderLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class ForceGUI extends JFrame{
    
    //Global labesl
    private JLabel title, description;
   
    private JPanel panel; 
  
    public ForceGUI(JPanel panel){
        super("The Force");
        setupLabels();
        this.panel = panel; 
        
        this.add(panel, BorderLayout.CENTER);
        this.setSize(500, 540);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }   

   //set up Label elements
   private void setupLabels(){
       title = new JLabel("Force Label");
       description = new JLabel("Force Description");
       this.add(title, BorderLayout.PAGE_START);
       this.add(description, BorderLayout.PAGE_END);
   } 
}
