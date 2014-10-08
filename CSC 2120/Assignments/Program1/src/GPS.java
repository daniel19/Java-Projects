public class GPS extends CarOption {
    public GPS(CarColor color) {
        super(color);
    }

    public GPS(CarOption option) {
        super(option);
    }

    @Override
    boolean equals(CarOption option) {
        if(option instanceof GPS ){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        return super.toString() + "GPS\r\n";
    }
}
