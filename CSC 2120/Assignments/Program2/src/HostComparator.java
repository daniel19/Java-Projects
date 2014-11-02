import java.util.Comparator;
import java.util.Locale;
import java.text.Collator;
public class HostComparator implements Comparator<String>{
    private static final Collator collator = Collator.getInstance(Locale.US);
    public int compare(String s1, String s2){
        return collator.compare(s1,s2);
    }
}
