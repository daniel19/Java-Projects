package ki;

/**
 *KeyedItem.java
 * Abstract Key that creates Comparable keys of <KeyedItem> for classes that 
 *  extend it. 
 */
public abstract class KeyedItem {
   private final Comparable key; 
   
   public KeyedItem(Integer k){
        key = k;
   }

   public Comparable getKey(){
       return key;
   }
}
