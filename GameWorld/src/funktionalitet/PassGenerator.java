package funktionalitet;
import java.util.Random;
 
public class PassGenerator {
 
    private static final String[] charsets = new String[] {".-_+!?=", "0123456789", 
    	"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"};
 
    public static String getRandomString(int length) {
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int c = 0; c < charsets.length; c++) {
        	int csLength = (c == 3) ? length : rand.nextInt(length - (3 - c)) + 1;
        	for (int i = 0; i < csLength; i++) {
        		int pos = rand.nextInt(charsets[c].length());
                sb.append(charsets[c].charAt(pos));
        	}
        	length -= csLength;
        }
        
        return sb.toString();
    }
    public String getPass(){
		return PassGenerator.getRandomString(8);
    	
    }
 
}