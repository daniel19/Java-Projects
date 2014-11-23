/**
 *StartState is the first state in the pattern that encounters the first unopened tags.
 */
public class StartState implements XMLState{
    private XMLController controller;
    /**Public Constructor**/
    public StartState(XMLController controller){
        this.controller = controller;
    }
    
    public boolean detectForwardSlash(char character){
        return false;
    }
    public boolean detectLeftAngle(char character){
        if(character == '<'){ 
            controller.setNextState(controller.getDetectState());
            return true;    
        }
        return false;
    }
    public boolean detectRightAngle(char character){
        return false;
    }
    public boolean detectCharacters(char character){
        return false;
    }  
}
