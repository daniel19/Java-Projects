public class Spoiler extends CarOption {
    public Spoiler(CarColor color) {
        super(color);
    }

    public Spoiler(CarOption opiton) {
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
