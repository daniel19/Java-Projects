public class DetectTag implements XMLState {
	/** Two way has-a relationship with XML **/
	private XMLController controller;

	/** Public Constructor **/
	public DetectTag(XMLController controller) { this.controller = controller; }

	public boolean detectForwardSlash(char character) {
		if (character == '/') {
			controller.setNextState(controller.getCloseState());
			return true;
		}
		return false;
	}

	public boolean detectLeftAngle(char character) { return false; }

	public boolean detectRightAngle(char character) { return false; }

	public boolean detectCharacters(char character) {
			if(!detectForwardSlash(character)){//Calls only the necessary inherited functions
                controller.setNextState(controller.getOpenState(character));
                return true;
            }
			return false;

	}
}
