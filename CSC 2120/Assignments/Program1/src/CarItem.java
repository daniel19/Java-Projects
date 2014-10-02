/**
 *  Abstract class the is the parent of the following classes:
 *      -CarModel
 *      -CarColor
 *      -CarOption
 */
public abstract class CarItem implements Car{
   /** Only one instance variable of type CarItem **/
   private CarItem item;

   public double cost(){
       return item.cost();
   }

   public String toString(){
        return item.toString()
   }
}
