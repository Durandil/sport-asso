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
		// Il faut ensuite insérer les lignes présentes dans le fichier Insert.txt
		// dans la base de données
		 BDD.init();

		// Initialise la fenêtre d'accueil
		// FenetreCompte fen=new FenetreCompte();
	
		// Initialise le menu du gérant (sans passer par l'identification)
		// MenuGerant menu = new MenuGerant();
		 	 
		 
	}
}