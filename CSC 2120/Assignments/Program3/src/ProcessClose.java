public class ProcessClose implements XMLState {
	/** Two way has-a relationship with XML **/
	private XMLController controller;

	private StringBuilder sb;

	/** Public Constructor **/
	public ProcessClose(XMLController controller) {
		this.controller = controller;
		sb = new StringBuilder();
	}

	public boolean detectForwardSlash(char character) {
		if (character == '/') {
			controller.setNextState(controller.getDetectState());
			return true;
		}
		return false;
	}

	public boolean detectLeftAngle(char character) {
		return false;
	}

	public boolean detectRightAngle(char character) throws XMLParseException {
		if (character == '>') {
			int stackSize = controller.xml.stackSize();
			String poppedTag = controller.popTagOffStack();
			if (poppedTag != sb.toString()) {
				// Throw XMLParseException
				throw new XMLParseException("\n\nOpen tag: " + poppedTag
						+ " does not match closing tag: " + sb.toString());
			}
			controller.addElement(controller.addWhitespace(stackSize)
					+ sb.toString());
			controller.setNextState(controller.getStartState());
			sb.delete(0, sb.length());
			return true;
		}
		return false;
	}

	public boolean detectCharacters(char character) {
		sb.append(character);
		return false;
	}
}
