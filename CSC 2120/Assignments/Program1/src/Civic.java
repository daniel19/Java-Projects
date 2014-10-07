/**
 * Civic is a subclass of CarModel
 *
 */
public class Civic extends CarModel{

    public Civic(){
        super();
    }

    public double cost() {
        return super.cost() + 19000.79;
    }

    public String toString(){
        return super.toString() + "Civic \r\n";
    }
}
