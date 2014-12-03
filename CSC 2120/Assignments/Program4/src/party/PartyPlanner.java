package party; /**
 * Write a description of class party.PartyPlanner here.
 * 
 * @author Martha Kosa 
 * @version 10/01/2014
 */
import java.io.*;
import java.util.*;

public class PartyPlanner implements Serializable, Statable
{
    
   private HashMap<String, Party> parties;
   private String plannerName;
   private static Comparator[] comps = {new PartyHostComparator(), new PartyDateComparator(), new PartyMaxGuestsComparator(),
                                        new PartyAttendanceComparator(), new PartyCostComparator()};
   private String fileStatus;
   
   public PartyPlanner() throws PartyPlannerException
   {
       plannerName = "DEFAULT";
       fileStatus = "No file read.";
       loadParties();
   }

   public PartyPlanner(String fileName, boolean isObjectFile) throws PartyPlannerException
   {
       fileStatus = loadParties(fileName,isObjectFile);
   }
   
   public PartyPlanner(String fileName) throws PartyPlannerException
   {
       this(fileName, false);
   }
   
   private void loadParties() throws PartyPlannerException
   {
       parties = new HashMap<String, Party>();
       
       addToParties("Graduation", "Joe Smith", "05/12/08", "Sherlock Park", 20,
                    true, 300);
       addToParties("Super Bowl", "Ann Jones", "02/03/08", "123 Main Street", 10, false,
                    20);
       addToParties("Fourth of July", "Tina Lucci", "07/04/08", "47 Elm Avenue", 40, true,
                    400);
       addToParties("Wedding", "Sam Snead", "02/12/08", "Country Club", 100, false, 50);
       addToParties("April Fools", "Rose Kennedy", "04/01/08", "Beach", 8, true, 1999.99);
   }

   private String loadParties(String fileName, boolean isObjectFile) throws PartyPlannerException
   {
       StringBuffer infoText = new StringBuffer("");
       try
       {
           if (isObjectFile)
           {
               FileInputStream fis = new FileInputStream(fileName);
               ObjectInputStream ois = new ObjectInputStream(fis);
               plannerName = (String) ois.readObject();
               parties = (HashMap<String, Party>) ois.readObject();
               infoText.append("party.PartyPlanner successfully read from object file " + fileName);
           }
           else
           {
               parties = new HashMap<String, Party>();
        
               FileIO br = new FileIO(fileName, FileIO.FOR_READING);
               String line = br.readLine();
               setPlannerName(line); // first line is the party planner name
               line = br.readLine();
               while (line != null)
               {
                  infoText.append("Current line: " + line + "\r\n");
                  try
                  {
                     infoText.append(processLine(line));
                  }
                  catch (Exception e)
                  {
                    infoText.append("Problem " + e.getMessage() + " with " + line + "\r\n");
                  }
                  line = br.readLine();
               }
           }
       }
       catch (FileIOException | IOException | ClassNotFoundException e)
       {
           throw new PartyPlannerException(e.getMessage());
       }
       return infoText.toString();
   }

   public String getFileStatus()
   {
       return fileStatus;
   }
   
