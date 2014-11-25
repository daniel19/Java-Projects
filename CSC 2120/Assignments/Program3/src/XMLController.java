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
	public XMLController(String filename) throws XMLParseException{
		close = new ProcessClose(this);
		open = new ProcessOpen(this);
		detection = new DetectTag(this);
		starting = new StartState(this);
		endBetween = new EndBetweenState(this);
		between = new BetweenState(this);
		current = starting;
		xml = new XML();
        try{
            fileOperator = new FileIO(filename, FileIO.FOR_READING);
        }catch(FileIOException e){
            throw new XMLParseException(e.getMessage());
        }
    }

	/**Sets the current state to one of the six state classes **/
    public void setNextState(XMLState state) {
		current = state;
	}
    /**Sends first char to Open state and returns reference to the Open state.**/
	public ProcessOpen getOpenState(char character) {
		open.appendFirstChar(character);
        return open;
	}
    /** Returns the state references. **/
	public ProcessClose getCloseState() {
		return close;
	}

    /** Returns the state references. **/
	public DetectTag getDetectState() {
		return detection;
	}

    /** Returns the state references. **/
	public StartState getStartState() {
		return starting;
	}

    /** Returns the state references. **/
	public BetweenState getBetweenState() {
		return between;
	}

    /** Returns the state references. **/
	public EndBetweenState getEndBetweenState() {
		return endBetween;
	}
    /** Pushes tag onto stack**/
	public void pushTagOntoStack(String tag) {
		xml.addToStack(tag);
	}
    /**Returns top of stack and then removes it.**/
	public String popTagOffStack() {
		return xml.removeFromList();
	}
    /** Adds the number of white spaces bassed on the number variable.
     * Used to add indents in the final output file.
     */
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
	
    /**Reads the users input file and passes each character from each line to the current state.**/
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
			    current.detectCharacters(lineCharacter);
            }
		}
	}
    /**Write the final element list to the specified output file.*/
	public void writeFile(String filename) {
		fileOperator = new FileIO(filename, FileIO.FOR_WRITING);
		ArrayList<String> list = xml.getList();
		for (String s : list) {
			fileOperator.writeLine(s);
		}
		fileOperator.close();
	}
}
