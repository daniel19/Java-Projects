
/**
 *  Abstract class the is the parent of the following classes:
 *      -CarModel
 *      -CarColor
 *      -CarOption
 */
public abstract class CarItem{
   /** Only one instance variable of type CarItem **/
   private CarItem next;

   public CarItem(CarItem item){
       next = item;
   }
   public double cost()
   {
       return next.cost();
   }

   public String toString(){
       return next.toString();
   }

    public boolean isDuplicate(CarOption check){
        if(next instanceof CarColor)
            return false;
        else if(check.equals((CarOption)next))
            return true;
        else
            return next.isDuplicate(check);
    }

}
