public class IceCream{
    private int scoops;
    private Flavor flv;
    private Topping tp;
    private static final double SCOOP_PRICE = 0.75;
    Flavors flavors = Flavors.getFlavors();
    Toppings toppings = Toppings.getToppings();
    public IceCream(int s, Flavor f, Topping t){
        if( f == null){
            f = flavors.getFlavor(1);
        }
       if(t == null){
            t = toppings.getTopping(1);
       }
       if( s < 0){
           s=1;
       }
           
        scoops = s;
        flv = f;
        tp = t;
    }

    public double price(){
        if(scoops > 1)
            return (scoops-1)*SCOOP_PRICE + tp.price();
        else
            return tp.price();
    }

    public String toString(){
        return "Flavor: " + flv + "\r\nTopping: " + tp+ "\r\nNumber of Scoops: " +scoops;
    }
}
