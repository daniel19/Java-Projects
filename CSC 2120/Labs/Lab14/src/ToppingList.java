import javax.swing.JList;
import java.util.ArrayList;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.util.Arrays;
import java.util.Vector;

public class ToppingList extends JList implements DragGestureListener, DropTargetListener
{
   private ArrayList<String> toppings;
   private ToppingSelected tsl;
   //DO THIS
   //constructor, etc.
    public ToppingList(ToppingSelected tsl){
        super();
        reset();
        this.tsl = tsl;

    }

    public void reset(){
        toppings = new ArrayList<String>();
        toppings.add("pepperoni");
        toppings.add("green peppers");
        toppings.add("sausage");
        toppings.add("onions");
        toppings.add("mushrooms");
        setListData(toppings.toArray());
    }


   //interface method to recognize the start of a drag event and track the drag as it proceeds
   public void dragGestureRecognized(DragGestureEvent dge) 
   {
      if (getSelectedIndex() == -1) return;  //no valid selection, ignore the drag

      //wrap the object up into a transferable
      //DO THIS
       ToppingTransferable transferable = new ToppingTransferable((String) getSelectedValue());
 

      //start the dragging process

      //extract the drag source out from the event object
      DragSource dragSource = dge.getDragSource();  

      //a copy of the dragged object placed inside the drag source
      //track the drag process
      dragSource.startDrag(dge, DragSource.DefaultCopyDrop, transferable, null);
   }

   //interface method to respond to a drop event
   public void drop(DropTargetDropEvent dtde) 
   {
      //get the dropped object and try to figure out what it is
      Transferable transfer = dtde.getTransferable();

      //possible flavors for the dropped object
      //DO THIS
      DataFlavor[] flavors = transfer.getTransferDataFlavors();

      String addTopping;
      try
      {
         //poll the flavors to determine the object being dropped
         //the drop target does not know from where the object has been dragged
         //the object can thus be of several different types
         //in our case, however, only one object type is allowed or expected--  a String reference

         //DO THIS
         addTopping = (String) transfer.getTransferData(flavors[0]);
         tsl.toppingSelected(addTopping);

      }
      //the flavor is not the correct type
      catch (UnsupportedFlavorException ufe)
      {
         dtde.rejectDrop();  
         return;
      }
      catch (java.io.IOException ioe)
      {
         dtde.rejectDrop();
         return;
      }

      //accept copy drops...
      dtde.acceptDrop(DnDConstants.ACTION_COPY);

      //if we made it this far, everything worked.
      dtde.dropComplete(true);

      //DO THIS remove the topping, inform the GUI
      removeTopping(addTopping);


   }

    public void removeTopping(String topping){
        toppings.remove(topping);
        tsl.toppingSelected(topping);
        setListData(toppings.toArray());
    }
   public void dragEnter(DropTargetDragEvent dtde) {}
   public void dragExit(DropTargetEvent dte) {}
   public void dragOver(DropTargetDragEvent dtde) {}
   public void dropActionChanged(DropTargetDragEvent dtde) {}
}