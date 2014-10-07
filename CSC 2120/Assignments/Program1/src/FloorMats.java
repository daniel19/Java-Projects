/**
 * Created by DanielBrown on 10/6/2014.
 */
public class FloorMats extends CarOption {
    public FloorMats(CarColor color) {
        super(color);
    }

    public FloorMats(CarOption opiton) {
        super(opiton);
    }
    @Override
    boolean equals(CarOption option) {
        if(option instanceof CarOption && option == this){
            return true;
        }else{
            return false;
        }
    }
}
