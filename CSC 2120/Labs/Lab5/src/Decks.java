/**
 *  Represents one or more decks of cards.
 */ 
public class Decks
{
   /** An array of Cards representing multiple complete decks of the standard 52 deck of cards. */
   private Card[] decks;  
   /**  The number of Cards remaining in the deck. */
   private int count;
   /**  The number of Decks. */
   private int numDecks;

   /**  The total number of Cards. */
   private final int SIZE; 
   /**  The size of a standard deck of cards. */
   public final int STANDARD_DECK_SIZE = 52;

   /**
    *  Constructor to create several copies of the standard 52 deck of cards and to shuffle them all together.
    */
   public Decks(int num)
   {
      if (num > 0 && num < 10)
      {
         numDecks = num;
      }
      else
      {
         numDecks = 1;
      }

      SIZE =  STANDARD_DECK_SIZE * numDecks;
      decks = new Card[SIZE];

      int count = 0;
      for (int i = 0; i < numDecks; i++)
      {
         //DO THIS (create the cards, add to the decks)
         //Note from the Card class that integers from 1 to 52 passed to the Card constructor will create the corresponding Card
         //you will need a counter to insert the Cards into the decks array

          for(int j =1; j <=52; j++){
            decks[(52*count)+j-1] = new Card(j);
          }
          count++;
      }

      //DO THIS
      //mix them up
      shuffle();
      //printArray(decks);

   }


    public void printArray(Card[] d){
        int count = 1;
        for( Card c : d){
            if (c != null){
                System.out.println("Card " + count + " :" + c.toString());
                count++;
            }
        }
    }
   /**
    *  Shuffles the Cards by selecting a random location for each Card.
    *  Create a new array of Cards.  
    *  Move each Card in the original Deck to a random location in the new Deck.
    *  Set count so that all of the cards are available again.
    */
   public void shuffle()
  {  
      //Copy of decks
      Card[] shuffle_deck = decks.clone();

      //use the Permutation class to shuffle the Cards
       Permutation p = new Permutation(SIZE,SIZE-1);
       for(int i = 0; i < SIZE-1; i++){
           //DEBUG - PRINT
           int val = p.next();
           decks[i] = shuffle_deck[val];
       } 
      
      count = SIZE - 1;  //the entire deck is available again
   }

   /**
    *  Returns the next Card in the Deck.  If the Deck is empty, the Deck is shuffled.
    *  Use your count instance variable to determine which Card to return.
    *  Decrement count.
    */
   public Card deal()
   {
      Card current;

      if (count == -1)
      {
         shuffle();
      }
      current = decks[count];
      count = count - 1;

      return current;
   }

   /**
    *  Returns the number of Cards remaining in the Deck.
    */
   public int getCount()
   {
      return count + 1;
   }

   /**
    *  Returns the number of Decks.
    */
   public int getNumDecks()
   {
      return numDecks;
   }

   /**
    *  Lists the Cards remaining in the Deck.
    */
   public String toString()
   {
      String temp = "";

      for (int i = count; i >= 0; i--)
      {
         temp += decks[i] + "\r\n";
      }

      return temp;
   }
}
