
public class HondaFitLoaded extends CarBuilder {

    @Override
    public CarItem orderCar() {
        buildModel('F');
        buildColor('S');
        buildOption('G');
        buildOption('S');

        return super.orderCar();
    }
}
