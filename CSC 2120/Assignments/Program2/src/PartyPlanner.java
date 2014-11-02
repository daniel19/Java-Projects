import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.HashMap;
import java.io.Serializable;
/**
 *PartyPlanner is a class that manages multiple party objects 
 *   organizing them in a HashMap
 *   @author Daniel Brown
 *   @version Oct. 24, 2014
 */
public class PartyPlanner implements Serializable, Statable{
   public static HostComparator hostComparator;
   public static DateComparator dateComparator;
   public static CostComparator costComparator; 
   private Comparator[] comps;
   private String fileStatus;
   private String plannerName;
   private HashMap<String, Party> parties;
   private static final long serialVersionUID= 1L;

   public PartyPlanner(){
        parties = new HashMap<String, Party>();
        comps = new Comparator[] {hostComparator, dateComparator, costComparator};
   }
   /**
    * Read appropriate date from a text file. This function is also
    * calls the overloaded constructor(string, boolean) if false
    * is given. 
    * @param filename a filename of text to read in.
    * @return PartyPlanner Object
    */
   public PartyPlanner(String filename) throws PartyPlannerException{
        this(filename, !filename.contains(".txt"));
   }
   /**
    *Called from overloaded Constructor. Determines if file is a text or 
    *  an object file.
    * @param filename a filename of text to read in.
    * @return PartyPlanner Object   
    */   
   public PartyPlanner(String filename, boolean isObjectFile) throws PartyPlannerException{
       Party p; 
       if(isObjectFile){
           try{
            ObjectInputStream oInput = new ObjectInputStream(new FileInputStream(filename));
            String firstline = (String)oInput.readObject();
             try{
                HashMap<String, Party> partys = (HashMap<String, Party>)oInput.readObject();
                parties.putAll(partys); 
             }catch(ClassCastException e){
               oInput.close();
               throw new PartyPlannerException("Can't cast to Hash Map: " + e.getMessage()); 
             }
            oInput.close();
           }catch(Exception ex){
                System.out.println("File Object Error:" + ex.getMessage());
                    return;
           }
       }else{
            //Create a FileIO object to read in text file.
            FileIO input = new FileIO(filename,",");
            String firstLine = input.readLine();
            while(!input.EOF()){
              //Get each word from line 
              Iterator<String> words = input.getTokens();
              ArrayList<String> wordList = new ArrayList<String>();
              while(words.hasNext()){
                //Add returned line to parties hashmap 
                wordList.add(words.next());
              } 
              p = new Party(wordList.get(0), wordList.get(1),
                           wordList.get(2), wordList.get(3),
                           Integer.parseInt(wordList.get(4)), Boolean.parseBoolean(wordList.get(6)),
                           Double.parseDouble(wordList.get(5)));
             addToParties(p);
            }
        }
        comps = new Comparator[] {hostComparator, dateComparator, costComparator};
   }

   private void addToParties(Party theParty){
     parties.put(theParty.getName() + theParty.getDate(), theParty); 
   }
   public boolean addToParties(String name, String host, String date, String loc, int maxGuests, double price, boolean perParty){
        Party p;
        try{
            p  = new Party(name, host, date, loc, maxGuests, perParty, price);
        }catch (PartyException e){
            return false;
        }
       addToParties(p);
       return true;
   }
   private Party findParty(String name, String date){
        return parties.get(name + date);
   }
   /**
    * Takes takes the average cost for all the parties in the HashMap.
    * @param none
    * @return average double of all party costs.
    */ 
   public double getAveageCostPerPerson(){
        int people =0;
        double sum =0;
        for(Party p : parties.values()){
            people += p.getNumAttending();
            sum += p.getCost();
        }
        return sum/people;
   }
   
