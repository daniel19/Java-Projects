import java.util.Comparator;
import java.util.Locale;
import java.text.Collator;
public class HostComparator implements Comparator<Party>{
    private static final Collator collator = Collator.getInstance(Locale.US);
    public int compare(Party s1, Party s2){
        return collator.compare(s1.getHost(),s2.getHost());
    }
}
