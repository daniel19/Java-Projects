import java.util.ArrayList;
import java.util.Iterator;

public class BSTDriver
{
   public static void main(String[] args)
   {
        AVLTree bstWorst = new AVLTree();
		CD[] cds = readMusic("cds.txt");
        WorstCDS wCD = new WorstCDS();
        LongestSong ls = new LongestSong();
        for(CD cd : cds){
            bstWorst.insert(cd);
        }

        ArrayList<CD> worst;
        bstWorst.execute(wCD);
        bstWorst.execute(ls);
        worst = wCD.getWorstCDs();
        System.out.println(worst.size());
        for(CD cd : worst)
            System.out.println(cd.toString());

        System.out.println("Longest song is " + ls.getLongestSong().getTitle());
   }
   private static void bstWork(){
         //call the height and isBalanced methods and display the results with all items inserted
		BinarySearchTree bst = new AVLTree();
		CD[] cds = readMusic("cds.txt");
		
		bst.insert(cds[16]);
		bst.insert(cds[10]);
		System.out.print("\n" + bst.isBalanced() + "\n");
		bst.insert(cds[5]);
		System.out.print("\n" + bst.isBalanced() + "\n");
		bst.insert(cds[4]);
		bst.delete("Fire Up The Blades");
		bst.delete("Fragmentary Evidence");
		bst.delete("Majesty And Decay");
		bst.delete("The Thin Line Between");
		bst.insert(cds[16]);
		bst.insert(cds[10]);
		bst.insert(cds[5]);
		bst.insert(cds[4]);
		bst.insert(cds[3]);
		bst.insert(cds[8]);
		try {
			bst.insert(cds[8]);
		} catch (TreeException tree) {
			System.out.print("\nDuplicate found");
		}
		bst.insert(cds[9]);
		bst.insert(cds[11]);
		bst.insert(cds[13]);
		bst.insert(cds[12]);
		bst.insert(cds[20]);
		bst.insert(cds[21]);
		bst.insert(cds[22]);
		bst.insert(cds[23]);
		bst.insert(cds[24]);
		bst.insert(cds[26]);
		bst.insert(cds[2]);
		bst.insert(cds[30]);
		bst.insert(cds[31]);
		bst.insert(cds[32]);
		bst.insert(cds[33]);
		bst.insert(cds[34]);
		bst.insert(cds[35]);
		bst.insert(cds[37]);
		bst.insert(cds[38]);
		bst.insert(cds[39]);
		bst.insert(cds[40]);
		bst.insert(cds[1]);
		
		System.out.print("\n" + bst.isBalanced() + "\n");
		
		bst.delete("Absu");
		bst.delete("Fire Up The Blades");
		bst.delete("Ice Upon The Night");
		bst.delete("Wolves");
		bst.delete("The Essence");
		bst.delete("The Thin Line Between");
		bst.delete("Framing Armageddon");
		bst.delete("Across The Dark");
		bst.delete("Death...The Brutal Way");
		bst.delete("12 Gauge");
		System.out.print("\n" + bst.isBalanced() + "\n");
		bst.delete("One");
		bst.delete("Holographic Universe");
		bst.delete("This is Hell");
		bst.delete("NeuThrone");
		bst.delete("Epitaph");
		bst.delete("For The Revolution");
		bst.delete("Deadlight");
		System.out.print("\n" + bst.isBalanced() + "\n");
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
