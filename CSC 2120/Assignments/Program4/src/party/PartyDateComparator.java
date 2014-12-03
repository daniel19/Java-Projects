package party;

import java.util.*;
public class PartyDateComparator implements Comparator<Party> {
    public int compare(Party p1, Party p2) {  // descending
        return p2.getDate().compareTo(p1.getDate());
    }
}