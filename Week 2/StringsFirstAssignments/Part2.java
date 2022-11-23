
/**
 * Write a description of Part2 here.
 * 
 * @author (Michael) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String r = "";
        if(dna.equals(dna.toUpperCase())){
            int startIndex = dna.indexOf(startCodon);
            if (startIndex == -1){ //ATG not found.
                return "";
            }
            int stopIndex = dna.indexOf(stopCodon, startIndex+3);
            if (stopIndex == -1){ //TAA not found.
                return "";
            }
        
            if ( (startIndex - stopIndex)%3 == 0){
                r = dna.substring(startIndex, stopIndex+3);
            }
            
        }
        if(dna.equals(dna.toLowerCase())){
            int startIndex = dna.indexOf(startCodon.toLowerCase());
            if (startIndex == -1){ //ATG not found.
                return "";
            }
            int stopIndex = dna.indexOf(stopCodon.toLowerCase(), startIndex+3);
            if (stopIndex == -1){ //TAA not found.
                return "";
            }
        
            if ( (startIndex - stopIndex)%3 == 0){
                r = dna.substring(startIndex, stopIndex+3);
            }
            
        }
        return r;
    }
    
    public void testSimpleGene(){
        String firstDna = "AGATGCGATACGCTTAATC";
        System.out.println("DNA strand " + firstDna);
        System.out.println("Gene " + findSimpleGene(firstDna, "ATG", "TAA"));
        
        String secondDna = "ATGGGTTAAGTC";
        System.out.println("DNA strand " + secondDna);
        System.out.println("Gene " + findSimpleGene(secondDna, "ATG", "TAA"));
        
        String thirdDna = "gatgctataat";
        System.out.println("DNA strand " + thirdDna);
        System.out.println("Gene " + findSimpleGene(thirdDna, "ATG", "TAA"));
        
        String fourthDna = "ATCGGTTAAAGTC";
        System.out.println("DNA strand " + fourthDna);
        System.out.println("Gene " + findSimpleGene(fourthDna, "ATG", "TAA"));
    }
    
    public static void main(String[] args){
        Part2 pr = new Part2();
        pr.testSimpleGene();
    }
}
