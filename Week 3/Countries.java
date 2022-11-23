import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of Countries here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Countries {
    public void listExporters(CSVParser parser, String exportOfInterest){
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // System.out.print(numberOfExporters(parser, "cocoa"));
        // bigExpoters(parser, " $999,999,999,999");
        listExportersTwoProducts(parser, "cotton", "flowers");
        // countryInfo(parser, "Nauru");
    }
    public void countryInfo(CSVParser parser, String country){
        // String countryName = "";
        for(CSVRecord record : parser){
            String c = record.get("Country");
            if (c.contains(country)){
                String countryName = record.get("Country");
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.print(countryName +" : ");
                System.out.print(export+" : ");
                System.out.print(value);
            }
        }
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,
                                         String exportItem2){
        for (CSVRecord record : parser){
            String export1 = record.get("Exports");
            String export2 = record.get("Exports");
            if (export1.contains(exportItem1) && export2.contains(exportItem2)){
                String countryName = record.get("Country");
                System.out.println(countryName);
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int i = 0;
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportItem)){
                i++;
            }
            
        }
        return i;
    }
    public void bigExpoters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()){
                String countryName = record.get("Country");
                String val = record.get("Value (dollars)");
                System.out.print(countryName + " ");
                System.out.println(val);
            }
        }
    }
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser){
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    public void testHottestInDay(){
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + 
                           " at " + largest.get("TimeEST"));
    }
    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
       }
       return largestSoFar;
    }
    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar){
        if (largestSoFar == null){
                largestSoFar = currentRow;
        }
        else {
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
        if (currentTemp > largestTemp){
                    largestSoFar = currentRow;
             }
        }
        return largestSoFar;    
    }
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + 
                           " at " + largest.get("DateUTC"));
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + lowest.get("TemperatureF") + 
                            " at " + lowest.get("DateUTC"));
    }
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord record : parser){
            coldestSoFar = getLowestOfTwo(record, coldestSoFar);
        }
        return coldestSoFar;
    }
    public CSVRecord getLowestOfTwo(CSVRecord record, CSVRecord coldestSoFar){
        if(coldestSoFar == null){
                coldestSoFar = record;
        }
        else{
             double currentTemp = Double.parseDouble(record.get("TemperatureF"));
             double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
             if(currentTemp < coldestTemp){
                    coldestSoFar = record;
             }
        }
        return coldestSoFar;
    }
}
