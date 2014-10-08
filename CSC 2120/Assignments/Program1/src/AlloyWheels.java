public class AlloyWheels extends CarOption {
    public AlloyWheels(CarColor color) {
        super(color);
    }

    public AlloyWheels(CarOption opiton) {
        super(opiton);
    }
    public String toString(){
        return super.toString() + "Alloy Wheels\r\n";
    }
    @Override
    boolean equals(CarOption option) {
        if(option instanceof AlloyWheels){
            return true;
        }else{
            return false;
        }

    }
}
