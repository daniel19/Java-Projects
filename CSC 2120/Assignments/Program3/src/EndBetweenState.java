/**
 * EndBetweenState one of the six XMLStates in Program3. Responsibilities:
 */
public class EndBetweenState implements XMLState {
	/** Two way has-a relationship with XML **/
	private XMLController controller;

	/** Public Constructor **/
	public EndBetweenState(XMLController controller) {
		this.controller = controller;
	}

	public boolean detectForwardSlash(char character) {
		if (character == '/') {
			controller.setNextState(controller.getCloseState());
			return true;
		}
		return false;
	}

	public boolean detectLeftAngle(char character) {
		return false;
	}

	public boolean detectRightAngle(char character) {
		return false;
	}

	public boolean detectCharacters(char character) {
		return false;
	}
}
