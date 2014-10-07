abstract class CarModel extends CarItem{
    public CarModel(){ super(null);}

    public String toString(){
        return super.toString() + "Model: ";
    }
}