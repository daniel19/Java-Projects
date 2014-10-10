import java.lang.System;
import java.util.ArrayList;

public class Driver{
    static Keyboard k = Keyboard.getKeyboard();
    public static void main (String[] args){
        double cost = 0;
        int carCounter = 0;
        CarBuilder cb;
        boolean run = true;
        String prompt = "Would you like to order a car (y/n)?\n";
        while(run){
            
            String userResponse = k.readString(prompt);
            char answer = myException(userResponse);
            if(answer == 'n')
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
                    prompt = "\nWould you like to order another car (y/n)? \n";
                    System.out.println("Your car cost is " + Currency.formatCurrency(cb.orderCar().cost()));
                    break;
                case 2:
                    cb = new HondaFitLoaded();
                    CarItem i = cb.orderCar();
                    showOrder(i);
                    cost = cost + i.cost();
                    carCounter++;
                    prompt = "\nWould you like to order another car (y/n)?\n";
                    System.out.println("Your car cost is " + Currency.formatCurrency(i.cost()));
                    break;
                default:
                    break;
            }
        }
        System.out.println("\n\nYou ordered " + carCounter + " car(s) for a grand total of " + Currency.formatCurrency(cost)); 

    }
    /**
     * Menu returns the user's selected integer.
     */
    private static int menu(){
        System.out.println("1. Build Your Own");
        System.out.println("2. Honda Fit Loaded\n");
        return k.readInt("Select from the above: ");
    }
    /**
     *requestModel uses the car builder to build a CarModel object
     */
    private static void requestModel(CarBuilder cb){
        boolean correctModel = false;
        while(!correctModel) {
            String ans = k.readString("(A)ccord, (C)ivic, (F)it: ");
            ans = ans.toUpperCase();
            char answer = myException(ans);
            if (answer == 'A' || answer == 'C' || answer == 'F') {
                correctModel = cb.buildModel(answer);
            }
        }
    }
    /**
     *requestColor uses the car builder to build a CarColor object
     */
    private static void requestColors(CarBuilder cb){
        boolean done = false;
        boolean correctColor = false;
        while(!done || !correctColor) {
            String ans = k.readString("(B)lack, (S)ilver, (W)hite, (D)one: ");
            ans = ans.toUpperCase();
            char answer = myException(ans);
            if (answer == 'B' || answer == 'W' || answer == 'S') {
                correctColor = cb.buildColor(answer);
            }else if(answer == 'D'){
                done = true;
            }
        }
    }
    /**
     *requestOptions uses the car builder to build a CarOption object
     */
    private static void requestOptions(CarBuilder cb){
        boolean correctOption = false;
        boolean done = false;
        ArrayList<String> menuList = new ArrayList<String>();
        menuList.add("(A)lloy Wheels, ");
        menuList.add("(F)loor Mats, ");
        menuList.add("(G)PS, ");
        menuList.add("(S)poilers, ");
        menuList.add("(D)one :");
        while(!correctOption || !done) {
            String ask = "";
            for(String s : menuList)    
                 ask = ask + s;
            String ans = k.readString(ask);
            ans = ans.toUpperCase();
            char answer = myException(ans);
            System.out.println("\n\n Answer: " + answer + "\n\n");
        
            if (answer == 'A' || answer == 'G' || answer == 'F' || answer == 'S' ) {
                correctOption = cb.buildOption(answer);
                switch(answer){
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
            }else if( answer == 'D'){
                done = true;
                correctOption = true;
            }
        }
    }
    /**
     *showOrder prints the details of the newly built CarItem using decorator pattern. 
     */
    private static void showOrder(CarItem item){
        System.out.print(item.toString());
    }
    /**
     *Function handles the user input to be more readable.
     */
    private static char myException(String a){
        if(a.length() > 1 && !a.equals("DONE") && !a.equals("BLACK") && !a.equals("ACCORD")){
         return 'x'; 
       }else{
          try{
              return a.charAt(0);
          }catch(StringIndexOutOfBoundsException e){
              return 'Z';
          }
       }
    }
}

