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

		// FenetreCompte fen=new FenetreCompte();
		// MenuUtilisateur men = new MenuUtilisateur();
		MenuGerant menu = new MenuGerant();

	}
}