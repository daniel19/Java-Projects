package problems.easy.algorithms;

/**
 *Checks to see if there is an element in an array such that the sum of elements
 * on the left equals to the sum of elements on the right. 
 */

public final class Sherlock{
    private int[] array; 

    public Sherlock(int[] array){
        this.array = array;
    }
    
    public boolean appeaseWatson(){
        return helpWatson(1);
    }
    
    private boolean helpWatson(int position){
        int sumLeft = 0, sumRight = 0;
        for(int i =0; i < position-1; i++){
            sumLeft = sumLeft + array[i];
        }

        for(int j = position+1; j < array.length; j++){
            sumRight = sumRight + array[j];
        }
        
        if(sumLeft == sumRight){
            return true;
        }else if(position > array.length){
            return false;
        }
        return position == array.length ? false:helpWatson(++position);
    }
}
