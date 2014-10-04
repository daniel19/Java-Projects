/**
 * Fit is a subclass of CarModel
 *
 */
public class Fit extends CarModel{


    @Override
    double cost() {
        return 17000.02;
    }

    public String toString(){
        return super.toString() + "Fit \r\n";
    }
}
