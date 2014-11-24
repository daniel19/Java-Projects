import java.util.ArrayList;
import java.util.Stack;

public class XML {
	boolean ready;
	private Stack<String> tagStack;
	private ArrayList<String> elementList;

	public XML(boolean ready) {
		this.ready = ready;
		tagStack = new Stack<String>();
		elementList = new ArrayList<String>();
	}

	public void addToStack(String tag) {
        tagStack.add(tag);
    }

	public int stackSize() {
		return tagStack.size();
	}

	public String removeFromList() throws XMLParseException {
		if(tagStack.empty())
            throw new XMLParseException("The number of open and closed tags do not match.");
        return tagStack.pop();
	}

	public void addElementToList(String element) {
		elementList.add(element);
	}

	public String getElementFromList(int position) {
		return elementList.get(position);
	}

	public ArrayList<String> getList() {
		return elementList;
	}
	
	public String stackString(){
		StringBuilder sb = new StringBuilder();
		for(String s : tagStack){
			sb.append(s + "\n");
	    }
		return sb.toString();
	}
}
