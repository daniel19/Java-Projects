package gui;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class NimGUI extends JFrame {
    private JPanel panel;

    public NimGUI(JPanel panel){
        super("Long NIM");
        this.panel = panel;
        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 540);
    }
}
