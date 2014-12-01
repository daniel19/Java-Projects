public class PartyException extends Exception {
   public PartyException() {
       this("General Party Problem");
   }
   
   public PartyException(String s) {
       super(s);
   }
}