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
	}

	public String popTagOffStack() {
		return xml.removeFromList();
	}

	public String addWhitespace(int number) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < number; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	public void addElement(String element) {
		xml.addElementToList(element);
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
		    int n;
            try{
                n = line.length();
            }catch(NullPointerException ex){
                n = 0;
            }    
            for (int i = 0; i < n; i++) {
		        char lineCharacter = line.charAt(i);
                if (current.detectLeftAngle(lineCharacter)) {

				}
                if (current.detectForwardSlash(lineCharacter)) {
                    continue;
				}
                if (current.detectRightAngle(lineCharacter)) {

                }
                if (current.detectCharacters(lineCharacter)) {

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
