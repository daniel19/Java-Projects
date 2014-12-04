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
    private JCheckBox perPartyBox, allPartiesBox, writeBox, objectFileBox;

    public PartyGUI(){
        super("Party Gui");
        this.setLayout(new GridLayout(2,2));
        infoArea = new JTextArea();
        scrollPane = new JScrollPane(infoArea);
        this.add(scrollPane);
        initButtonPanel();
        this.add(panelButtons);
        initTextFieldPanel();
        this.add(panelText,1);
        initBoxPanel();
        this.add(panelBoxes);
        this.setSize(800, 680);
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
        panelButtons.add(buttonRegret);
        panelButtons.add(buttonADD);
        panelButtons.add(buttonFile);
        panelButtons.add(buttonGuests);
        panelButtons.add(buttonHelp);
        panelButtons.add(buttonInvite);
        panelButtons.add(buttonPay);
        panelButtons.add(buttonADD);
        panelButtons.add(buttonPrices);
        panelButtons.add(buttonSort);
        panelButtons.add(buttonStatus);
        panelButtons.add(buttonWho);
    }
    private void initTextFieldPanel(){
        panelText = new JPanel(new GridLayout(9,2));
        lblDate = new JLabel("Date");
        txtDate = new JTextField();
        panelText.add(lblDate);
        panelText.add(txtDate);
        lblGuest = new JLabel("Guests");
        txtGuest = new JTextField();
        panelText.add(lblGuest);
        panelText.add(txtGuest);
        lblHost = new JLabel("Host");
        txtHost = new JTextField();
        panelText.add(lblHost);
        panelText.add(txtHost);
        lblLocation = new JLabel("Location");
        txtLocation = new JTextField();
        panelText.add(lblLocation);
        panelText.add(txtLocation);
        lblMax = new JLabel("Max Guests");
        txtMax = new JTextField();
        panelText.add(lblMax);
        panelText.add(txtMax);
        lblParty = new JLabel("Party Name");
        txtParty = new JTextField();
        panelText.add(lblParty);
        panelText.add(txtParty);
        lblPrice = new JLabel("Price");
        txtPrice = new JTextField();
        panelText.add(lblPrice);
        panelText.add(txtPrice);
        lblSortAlgorithm = new JLabel("Sorting Algorithm");
        txtSortAlgorithm = new JTextField();
        panelText.add(lblSortAlgorithm);
        panelText.add(txtSortAlgorithm);
        lblSortField = new JLabel("Sort Field");
        txtSortField = new JTextField();
        panelText.add(lblSortField);
        panelText.add(txtSortField);
    }
    private void initBoxPanel(){
        panelBoxes = new JPanel(new FlowLayout());
        perPartyBox = new JCheckBox("Per Party");
        allPartiesBox = new JCheckBox("All Parties");
        writeBox = new JCheckBox("Write to File.");
        objectFileBox = new JCheckBox("Use object file");

    }
}
