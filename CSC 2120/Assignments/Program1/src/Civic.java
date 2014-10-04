/**
 * Civic is a subclass of CarModel
 *
 */
public class Civic extends CarModel{


    @Override
    double cost() {
        return 19000.79;
    }

    public String toString(){
        return super.toString() + "Civic \r\n";
    }
}
