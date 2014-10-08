import java.lang.System;
import java.util.ArrayList;

public class Driver{
    static Keyboard k = Keyboard.getKeyboard();
    public static void main (String[] args){
        double cost = 0;
        int carCounter = 0;
        CarBuilder cb;
        boolean run = true;
        String prompt = "Would you like to order a car (y/n)? ";
        while(run){
            String userResponse = k.readString(prompt);
            if(userResponse.charAt(0) == 'n')
                break;

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
                    prompt = "\nWould you like to order another car (y/n)? ";
                    break;
                case 2:
                    cb = new HondaFitLoaded();
                    CarItem i = cb.orderCar();
                    showOrder(i);
                    cost = cost + i.cost();
                    carCounter++;
                    prompt = "\nWould you like to order another car (y/n)? ";
                    break;
                default:
                    break;
            }
        }
        System.out.println("\n\nYou ordered " + carCounter + " car(s) for a grand total of " + Currency.formatCurrency(cost)); 

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
            if (ans.charAt(0) == 'A' || ans.charAt(0) == 'C' || ans.charAt(0) == 'F') {
                correctModel = cb.buildModel(ans.charAt(0));
            }
        }
    }
    private static void requestColors(CarBuilder cb){
        boolean done = false;
        boolean correctColor = false;
        while(!done || !correctColor) {
            String ans = k.readString("(B)lack, (S)ilver, (W)hite, (D)one: ");
            if (ans.charAt(0) == 'B' || ans.charAt(0) == 'W' || ans.charAt(0) == 'S') {
                correctColor = cb.buildColor(ans.charAt(0));
            }else if(ans.charAt(0) == 'D'){
                done = true;
            }
            System.out.println("\n\n correctColor:" + correctColor + "  done: " + done + "\n\n");
        }
    }
    private static void requestOptions(CarBuilder cb){
        boolean correctOption = false;
        boolean done = false;
        ArrayList<String> menuList = new ArrayList<String>();
        menuList.add("(A)lloy Wheels, ");
        menuList.add("(F)loor Mats, ");
        menuList.add("(G)PS, ");
        menuList.add("(S)poilers, ");
        menuList.add("(D)one");
        while(!correctOption || !done) {
            String ask = "";
            for(String s : menuList)    
                 ask = ask + s;
            String ans = k.readString(ask);
            if (ans.charAt(0) == 'A' || ans.charAt(0) == 'G' || ans.charAt(0) == 'F' || ans.charAt(0) == 'S' ) {
                correctOption = cb.buildOption(ans.charAt(0));
                switch(ans.charAt(0)){
                   case 'A':
                        menuList.remove("(A)lloy Wheels, ");
                        break;
                   case 'G':
                        menuList.remove("(G)PS, ");
                        break;
                   case 'F':
                        menuList.remove("(F)loor Mats, ");
                        break;
                   case 'S':
                        menuList.remove("(S)poilers, ");
                        break;
                   default:
                        break;
                }
            }else if( ans.charAt(0) == 'D'){
                done = true;
            }
        }
    }
    private static void showOrder(CarItem item){
        System.out.print(item.toString());
    }

}

