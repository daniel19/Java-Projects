abstract class CarColor extends CarItem {
    /**
     *First constructor accepts models
     */
    public CarColor(CarModel model){
        super(model);
    }

    public CarColor(CarColor color){
        super(color);
    }


    public double cost() {
        return super.cost() + 247.67;
    }
    public String toString(){
        return super.toString()+"Color: "; }
}
