import java.util.ArrayList;

/**
 * Controller class for our XML states Responsibilities: -Properly formats the
 * xml tree -Reads and writes XML files. -Manages the states during xml parsing.
 */
public class XMLController {
	private ProcessClose close;
	private ProcessOpen open;
	private DetectTag detection;
	private EndBetweenState endBetween;
	private BetweenState between;
	private StartState starting;
	private XMLState current;

	XML xml;

	private FileIO fileOperator;

	/** Public Constructor **/
	public XMLController(String filename) {
		close = new ProcessClose(this);
		open = new ProcessOpen(this);
		detection = new DetectTag(this);
		starting = new StartState(this);
		endBetween = new EndBetweenState(this);
		between = new BetweenState(this);
		current = starting;
		xml = new XML(true);
		fileOperator = new FileIO(filename, FileIO.FOR_READING);
	}

	// State methods
	public void setNextState(XMLState state) {
		current = state;
	}

	public ProcessOpen getOpenState() {
		return open;
	}

	public ProcessClose getCloseState() {
		return close;
	}

	public DetectTag getDetectState() {
		return detection;
	}

	public StartState getStartState() {
		return starting;
	}

	public BetweenState getBetweenState() {
		return between;
	}

	public EndBetweenState getEndBetweenState() {
		return endBetween;
	}

	public void pushTagOntoStack(String tag) {
		xml.addToStack(tag);
		//printStack();
	}

	public String popTagOffStack() {
		return xml.removeFromList();
	}

	public void incrementTags(String selector) {
		if (selector == "open") {
			xml.incrementOpenCount();
		} else if (selector == "close") {
			xml.incrementClosedCount();
		}
	}

	public String addWhitespace(int number) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < number; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	public void addElement(String element) {
		xml.addElementtoList(element);
	}

	public String getElement(int position) {
		return xml.getElementFromList(position);
	}

	private void printStack(){
		System.out.println(xml.stackString());
	}
	public void readFile() {
		while (!fileOperator.EOF()) {
			String line = fileOperator.readLine();
			for (int i = 0, n = line.length(); i < n; i++) {
				char lineCharacter = line.charAt(i);
				if (current.detectLeftAngle(lineCharacter)) {
					// break from loop iteration
				} else if (current.detectForwardSlash(lineCharacter)) {
					// break from loop iteration
				} else if (current.detectRightAngle(lineCharacter)) {
					// break from loop iteration
				} else if (current.detectCharacters(lineCharacter)) {
					// break from loop iteration
				}
			}
		}
	}

	public void writeFile(String filename) {
		fileOperator = new FileIO(filename, FileIO.FOR_WRITING);
		ArrayList<String> list = xml.getList();
		for (String s : list) {
			fileOperator.writeLine(s);
		}
		fileOperator.close();
	}
}
