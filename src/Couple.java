public class Couple {

	 int frequence;
	 char car;

	public Couple(int frequence, char car){
		this.frequence = frequence;
		this.car = car;
	}
	public Couple(){
		
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
	
}
