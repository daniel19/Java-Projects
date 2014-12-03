package party;

import java.util.*;
public class PartyMaxGuestsComparator implements Comparator<Party> {
    public int compare(Party p1, Party p2) {  // descending
        return p2.getMaxGuests() - p1.getMaxGuests();
    }
}