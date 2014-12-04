import java.awt.Graphics;
import java.awt.Image;

public abstract class DecoratedPizza implements Drawable
{
   /** 
    *  ImageLoader is responsible for loading images.
    *  Call the getImage passing the file name and store the result in an Image object.
    *  Use the drawImage in the Graphics class to draw the image.
    */
   private static final ImageLoader il = ImageLoader.getImageLoader();
   protected DecoratedPizza pizza;

   //Pizza will use the default constructor
   public DecoratedPizza()
   {
      pizza = null;
   }

   public DecoratedPizza(DecoratedPizza pizza)
   {
      this.pizza = pizza;
   }

   public abstract double pizzaCost();
   public abstract String getImage();

   public void draw(Graphics g, int width, int height){
       String imageFile = "images/" + getImage() + ".jpg";
      Image image = il.getImage(imageFile);
      g.drawImage(image, 0, 0,null);
   }
}