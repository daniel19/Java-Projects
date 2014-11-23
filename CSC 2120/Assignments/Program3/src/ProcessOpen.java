import java.util.ArrayList;
/**
 *ProcessOpen one of the six XMLStates in Program3.
 *Responsibilities:
 *  -Push open tag characters onto stack.
 */
public class ProcessOpen implements XMLState {
    /** Two way has-a relationship **/
    private XMLController controller;

    /**Collection of all tags**/
    ArrayList<String> tags = new ArrayList<String>(); 
    
    private StringBuilder sb; 
    /**Public Constructor**/
    public ProcessOpen(XMLController controller){
        this.controller = controller;
        sb = new StringBuilder();
    }
    
    public boolean detectForwardSlash(char character){
        return false;
    }
    public boolean detectLeftAngle(char character){
        return false;
    }
    public boolean detectRightAngle(char character){
        if(character == '>'){
            controller.pushTagOntoStack(sb.toString());
            sb.delete(0, sb.length()); //make room for new open tags
            controller.incrementTags("open");
            controller.setNextState(controller.getBetweeState());
            return true;
        }
        return false;
    }
    public boolean detectCharacters(char character){
        sb.append(character);
        return false;
    }  
}
