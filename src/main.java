import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		boolean jouer = true;
		Scanner sc = new Scanner(System.in);
		String test;
		while(jouer){
			System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
			System.out.println("			Bienvenu dans l'algorithme d'huffman, vous allez pouvoir coder et decoder des chaines de caractere");
			System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
			 // On créer un arbre
	        ArbreHuff tree = creerArbre(charFreqs,tabChar);
	        
	        
	        System.out.println("TABLE DES CODES");
	        System.out.println("LETTRE\tFREQUENCE\tCODE HUFFMAN");
	        AfficherCodes(tree, new StringBuffer());
	        
	        System.out.println(" \nEntrez une phrase comportant les éléments de l'aphabet données :");
	        test=sc.nextLine();
	        init(test);
	        // Encoder le texte
	        String encode = encode(tree,test);
	        // Afficher le texte encodé
	        System.out.println("\nTEXTE ENCODÉ");
	        System.out.println(encode); 
	        
	        // Decoder le texte
	        System.out.println("\n\nTEXTE DÉCODÉ");
	        System.out.println(decode(tree,encode));

			System.out.println("voulez vous recoder une chaine de caractere ?");
			jouer=sc.nextBoolean();
			
		}
	}

	private static char[] decode(ArbreHuff tree, String encode) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String encode(ArbreHuff tree, String test) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void AfficherCodes(ArbreHuff tree, StringBuffer stringBuffer) {
		// TODO Auto-generated method stub
		
	}

}
