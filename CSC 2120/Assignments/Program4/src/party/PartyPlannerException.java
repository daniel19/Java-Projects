package party;

public class PartyPlannerException extends Exception {
   public PartyPlannerException() {
       this("General party.Party Planner Problem");
   }
   
   public PartyPlannerException(String s) {
       super(s);
   }
}