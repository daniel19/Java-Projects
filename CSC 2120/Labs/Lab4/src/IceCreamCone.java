public class IceCreamCone{
    Cone coneType;
    IceCream cream;
    private static final double BASE_PRICE = 1.99;
    Flavors flavors = Flavors.getFlavors();
    Toppings toppings = Toppings.getToppings();
    public IceCreamCone(Cone c, IceCream ic){
        if( c == null){
            c = new Cone(1);
        }
        if( ic == null){
            Flavor flavor = flavors.getFlavor(1);
            Topping topping = toppings.getTopping(1);
            ic = new IceCream(1,flavor, topping );
        }

        coneType = c; 
        cream = ic;
    }

    public String price(){
        return Currency.formatCurrency(BASE_PRICE + coneType.price() + cream.price());
    }

    public String toString(){
        return "Cone: " + coneType.toString() + "\r\n" + cream.toString() + "\r\nPrice: " + price();
    }
}
