public abstract class CarOption extends CarItem{
    public CarOption(CarColor color){
        super(color);
    }

    public CarOption(CarOption opiton){
        super(opiton);
    }

    public boolean isDuplicate(){return false;};
    abstract boolean equals(CarOption option);

}