package application;

import basededonnees.BDD;
import ihm.MenuGerant;
import ihm.MenuUtilisateur;
import ihm.Accueil.FenetreCompte;

/**
 * Classe qui contient la m�thode main du programme.
 */
public class Principale {

	/**
	 * M�thode main du programme
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// Initialiser la base de donn�es
		// BDD.init();

		// Initialise la fen�tre d'accueil
		 FenetreCompte fen=new FenetreCompte();
	
		// Initialise le menu du g�rant (sans passer par l'identification)
		// MenuGerant menu = new MenuGerant();

	}
}