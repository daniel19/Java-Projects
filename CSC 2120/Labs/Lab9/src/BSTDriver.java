import java.util.ArrayList; import java.util.Iterator; 
public class BSTDriver
{
   public static void main(String[] args)
   {
      //call the height and isBalanced methods and display the results with all items inserted
      System.out.println("Correct Implementation\n");
      for(int i=0; i <= 10; i++){
          System.out.print("-");
          if(i == 10)
              System.out.println();
      }
      BinarySearchTree st = new BinarySearchTree();
      CD[] cds = readMusic("cds.txt");
      for(int i=0; i <= 2; i++){
          st.insert(cds[i]);
      }
      //st.delete(cds[0].getKey());
      //st.insert(cds[0]);
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
