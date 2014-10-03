import java.lang.reflect.Array;
import java.util.Arrays;
import ki.KeyedItem;
import bst.*;

public class TreeSort{
    
    public static KeyedItem[] treeSort(KeyedItem[] unsorted){ //convenience method to sort all of the items in the array (calls the next method)
        return treeSort(unsorted, unsorted.length);
    }

    public static KeyedItem[] treeSort(KeyedItem[] unsorted, int n){ //sort the first n items in the array (check n for validity)
        Class ac = unsorted.getClass().getComponentType();
        KeyedItem[]  copy_array = (KeyedItem[])Array.newInstance(ac, unsorted.length); 
        copy_array = Arrays.copyOfRange(unsorted, 0, n); 
        BinarySearchTree bt = new BinarySearchTree(true, true);
        for(KeyedItem item : copy_array)
            bt.insert(item);
        TreeIterator btIterator = bt.iterator();
        btIterator.setInorder();
        int count = 0;
        while(btIterator.hasNext()){
           if(count <= n)
            copy_array[count] = (KeyedItem)btIterator.next();
          else
            copy_array[count] = unsorted[count];    
        }
        return copy_array;
    }


}
             
