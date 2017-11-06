import java.util.ArrayList;

public class ListeR{
	
	ArbreHuff tete;
	ListeR reste;
	boolean vide;
	
	public ListeR(ArbreHuff tete, ListeR reste) {
		this.tete = tete;
		this.reste = reste;
		this.vide = false;
	}
	public ListeR() {
		this.vide = true;
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
	
	//inserer dans l'ordre
	public ListeR InsererOrd(ListeR l, ArbreHuff val){
		if(l.vide){
			return l.prefix(val);
		}
		
		else if (val.couple.frequence<l.tete.couple.frequence){
			return l.prefix(val);
		}
		else
			return InsererOrd(l.reste, val).prefix(l.tete);
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
