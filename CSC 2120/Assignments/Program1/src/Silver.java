
public class Silver extends CarColor {
    public Silver(CarModel model) {
        super(model);
    }

    public Silver(CarColor color) {
        super(color);
    }

    public String toString(){
        return super.toString() + "Silver\r\n";
    }
}
