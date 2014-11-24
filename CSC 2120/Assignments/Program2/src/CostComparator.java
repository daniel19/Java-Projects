import java.util.Comparator;
/**
 * CostComparator implements Comparator to compare the two Party objects. 
 */
public class CostComparator implements Comparator<Party>{
    public int compare(Party c1, Party c2){
        if(c1.getCost() > c2.getCost()) return 1;
        if(c1.getCost() < c2.getCost()) return -1;
        return 0;
    } 
}
