import java.util.*;
public class TableSTBased implements TableInterface{
    AdaptableBinarySearchTree st; 
    int size;
    public TableSTBased(){
        st = new AdaptableBinarySearchTree();
        size=0;
    } 
    @Override
    public boolean tableIsEmpty(){
        return (size == 0);
    }

    @Override
    public int tableSize(){
        return size;
    }

    @Override
    public void tableInsert(KeyedItem item) throws TableException{
        try{
            st.insert(item);
        }catch( TreeException e){
            throw new TableException("Duplicate found");
        }
        size++;
    }

   @Override
   public boolean tableDelete(Comparable searchKey){
        try{
            st.delete(searchKey);
        }catch (TreeException e){
            return false;
        }
        size--;
        return true;
   } 
   @Override
   public KeyedItem tableRetrieve(Comparable searchKey){
       return  st.retrieve(searchKey);
   }
  
   /**
    * Sets and returns a level order traversal to check that 
    * the adaptable property is satisfied.
    */
   @Override
   public Iterator iterator(){
        TreeIterator stIterator = st.iterator();
        stIterator.setLevelorder();
        //stIterator.setInorder();
        return stIterator;
   }
}
