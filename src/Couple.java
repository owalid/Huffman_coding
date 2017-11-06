import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Couple {
	int frequence;
	char car;
	
	Couple(int frequence, char car){
		this.frequence = frequence;
		this.car = car;
	}
	
	Couple(int frequence){
		this.frequence = frequence;
	}

	public int getFrequence() {
		return frequence;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public char getCar() {
		return car;
	}

	public void setCar(char car) {
		this.car = car;
	}
	
	public ArrayList<Couple> init(String s){
		int nbOccurences;
		Map<String,Integer> m = new HashMap<String,Integer>();
		
		
        for (int i = 0; i < s.length(); i++) {
            int nbOccurrences = 0;
             
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j))
                    nbOccurrences++;
            }
             
            m.put(s.charAt(i), nbOccurences);
            System.out.println("Le caractère " + s.charAt(i) + " apparaît " + nbOccurrences + " fois.");
        }
	}
	
}
