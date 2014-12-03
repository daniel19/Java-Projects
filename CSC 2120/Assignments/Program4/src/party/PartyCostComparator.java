package party;

import java.util.*;
public class PartyCostComparator implements Comparator<Party> {
    public int compare(Party p1, Party p2) {  // ascending
        double p1Cost = p1.getCost();
        double p2Cost = p2.getCost();
        double diff = p1Cost - p2Cost;
        int result = 0;
        if (Math.abs(diff) >= 0.01) {
            if (diff > 0) {
                result = 1;
            }
            else {
                result = -1;
            }
        }
        return result;
    }
}