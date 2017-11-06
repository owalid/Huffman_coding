import java.util.ArrayList;
import java.util.PriorityQueue;

public class ArbreHuff  {

	public boolean vide;
	public Couple couple;
	public ArbreHuff fg, fd;
	
	public ArbreHuff (ArbreHuff fd, ArbreHuff fg, Couple couple) {
		this.fd = fd;
		this.fg = fg;
		this.couple = couple;
	}

	public void init (String s){
		for (int i=0;i<s.length();i++){
			return init(s.);
		}
	}
//	public ArbreHuff (Couple couple) {
//		this.couple = couple;
//	}

//	public ArbreHuff(ArbreHuff fd, ArbreHuff fg, Couple couple) {
//		this.fd = fd;
//		this.fg = fg;
//		this.couple = couple;
//	}


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
	
    public static ArbreHuff creerArbre(int[] charFreqs, char[]tabChar) {
        
        PriorityQueue<ArbreHuff> trees = new PriorityQueue<ArbreHuff>();
        // On crée des feuilles
        for (int i = 0; i < charFreqs.length; i++){
                trees.offer(new HuffFeuille(charFreqs[i], tabChar[i]));
        }
   
        // Création de l'arbre de bas en haut
        while (trees.size() > 1) {
            // On prend les deux feuilles avec la plus petite fréquence
            ArbreHuff a = trees.poll(); //Retourne le prochain dans la liste ou NULL s'il y'en a pas
            ArbreHuff b = trees.poll(); //Retourne le prochain dans la liste ou NULL s'il y'en a pas
 
            // On crée l'arbre binaire
            trees.offer(new HuffNoeud(a, b)); 
        }
        
        return trees.poll();
    }
 

    public static String encode(ArbreHuff tree, String encode){
    	assert tree != null;
    	
    	String encodeText = "";
        for (char c : encode.toCharArray()){
        	encodeText+=(getCodes(tree, new StringBuffer(),c));
        }
    	return encodeText; // Retourne le texte encodé
    }
    
    public static String decode(ArbreHuff tree, String encode) {
    	assert tree != null;
    	
    	String decodeText="";
    	HuffmanNoeud node = (HuffmanNoeud)tree;
    	for (char code : encode.toCharArray()){
    		if (code == '0'){ 
    		    if (node.gauche instanceof HuffmanFeuille) { 
    		    	decodeText += ((HuffmanFeuille)node.gauche).lettre; // Retour la valeur dans la feuille gauche  
	                node = (HuffmanNoeud)tree; 
	    		}else{
	    			node = (HuffmanNoeud) node.gauche;  
	    		}
    		}else if (code == '1'){ 
    		    if (node.droite instanceof HuffmanFeuille) {
    		    	decodeText += ((HuffmanFeuille)node.droite).lettre; // Retour la valeur dans la feuille gauche droite
	                node = (HuffmanNoeud)tree; 
	    		}else{
	    			node = (HuffmanNoeud) node.droite; 
	    		}
    		}
    	} 
    	return decodeText; // Retourne le texte décodé
    }    
    
    public static void AfficherCodes(ArbreHuff tree, StringBuffer prefix) {
        assert tree != null;
        
        if (tree instanceof HuffmanFeuille) {
            HuffmanFeuille feuille = (HuffmanFeuille)tree;
            
            // Affiche les infos de la feuille
            System.out.println(feuille.lettre + "\t" + feuille.frequence + "\t\t" + prefix);
 
        } else if (tree instanceof HuffmanNoeud) {
            HuffmanNoeud node = (HuffmanNoeud)tree;
 
            // traverse gauche
            prefix.append('0');
            AfficherCodes(node.gauche, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            // traverse droite
            prefix.append('1');
            AfficherCodes(node.droite, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    public static String getCodes(ArbreHuff tree, StringBuffer prefix, char w) {
        assert tree != null;
        
        if (tree instanceof HuffmanFeuille) {
            HuffmanFeuille feuille = (HuffmanFeuille)tree;
            
            // Retounr le texte codé de la feuille
            if (feuille.lettre == w ){
            	return prefix.toString();
            }
            
        } else if (tree instanceof HuffmanNoeud) {
            HuffmanNoeud node = (HuffmanNoeud)tree;
 
            //recherche à gauche
            prefix.append('0');
            String gauche = getCodes(node.gauche, prefix, w);
            prefix.deleteCharAt(prefix.length()-1);
 
            // recherche à droite
            prefix.append('1');
            String droite = getCodes(node.droite, prefix,w);
            prefix.deleteCharAt(prefix.length()-1);
            
            if (gauche==null) return droite; else return gauche;
        }
		return null;
    }

}

   
	



