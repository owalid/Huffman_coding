public class ArbreHuff {

	public Couple couple;
	public ArbreHuff fg, fd;
	
	public ArbreHuff(Couple couple, ArbreHuff fd, ArbreHuff fg) {
		this.couple = couple;
		this.fd = fd;
		this.fg = fg;
	}

	/*
	 * 
	 */

	public ArbreHuff(Couple couple) {
		this.couple = couple;
	}

	public ArbreHuff() {
		// TODO Auto-generated constructor stub
	}

	public boolean isFeuille() { //
		if (this.fg == null && this.fd == null) {
			return true;
		} else {
			return false;
		}

	}
	
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

	// Fonction d'affichage comme décrite dans le cours (avec séparations)
	public void afficheInfixe() {
		if (!isVide()) {
			fg.afficheInfixe();
			System.out.println("'" + couple.getCar() + "' : " + couple.getFrequence());
			fd.afficheInfixe();
		}
	}

	@Override
	public String toString() {
		return "ArbreHuff [couple=" + couple + ", fg=" + fg + ", fd=" + fd + "]";
	}

}
