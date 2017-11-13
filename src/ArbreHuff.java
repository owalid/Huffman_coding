public class ArbreHuff  {

	public boolean vide;
	public Couple couple;
	public ArbreHuff fg, fd;
	
	public ArbreHuff (ArbreHuff fd, ArbreHuff fg, Couple couple) {
		this.fd = fd;
		this.fg = fg;
		this.couple = couple;
		vide = false;
	}
	
	public ArbreHuff(Couple couple){
		this.couple = couple;
		vide = false;
	}
	
	public ArbreHuff(){
		vide = true;
	}
	
	public boolean isFeuille(){
		if(this.vide){
			return true;
		}
		else if (this.fg.vide && this.fd.vide){
			return true;
		}
		else {
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
		return vide;
	}

	public void setVide(boolean vide) {
		this.vide = vide;
	}
	
	@Override
	public String toString() {
		return "ArbreHuff [vide=" + vide + ", couple=" + couple + ", fg=" + fg + ", fd=" + fd + "]";
	}

}

   
	



