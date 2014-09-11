import java.util.PriorityQueue;
//import java.util.Comparator;

/**
 *Main Driver for sorting a users CDs
 *@author Daniel Brown
 */

public class PQDriver{
//    public static Comparator<String> comparator; 
    public static PriorityQueue<CD> pq = new PriorityQueue<CD>();

    public static void main(String[] args){
         
        insertCD(pq, createCD("The Gallery", "Dark Tranquillity")); 
        insertCD(pq, createCD("Since the Day It All Came Down", "Insomnium")); 
        insertCD(pq, createCD("If Emotions Still Burn", "Ablaze My Sorrow")); 
        insertCD(pq, createCD("Swamplord", "Kalmah")); 
        insertCD(pq, createCD("Towards the Twilight", "Night in Gales")); 
        insertCD(pq, createCD("An Epic Defiance", "Detonation")); 
        insertCD(pq, createCD("From Your Grave", "The Absence")); 
        insertCD(pq, createCD("Inhumanity", "Mors Principium Est")); 
        insertCD(pq, new CD("The Gallery", "Dark Tranquillity", 13.99, 11)); //a "duplicate" with a different value and tracks 
        insertCD(pq, createCD("Timeless Departure", "Skyfire")); 
        insertCD(pq, createCD("Spirits and August Light", "Omnium Gatherum")); 
        insertCD(pq, createCD("Slaughter of the Soul", "At the Gates")); 
        insertCD(pq, createCD("Shadows and Dust", "Kataklysm")); 
        insertCD(pq, createCD("Fate of Norns", "Amon Amarth")); 
        insertCD(pq, createCD("Corroding From Inside", "Searing Meadow")); 
        insertCD(pq, createCD("The Glorious Burden", "Iced Earth")); 
        insertCD(pq, createCD("Stained", "Imperanon")); 
        insertCD(pq, createCD("As Night Conquers Day", "Autumn Leaves")); 
        
        //Pop(poll) off each element in the queue
        while(pq.size() > 0){
            System.out.println("CD: " + pq.poll().toString());
        }
    }
    /**
     * Returns a new instance of CD
     * @param title - String representation of a CD's title
     * @param artist - String representation of album artist
     * @return CD Objet
     */
    public static CD createCD(String title, String artist){
        return new CD(title, artist, 14.99, 10);
    }
    /**
     * Inserts a CD into the queue.
     * @param p - PriorityQueue object that holds CDs
     * @param artist - CD object to be inserted into the queue
     */

    public static void insertCD(PriorityQueue<CD> p, CD cd){
        p.offer(cd);
    }
}
