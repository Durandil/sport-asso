package application;

import basededonnees.BDD;
import ihm.MenuGerant;
import ihm.MenuUtilisateur;
import ihm.Accueil.FenetreCompte;

/**
 * Classe qui contient la méthode main du programme.
 */
public class Principale {

	/**
	 * Méthode main du programme
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// Initialiser la base de données
		// BDD.init();

		// FenetreCompte fen=new FenetreCompte();
		// MenuUtilisateur men = new MenuUtilisateur();
		MenuGerant menu = new MenuGerant();

	}
}