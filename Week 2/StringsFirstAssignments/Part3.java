
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public String twoOccurences(String stringa, String stringb){
        String occ = "";
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1){//no occurrance stringa in stringb
            return occ = "false";
        }
        int stopIndex = stringb.indexOf(stringa, startIndex+1);
        if (stopIndex == -1){ 
            return occ = "false";
        }
        if (startIndex != -1 && stopIndex != -1){
            String result = stringb.substring(startIndex, stopIndex);
            return occ = "true";
        }
        return occ;
    }
    
    public String lastPart(String stringa, String stringb){
        String result = "";
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1)
        {
            return result = stringb;
        }else
        {
            result = stringb.substring(startIndex+stringa.length());
        }
        return result;
    }
    
    public void testing(){
        String a = "zoo";
        String b = "forest";
        System.out.println("Stringa : " + a);
        System.out.println("Stringb : " + b);
        System.out.println("Results = " + twoOccurences(a,b));
        System.out.println("The part of the string after "+ a + " in " + b + " is " +lastPart(a,b));
        
        String c = "i";
        String d = "Michael Nyoni";
        System.out.println("Stringa : " + c);
        System.out.println("Stringb : " + d);
        System.out.println("Results = " + twoOccurences(c,d));
        System.out.println("The part of the string after "+ c + " in " + d + " is " +lastPart(c,d));
        
        String e = "an";
        String f = "banana";
        System.out.println("Stringa : " + e);
        System.out.println("Stringb : " + f);
        System.out.println("Results = " + twoOccurences(e,f));
        System.out.println("The part of the string after "+ e + " in " + f + " is " +lastPart(e,f));
    }
    public static void main(String[] args){
        Part3 pr = new Part3();
        pr.testing();
    }
}
