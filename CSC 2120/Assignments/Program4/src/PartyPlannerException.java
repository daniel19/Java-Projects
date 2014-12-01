public class PartyPlannerException extends Exception {
   public PartyPlannerException() {
       this("General Party Planner Problem");
   }
   
   public PartyPlannerException(String s) {
       super(s);
   }
}