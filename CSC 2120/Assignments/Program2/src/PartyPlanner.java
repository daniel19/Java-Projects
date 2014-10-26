import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
/**
 *PartyPlanner is a class that manages multiple party objects 
 *   organizing them in a HashMap
 *   @author Daniel Brown
 *   @version Oct. 24, 2014
 */
public class PartyPlanner implements Serializable, Statable{
   private static Comparator[] comps;
   private String fileStatus;
   private String plannerName;
   private HashMap<String, Party> parties;
   private static final long serialVersionUID= 1L;

   public PartyPlanner(){
        parties = new HashMap<String, Party>();
        comps = new Comparator[0];
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
               System.out.println(e.toString());
               System.out.println("Can't cast to HashMap"); 
             }
            oInput.close();
           }catch(Exception ex){
                System.out.println("File Object Error");
                    return;
           }
       }else{
            //Create a FileIO object to read in text file.
            FileIO input = new FileIO(filename,",");
            String firstLine = input.readLine();
            while(!input.EOF()){
              //Get each word from line 
              Iterator<String> words = input.getTokens();
              ArrayList<String> wordList = new ArrayList();
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
   public double getAveageCostPerPerson(){}
   
   /**
    * Return value of instance variable  fileStatus
    * @return fileStatus - String
    */
   public String getFileStatus(){return fileStatus;}
   public int getMaxGuests(String name, String date){
       return  parties.get(name+date).getMaxGuests();
   }
   public int getNumAttending(String name, String date){
       return  parties.get(name+date).getNumAttending();
   }
   public int getNumNotAttending(String name, String date){
       return  parties.get(name+date).getNumNotAttending();
   }
   public int getNumUnkown(String name, String date){
       return  parties.get(name+date).getNumNotAttending();
   }
   public boolean takeRegret(String who, String name, String date){}
   public boolean takeAccept(String who, String name, String date){}
   public String toString(){}
   public String pay(String name, String date){}
   public String pay(String name, String date, int numMonths){}
   public boolean invite(String who, String partyName, String date){}
   public String inviteToAll(String who){}
   public String getWhosInvited(String who){}
   public boolean updatePrice(String name, String date, int percent){}
   public void setPaymentStatus(String name, String date, boolean isPaid){}
   public String getState(){}
   
   /**
    *Writes parties to specified text file.
    *@param filename - String
    *@return nothing
    */
   public void writeToFile(String filename){
       FileIO out = new FileIO(filename, FileIO.FOR_WRITING);
  //     for(Map.Entry<String, Party> entry: parties.entrySet())
  //          out.writeLine(entry.toString());
       out.writeLine(this.toString());
       out.close(); 
   }
   /**
    *Writes to object to specified file.
    *@param  filename - String 
    *@return nothing
    */
   public void writeToObjectFile(String filename){
       try{
           ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
           out.writeObject(parties);
           out.close();
       }catch(Exception  e){
           System.out.println(e.toString());
           System.out.println("Could not write to file " + filename + "."); 
       }
   }
}
                                         
