public class Spoiler extends CarOption {
    public Spoiler(CarColor color) {
        super(color);
    }

    public Spoiler(CarOption opiton) {
        super(opiton);
    }

    public double cost(){
        return super.cost();
    }
    @Override
    boolean equals(CarOption option) {
        if(option instanceof Spoiler){
             return true;
        }else{
            return false;
        }

    }

    public String toString(){
        return super.toString() + "Spoiler \r\n";
    }
}
