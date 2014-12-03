package party;

import java.util.*;
public class PartyAttendanceComparator implements Comparator<Party> {
    public int compare(Party p1, Party p2) {  // ascending
        return p1.getNumAttending() - p2.getNumAttending();
    }
}