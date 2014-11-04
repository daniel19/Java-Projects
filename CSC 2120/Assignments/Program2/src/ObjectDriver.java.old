import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class ObjectDriver{
    public static void main(String args[]){
        /*This method will return true if the date is not in the form yy/mm/dd, where yy, mm, and dd are integers,
         yy is between 00 and 99 inclusive, mm is between 01 and 12 inclusive,
         and dd is between 01 and the latest day in the respective month denoted by mm,
         considering leap years, too. If the date is properly formed, the method will return false (not invalid).
        */
        System.out.print(isDateInvalid("14/12/33"));
    }

    public static boolean isDateInvalid(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
        format.setLenient(false);
        try{
           Date d = format.parse(date);
           }catch (ParseException e){
            return true;
        }catch (IllegalArgumentException e){
            return true;
        }
        return false;
    }
}
