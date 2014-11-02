import java.util.Comparator;
/**
 *  Utility class to provide sorting algorithms and simple date invalidation.
 */
public class Utilities
{


   /**
    * Sorts the specified array of Comparator<T> objects using the selection
    * sort algorithm. <br>
    * The entire array is sorted.<br>  
    */
   public static <T> void selectionSort(Comparator<T>[] sort)
   {
      if (sort != null)
      {
         selectionSort(sort, sort.length);
      }
   }

   /**
    * Sorts the specified array of Comparator<T> objects using the selection
    * sort algorithm. <br>  
    * The number of items sorted (starting at the beginning of the array) is n.
    */
   public static <T> void selectionSort (Comparator<T>[] sort, int n)
   {
      //should check n to make sure that it doesn't exceed the array parameters
      int min;
      Comparator<T> temp;

      if (n > sort.length || n <= 0)
      {
         n = sort.length;
      }

      for (int index = 0; index < n - 1; index++)
      {
         min = index;
         for (int scan = index+1; scan < n; scan++)
         {
            if (sort[scan].compareTo(sort[min]) < 0)
            {
        		min = scan;
            }
         }

         // Swap the values
         temp = sort[min];
         sort[min] = sort[index];
         sort[index] = temp;
      }
   }


   /**
    * Sorts the specified array of Comparator<T> objects using the insertion
    * sort algorithm. <br>
    * The entire array is sorted.<br>  
    */
   public static <T> void insertionSort(Comparator<T>[] sort)
   {
      if (sort != null)
      {
         insertionSort(sort, sort.length);
      }
   }

   /**
    * Sorts the specified array of Comparator<T> objects using the insertion
    * sort algorithm. <br>  
    * The number of items sorted (starting at the beginning of the array) is n.
    */
   public static <T> void insertionSort (Comparator<T>[] sort, int n)
   {
      Comparator<T> temp;
      int position;

      if (n>sort.length || n<=0)
      {
         n=sort.length;
      }

      for (int index = 1; index < n; index++)
      {
         temp = sort[index];
         position = index;

         // shift larger values to the right
         while (position > 0 && sort[position-1].compareTo(temp) > 0)
         {
            sort[position] = sort[position-1];
            position--;
         }
            
         sort[position] = temp;
      }
   }


}
