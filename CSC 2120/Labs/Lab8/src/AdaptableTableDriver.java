import java.util.ArrayList;
import java.util.Iterator;

public class AdaptableTableDriver
{

   public static void main(String[] args)
   {
      TableInterface st = new TableSTBased();  
      CD[] cds = readMusic("cds.txt");
     
      for (int i = 0; i < cds.length; i++)
      {
         try
         {
            st.tableInsert(cds[i]);
         }
         catch(TableException te)
         {
             System.out.println(cds[i] + " was not added.\n");
         }
      }

      //DO THIS
      //test retrieval of several items and make sure that those items are moved to the root
      System.out.println(st.tableSize());
      for(int j = 0; j < 3; j++){
        st.tableRetrieve(cds[j].getKey());
      }

      //test removal of a few items (removal should not reorganize the tree)
      for(int j = 0; j < 3; j++){
        st.tableDelete(cds[j+3].getKey());
      }


   }

   public static void displayTable(TableInterface ti)
   {
      Iterator iter = ti.iterator();
      while(iter.hasNext())
      {
         System.out.println(iter.next());
      }
   }

   private static CD[] readMusic(String fileName)
   {
      FileIO file = new FileIO(fileName, FileIO.FOR_READING);
      String str = file.readLine();
      ArrayList<CD> cds = new ArrayList<CD>();
      while (!file.EOF())
      {
         String title = file.readLine();
         int year = Integer.parseInt(file.readLine());
         int rating = Integer.parseInt(file.readLine());
         int numTracks = Integer.parseInt(file.readLine());
         CD cd = new CD(title, str, year, rating, numTracks);

         cds.add(cd);
         int tracks = 1;

         while (tracks <= numTracks)
         {
            String temp = file.readLine();
            String[] line = temp.split(",");
            String len = line[0];
            String songTitle = line[1];
            cd.addSong(songTitle, len);
            tracks++;
         }

         str = file.readLine();
      }

      CD[] cds_array = new CD[cds.size()];
      int i = 0;
      for(CD cd : cds)
      {
         cds_array[i] = cds.get(i);
         i++;
      }
      return cds_array;
   }


}
