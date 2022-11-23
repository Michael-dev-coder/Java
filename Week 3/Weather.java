import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weather {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord record : parser){
            coldestSoFar = getLowestOfTwo(record, coldestSoFar);
        }
        return coldestSoFar;
    }
    public String fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        File file = null;
        double num = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord record = coldestHourInFile(fr.getCSVParser());
            if (record != null){
                coldestSoFar = getLowestOfTwo(record, coldestSoFar);
                file = f;
            }
        }
        System.out.println("Coldest day was in file " + file.getName());
        System.out.println("Coldest temperature on that day was " 
                                    + coldestSoFar.get("TemperatureF"));
        return file.getName();
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumiditySoFar = null;
        for(CSVRecord record : parser){
            lowestHumiditySoFar = getLowestHumidityOfTwo(record, lowestHumiditySoFar);
        }
        return lowestHumiditySoFar;
    }
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for ( File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = lowestHumidityInFile(parser);
            String r = record.get("Humidity");
            lowestHumiditySoFar = getLowestHumidityOfTwo(record, lowestHumiditySoFar);
        }
        return lowestHumiditySoFar;
    }
    public double averageTemperatureInFile(CSVParser parser){
        CSVRecord averageTemp = null;
        double averageTempInFile = 0.0;
        int i = 0;
        for (CSVRecord record : parser){
            averageTemp = record;
            if (averageTemp != null){
                double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                // double maxTemp = Double.parseDouble(averageTemp.get("TemperatureF"));
                if(currentTemp > 0){
                    averageTempInFile =  averageTempInFile + currentTemp;
                   
                }
            }
            i++;
        }
        return averageTempInFile/i;
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        CSVRecord average = null;
        double averageTemp = 0.0;
        double totalAverageTemp = 0.0;
        int i = 0;
        for(CSVRecord record : parser){
            double currentHum = Double.parseDouble(record.get("Humidity"));
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            if(currentHum >= value){
                if(currentTemp > 0){
                    averageTemp =  averageTemp + currentTemp;
                    i++;
                }
            }
        }
        if (averageTemp != 0.0){
            totalAverageTemp = averageTemp/i;
        }else{
            totalAverageTemp = 0.0;
        }
        return totalAverageTemp;
    }
    public CSVRecord getLowestOfTwo(CSVRecord record, CSVRecord coldestSoFar){
        String r = record.get("TemperatureF");
            if(coldestSoFar == null){
                    coldestSoFar = record;
            }
            else if(r.equals("-9999")){  
                 System.out.println("-9999 Found!");
            }else{
                double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                 double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                 if(currentTemp < coldestTemp){
                        coldestSoFar = record;
                 }
            }
            return coldestSoFar;
    }
    public CSVRecord getLowestHumidityOfTwo(CSVRecord record, CSVRecord lowestHumiditySoFar){
        String r = record.get("Humidity");
        String l = record.get("Humidity");
        if (lowestHumiditySoFar == null){
                lowestHumiditySoFar = record;
            }
            if(r.equals("N/A")){
                System.out.println("N/A found!");
            }else{
                double currentHumidity = Double.parseDouble(record.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
                if (currentHumidity < lowestHumidity){
                    lowestHumiditySoFar = record;
                }
            }
        return lowestHumiditySoFar;
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + lowest.get("TemperatureF") + 
                            " at " + lowest.get("DateUTC"));
    }
    public void testFileWithColdestTemperature(){
        String lowest = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + lowest);
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " 
                            + csv.get("DateUTC") + " " /*+ csv.get("TimeEDT")*/);
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") + " at " 
                           + lowestHumidity.get("DateUTC") + " " /*+ lowestHumidity.get("TimeEST")*/);
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double csv = averageTemperatureInFile(parser);
        System.out.println("Average Temperature in file is " + csv);
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double csv = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(csv != 0.0){
           System.out.println("Average Temp when high Humidity is " + csv);
        }else{
            System.out.println("No temperatures with that humidity");
        }
    }
}
