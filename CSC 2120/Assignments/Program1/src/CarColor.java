abstract class CarColor extends CarItem {
    /**
     *First constructor accepts models
     */
    public CarColor(CarModel model){
        super(model);
    }

    public CarColor(CarOption option){
        super(option);
    }

    @Override
    double cost() {
        return 247.67;
    }

}