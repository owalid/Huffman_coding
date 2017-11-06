import java.util.ArrayList;

public class ListeR{
	
	int tete;
	ListeR reste;
	boolean vide;
	
	public ListeR(int tete, ListeR reste) {
		this.tete = tete;
		this.reste = reste;
		this.vide = false;
	}
	public ListeR() {
		this.vide = true;
	}
	
	public int getTete() {
			return tete;	
	}
	public ListeR getReste() {	
			return reste;
	
	}
	public boolean isVide() {
		return vide;
	}
	
	public ListeR prefix(int i){
		return new ListeR(i,this);
	}
	
	public ArrayList<Couple> trier(ArrayList<Couple> tab){
		int i;
		boolean perm;
		Couple tampon;
		
		do{
			perm=false;
		for(i=0; i<=tab.size(); i++){
			if(tab.get(i).getFrequence()>tab.get(i+1).getFrequence()){		//SI TAB(i) < TAB(i+1)
				//ON STOCKE TAB(i) dans une variable temporaire
				tampon= tab.get(i);						
				//ON ECHANGE LES FREQUENCES ET LETTRES ENTRE TAB(i) ET TAB(i+1)
				tab.get(i).setFrequence(tab.get(i+1).getFrequence());
				tab.get(i).setCar(tab.get(i+1).getCar());
				
				//ON ECHANGE LES FREQUENCES ET LETTRES ENTRE TAMPON ET TAB(i+1)	
				tab.get(i+1).setFrequence(tampon.frequence);
				tab.get(i+1).setCar(tampon.car);
				perm= true;
			}
		  }
		
		} while(perm);
		return tab;
	}
	
	public ArrayList<Couple> somme(ArrayList<Couple> tab){
		int i;
		int j = 0;
		int res;

		
		for (i=0;i<=tab.size();i+=2){
			// res = resultat des 2 valeurs additionné
			res = tab.get(i).getFrequence() + tab.get(i+1).getFrequence();
			while(j<=tab.size()){
				//Insere la valeur entre l'intervalle
				if (tab.get(j).getFrequence() < res && tab.get(j+1).getFrequence() > res ){
				Couple e = new Couple(res);
					tab.add(j, e);
				}
			}		
		}	
		return tab;
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