   private String processLine(String line) throws PartyPlannerException
   {
      StringBuffer infoText = new StringBuffer("");
      String[] tokens = line.split(",");
      String partyName = tokens[0];
      String hostName = tokens[1];
      String date = tokens[2];
      String loc = tokens[3];
      int maxGuests = Integer.parseInt(tokens[4]);
      double price = Double.parseDouble(tokens[5]);
      boolean isPerParty = Boolean.parseBoolean(tokens[6]);
      boolean isPaidFor = Boolean.parseBoolean(tokens[7]);
      boolean success = addToParties(partyName, hostName, date, loc, maxGuests,
                                             isPerParty, price);
      if (!success)
      {
          infoText.append(partyName + " on " + date + " not added.\r\n");
          return infoText.toString();
      }
      infoText.append(partyName + " on " + date + " added.\r\n");
      int numAccepted = Integer.parseInt(tokens[8]);
      for (int i = 9; i < numAccepted + 9; i++)
      {
          infoText.append("Inviting and accepting " + tokens[i] + ".\r\n");
          invite(tokens[i].trim(), partyName, date);
          takeAccept(tokens[i].trim(), partyName, date);
      }
      int numRegretted = Integer.parseInt(tokens[9 + numAccepted]);
      for (int i = 10 + numAccepted; i < 10 + numAccepted + numRegretted; i++)
      {
          infoText.append("Inviting and regretting " + tokens[i] + ".\r\n");
          invite(tokens[i].trim(), partyName, date);
          takeRegret(tokens[i].trim(), partyName, date);
      }
      int numUnknown = Integer.parseInt(tokens[10 + numAccepted + numRegretted]);
      for (int i = 11 + numAccepted + numRegretted; i < 11 + numAccepted + numRegretted + numUnknown; i++)
      {
          infoText.append("Inviting " + tokens[i] + ".\r\n");
          invite(tokens[i].trim(), partyName, date);
      }
      infoText.append("Setting payment status to " + isPaidFor + ".\r\n");
      setPaymentStatus(partyName, date, isPaidFor);
      return infoText.toString();
   }
    
   public boolean addToParties(String name, String host, String date, String location, int maxGuests,
                               boolean perParty, double price) throws PartyPlannerException
   {   
       boolean result = false;
       Party party = findParty(name, date);
       if (party == null)
       {
            result = true;
            try
            {
               party = new Party(name, host, date, location, maxGuests, perParty, price);
               addToParties(party);
            }
            catch (PartyException pe)
            {
                throw new PartyPlannerException(pe.getMessage());
            }
       }
       return result;
   }

    private void addToParties(Party theParty)
    {
        parties.put(theParty.getKey(), theParty);
    }

   public String sort(int field, int alg)
   {
       Collection<Party> allParties = parties.values();
       Party[] allPartiesArray = allParties.toArray(new Party[0]);
       Comparator<Party> currComp;
       if (field >= 1 && field <= comps.length)
       {
           currComp = (Comparator<Party>) comps[field - 1];
       }
       else
       {
           currComp = (Comparator<Party>) comps[0];
       }
       if (alg == 1)
       {
            Utilities.<Party> selectionSort(allPartiesArray, currComp);
       }  
       else if (alg == 2)
       {
            Utilities.<Party> insertionSort(allPartiesArray, currComp);
       }
       else
       {
            Utilities.<Party> bubbleSort(allPartiesArray, currComp);
       }
       
       StringBuffer result = new StringBuffer("");
       for (Party party: allPartiesArray)
       {
           result.append(party + "\r\n");
       }
       return result.toString();
   }
   
   public String getPlannerName()
   {
       return plannerName;
   }

   public void setPlannerName(String name)
   {
       plannerName = name;
   }
   
   public String getUnpaidParties()
   {
       String result = "Unpaid parties:\r\n";
       Collection<Party> allParties = parties.values();
       for (Party theParty: allParties)
       {
            if (!theParty.isPaid())
            {
                result += theParty.getHost() + "'s party on " + theParty.getDate() + "\r\n";
            }
       }
       return result;
   }
   
   public String getPaidParties()
   {
       String result = "Paid Parties:\r\n";
       Collection<Party> allParties = parties.values();
       for (Party theParty: allParties)
       {
            if (theParty.isPaid())
            {
                result += theParty.getHost() + "'s party on " + theParty.getDate() + "\r\n";
            }
       }
       return result;

   }
   
   public double getAverageCostPerPerson()
   {
       double avgCost = 0;
       double totalCost = 0;
       int totalNumAttending = 0;
       Collection<Party> allParties = parties.values();
       for (Party theParty: allParties)
       {
            if (theParty.isPaid())
            {
                totalNumAttending += theParty.getNumAttending();
                totalCost += theParty.getCost();
            }
       }
       if (totalNumAttending != 0)
       {
           avgCost = totalCost / totalNumAttending;
       }
       return avgCost;
   }
   
   private Party findParty(String name, String date)
   {
       return parties.get(name + date);
   }
   
