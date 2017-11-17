//-----------------------------------------------------------------------------------------
//EL AYADI OTHMANE / ASFAR MOHAMED RAFFIQUE  TP3B1
//-----------------------------------------------------------------------------------------

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

/**
 * 
 * @author Othmane & Asfar
 * 
 * 
 *     Comme vous n'avez pas mis des le debut du projet le tableau des frequences 
 *			Nous avons choisi de definir nous meme notre tableau 
 *					Merci pour votre comprension 
 *
 */
public class main {
	
	//--------------------------------
	//Init fichier et variables locales
	//--------------------------------
	

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		FileReader fic = null;//Lecteur de fichier
		BufferedReader tampon = null; //tampon
		Scanner sc = new Scanner(System.in); //Scanner qui permetteras de recuperer la phrase a encoder
		String mot; // mot a encoder recuperer par le scanner
		int[] freqs = new int[100]; //tableau de frequence
		String tabFic[]; // Tableau qui va récupérer les lignes du fichier .CSV
		char chars[] = new char[100]; // Tableau contenant les caractères
		LineNumberReader countLine;

		// Initialisation du fic de frequences
		try {
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
				String ligne = tampon.readLine();
				if (ligne == null)
					break;
				tabFic = ligne.split(" ; ");
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

			// On crée la liste contenant les arbres
			ListeR l = new ListeR(new ArbreHuff(new Couple(freqs[0], chars[0])));
			for (i = 1; i < chars.length; i++) {
				//insere dans l'ordres les arbres reduit a des feuilles
				l = l.insererOrd(new ArbreHuff(new Couple(freqs[i], chars[i])));
			}
	
			//Algo permettant de construire l'arbre de huffman
			ArbreHuff tree = huffman(l);

			System.out.println("Table");
			System.out.println("lettre\t frequence \t code");
			
			afficherCodes(tree, new StringBuffer());
		
			System.out.println(" \nEntrez la phrase que vous voulez coder : \n");
			mot = sc.nextLine();

			// Encoder le texte
			String encode = encode(tree, mot);
			// Afficher le texte encodé
			System.out.println("\n\n\nphrase codé");
			System.out.println(encode);

			// Decoder le texte
			System.out.println("\n\n\nphrase decodé");
			System.out.println(decode(tree, encode));


	}


	public static ArbreHuff huffman(ListeR l) { 
		assert l != null;
		while (!l.getReste().isVide()) {
			ArbreHuff x = l.getTete();
			ArbreHuff y = l.getReste().getTete();
			ArbreHuff z = new ArbreHuff(new Couple(x.getCouple().getFrequence() + y.getCouple().getFrequence()), y , x);
			
			//supprime les deux premier ellement additionner et ajoute l'arbre 
			l = l.supprimerOrd();
			l = l.supprimerOrd();
			l = l.insererOrd(z);
		}
		return l.getTete();
	}

	public static String decode(ArbreHuff tree, String encode) {
		assert tree != null;

		String decodeText = "";
		String codeCar ="";
	
		 ArbreHuff node = tree;//creer une copie de tree qui nous serviras a le parcourir

		for (char code : encode.toCharArray()) {

			if (code == '0') {//si le code est a 0 alors ils faut chercher dans le fg
				
				// si c est une feuille
				if (node.getFg().isFeuille()) {
					decodeText += node.getFg().getCouple().getCar(); // Retour la valeur dans la feuille gauche							
					node = 	tree;
				} 
				else {
					node = node.getFg(); //sinon on continue dans le fg
				}
			} else if (code == '1') {//si le code est a 1 alors ils faut chercher dans le fd
				if (node.getFd().isFeuille()) {
					decodeText += node.getFd().getCouple().getCar(); // Retour la valeur dans la feuille droite													
					node = tree;
				} 
				else {
					node = node.getFd();
				}
			}
		}
		return decodeText; // Retourne le texte décodé
	}

	private static String encode(ArbreHuff tree, String mot) {
		assert tree != null;

		String encodeText = "";
		for (char c : mot.toCharArray()) {
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

			// on parcours la gauche
			prefix.append('0');
			afficherCodes(tree.fg, prefix);
			prefix.deleteCharAt(prefix.length() - 1);

			// on parcours la droite
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
