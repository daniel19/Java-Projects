/**
 *
 */
public class Cone{
    private int coneType;
    public Cone(int type){
         coneType = type;  
    }

    public double price(){
        switch(coneType){
            case 1:
                return 0.59;
            case 2:
                return 0.79;
            case 3:
                return 0.0;
            default: 
                return 0.0;
        }

    }
    public String toString(){
        String ct; 
        switch(coneType){
            case 1:
                ct = "SUGAR";
                break;
            case 2:
                ct = "WAFFLE";
                break;
            case 3:
                ct = "CUP";
                break;
            default:
                ct = "CUP";
                break;
        }


    return ct;
    }
}
