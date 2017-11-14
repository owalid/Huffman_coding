public class ArbreHuff  {

	public int vide;
	public Couple couple;
	public ArbreHuff fg, fd;
	
	public ArbreHuff (ArbreHuff fd, ArbreHuff fg, Couple couple) {
		this.fd = fd;
		this.fg = fg;
		this.couple = couple;
		vide = 0;
	}
	
	public ArbreHuff(Couple couple){
		this.couple = couple;
		vide = 0;
	}
	
	public ArbreHuff(){
		this.couple.frequence = Integer.MAX_VALUE;
	}
	
	public boolean isFeuille(){
		if(this.isVide() == Integer.MAX_VALUE){
			return true;
		}
		else if (this.fg.isVide() == Integer.MAX_VALUE && this.fd.isVide() == Integer.MAX_VALUE){
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


	public int isVide() {
		if(this.couple == null){
			this.vide = Integer.MAX_VALUE;
		}
		return this.vide;
	}

	public void setVide(int vide) {
		this.vide = vide;
	}
	
	@Override
	public String toString() {
		return "ArbreHuff [vide=" + vide + ", couple=" + couple + ", fg=" + fg + ", fd=" + fd + "]";
	}

}

   
	



