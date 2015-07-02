package problems.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class is used to test the 3n+1 problem from UVa Online Judge.
 */

public class Kelly{
    private int i, j;
    private List<Integer> intList = new ArrayList<>();
    
    public Kelly(int i, int j){
        this.i = i;
        this.j = j;
    }

    private int returnSamples(){
        List<Integer> lengths = new ArrayList<>();
        while(this.i <= this.j){
            if(intList.size() == 0){
                problem(i);
            }else{
                lengths.add(intList.size());
                intList.removeAll(intList);
            }
            i++;
        }
        return Collections.max(lengths);
    }

    public int getCycleLength(){
        return returnSamples();
    }

    private void problem(int n){
        //System.out.println(n);
        intList.add(n);
        if(n == 1){
            return;
        }else if(n % 2 != 0){
            problem(3*n+1);
        }else{
            problem(n/2);
        }
    }

}
