import java.util.ArrayList;
public class Sections
{
   private ArrayList<Section> sections;

   private double[] grade_constants;
   private int total_num_labs;

   //all sections should use the same random number generator
   private Random rand;
   
   public Sections(int num_sections, double[] grade_constants, int total_num_labs)
   {
      this.grade_constants = grade_constants;
      sections = new ArrayList<Section>();

      for (int i = 1; i <= num_sections; i++)
      {
         Section section = new Section(i, total_num_labs);
         sections.add(section);
      }

      rand = Random.getRandomNumberGenerator(); 
   }

   //DO THIS
   //several short methods must be implemented (note: some have been done, see below)
    public int getNumSections(){return sections.size();}
    public int getNumStudents(int sectionID){
        return sections.get(sectionID -1).getNumStudents();
    }
    public String getStudentName(int sectionID, int studentID){
        return sections.get(sectionID-1).getStudentName(studentID);
    }
    public int getNumLabs(int sectionID){
        return sections.get(sectionID-1).getNumLabs();
    }
    public void setInactive(int sectionID, int studentID){
        sections.get(sectionID-1).setInactive(studentID);
    }
    public void setNotPresent(int sectionID, int studentID, int labID){
        sections.get(sectionID-1).setNotPresent(studentID, labID);        
    }
    public String partnerList(int sectionID, int studentID){
        return sections.get(sectionID-1).getPartnerList(studentID);
    }
    public String studentInfo(int sectionID, int studentID){
        return sections.get(sectionID-1).getInfo(studentID);
    }
    public void addLab(int sectionID){
       sections.get(sectionID-1).addLab();
    }

 
   //no work below this point
   public void computePartners(int sectionID)
   {
      if (sectionID <= 0 || sectionID > getNumSections())
      {
         return;
      }

      Section section = sections.get(sectionID - 1);
      section.computePartners(rand);
   }

   public void writeFiles()
   {
      for (Section section : sections)
      {
         section.writeFile(grade_constants, total_num_labs);
      }
   }
}
