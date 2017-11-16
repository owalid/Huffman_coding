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
	
	// setter pour initialiser un caractere et cr�er le fils gauche et fils droit
		public void setRacine(Couple c)
		{
			couple=c;
			fg=new ArbreHuff();
			fd=new ArbreHuff();
			vide = 0;
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
		if(this.fg == null && this.fd == null){
			this.vide = Integer.MAX_VALUE;
		}
		return this.vide;
	}

	public void setVide(int vide) {
		this.vide = vide;
	}
	
	public Couple info()
	{
		return couple;
	}
	
	//Fonction d'affichage comme décrite dans le cours (avec séparations)
	public void afficheInfixe()
	{
		if(vide == 0)
		{
			fg.afficheInfixe();
			System.out.println("'" + info().getCar() + "' : " + info().getFrequence());
			fd.afficheInfixe();
		}
	}

	@Override
	public String toString() {
		return "ArbreHuff [vide=" + vide + ", couple=" + couple + ", fg=" + fg + ", fd=" + fd + "]";
	}
	


}

   
	



