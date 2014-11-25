import java.util.ArrayList;
import java.util.Stack;

public class XML {
	/**Dynamic stack of strings to compare tags.**/
    private Stack<String> tagStack;
	/**List that is printed an output file**/
	private ArrayList<String> elementList;

    /**Public Constructor**/
	public XML() {
		tagStack = new Stack<String>();
		elementList = new ArrayList<String>();
	}
    /**Adds open/closed tags to the stack**/
	public void addToStack(String tag) {
        tagStack.add(tag);
    }
    /** Returns the current Stack size **/
	public int stackSize() {
		return tagStack.size();
	}
    /**Returns tag on top of stack.**/
	public String removeFromList() throws XMLParseException {
		if(tagStack.empty())
            throw new XMLParseException("The number of open and closed tags do not match.");
        return tagStack.pop();
	}
    /**Add values to the list **/
	public void addElementToList(String element) {
		elementList.add(element);
	}

	public String getElementFromList(int position) {
		return elementList.get(position);
	}

	/**Returns the complete list**/
    public ArrayList<String> getList() {
		return elementList;
	}
	
	/**Used to print the state of the stack.**/
    public String stackString(){
		StringBuilder sb = new StringBuilder();
		for(String s : tagStack){
			sb.append(s + "\n");
	    }
		return sb.toString();
	}
}
