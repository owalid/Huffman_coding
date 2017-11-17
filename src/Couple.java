//-----------------------------------------------------------------------------------------
//EL AYADI OTHMANE / ASFAR MOHAMED RAFFIQUE  TP3B1
//-----------------------------------------------------------------------------------------

/**
 * 
 * @author Othmane & Asfar
 *
 */

public class Couple {

	private int frequence;
	private char car;

	//-------------
	//constructeurs
	//-------------
	public Couple(int frequence, char car) {
		this.frequence = frequence;
		this.car = car;
	}

	public Couple(int frequence) {
		this.frequence = frequence;
	}

	public Couple() {
	}

	//-------------
	//Getter and Setter
	//-------------
	@Override
	public String toString() {
		return "Couple [frequence=" + frequence + ", car=" + car + "]";
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
