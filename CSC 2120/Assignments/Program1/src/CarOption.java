public abstract class CarOption extends CarItem{
    public CarOption(CarColor color){
        super(color);
    }

    public CarOption(CarOption opiton){
        super(opiton);
    }

    public boolean isDuplicate(){return false;};
    public double cost(){
        return super.cost() + 399.33;
    }
    abstract boolean equals(CarOption option);
    public String toString(){
        return super.toString() + "Option: ";
    }

}