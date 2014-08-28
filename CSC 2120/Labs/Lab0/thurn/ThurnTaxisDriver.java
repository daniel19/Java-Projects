package thurn;

public class ThurnTaxisDriver
{
   //entry point
   public static void main(String[] args)
   {
      gui.SimpleDialogs sd = gui.SimpleDialogs.getSimpleDialogs();
      sd.useSystemStyle();

      ThurnTaxis tt = new ThurnTaxis(980, 781);
   }
}