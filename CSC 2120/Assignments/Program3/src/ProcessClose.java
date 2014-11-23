public class ProcessClose implements XMLState {
  /**Two way has-a relationship with XML**/
    private XMLController controller;
    
    private StringBuilder sb;
    /**Public Constructor**/
    public ProcessClose(XMLController controller){
        this.controller = controller;
        sb = new StringBuilder();
    }
    
    public boolean detectForwardSlash(char character){
        if(character == '/'){ 
            controller.setNextState(controller.getDetectState());
            return true;    
        }
        return false;
    }
    public boolean detectLeftAngle(char character){
        return false;
    }
    public boolean detectRightAngle(char character){
        if(character == '>'){
            controller.pushTagOntoStack(sb.toString()); 
            sb.delete(0,sb.length());
            controller.incrementTags("close"); 
            controller.setNextState(controller.getStartState());
            return true;    
        }
        return false;
    }
    public boolean detectCharacters(char character){
        sb.append(character);
        return false;
    }  
}
