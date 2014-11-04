import java.util.Comparator;
public class CostComparator implements Comparator<Party>{
    public int compare(Party c1, Party c2){
        if(c1.getCost() > c2.getCost()) return 1;
        if(c1.getCost() < c2.getCost()) return -1;
        return 0;
    } 
}
