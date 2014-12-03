package party;

import java.util.*;
public class PartyHostComparator implements Comparator<Party> {
    public int compare(Party p1, Party p2) {  // ascending
        return p1.getHost().compareToIgnoreCase(p2.getHost());
    }
}