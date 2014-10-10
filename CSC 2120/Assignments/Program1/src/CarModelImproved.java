/**
 *Class eliminates the need for any extra CarModel subclasses.
 *
 */
public class CarModelImproved extends CarModel{
    double cost;
    String name;
    public CarModelImproved(double cost, String name){
        switch(name.charAt(0)){
            case 'A':
                 this.name = "Accord";
                 this.cost = cost;
                break;
            case 'C':
                this.name = "Civic";
                this.cost = cost;
                break;
            case 'F':
                this.name = "Fit";
                this.cost = cost;
                break;
            default:
                this.name = "Car";
                this.cost = cost;
                break;
        }
    }

    public String toString(){
        return super.toString() + name + "\r\n";
    }

    public double cost(){
        return cost;
    }
}
