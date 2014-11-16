import java.util.ArrayList;
public class WorstCDS implements Command<CD>{
    private ArrayList<CD> cdList = new ArrayList<CD>();;
    public void execute(CD cd){
       int rating = cd.getRating();
       if(rating <= 5) {
          cdList.add(cd);
       }
    }
    public ArrayList<CD> getWorstCDs(){
           return cdList;
    }
}
