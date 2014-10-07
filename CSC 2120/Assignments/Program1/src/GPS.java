public class GPS extends CarOption {
    public GPS(CarColor color) {
        super(color);
    }

    public GPS(CarOption opiton) {
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
