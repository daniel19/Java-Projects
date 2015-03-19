import gui.PartyGUI;
public class PartyGUIDriver {
    public static void main(String[] args){
        PartyGUI gui = null;
        if(args.length <= 0){
            gui = new PartyGUI();

        }else{
            gui = new PartyGUI(args[0], Boolean.parseBoolean(args[1]));
        }
        gui.setVisible(true);
    }
}
