import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class ListeR{
	
	ArbreHuff tete;
	ListeR reste;
	int vide;
	
	public ListeR(ArbreHuff tete, ListeR reste) {
		this.tete = tete;
		this.reste = reste;
		this.vide = 0;
	}
	public ListeR(ArbreHuff tete) {
		this.tete = tete;
		this.vide = 0;
	}
	
	public ListeR() {
		this.vide = Integer.MAX_VALUE;
	}
	
	//inserer dans l'ordre
	public ListeR insererOrd(ArbreHuff val){
		if(this.isVide() != 0){
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
		if(this.isVide() == Integer.MAX_VALUE){
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
	public int isVide() {
		if(reste == null){
			this.vide = Integer.MAX_VALUE;
		}
		return this.vide;
	}
	
	public ListeR prefix(ArbreHuff i){
		return new ListeR(i,this);
	}
	
	
	@Override
	public String toString() {
		if(this.isVide() != 0)
			return "Liste : " + tete + reste.toStringBis();
		else
			return "Liste vide.";
	}
	private String toStringBis() {
		if(this.isVide() != 0)
			return ", "+tete + reste.toStringBis();
		else
			return ".";
	}
	
}
