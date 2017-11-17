//-----------------------------------------------------------------------------------------
//EL AYADI OTHMANE / ASFAR MOHAMED RAFFIQUE  TP3B1
//-----------------------------------------------------------------------------------------

/**
 * 
 * @author Othmane & Asfar
 *
 */
public class ArbreHuff {

	public Couple couple;
	public ArbreHuff fg, fd;
	
	//-------------
	//constructeurs
	//-------------
	public ArbreHuff(Couple couple, ArbreHuff fd, ArbreHuff fg) {
		this.couple = couple;
		this.fd = fd;
		this.fg = fg;
	}


	public ArbreHuff(Couple couple) {
		this.couple = couple;
	}

	
	public ArbreHuff() {
		this.couple = new Couple();
	}

	//-------------
	//methodes
	//-------------
	
	//Savoir si un arbre est une feuille
	public boolean isFeuille() { //
		if (this.fg == null && this.fd == null) {
			return true;
		} else {
			return false;
		}

	}
	
	
	//-------------
	//Getter and Setter
	//-------------
	
	public Couple getCouple() {
		return couple;
	}

	public void setCouple(Couple couple) {
		this.couple = couple;
	}

	public ArbreHuff getFg() {
		return fg;
	}

	public void setFg(ArbreHuff fg) {
		this.fg = fg;
	}

	public ArbreHuff getFd() {
		return fd;
	}

	public void setFd(ArbreHuff fd) {
		this.fd = fd;
	}

	public boolean isVide() {
		return this.couple == null;
	}

	@Override
	public String toString() {
		return "ArbreHuff [couple=" + couple + ", fg=" + fg + ", fd=" + fd + "]";
	}

}
