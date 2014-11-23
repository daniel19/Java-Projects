/**
 * Controller class for our XML states
 */
public class XMLController{
    private ProcessClose close;
    private ProcessOpen open;
    private DetectTag detection;
    private EndBetweenState endBetween;
    private BetweenState between;
    private StartState starting;
    private XMLState current;
    
    XML tags;

    private FileIO fileOperator;
    /**Public Constructor**/
    public XMLController(String filename){
        close = new ProcessClose(this);
        open = new ProcessOpen(this);
        detection = new DetectTag(this);
        starting = new StartState(this);
        endBetween = new EndBetweenState(this);
        between = new BetweenState(this);
        current = starting;
        tags = new XML(true);
        fileOperator = new FileIO(filename, FileIO.FOR_READING);
    }

    //State methods
    public void setNextState(XMLState state){
        current = state;
    }

    public ProcessOpen getOpenState(){ return open; }
    public ProcessClose getCloseState(){ return close;}
    public DetectTag getDetectState(){return detection;}
    public StartState getStartState(){return starting;}
    public BetweenState getBetweeState(){return between;}
    public EndBetweenState getEndBetweenState() {return endBetween;}
    
    public void pushTagOntoStack(String tag){
        tags.addToList(tag);
    }
    public void incrementTags(String selector){
        if(selector == "open"){
            tags.incrementOpenCount();
        }else if(selector == "close"){
            tags.incrementClosedCount();
        }
    }
    public void readFile(){
       while(!fileOperator.EOF()){
            String line = fileOperator.readLine();
            for(int i=0, n = line.length(); i < n; i++){
                char lineCharacter = line.charAt(i);
                if(current.detectLeftAngle(lineCharacter)){
                    //break from loop iteration
                }else if(current.detectForwardSlash(lineCharacter)){
                    //break from loop iteration
                }else if(current.detectRightAngle(lineCharacter)){
                   //break from loop iteration
                }else if(current.detectCharacters(lineCharacter)){
                   //break from loop iteration
                }
            }
       }         
    }
}
