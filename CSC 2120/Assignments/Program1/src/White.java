public class White extends CarColor {
    public White(CarModel model) {
        super(model);
    }

    public White(CarColor color) {
        super(color);
    }

    public String toString(){
        return super.toString() + "White\r\n";
    }

}
