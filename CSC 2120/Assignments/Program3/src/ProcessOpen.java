/**
 * ProcessOpen one of the six XMLStates in Program3. 
 * Responsibilities: -Push
 * open tag characters onto stack.
 */
public class ProcessOpen implements XMLState {
	/** Two way has-a relationship **/
	private XMLController controller;

	/** Build up elements/tag values **/
	private StringBuilder sb;

	/** Public Constructor **/
	public ProcessOpen(XMLController controller) {
		this.controller = controller;
		sb = new StringBuilder();
	}

	public boolean detectForwardSlash(char character) {
		return false;
	}

	public boolean detectLeftAngle(char character) {
		return false;
	}

	public boolean detectRightAngle(char character) {
        if (character == '>') {
            String tag = "<" + sb.toString() + ">";
			controller.pushTagOntoStack(sb.toString());
			controller.addElement(controller.addWhitespace(controller.xml
					.stackSize()) + tag);
			sb.delete(0, sb.length()); // make room for new open xml tag
			controller.setNextState(controller.getBetweenState());
			return true;
		}
		return false;
	}

	public boolean detectCharacters(char character) {
        if(!detectLeftAngle(character) && !detectRightAngle(character) && !detectForwardSlash(character)){
            sb.append(character);
        }
        return false;
	}
    
    public void appendFirstChar(char character){
        sb.append(character);
    }
}
