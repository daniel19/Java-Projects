/**
 * Accord is a subclass of CarModel
 *
 */
public class Accord extends CarModel{


    @Override
    double cost() {
        return 23000.99;
    }

    public String toString(){
       return super.toString() + "Accord \r\n";
    }
}
