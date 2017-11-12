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
	
    public ArbreHuff creerArbre(char c, int freq) {
    	return new ArbreHuff(new Couple(freq,c));
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

	public boolean isVide() {
		return vide;
	}

	public void setVide(boolean vide) {
		this.vide = vide;
	}
}

   
	



