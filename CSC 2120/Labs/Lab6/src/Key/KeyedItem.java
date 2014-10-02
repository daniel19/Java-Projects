package Key;

/**
 *KeyedItem.java
 * Abstract Key that creates Comparable keys of <T> for classes that 
 *  extend it. 
 */
public abstract class KeyedItem{
   final Comparable key; 
   
   public KeyedItem(Comparable k){
        key = k;
   }

    public Comparable<KeyedItem> getKey(){
        return key;
    }

}
