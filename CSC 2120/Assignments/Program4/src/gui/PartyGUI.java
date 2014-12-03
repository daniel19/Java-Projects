package gui;

import javax.swing.*;
import party.PartyPlanner;

import java.awt.*;

/**
 * PartyGUI - class to hold all physical components.
 */
public class PartyGUI extends JFrame{
    private PartyPlanner p;
    private JPanel panelButtons, panelText, panelBoxes;
    private JTextArea infoArea;
    private JScrollPane scrollPane;
    private JButton buttonSort, buttonADD, buttonStatus, buttonInvite, buttonAccept,
                    buttonRegret, buttonHelp, buttonPrices, buttonWho, buttonPay,
                    buttonGuests, buttonFile;
    private JLabel lblGuest, lblParty, lblDate, lblHost, lblLocation, lblMax, lblPrice,
                   lblSortField, lblSortAlgorithm;
    private JTextField txtGuest, txtParty, txtDate, txtHost, txtLocation, txtMax, txtPrice,
            txtSortField, txtSortAlgorithm;

    public PartyGUI(){
        super("Party Gui");
        this.setLayout(new GridLayout(2,1));
        infoArea = new JTextArea();
        scrollPane = new JScrollPane(infoArea);
        this.add(scrollPane,0);
    }
}
