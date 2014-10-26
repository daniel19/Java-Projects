import java.util.ArrayList;
import java.util.List;

public class MasterMindAIConsistent implements MasterMindAI
{
   private MasterMind game;

   public MasterMindAIConsistent(MasterMind game)  
   {
      this.game = game;
   }

   //DO THIS
   //is the guess consistent with all previous results?
   //that is, if you compare your random guess to a previous guess, do you get the same number of black and white buttons?
   //don't just check your random guess against the secret guess until you get a good result
   private boolean analyzeGuess(Guess nextGuess)
   {
      List<Guess> guesses = game.getGuesses();  //get all previous guesses from the game
      int num_guesses = guesses.size();
      int result = 0;
      int[] prevArray = null;
      //loop over all previous guesses
      for (int i = 0; i < guesses.size(); i++)
      {
          if(i == 0)
              prevArray = guesses.get(i).reportResult(nextGuess);
          if(i > 0){
            //Compare previous and current
            int[] pegArray = guesses.get(i).reportResult(nextGuess);
            if(prevArray[0] == pegArray[0]){
                if(prevArray[1] == pegArray[1])
                    result++;
            }
            prevArray = pegArray;
          }

      }
      
      if(!(result == num_guesses))
        return false;
        
      return true;
   }

   //DO THIS
   public Guess nextGuess(int guess_id)
   {
      List<Guess> guesses = game.getGuesses();
      int num_guesses = guesses.size();
      Guess nextGuess = null;

      if (guesses.size() > 0)
      {
         Guess trialGuess = null;

         boolean good = false;
         //keep obtaining a random guess until you get one that works with all previous results
         //it is cheating to keep obtaining a random guess until you match the solution
         for(i = 0; i < num_guesses; i++){
            
         }

 
         nextGuess = trialGuess;  //found a good one
      }
      else
      {
         nextGuess = makeRandomGuess(guess_id);
      }

      return nextGuess;
   }
 
   //DO THIS
   //use the Random class to make a randomly generated guess
   private static Guess makeRandomGuess(int guess_id)
   {
      Guess randomGuess = new Guess(guess_id);
      Random random = Random.getRandomNumberGenerator();
      int newID = random.randomInt(1, guess_id);
      randomGuess = new Guess(newID);

      return randomGuess;
   }
}
