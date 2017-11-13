public class ListeR{
	
	ArbreHuff tete;
	ListeR reste;
	boolean vide;
	
	public ListeR(ArbreHuff tete, ListeR reste) {
		this.tete = tete;
		this.reste = reste;
		this.vide = false;
	}
	public ListeR(ArbreHuff tete) {
		this.tete = tete;
		this.vide = false;
	}
	
	public ListeR() {
		this.vide = true;
	}
	
	//inserer dans l'ordre
	public ListeR insererOrd(ArbreHuff val){
		if(vide){
			return this.prefix(val);
		}	
		else if (val.couple.getFrequence()>=this.tete.couple.getFrequence()){
			return this.prefix(val);
		}
		else{
			return this.reste.insererOrd(val).prefix(this.tete);
		}
	}
	
	//Suprimer dans l'ordre
	public ListeR supprimerOrd(ArbreHuff v){
		if(this.vide){
			return this;
		}
		else if(this.tete == v){
			return this.reste;
		}
		else if (v.couple.getFrequence()>= this.tete.couple.getFrequence()){
			return this;
		}
		else
			return this.reste.supprimerOrd(v).prefix(this.tete);
	}
		
	public ArbreHuff getTete() {
			return tete;	
	}
	public ListeR getReste() {	
			return reste;
	
	}
	public boolean isVide() {
		return vide;
	}
	
	public ListeR prefix(ArbreHuff i){
		return new ListeR(i,this);
	}
	
	
	@Override
	public String toString() {
		if(!this.isVide())
			return "Liste : " + tete + reste.toStringBis();
		else
			return "Liste vide.";
	}
	private String toStringBis() {
		if(!this.isVide())
			return ", "+tete + reste.toStringBis();
		else
			return ".";
	}
	
}