   /**
    * Return value of instance variable  fileStatus
    * @return fileStatus - String
    */
   public String getFileStatus(){return fileStatus;}
    /**
     * Returns the max number of guests from the specified party object.
     * @param name - string title of party
     * @param date - string date of party
     * @return max number of guests.
     * /   
   public int getMaxGuests(String name, String date){
       return  parties.get(name+date).getMaxGuests();
   
   }   

   /**
    *Returns the number of guest attending a party.
    * @param name - name of a party
    * @param date - date of a party
    * @return number of guests at a party.
    */
   public int getNumAttending(String name, String date){
       return  parties.get(name+date).getNumAttending();
   }
    /**
    *Returns the number of guest not attending a party.
    * @param name - name of a party
    * @param date - date of a party
    * @return number of guests not at a party.
    */  public int getNumNotAttending(String name, String date){
       return  parties.get(name+date).getNumNotAttending();
   }
   /**
    *Returns the number of guest attending a party.
    * @param name - name of a party
    * @param date - date of a party
    * @return number of guests at a party.
    */public int getNumUnkown(String name, String date){
       return  parties.get(name+date).getNumNotAttending();
   }
    /**
    * A guest declines an invitation to a party.
    * @param name - name of a party
    * @param date - date of a party
    * @param who  - name of party guest.
    * @return a boolean is returned if guest was on the guest list.
    */  
   public boolean takeRegret(String who, String name, String date){
        Party p = findParty(name, date);
        return p.takeRegret(who);
   }
   /**
    * A guest accepts an invitation to a party.
    * @param name - name of a party
    * @param date - date of a party
    * @param who  - name of party guest.
    * @return a boolean is returned if guest was on the guest list.
    */
   public boolean takeAccept(String who, String name, String date){
       Party p = findParty(name, date);
       return p.takeAccept(who);
   }
    /**
     *A string representation of all the parties in the HashMap.
     * @return String of all parties.
     */
   public String toString(){
       StringBuilder sb = new StringBuilder();
       for(Party p: parties.values()){
            sb.append(p.toString());
       }
       return sb.toString();
   }
   /**
    *Returns a descriptive status of the monetary status of a party.
    * @param name of a party
    * @param date of a party
    * @return string description of the payment status.
    */
   public String pay(String name, String date){
        return findParty(name, date).pay();
   }
   /**
    * Overloaded pay() returns a descriptive status of the monetary status of a party.
    * @param name of a party
    * @param date of a party
    * @return string description of the payment status.
    */
   public String pay(String name, String date, int numMonths){
        return findParty(name, date).pay(numMonths);
   }
   
   public boolean invite(String who, String partyName, String date){
        return findParty(partyName, date).invite(who);
   }
   public String inviteToAll(String who){
       StringBuilder sb = new StringBuilder(); 
       for(Party p : parties.values()){
            sb.append(p.invite(who));
       }
       return sb.toString();
   }
   public String getWhosInvited(String name, String date){
       return findParty(name, date).getWhosInvited();
   }
   public boolean updatePrice(String name, String date, int percent){
        return findParty(name, date).updatePrice(percent);
   }
   public void setPaymentStatus(String name, String date, boolean isPaid){
        findParty(name, date).setPaid(isPaid);
   }
   public String getState(){
        StringBuilder sb = new StringBuilder();
        for(Party p : parties.values()){
            sb.append(p.getState());
        }
        return sb.toString();
   }
   
   /**
    *Writes parties to specified text file.
    *@param filename - String
    *@return nothing
    */
   public void writeToFile(String filename) throws PartyPlannerException{
       try{
           FileIO out = new FileIO(filename, FileIO.FOR_WRITING);
  //     for(Map.Entry<String, Party> entry: parties.entrySet())
  //          out.writeLine(entry.toString());
       out.writeLine(this.toString());
       out.close();
       }catch(Exception e){
              
       } 
   }
   /**
    *Writes to object to specified file.
    *@param  filename - String 
    *@return nothing
 * @throws PartyPlannerException 
    */
   public void writeToObjectFile(String filename) throws PartyPlannerException{
       try{
           ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
           out.writeObject(parties);
           out.close();
       }catch(Exception  e){
           throw new PartyPlannerException("Can't write object to " + filename + "." + e.getMessage());
       }
   }
}
                                         
