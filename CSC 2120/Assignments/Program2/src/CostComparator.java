import java.util.Comparator;
public class CostComparator implements Comparator<Double>{
    public int compare(Double c1, Double c2){
        if(c1 > c2) return 1;
        if(c1 < c2) return -1;
        return 0;
    } 
}
