public class Student
{
   private int studentID;
   private String name;
   private int rank;

   private Labs labs;

   //relevant to the current lab only
   private boolean active;
   private boolean hasPartner;

   public Student(int id, String name, boolean active, int rank, Labs labs)
   {
      studentID = id;
      this.name = name;

      this.labs = labs;
      this.active = active;

      hasPartner = false;
      this.rank = rank;
   }

   //DO THIS
   //several short methods must be implemented (note: some have been done, see below)
   public int getStudentID(){return studentID;}   
   public int getRank(){return rank;}
   public Labs studentLabs(){return labs;}
   public String getName(){return name;}
   public char getGrade(int labID){
        return labs.getGrade(labID);
   };
   public int getNumLabs(){
        return labs.getNumLabs(); 
   }
   public boolean isPresent(int labID){
       return labs.isPresent(labID);
   }
   
   public int getPartnerID(int labID){
       return labs.getPartnerID(labID);
   }
   //no work required below this point

   //just assigned the partner for the current lab
   public void setPartnerID(int partnerID)
   {
      int labID = getNumLabs();
      labs.setPartnerID(labID, partnerID);
      hasPartner = true;
   }
  
   public char computeFinalGrade(double[] grade_constants)  //the constants to achieve each letter grade
   {
      if (!active) 
      {
         return 'W';  //student has withdrawn from the class
      }

      return labs.computeFinalGrade(grade_constants);
   }

   public String toString()
   {
      String temp = "" + studentID + ",";
      temp += name;
      if (active)
      {
         temp += "," + "Y";
      }
      else
      {
         temp += "," + "N";
      }

      temp += "," + rank;  //new feature

      int numLabs = labs.getNumLabs();
      for (int labID = 1; labID <= numLabs; labID++)
      {
         temp += ",";
         temp += labs.getGrade(labID) + ",";

            try
            {
               temp += labs.getPartnerID(labID);  //inactive students will cause a problem here
            }
            catch (IndexOutOfBoundsException ioobe)  
            {
            }
      }

      return temp;
   }
}
