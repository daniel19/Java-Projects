import java.lang.System;

public class Driver{
    static Keyboard k = Keyboard.getKeyboard();
    public static void main (String[] args){

        double cost = 0;
        int carCounter = 0;
        CarBuilder cb;
        boolean run = true;

        while(run){
            String userResponse = k.readString("Would you like to order a car (y/n)? ");
            if(userResponse == "n")
                run = false;

            int response = menu();
            switch (response){
                case 1:
                    cb = new CarBuilder();
                    requestModel(cb);
                    requestColors(cb);
                    requestOptions(cb);
                    showOrder(cb.orderCar());
                    carCounter++;
                    cost = cost + cb.orderCar().cost();
                    break;
                case 2:
                    cb = new HondaFitLoaded();
                    CarItem i = cb.orderCar();
                    showOrder(i);
                    //cost = cost + i.cost();
                    carCounter++;
                    userResponse = k.readString("Would you like to order another car (y/n)? ");
                    if(userResponse =="n")
                        run = false;
                    break;
                default:
                    break;
            }
        }

    }
    private static int menu(){
        System.out.println("1. Build Your Own");
        System.out.println("2. Honda Fit Loaded\n");
        return k.readInt("Select from the above: ");
    }
    private static void requestModel(CarBuilder cb){
        boolean correctModel = false;
        while(!correctModel) {
            String ans = k.readString("(A)ccord, (C)ivic, (F)it: ");
            if (ans == "A" || ans == "C" || ans == "F") {
                correctModel = cb.buildModel(ans.charAt(0));
            }
        }
    }
    private static void requestColors(CarBuilder cb){
        boolean done = false;
        boolean correctColor = false;
        while(!done && !correctColor) {
            String ans = k.readString("(B)lack, (S)ilver, (W)hite, (D)one: ");
            if (ans == "B" || ans == "W" || ans == "S") {
                correctColor = cb.buildColor(ans.charAt(0));
            }else if(ans == "D"){
                done = true;
            }
        }
    }
    private static void requestOptions(CarBuilder cb){
        boolean correctOption = false;
        boolean done = false;
        while(!correctOption && !done) {
            String ans = k.readString("(A)lloy Wheels, (F)loor Mats, (G)PS, (S)poilers, (D)one: ");
            if (ans == "A" || ans == "G" || ans == "F" || ans == "S" ) {
                correctOption = cb.buildOption(ans.charAt(0));
            }else if( ans == "D"){
                done = true;
            }
        }
    }
    private static void showOrder(CarItem item){
        System.out.print(item.toString());
    }

}

