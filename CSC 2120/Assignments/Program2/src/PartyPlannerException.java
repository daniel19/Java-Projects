/**
 * PartyPlannerException: a checked exception subclass that throws exceptions for PartyPlanner.
 * @author Daniel Brown
 */

@SuppressWarnings("serial")
public class PartyPlannerException extends Exception{
    public PartyPlannerException(){
        super();
    }
    public PartyPlannerException(String message){
        super(message);
    }
}
