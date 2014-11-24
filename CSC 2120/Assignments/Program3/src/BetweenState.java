/**
 * BetweenState one of the six XMLStates in Program3. Responsibilities:
 * -Captures the elements within a given tag.
 */
public class BetweenState implements XMLState {
	/** Two way has-a relationship with XML **/
	private XMLController controller;

	private StringBuilder sb;

	/** Public Constructor **/
	public BetweenState(XMLController controller) {
		this.controller = controller;
		sb = new StringBuilder();
	}

	public boolean detectForwardSlash(char character) {
		return false;
	}

	public boolean detectLeftAngle(char character) {
		if (character == '<' && sb.length() >= 1) {
			controller.addElement(controller.addWhitespace(controller.xml
					.stackSize()) + sb.toString());
			sb = sb.delete(0, sb.length());
			controller.setNextState(controller.getEndBetweenState());
			return true;
		} else if (character == '<' && sb.length() < 1) {
			controller.setNextState(controller.getDetectState());
			return true;
		}
		return false;
	}

	public boolean detectRightAngle(char character) {
		return false;
	}

	public boolean detectCharacters(char character) {
		sb.append(character);
		return false;
	}
}
