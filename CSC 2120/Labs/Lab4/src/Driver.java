public class Driver{
    public static Keyboard k = Keyboard.getKeyboard();
    public static void main(String[] args){
        String response =  k.readString("Would you like to order an ice cream cone? (y/n):  ");
        while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
            System.out.println("Please enter y or n.");
            response =  k.readString("Would you like to order an ice cream cone? (y/n):  ");
        }
        int orders =0;
        double price = 0;
        while(response.equalsIgnoreCase("y")){
           Flavor f =  getFlavorChoice();
           Topping t = getToppingChoice();
           int s = getScoopChoice();
           Cone c = getConeChoice();


           IceCreamCone ic = new IceCreamCone(c, new IceCream(s, f, t));
           System.out.println("\r\n" + ic.toString());
           orders++;
           price = price + Double.parseDouble(ic.price().substring(1));
           response = k.readString("\r\nWould you like to order another ice cream cone? (y/n): ");
           while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
               System.out.println("Please enter y or n.");
               response =  k.readString("Would you like to order another ice cream cone? (y/n):  ");
           }
        }
        System.out.println("You total order for " + orders + " order(s) of ice cream is $" + String.format("%1$,.2f",price));

    }

    public static Flavor getFlavorChoice(){
        Flavors flv = Flavors.getFlavors();
        System.out.println(flv.listFlavors());
        
        int response = k.readInt("Enter in your desired flavor: ");
        while(response < 1 || response > 18){
            System.out.println("Please enter a number between 1 and 18 for your desired flavor.");
            response = k.readInt("Enter in your desired flavor: ");
        }
        
        return flv.getFlavor(response);
    }

    public static Cone getConeChoice(){
        int response = k.readInt("Would you like a 1: Sugar Cone, 2: Waffle Cone, or 3: Cup? ");
        while(response < 1 || response > 3){
            System.out.println("Please enter 1, 2, or 3 for your answer.");
            response = k.readInt("Would you like a 1: Sugar Cone, 2: Waffle Cone, or 3: Cup? ");
        }
        
        return new Cone(response); 

    }

    public static Topping getToppingChoice(){
        Toppings ts = Toppings.getToppings();
        System.out.println(ts.listToppings());

        int response = k.readInt("Enter in your desired topping: ");
        while(response < 1 || response > 5){
            System.out.println("Please enter 1, 2, 3, 4, or 5 for your answer.");
            response = k.readInt("Enter in your desired topping: ");
        }
        
        return ts.getTopping(response);
    }

    public static int getScoopChoice(){
       int response = k.readInt("How many scoops: (1,2, or 3): ");
       while(response < 1 || response > 3){
            System.out.println("Please enter 1, 2, or 3 for your answer.");
            response = k.readInt("How many scoops: (1,2, or 3): ");
       }
       
       return response;
    }
}
