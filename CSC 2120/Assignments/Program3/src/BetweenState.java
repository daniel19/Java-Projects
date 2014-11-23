/**
 *BetweenState one of the six XMLStates in Program3.
 *Responsibilities:
 *  -Nothing explicit for the moment.
 */
public class BetweenState implements XMLState {
    /**Two way has-a relationship with XML**/
    private XMLController controller;
    /**Public Constructor**/
    public BetweenState(XMLController controller){
        this.controller = controller;
    }
    
    public boolean detectForwardSlash(char character){
        return false;
    }
    public boolean detectLeftAngle(char character){
        if(character == '<'){
            controller.setNextState(controller.getEndBetweenState());
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
