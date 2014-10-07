/**
 * Fit is a subclass of CarModel
 *
 */
public class Fit extends CarModel{

    public Fit(){
        super();
    }
    public double cost() {
        return super.cost() + 17000.02;
    }

    public String toString(){
        return super.toString() + "Fit \r\n";
    }
}
