import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class ObjectDriver{
    public static void main(String args[]){
        try{
            ObjectInputStream oInput = new ObjectInputStream(new FileInputStream("parties.ser"));
            String firstline = (String)oInput.readObject();
            HashMap<String, Party> partys = (HashMap<String, Party>)oInput.readObject();
            for(Map.Entry<String, Party> entry : partys.entrySet()){
                System.out.println(entry + "\r\n");    
            }
            oInput.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
