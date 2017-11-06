import java.util.HashMap;
import java.util.Map;

public class HuffNoeud extends Couple{
	
	
	


		HuffNoeud(int frequence, char car) {
		super(frequence, car);
		// TODO Auto-generated constructor stub
	}

		//couple
		public Couple init(String s, char c){
			int nbOccurrences;
			Couple couple;
			
			
	        for (int i = 0; i < s.length(); i++) {
	            nbOccurrences = 0;
	             
	            for (int j = 0; j < s.length(); j++) {
	                if (s.charAt(i) == s.charAt(j))
	                    nbOccurrences++;
	            }
	             
	            return(new Couple (nbOccurrences, s.charAt(i)));
	            
	        }
	        
			
		}
}
