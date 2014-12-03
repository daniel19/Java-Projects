package gui;

import javax.swing.*;
import party.PartyPlanner;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        this.setLayout(new FlowLayout());
        infoArea = new JTextArea();
        scrollPane = new JScrollPane(infoArea);
        this.add(scrollPane);
        initButtonPanel();
        this.add(panelButtons);
        this.setSize(400, 480);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    System.exit(0);
                }
            });
    }

    private void initButtonPanel(){
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        buttonAccept = new JButton("Accept");
        buttonSort = new JButton("Sort");
        buttonADD = new JButton("ADD");
        buttonStatus = new JButton("Status");
        buttonRegret = new JButton("Regret");
        buttonInvite = new JButton("Invite");
        buttonHelp = new JButton("Help");
        buttonPay = new JButton("Pay");
        buttonPrices = new JButton("Prices");
        buttonWho = new JButton("Who");
        buttonGuests = new JButton("Guests");
        buttonFile = new JButton("File");
        panelButtons.add(buttonAccept);
        panelButtons.add(buttonADD);
        panelButtons.add(buttonFile);
        panelButtons.add(buttonGuests);
        panelButtons.add(buttonHelp);
        panelButtons.add(buttonInvite);
        panelButtons.add(buttonPay);
        panelButtons.add(buttonADD);
        panelButtons.add(buttonPrices);
        panelButtons.add(buttonRegret);
        panelButtons.add(buttonSort);
        panelButtons.add(buttonStatus);
        panelButtons.add(buttonWho);
    }
}
