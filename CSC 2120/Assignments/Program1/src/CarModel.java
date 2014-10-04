abstract class CarModel extends CarItem{
    public CarModel(){ super(null);}
    abstract double cost();
    public String toString(){
        return "Model: ";
    }
}