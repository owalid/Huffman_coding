import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		boolean jouer = true;
		Scanner sc = new Scanner(System.in);
		String mot;
		int[] freqs = new int[100]; // Tableau qui va contenir les
										// fréquences
		String tabFic[]; // Tableau qui va récupérer les lignes du fichier .CSV
		char chars[] = new char[100]; // Tableau contenant les caractères
		FileReader fic = null;
		BufferedReader tampon = null;
		LineNumberReader countLine;

		// Initialise le fichier à lire
		try {
			// Initialise le fichier à lire
			fic = new FileReader("frequence.csv");
			countLine = new LineNumberReader(new FileReader("frequence.csv"));
			countLine.skip(Long.MAX_VALUE);
			int nbLignes = countLine.getLineNumber();
			countLine.close();

			freqs = new int[nbLignes];
			chars = new char[nbLignes];
			tampon = new BufferedReader(fic);
			int j = 0;

			while (true) {
				// Lit une ligne de frequence.csv
				String ligne = tampon.readLine();
				// Vérifie la fin de fichier
				if (ligne == null)
					break;
				// sépare la fréquence et la lettre
				tabFic = ligne.split(" ; ");

				// On remplie les tableaux de fréquences et de lettre
				for (int i = 0; i < 1; i++) {
					chars[j] = tabFic[i].charAt(0);
					freqs[j] = Integer.parseInt(tabFic[i + 1]);
				}
				j++;

			}
			tampon.close();
			fic.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		int temp;
		char temp2;
		int k;
		int i;
		
		for (i = 0; i < freqs.length; i++) {
			temp = freqs[i];
			temp2 = chars[i];
			k=i-1;
			
			while (k >= 0 && freqs[k] > temp) {
				freqs[k + 1] = freqs[k];
				chars[k + 1] = chars[k];
				k--;
			}
			
			freqs[k + 1] = temp;
			chars[k + 1] = temp2;
		}


			System.out.println(
					"/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
			System.out.println(
					"			Bienvenu dans l'algorithme d'huffman, vous allez pouvoir coder et decoder des chaines de caractere");
			System.out.println(
					"/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");

			// for (i=0; i<tabChar.length; i++){
			//
			// System.out.println(charFreqs[i] + "\t" + tabChar[i]);
			// }

			// On crée la liste contenant les arbres
			ListeR l = new ListeR(new ArbreHuff(new Couple(freqs[0], chars[0])));


			for (i = 1; i < chars.length; i++) {
				l = l.insererOrd(new ArbreHuff(new Couple(freqs[i], chars[i])));
				
				System.out.println(l.toString());

			}

		
			ArbreHuff tree = huffman(l);
			
			// System.out.println(tree.couple.toString());

			System.out.println("TABLE DES CODES");
			System.out.println("LETTRE\tFREQUENCE\tCODE HUFFMAN");
			if (tree == null) {
				System.out.println("y'a rien");
			} else {
				System.out.println("y'a qqch");
			}
			afficherCodes(tree, new StringBuffer());
		

			System.out.println(" \nEntrez une phrase comportant les éléments de l'aphabet données :");
			mot = sc.nextLine();

			// Encoder le texte
			String encode = encode(tree, mot);
			// Afficher le texte encodé
			System.out.println("\nTEXTE ENCODÉ");
			System.out.println(encode);

			// Decoder le texte
			System.out.println("\n\nTEXTE DÉCODÉ");
			System.out.println(decode(tree, encode));


	}


	public static ArbreHuff huffman(ListeR l) { 
		assert l != null;
		while (!l.getReste().isVide()) {
			System.out.println("c");
			
			//ArbreHuff z = new ArbreHuff(new Couple());
			
			ArbreHuff x = l.getTete();
			//z.setFg(x);
			ArbreHuff y = l.getReste().getTete();
			//z.setFd(y);
			ArbreHuff z = new ArbreHuff(new Couple(x.getCouple().getFrequence() + y.getCouple().getFrequence()), y , x);
			//z.getCouple().setFrequence(x.getCouple().getFrequence() + y.getCouple().getFrequence());

			l = l.supprimerOrd();
			l = l.supprimerOrd();
			l = l.insererOrd(z);
		}
		return l.getTete();
	}

	public static String decode(ArbreHuff tree, String encode) {
		assert tree != null;

		String decodeText = "";
		ArbreHuff node = new ArbreHuff();
		for (char code : encode.toCharArray()) {
			if (code == '0') {
				// si c est une feuille
				if (tree.fg.isFeuille()) {
					decodeText += tree.fg.couple.getCar(); // Retour la valeur dans
														// la feuille gauche
					node = tree;
				} 
//				else {
//					node = node.fg;
//				}
			} else if (code == '1') {
				if (tree.fd.isFeuille()) {
					decodeText += tree.fg.couple.getCar(); // Retour la valeur dans
														// la feuille gauche
														// droite
					node = tree;
				} 
//				else {
//					node = node.fd;
//				}
			}
		}
		return decodeText; // Retourne le texte décodé
	}

	private static String encode(ArbreHuff tree, String test) {
		assert tree != null;

		String encodeText = "";
		for (char c : test.toCharArray()) {
			encodeText += (getCodes(tree, new StringBuffer(), c));
		}
		return encodeText;
	}

	public static void afficherCodes(ArbreHuff tree, StringBuffer prefix) {
		assert tree != null;

		if (tree.isFeuille()) {
			// Affiche les infos de la feuille
			System.out.println(tree.couple.getCar() + "\t" + tree.couple.getFrequence() + "\t\t" + prefix);
		}

		else {

			// traverse gauche
			prefix.append('0');
			afficherCodes(tree.fg, prefix);
			prefix.deleteCharAt(prefix.length() - 1);

			// traverse droite
			prefix.append('1');
			afficherCodes(tree.fd, prefix);
			prefix.deleteCharAt(prefix.length() - 1);
		}

	}

	public static String getCodes(ArbreHuff tree, StringBuffer prefix, char w) {
		assert tree != null;

		if (tree.isFeuille()) {
			// Retoune le texte codé de la feuille
			if (tree.couple.getCar() == w) {
				return prefix.toString();
			}

		} else {
			// recherche à gauche
			prefix.append('0');
			String gauche = getCodes(tree.fg, prefix, w);
			prefix.deleteCharAt(prefix.length() - 1);

			// recherche à droite
			prefix.append('1');
			String droite = getCodes(tree.fd, prefix, w);
			prefix.deleteCharAt(prefix.length() - 1);

			if (gauche == null)
				return droite;

			else
				return gauche;
		}
		return null;
	}
}
