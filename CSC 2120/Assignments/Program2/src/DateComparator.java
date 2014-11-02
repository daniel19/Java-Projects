import java.util.Date;
import java.util.Comparator;
public class DateComparator implements Comparator<Date>{
    public int compare(Date d1, Date d2){
       return d1.compareTo(d2); 
    }
}
