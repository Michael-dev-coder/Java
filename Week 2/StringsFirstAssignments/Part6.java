
/**
 * Write a description of Part6 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part6 {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
            
        }
       
        return dnaStr.length();
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if ( startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if (minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        int i = 1;
        while (true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) 
                         + currentGene.length();
            System.out.println(i++);
        }
    }
    
    public int howMany(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        int i = 1;
        while (true){
            int currentOccu = stringb.indexOf(stringa, startIndex);
            if (currentOccu == -1){
                break;
            }
            i = i + 1;
            startIndex = stringb.indexOf(stringa, startIndex+1)
                                        + stringb.length();
            
        }
        return i;
    }
}
