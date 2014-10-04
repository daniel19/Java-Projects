/**
 *  Abstract class the is the parent of the following classes:
 *      -CarModel
 *      -CarColor
 *      -CarOption
 */
public abstract class CarItem{
   /** Only one instance variable of type CarItem **/
   private CarItem item;

   public CarItem(CarItem item){
       this.item = item;
   }
   abstract double cost();

   public String toString(){
        return item.toString();
   }
}
