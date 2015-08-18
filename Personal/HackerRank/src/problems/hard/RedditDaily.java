package problems.hard;

import java.util.HashMap;
import java.util.Map;

public class RedditDaily{
    private RedditDaily(){
        throw new AssertionError();
    }
        
    public static class Fortune{
        private String gameWord;
        private String secretWord;
        private Map<String, Integer> secretMap = new HashMap<>();

        public Fortune(String gameWord, String secretWord){
            this.gameWord = gameWord;
            this.secretWord = secretWord;
            for(char character : secretWord.toCharArray()){
                String c = String.valueOf(character);
                if(secretMap.containsKey(c)){
                    int value = secretMap.get(c) + 1;
                    secretMap.put(c, value);
                }else{
                    secretMap.put(c, 1);
                }
            }
        }

        public boolean analyze(){            
            int position = 0;
            char[] characters = secretWord.toCharArray();
            Map<String, Integer> charMap = new HashMap<>();
           
            if(gameWord.length() < secretWord.length())
               return false;

            for(char character : gameWord.toCharArray()) {
                if(secretWord.contains(String.valueOf(character)) && charMap.containsKey(String.valueOf(character))){
                    int value = charMap.get(String.valueOf(character)) + 1;
                    charMap.put(String.valueOf(character), value);
                }else if(secretWord.contains(String.valueOf(character))){
                    charMap.put(String.valueOf(character), 1);
                }
                if(position == secretWord.length()){
                    return true;
                }else if(character == characters[position]) {
                    position++;
                }
            } 
            
            if(checkPositions(charMap))
                return false;

            return position == secretWord.length();
        }

        private boolean checkPositions(Map<String, Integer> map){
            for(Map.Entry<String, Integer> pair : map.entrySet()){
                if(pair.getValue().equals(secretMap.get(pair.getKey())))
                    return true;    
            }
            return false;
        }
    }
}
