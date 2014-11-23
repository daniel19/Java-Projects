/**
 * State interface to operate different XML states.
 * XMLState.java 
 */
public interface XMLState{
    /** Checks to see if a character is '<' **/
    public boolean detectLeftAngle(char character);
    /** Checks to see if a character is '>' **/
    public boolean detectRightAngle(char character);
    /** Checks to see if a character is '/' **/
    public boolean detectForwardSlash(char character);
    /** Checks the other characters that could be within a tag**/
    public boolean detectCharacters(char character);
}
