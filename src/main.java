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
		String test;
		int[] charFreqs = new int[100]; // Tableau qui va contenir les
										// fréquences
		String tabL[]; // Tableau qui va récupérer les lignes du fichier .CSV
		char tabChar[] = new char[100]; // Tableau contenant les caractères
		FileReader monFichier = null;
		BufferedReader tampon = null;
		LineNumberReader lnr;

		// Initialise le fichier à lire
		try {
			// Initialise le fichier à lire
			monFichier = new FileReader("frequence.csv");
			lnr = new LineNumberReader(new FileReader("frequence.csv"));
			lnr.skip(Long.MAX_VALUE);
			int nbLignes = lnr.getLineNumber();
			lnr.close();

			charFreqs = new int[nbLignes];
			tabChar = new char[nbLignes];
			tampon = new BufferedReader(monFichier);
			int j = 0;

			while (true) {
				// Lit une ligne de frequence.csv
				String ligne = tampon.readLine();
				// Vérifie la fin de fichier
				if (ligne == null)
					break;
				// sépare la fréquence et la lettre
				tabL = ligne.split(" ; ");

				// On remplie les tableaux de fréquences et de lettre
				for (int i = 0; i < 1; i++) {
					tabChar[j] = tabL[i].charAt(0);
					charFreqs[j] = Integer.parseInt(tabL[i + 1]);
				}
				j++;

			}
			tampon.close();
			monFichier.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		int temp;
		char temp2;
		int k;
		int i;
		for (i = 0; i < charFreqs.length; i++) {
			temp = charFreqs[i];
			temp2 = tabChar[i];
			k = i - 1;
			while (k >= 0 && charFreqs[k] > temp) {
				charFreqs[k + 1] = charFreqs[k];
				tabChar[k + 1] = tabChar[k];
				k = k - 1;
			}
			charFreqs[k + 1] = temp;
			tabChar[k + 1] = temp2;
		}

		while (jouer) {
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
			ListeR l = new ListeR(new ArbreHuff(new Couple(charFreqs[0], tabChar[0])));


			for (i = 1; i < tabChar.length; i++) {
				l = l.insererOrd(new ArbreHuff(new Couple(charFreqs[i], tabChar[i])));
				
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
			System.exit(0);

			System.out.println(" \nEntrez une phrase comportant les éléments de l'aphabet données :");
			test = sc.nextLine();

			// Encoder le texte
			String encode = encode(tree, test);
			// Afficher le texte encodé
			System.out.println("\nTEXTE ENCODÉ");
			System.out.println(encode);

			// Decoder le texte
			System.out.println("\n\nTEXTE DÉCODÉ");
			System.out.println(decode(tree, encode));

			System.out.println("voulez vous recoder une chaine de caractere ?");
			jouer = sc.nextBoolean();

		}
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
			ArbreHuff z = new ArbreHuff(new Couple(x.getCouple().getFrequence() + y.getCouple().getFrequence()));
			z.setFg(x);
			z.setFd(y);
			//z.getCouple().setFrequence(x.getCouple().getFrequence() + y.getCouple().getFrequence());
			l = l.insererOrd(z);
			l = l.supprimerOrd(x);
			l = l.supprimerOrd(y);

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
				} else {
					node = node.fg;
				}
			} else if (code == '1') {
				if (tree.fd.isFeuille()) {
					decodeText += tree.fg.couple.getCar(); // Retour la valeur dans
														// la feuille gauche
														// droite
					node = tree;
				} else {
					node = node.fd;
				}
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
