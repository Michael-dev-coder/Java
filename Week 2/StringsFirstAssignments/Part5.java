import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part5 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part5 {
    public static void main(String[] args){
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for ( String s : ur.words()){
                String itemLower = s.toLowerCase();
       	        int pos = itemLower.indexOf("youtube.com");
       	        
       	        if(pos != -1){
       	                int beg = s.lastIndexOf("\"",pos);
                   	int end = s.indexOf("\"", pos+1);
                   	System.out.println(s.substring(beg+1,end));
       	        }/*else{
       	            System.out.println("");
       	        }*/
            // System.out.println(s);
        }
    }
}
