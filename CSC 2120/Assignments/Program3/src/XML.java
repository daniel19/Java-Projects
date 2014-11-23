import java.util.Stack;
public class XML{
    boolean ready;
    private int openTags;
    private int closedTags;
    private Stack<String> tagStack;
    public XML(boolean ready){
        this.ready = ready;
        tagStack = new Stack<String>();
        openTags = 0;
        closedTags = 0;
    }   

   public void incrementClosedCount(){
        closedTags++;
   } 
   public void incrementOpenCount(){
        openTags++;
   } 
   public void addToList(String tag){
       tagStack.add(tag);
   }

   public String removeFromList(){
       return tagStack.pop();
   } 
}
