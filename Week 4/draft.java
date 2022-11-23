import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of draft here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class draft {
    public void printName(){
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100){
                System.out.println("Name " + rec.get(0) + 
                                "Gender " + rec.get(1) +
                                "Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int count = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){
                totalBoys += numBorn;
                count++;
            }else{
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girls = " + totalGirls);
        System.out.println("Count = " + count);
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 0;
        int i = 0;
        // String test = "";
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(rec.get(1).equals(gender)){
                rank += numBorn;
                // test = rec.get(0);
                i++;
            }else{
                return -1;
            }
            if (rec.get(0).equals(name)){
                rank -= numBorn;
                break;
            }
        }
        // System.out.println("Name " + test);
        // System.out.println("Rank = " + i);
        return i;
    }
    
    public String getName(int year, int rank, String gender){
        String results = "";
        int i = 0;
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(rec.get(1).equals(gender)){
                results = rec.get(0);
                i++;
            }
            if (rank == i){
                break;
            }
            // return "NO NAME";
        }
        return results;
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
      int rank = getRank(year, name, gender);
      if (rank != -1){
          String newName = getName(newYear, rank, gender);
          System.out.println(name+" born in "+ year +" would be "+ newName + " if she was born in "+ 
                             newYear);
      }else{
          System.out.println("NO NAME");
      }
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
        }
        return 0;
    }
    
    public void testTotalBirths (){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testGetRank(){
        System.out.println(getRank(1971, "Frank", "M"));
    }
    
    public void testGetName(){
        System.out.println(getName(1982, 450, "M"));
    }
    
    public void testwhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "F");
    }
}
