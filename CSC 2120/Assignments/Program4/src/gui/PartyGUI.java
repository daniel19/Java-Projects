package gui;

import javax.swing.*;
import party.PartyPlanner;
import party.PartyPlannerException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Paths;


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
        this.setSize(700, 680);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    System.exit(0);
                }
            });
        disableInput(this.getContentPane(), true);
        System.out.println("First");
    }
    public PartyGUI(String filename, boolean isObjectFile){
        this();
        try{
            p = new PartyPlanner(filename, isObjectFile);
            disableInput(this.getContentPane(), false);
        }catch (PartyPlannerException ex){
            infoArea.append("File could not be opened." + ex.getMessage() + "\n\n\n");
        }
        System.out.println("Second");
    }

    private void initButtonPanel(){
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        buttonAccept = new JButton("Accept");
        buttonAccept.addActionListener(new AcceptListener());
        buttonSort = new JButton("Sort");
        buttonSort.addActionListener(new SortListener());
        buttonADD = new JButton("ADD");
        buttonADD.addActionListener(new AddPartyListener());
        buttonStatus = new JButton("Status");
        buttonStatus.addActionListener(new StatusListener());
        buttonRegret = new JButton("Regret");
        buttonRegret.addActionListener(new RegretListener());
        buttonInvite = new JButton("Invite");
        buttonInvite.addActionListener(new InviteListener());
        buttonHelp = new JButton("Help");
        buttonHelp.addActionListener(new HelpListener());
        buttonPay = new JButton("Pay");
        buttonPay.addActionListener(new PayListener());
        buttonPrices = new JButton("Prices");
        buttonPrices.addActionListener(new PriceListener());
        buttonWho = new JButton("Who");
        buttonWho.addActionListener(new WhoListener());
        buttonGuests = new JButton("Guests");
        buttonGuests.addActionListener(new GuestListener());
        buttonFile = new JButton("File");
        buttonFile.setName("File");
        buttonFile.addActionListener(new FileListener());
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
        lblDate = new JLabel("Date", SwingConstants.CENTER);
        txtDate = new JTextField();
        panelText.add(lblDate);
        panelText.add(txtDate);
        lblGuest = new JLabel("Guests", SwingConstants.CENTER);
        txtGuest = new JTextField();
        panelText.add(lblGuest);
        panelText.add(txtGuest);
        lblHost = new JLabel("Host", SwingConstants.CENTER);
        txtHost = new JTextField();
        panelText.add(lblHost);
        panelText.add(txtHost);
        lblLocation = new JLabel("Location", SwingConstants.CENTER);
        txtLocation = new JTextField();
        panelText.add(lblLocation);
        panelText.add(txtLocation);
        lblMax = new JLabel("Max Guests", SwingConstants.CENTER);
        txtMax = new JTextField();
        panelText.add(lblMax);
        panelText.add(txtMax);
        lblParty = new JLabel("Party Name", SwingConstants.CENTER);
        txtParty = new JTextField();
        panelText.add(lblParty);
        panelText.add(txtParty);
        lblPrice = new JLabel("Price", SwingConstants.CENTER);
        txtPrice = new JTextField();
        panelText.add(lblPrice);
        panelText.add(txtPrice);
        lblSortAlgorithm = new JLabel("Sorting Algorithm", SwingConstants.CENTER);
        txtSortAlgorithm = new JTextField();
        panelText.add(lblSortAlgorithm);
        panelText.add(txtSortAlgorithm);
        lblSortField = new JLabel("Sort Field", SwingConstants.CENTER);
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
        objectFileBox.setName("Object");

        panelBoxes.add(perPartyBox);
        panelBoxes.add(allPartiesBox);
        panelBoxes.add(writeBox);
        panelBoxes.add(objectFileBox);
    }

    //Begin adding Action Listeners
    private class AddPartyListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //Grab data from text boxes
            String partyName, dateText, hostName, locationString, guestNumber,
                   pricePerString;
            int maxGuests;
            double price;
            try{
                partyName = txtParty.getText();
                dateText = txtDate.getText();
                hostName = txtHost.getText();
                locationString = txtLocation.getText();
                guestNumber = txtMax.getText();
                pricePerString = txtPrice.getText();

                maxGuests = Integer.parseInt(guestNumber);
                price = Double.parseDouble(pricePerString);

                boolean perParty = perPartyBox.isSelected();
                p.addToParties(partyName, hostName,dateText, locationString, maxGuests, perParty,price);
            }catch(NumberFormatException| PartyPlannerException ex){
                infoArea.append("Invalid data detected. Try Again." + ex.getMessage() + "\n\n\n");
            }finally {
                //Clear all inputs
                clearInput(PartyGUI.this.getContentPane());
            }
        }
    }
    private class StatusListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //Print status of Party Planner object
            String status =p.toString();
            infoArea.append(status + "\n\n\n");
        }
    }
    private class InviteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String partyName, dateText, guestName;
            partyName = txtParty.getText();
            dateText = txtDate.getText();
            guestName = txtGuest.getText();

            if(p.invite(guestName,partyName, dateText)){
                infoArea.append(guestName + " has been invited to the " + partyName + " " + dateText + " party\n\n\n");
            }else{
                infoArea.append(guestName + " has not been invited to the " + partyName + " " + dateText + " party.");
                infoArea.append("Party could not be found\n\n\n");
            }
            clearInput(PartyGUI.this.getContentPane());
        }
    }
    private class AcceptListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String partyName, dateText, guestName;
            partyName = txtParty.getText();
            dateText = txtDate.getText();
            guestName = txtGuest.getText();

            if(p.takeAccept(guestName, partyName, dateText)){
                infoArea.append(guestName + " has accepted the invitation to the " + partyName + " " + dateText + " party.\n\n\n");
            }else{
                infoArea.append(guestName + "could not RSVP to the " + partyName + " " + dateText + " party.");
                infoArea.append("Party could not be found\n\n\n");
            }

            clearInput(PartyGUI.this.getContentPane());
        }
    }
    private class RegretListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String partyName, dateText, guestName;
            partyName = txtParty.getText();
            dateText = txtDate.getText();
            guestName = txtGuest.getText();

            if(p.takeRegret(guestName, partyName, dateText)){
                infoArea.append(guestName + " has rejected the invitation to the " + partyName + " " + dateText + " party." + "\n\n\n");
            }else{
                infoArea.append(guestName + "could not RSVP to the " + partyName + " " + dateText + " party.");
                infoArea.append("Party could not be found\n\n\n");
            }
            clearInput(PartyGUI.this.getContentPane());
        }
    }
    private class HelpListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           infoArea.append("1. Host(asc)\n2. Date(desc)\n3. Max number of Guests(desc)\n4. Number Attending(asc)\n" +
                   "5. Cost(asc)\n\nSort Algorithm\n1. Selection Sort\n2. Insertion Sort\n3. Bubble Sort" + "\n\n\n");
        }
    }
    private class SortListener implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e){
            int sortField, sortAlg;

            try{
               sortField = Integer.parseInt(txtSortField.getText());
               sortAlg = Integer.parseInt(txtSortAlgorithm.getText());

               infoArea.append(p.sort(sortField,sortAlg)+"\n\n\n");
            }catch (NumberFormatException ex){
                infoArea.append("Invalid data detected\n\n\n");
            }finally {
                //Clear all inputs
                clearInput(PartyGUI.this.getContentPane());
            }
        }
    }
    private class WhoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String partyName, dateText;
            partyName = txtParty.getText();
            dateText = txtDate.getText();
            infoArea.append(p.getWhosInvited(partyName,dateText) + "\n\n\n");
            clearInput(PartyGUI.this.getContentPane());
        }

    }
    private class PriceListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String partyName, dateText;
            partyName = txtParty.getText();
            dateText = txtDate.getText();
            double price;
            try{
                price = Double.parseDouble(txtPrice.getText());
                if(p.updatePrice(partyName, dateText, (int)Math.ceil(price))){
                    infoArea.append("Price has been updated for party " + partyName + " " + dateText + "\n\n\n");
                }else{
                    infoArea.append("Price has not been updated for party " + partyName + " " + dateText);
                    infoArea.append("Party not found.\n\n\n");
                }
            }catch (NumberFormatException ex){
                infoArea.append("Invalid Data found.\n");
            }finally {
                //Clear all inputs
                clearInput(PartyGUI.this.getContentPane());
            }
        }
    }
    private class PayListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String partyName, dateText;
            partyName = txtParty.getText();
            dateText = txtDate.getText();
            int months;
            try{
                months = Integer.parseInt(txtPrice.getText());
                infoArea.append(p.pay(partyName, dateText, months));
            }catch (NumberFormatException ex){
                infoArea.append("Invalid data found.\n\n\n");
            }finally {
                //Clear all inputs
                clearInput(PartyGUI.this.getContentPane());
            }
        }
    }
    private class GuestListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String partyName, dateText;
            partyName = txtParty.getText();
            dateText = txtDate.getText();
            int max = p.getMaxGuests(partyName, dateText);
            int accepting = p.getNumAttending(partyName, dateText);
            int nonAttending = p.getNumNotAttending(partyName, dateText);
            int unknown = p.getNumUnknown(partyName, dateText);
            if(unknown == -1 || accepting == -1 || nonAttending ==-1){
                infoArea.append("Party " + partyName+ " " +dateText+ " not found.\n\n");
            }else {
                infoArea.append("\nNumber Attending: " + accepting + "\nMax Number: " + max + "\nNumber not Attending: " + nonAttending
                + "\nNumber Unknown: " + unknown + "\n\n\n");
            }
            clearInput(PartyGUI.this.getContentPane());
        }
    }
    private class FileListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            boolean write = writeBox.isSelected();
            boolean objectFile = objectFileBox.isSelected();
            JFileChooser fc = new JFileChooser(Paths.get(".").toAbsolutePath().normalize().toString());
            if(write){
                int fileValue = fc.showSaveDialog(PartyGUI.this);
                if(fileValue == JFileChooser.APPROVE_OPTION){
                    if(objectFile){
                      try{
                          p.writeToObjectFile(fc.getSelectedFile().getName());
                          infoArea.append("File " + fc.getSelectedFile().getName() + " was written successfully.\n\n\n");
                      }catch (PartyPlannerException ex){
                          infoArea.append(ex.getMessage() + "\n\n\n");
                      }
                    }else{
                        try{
                            p.writeToFile(fc.getSelectedFile().getName());
                            infoArea.append("File " + fc.getSelectedFile().getName() + " was written successfully.\n\n\n");
                        }catch (PartyPlannerException ex){
                            infoArea.append(ex.getMessage() + "\n\n\n");
                        }
                    }
                }
            }else{
                //open file
                int fileValue = fc.showOpenDialog(PartyGUI.this);
                if(fileValue == fc.APPROVE_OPTION) {
                    if (objectFile) {
                        try {
                            p = new PartyPlanner(fc.getSelectedFile().getAbsolutePath().toString(), true);
                            infoArea.append(p.getState());
                            disableInput(PartyGUI.this.getContentPane(), false);
                        } catch (PartyPlannerException ex) {
                            infoArea.append("Error Opening: " + ex.getMessage() + "\n\n\n");
                        }
                    } else {
                        try {
                            p = new PartyPlanner(fc.getSelectedFile().getAbsolutePath().toString(), false);
                            infoArea.append(p.getState());
                            disableInput(PartyGUI.this.getContentPane(), false);
                        } catch (PartyPlannerException ex) {
                            infoArea.append(ex.getMessage() + "\n\n\n");
                        }
                    }
                }
            }
        }
    }

    /**
     * Clears all text fields and deselects all checkboxes.
     * @param container
     */
    private void clearInput(Container container) {
        for(Component c : container.getComponents()){
            if( c instanceof  JTextField){
               ((JTextField) c).setText("");
            }else if( c instanceof JCheckBox){
                ((JCheckBox) c).setSelected(false);
            }else if (c instanceof Container){
                clearInput((Container) c);
            }
        }
    }

    /**
     * At beginning of program, this function is used to disable unnecessary inputs until
     * a party file is loaded.
     * @param container
     * @param flag
     */
    private void disableInput(Container container, boolean flag){
        for(Component c : container.getComponents()){
            if( !(c instanceof  JPanel)){
                if(flag && !(c instanceof JTextArea) && !(c.getName() == "File") && !(c.getName() == "Object")) {
                    c.setEnabled(false);
                }else{
                    c.setEnabled(true);
                }
            }else{
                disableInput((Container) c, flag);
            }
        }
    }
}
