//-----------------------------------------------------------------------------------------
//EL AYADI OTHMANE / ASFAR MOHAMED RAFFIQUE  TP3B1
//-----------------------------------------------------------------------------------------

/**
 * 
 * @author Othmane & Asfar
 *
 */
public class ListeR {

	ArbreHuff tete;
	ListeR reste;

	//-------------
	//constructeurs
	//-------------
	public ListeR(ArbreHuff tete, ListeR reste) {
		this.tete = tete;
		this.reste = reste;
	}

	public ListeR(ArbreHuff tete) {
		this.tete = tete;
		this.reste = new ListeR();
	}

	public ListeR() {
	}

	//-------------
	//methodes
	//-------------
	
	
	// inserer dans l'ordre
	public ListeR insererOrd(ArbreHuff val) { //
		if (this.isVide()) {
			return this.prefix(val);
		} else if (val.couple.getFrequence() <= this.tete.couple.getFrequence()) {
			return this.prefix(val);
		}
		else {
			return (this.reste.insererOrd(val).prefix(this.tete));
		}
	}
	
	// Supprimer un element dans une liste
	public ListeR supprimerOrd() { 
		return this.getReste();
	}

	public ListeR prefix(ArbreHuff i) {
		return new ListeR(i, this);
	}
	
	//-------------
	//Getter and Setter
	//-------------

	public ArbreHuff getTete() {
		return tete;
	}

	public ListeR getReste() {
		return reste;

	}

	public boolean isVide() {
		return tete == null;
	}

	public void setTete(ArbreHuff tete) {
		this.tete = tete;
	}

	public void setReste(ListeR reste) {
		this.reste = reste;
	}

	@Override
	public String toString() {
		if (!this.isVide())
			return "Liste : " + tete.toString() /* + reste.toStringBis() */;
		else
			return "Liste vide.";
	}

	private String toStringBis() {
		if (!this.isVide())
			return ", " + tete + reste.toStringBis();
		else
			return ".";
	}

}
