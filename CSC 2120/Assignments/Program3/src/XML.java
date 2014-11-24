import java.util.ArrayList;
import java.util.Stack;

public class XML {
	boolean ready;
	private int openTags;
	private int closedTags;
	private Stack<String> tagStack;
	private ArrayList<String> elementList;

	public XML(boolean ready) {
		this.ready = ready;
		tagStack = new Stack<String>();
		elementList = new ArrayList<String>();
		openTags = 0;
		closedTags = 0;
	}

	public void incrementClosedCount() {
		closedTags++;
	}

	public void incrementOpenCount() {
		openTags++;
	}

	public void addToStack(String tag) {
		tagStack.add(tag);
	}

	public int stackSize() {
		return tagStack.size();
	}

	public String removeFromList() {
		return tagStack.pop();
	}

	public void addElementtoList(String element) {
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
			sb.append(s);
	    }
		return sb.toString();
	}
}
