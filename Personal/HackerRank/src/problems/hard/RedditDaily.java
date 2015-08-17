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
            
            for(char character : gameWord.toCharArray()){
                if(character == characters[position])
                   position++;
                if(secretWord.contains(String.valueOf(character)) && charMap.containsKey(String.valueOf(character))){
                    System.out.print(character + " ");
                    int value = charMap.get(String.valueOf(character)) + 1;
                    charMap.put(String.valueOf(character), value);
                }else if(secretWord.contains(String.valueOf(character))){
                    System.out.print(character + " ");
                    charMap.put(String.valueOf(character), 1);
                }
            } 
            
            if(checkPositions(charMap))
                return false;
            return position == secretWord.length() ? true : false;
        }

        private boolean checkPositions(Map<String, Integer> map){
            for(Map.Entry<String, Integer> pair : map.entrySet()){
                if(pair.getValue() != secretMap.get(pair.getKey()))
                    return true;    
            }
            return false;
        }
    }
}
