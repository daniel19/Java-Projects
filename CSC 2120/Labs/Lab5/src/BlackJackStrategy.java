import matrix.*;

/**
 *   Given a player's cards and the dealer's up card, this class finds the desired move 
 *   for a blackjack player based on the strategy given in a file (typically the optimal strategy).  
 */
public class BlackJackStrategy
{
   /** 
    *  A 2D array representing the optimal strategy read in from a file. 
    *  A 1 indicates a stand, a 2 indicates a hit, a 3 indicates a double, and a 4 indicates a split.
    */
   private BasicMatrixInterface strategy;

   /**
    *  Extracts the desired move (excluding splits) from the strategy array for hard and soft hands.
    *  Use the hand value to make it easier to identify the necessary row from the strategy array.
    *  Returns a 'S' if the correct play is to stand.
    *  Returns an 'H' if the correct play is to hit.
    *  Returns a 'D' if the correct play is to double.
    *  Hint: if the player's hand is soft, you need to add 7 to the player's hand value to get the row for the correct play.<br>
    *  Hint: if the dealer's up card is an Ace (which has value 1) you need to extract the correct play from column 9. <br>
    *  Note: the game (<b>BlackJackPlayer</b>) will interpret a 'D' as an 'H' if the hand cannot be doubled.
    *  This class only has the responsibility of reading from the matrix.
    */
   public char getMove(BlackJackHand player, BlackJackHand dealer) 
   {
      int up_card = dealer.upCard();
      int player_value = player.handValue();
      char optimal = 'S';

      int col = up_card;
     
      if(up_card == 1)//Dealer Ace card hint
         col = 9;


      //First determine if player hand is hard or soft
      if(player.isSoft()){
        player_value +=7;
        optimal = getAction(strategy.getElement(player_value, col));
      }else{
        optimal = getAction(strategy.getElement(player_value, col));
      }


      return optimal;
   }

    /**
     * Returns the Char from the selected value in matrix
     * @param val Value from strategy.getElement()
     * @return char optimal
     */
   public char getAction(double val){
       switch((int)val){
           case 1:
               return 'S';
           case 2:
               return 'H';
           case 3:
               return 'D';
           case 4:
               return 'P';
           default:
               return 'S';

       }
   }

   /**
    *  Determines if a split is the desired move if the first two cards in a hand have the same value.
    *  Use the (modified) hand value to make it easier to identify the necessary row from the strategy matrix.
    *  Returns a false if a split is not the correct play or true if a split is the correct play according to the strategy.
    */
   public boolean shouldSplit(BlackJackHand player, BlackJackHand dealer)
   {
      int up_card = dealer.upCard();

      boolean should_split = false;
      int row = player.handValue();
      int col = up_card;
      if(player.canSplit() && getAction(strategy.getElement(row, col)) == 'P'){
        //Determine strategy
        should_split = true;
      }

      return should_split;
   }

   /** Reads the strategy in from a file. */
   public BlackJackStrategy(String fileName)
   {
      strategy = MatrixCreator.create(39, 10);

      FileIO file = new FileIO(fileName, FileIO.FOR_READING);

      int rows = strategy.getNumRows();
      int cols = strategy.getNumColumns();

      for (int i = 1; i <= rows; i++)
      {
         for (int j = 1; j <= cols; j++)
         {
            String line = file.readLine();
            int strategy_int = Integer.parseInt(line);
            strategy.setElement(i, j, strategy_int);
         }
      }
      
      file.close();
   }
}
