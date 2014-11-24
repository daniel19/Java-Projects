import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
/**
 * DateComparator implements Comparator to compare the two Party objects. 
 */
public class DateComparator implements Comparator<Party>{
    private static SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
    public int compare(Party d1, Party d2){
        format.setLenient(false);
        Date one, two;
        try{
            one = format.parse(d1.getDate());
            two = format.parse(d2.getDate());
        }catch(ParseException e){
            System.out.println("\nInvalid date found\n");
            return 0;
        }catch(IllegalArgumentException e){
            System.out.println("\nInvalid date found\n");
            return 0;
        }
        return one.compareTo(two); 
    }
}
