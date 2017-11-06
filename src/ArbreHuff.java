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

//	public void init (String s){
//		for (int i=0;i<s.length();i++){
//			//calculer la frequence
//			//creerArbre
//			//insererOrd
//		}
//	}
//	
	//algo
	public ArbreHuff huffman(ListeR l){
		while(!l.reste.vide){
			ArbreHuff z = new ArbreHuff();
			ArbreHuff x = l.tete;
			z.fg = x;
			z.vide = false;
			ArbreHuff y = l.tete;
			z.fd = y;
			
			z.couple.frequence = x.couple.frequence + y.couple.frequence;
			
			l.InsererOrd(l,z);
		}
		return(l.getTete());
	}


//    public static String encode(ArbreHuff tree, String encode){
//    	assert tree != null;
//    	
//    	String encodeText = "";
//        for (char c : encode.toCharArray()){
//        	encodeText+=(getCodes(tree, new StringBuffer(),c));
//        }
//    	return encodeText; // Retourne le texte encodé
//    }
//    
//    public static String decode(ArbreHuff tree, String encode) {
//    	assert tree != null;
//    	
//    	String decodeText="";
//    	HuffmanNoeud node = (HuffmanNoeud)tree;
//    	for (char code : encode.toCharArray()){
//    		if (code == '0'){ 
//    		    if (node.gauche instanceof HuffmanFeuille) { 
//    		    	decodeText += ((HuffmanFeuille)node.gauche).lettre; // Retour la valeur dans la feuille gauche  
//	                node = (HuffmanNoeud)tree; 
//	    		}else{
//	    			node = (HuffmanNoeud) node.gauche;  
//	    		}
//    		}else if (code == '1'){ 
//    		    if (node.droite instanceof HuffmanFeuille) {
//    		    	decodeText += ((HuffmanFeuille)node.droite).lettre; // Retour la valeur dans la feuille gauche droite
//	                node = (HuffmanNoeud)tree; 
//	    		}else{
//	    			node = (HuffmanNoeud) node.droite; 
//	    		}
//    		}
//    	} 
//    	return decodeText; // Retourne le texte décodé
//    }    
//    
//    public static void AfficherCodes(ArbreHuff tree, StringBuffer prefix) {
//        assert tree != null;
//        
//        if (tree instanceof HuffmanFeuille) {
//            HuffmanFeuille feuille = (HuffmanFeuille)tree;
//            
//            // Affiche les infos de la feuille
//            System.out.println(feuille.lettre + "\t" + feuille.frequence + "\t\t" + prefix);
// 
//        } else if (tree instanceof HuffmanNoeud) {
//            HuffmanNoeud node = (HuffmanNoeud)tree;
// 
//            // traverse gauche
//            prefix.append('0');
//            AfficherCodes(node.gauche, prefix);
//            prefix.deleteCharAt(prefix.length()-1);
// 
//            // traverse droite
//            prefix.append('1');
//            AfficherCodes(node.droite, prefix);
//            prefix.deleteCharAt(prefix.length()-1);
//        }
//    }
//
//    public static String getCodes(ArbreHuff tree, StringBuffer prefix, char w) {
//        assert tree != null;
//        
//        if (tree instanceof HuffmanFeuille) {
//            HuffmanFeuille feuille = (HuffmanFeuille)tree;
//            
//            // Retounr le texte codé de la feuille
//            if (feuille.lettre == w ){
//            	return prefix.toString();
//            }
//            
//        } else if (tree instanceof HuffmanNoeud) {
//            HuffmanNoeud node = (HuffmanNoeud)tree;
// 
//            //recherche à gauche
//            prefix.append('0');
//            String gauche = getCodes(node.gauche, prefix, w);
//            prefix.deleteCharAt(prefix.length()-1);
// 
//            // recherche à droite
//            prefix.append('1');
//            String droite = getCodes(node.droite, prefix,w);
//            prefix.deleteCharAt(prefix.length()-1);
//            
//            if (gauche==null) return droite; else return gauche;
//        }
//		return null;
//    }

}

   
	



