import java.io.*;
import java.util.*;
import java.io.Serializable;
/**
 *PartyPlanner is a class that manages multiple party objects 
 *   organizing them in a HashMap
 *   @author Daniel Brown
 *   @version Oct. 24, 2014
 */
public class PartyPlanner implements Serializable, Statable{
   public static HostComparator hostComparator = new HostComparator();
   public static DateComparator dateComparator = new DateComparator();
   public static CostComparator costComparator = new CostComparator();
   private Comparator[] comps;
   private String fileStatus;
   private String plannerName;
   private HashMap<String, Party> parties;
   private static final long serialVersionUID= 1L;

   public PartyPlanner(){
        parties = new HashMap<String, Party>();
        comps = new Comparator[] {hostComparator, dateComparator, costComparator};
        plannerName = "Vacancy";
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
       parties = new HashMap<String, Party>();
       if(isObjectFile){
           try{
            ObjectInputStream oInput = new ObjectInputStream(new FileInputStream(filename));
            String firstline = (String)oInput.readObject();
            plannerName = firstline; 
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
            plannerName = firstLine;
            while(!input.EOF()){
              //Get each word from line 
              Iterator<String> words = input.getTokens();
              ArrayList<String> wordList = new ArrayList<String>();
              while(words.hasNext()){
                //Add returned line to parties hashmap 
                wordList.add(words.next());
              }
              if(wordList.size() <= 1){
                break;
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
    public void setName(String s){
        plannerName = s;
    }
    /**
     * Adds a party object to the hashmap.
     * @param theParty
     */
   private void addToParties(Party theParty){
     parties.put(theParty.getName() + theParty.getDate(), theParty); 
   }

    public String sort(int fieldPosition, int algorithm){
        ArrayList<Party> pList = new ArrayList<Party>(parties.values());
        Party[] pArray = new Party[0];
        pArray= pList.toArray(pArray);
        switch(algorithm){
            case 2:
                Utilities.insertionSort(pArray, comps[fieldPosition]);
            case 1:
                Utilities.selectionSort(pArray, comps[fieldPosition]);
            default:
                break;    
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Party p : pArray){
            stringBuilder.append(p.toString() + "\n\n");
        }
        return stringBuilder.toString();
    }
   public boolean addToParties(String name, String host, String date, String loc, int maxGuests, double price, boolean perParty) throws PartyPlannerException{
        Party p;
        try{
            p  = new Party(name, host, date, loc, maxGuests, perParty, price);
        }catch (PartyException e){
            throw new PartyPlannerException("Duplicate found");
        }
       addToParties(p);
       return true;
   }

    /**
     * Helper method to get items out of the hashmap.
     * @param name
     * @param date
     * @return
     */
   private Party findParty(String name, String date){
        return parties.get(name + date);
   }
   /**
    * Takes takes the average cost for all the parties in the HashMap.
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
    */
    public int getNumNotAttending(String name, String date){
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
            //sb.append(p.getState());
            sb.append(p.toString() + "\n\n");
        }
        return sb.toString();
   }
   public String getPaidParties(){
       StringBuilder sb = new StringBuilder(); 
       for(Party p : parties.values()){
          if(p.isPaid()) sb.append(p.toString());
       }
    return sb.toString();
   } 
   public String getUnpaidParties(){
       StringBuilder sb = new StringBuilder(); 
       for(Party p : parties.values()){
          if(!p.isPaid()) sb.append(p.toString());
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
           out.writeLine(this.toString());
           out.close();
       }catch(Exception e){
            throw new PartyPlannerException("Unable to write file: " + e.getMessage());
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
                                         
