
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene(String dna){
        String r = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){ //ATG not found.
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1){ //TAA not found.
            return "";
        }
       
        if ( (startIndex - stopIndex)%3 == 0){
            r = dna.substring(startIndex, stopIndex+3);
        }
        return r;
    }
    
    public void testSimpleGene(){
        String firstDna = "AGATGCGATACGCTTAATC";
        System.out.println("DNA strand " + firstDna);
        System.out.println("Gene " + findSimpleGene(firstDna));
        
        String secondDna = "ATGGGTTAAGTC";
        System.out.println("DNA strand " + secondDna);
        System.out.println("Gene " + findSimpleGene(secondDna));
        
        String thirdDna = "ATGGGTTAGAGTC";
        System.out.println("DNA strand " + thirdDna);
        System.out.println("Gene " + findSimpleGene(thirdDna));
        
        String fourthDna = "ATCGGTTAAAGTC";
        System.out.println("DNA strand " + fourthDna);
        System.out.println("Gene " + findSimpleGene(fourthDna));
    }
    
    public static void main(String[] args){
        Part1 fs = new Part1();
        fs.testSimpleGene();
    }
}
