public class AlloyWheels extends CarOption {
    public AlloyWheels(CarColor color) {
        super(color);
    }

    public AlloyWheels(CarOption opiton) {
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
