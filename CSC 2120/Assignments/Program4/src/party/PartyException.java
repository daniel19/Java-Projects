package party;

public class PartyException extends Exception {
   public PartyException() {
       this("General party.Party Problem");
   }
   
   public PartyException(String s) {
       super(s);
   }
}