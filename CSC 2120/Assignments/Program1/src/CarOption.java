public class CarOption extends CarItem{
    public CarOption(CarColor color){
        super(color);
    }

    public CarOption(CarOption opiton){
        super(opiton);
    }

    @Override
    double cost() {
        return 399.33;
    }
}