   public int getMaxGuests(String name, String date)
   {
       int max = 0;
       Party party = findParty(name, date);
       if (party != null)
       {
           max = party.getMaxGuests();
       }
       return max;
   }

   public int getNumAttending(String name, String date)
   {
       int num = -1;
       Party party = findParty(name, date);
       if (party != null)
       {
           num = party.getNumAttending();
       }
       return num;
   }

   public int getNumNotAttending(String name, String date)
   {
       int num = -1;
       Party party = findParty(name, date);
       if (party != null)
       {
           num = party.getNumNotAttending();
       }
       return num;
   }
   
   public int getNumUnknown(String name, String date)
   {
       int num = -1;
       Party party = findParty(name, date);
       if (party != null)
       {
           num = party.getNumUnknown();
       }
       return num;
   }
   
   public boolean takeRegret(String who, String name, String date)
   {
       boolean result = false;
       Party party = findParty(name, date);
       if (party != null)
       {
           result = party.takeRegret(who);
       }
       return result;
   }

   public boolean takeAccept(String who, String name, String date)
   {
       boolean result = false;
       Party party = findParty(name, date);
       if (party != null)
       {
           result = party.takeAccept(who);
       }
       return result;
   }
   
   public String toString()
   {
       String result = "party.Party Planner: " + plannerName + "\r\nwith the following:\r\n";
       Collection<Party> allParties = parties.values();
       for (Party party: allParties)
       {
            result += "\r\n****\r\n" + party;
       }
       return result;
   }
   
   public String pay(String name, String date)
   {
      return pay(name, date, 1);
   }
   
   public String pay(String name, String date, int numMonths)
   {
       String result = "Unknown party.Party: " + name + " on " + date;
       Party party = findParty(name, date);
       if (party != null)
       {
           result = party.pay(numMonths);
       }
       return result;
   }
   
   public boolean invite(String who, String partyName, String date)
   {
       boolean result = false;
       Party party = findParty(partyName, date);
       if (party != null)
       {
           result = party.invite(who);
       }
       return result;
   }
   
   public String inviteToAll(String who)
   {
       StringBuffer result = new StringBuffer(who);
       Collection<Party> allParties = parties.values();
       if (allParties.size() > 0)
       {
           for (Party party : allParties)
           {
               result.append("\r\ninvited to party " + party.getName() + " on " +
                        party.getDate() + "? " + party.invite(who));
           }
       }
       else
       {
           result.append(" invited to no parties");
       }    
       return result.toString();
   }
   
   public String getWhosInvited(String name, String date)
   {
       String result = "party.Party unknown";
       Party party = findParty(name, date);
       if (party != null)
       {
           result = party.getWhosInvited();
       }
       return result;
   }
   
   public boolean updatePrice(String name, String date, int percentage)
   {
       boolean result = false;
       Party party = findParty(name, date);
       if (party != null)
       {
           result = party.updatePrice(percentage);
       }
       return result;
   }

   public void setPaymentStatus(String name, String date, boolean isPaid)
   {
       Party party = findParty(name, date);
       if (party != null)
       {
           party.setPaid(isPaid);
       }
   }
   
   public String getState()
   {
       StringBuffer result = new StringBuffer(plannerName + "\r\n");
       Collection<Party> allParties = parties.values();
       for (Party party: allParties)
       {
           result.append(party.toFileString()+ "\r\n");
       }
       return result.toString();
   }
   
   public void writeToFile(String fileName) throws PartyPlannerException
   {
       try
       {
           FileIO fw = new FileIO(fileName, FileIO.FOR_WRITING);
           fw.writeLine(getState());
           fw.close();
       }
       catch (FileIOException fioe)
       {
           throw new PartyPlannerException(fioe.getMessage());
       }
   }
   
   public void writeToObjectFile(String fileName) throws PartyPlannerException
   {
       try
       {
          FileOutputStream fos = new FileOutputStream(fileName);
          ObjectOutputStream oos = new ObjectOutputStream(fos);
          oos.writeObject(plannerName);
          oos.writeObject(parties);
          fos.close();
       }
       catch (IOException ioe)
       {
          throw new PartyPlannerException("Problem writing information to file " + fileName);
       }
   }
}