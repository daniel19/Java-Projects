public class Black extends CarColor {
    public Black(CarModel model) {
        super(model);
    }

    public Black(CarColor color) {
        super(color);
    }

    public String toString(){
        return super.toString() + "Black\r\n";
    }
}
