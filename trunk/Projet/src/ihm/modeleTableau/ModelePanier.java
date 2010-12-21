package ihm.modeleTableau;

import ihm.Client.FenetreCommandeArticle;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * La classe ModelePanier hérite de la classe AbstractTableModel. Sa spécificité
 * est de pouvoir afficher uniquement les articles qui sont dans l'état
 * "En stock" en face de la quantité commandée présente dans le panier du client
 * 
 * @see FenetreCommandeArticle
 * 
 */
public class ModelePanier extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Tableau qui contiendra les articles
	 * 
	 * @see ModelePanier#getRowCount()
	 * @see ModelePanier#ModelePanier(ArrayList)
	 * @see ModelePanier#getValueAt(int, int)
	 * 
	 */
	private final Object[][] donnees;

	/**
	 * Liste des noms de colonnes du tableau
	 * 
	 * @see ModelePanier#getColumnCount()
	 * @see ModelePanier#getColumnName(int)
	 */
	private final String[] entetes = { "Numero", "Quantite désirée", };

	/**
	 * Constructeur de la classe ModelePanier
	 * 
	 * @param panier
	 *            ArrayList associant les identifiants d'article à leurs
	 *            quantités commandées
	 * 
	 * @see FenetreCommandeArticle
	 * @see FenetreCommandeArticle#initComponent()
	 */
	public ModelePanier(ArrayList<String[]> panier) {
		super();
		int taille = 1000;
		if (panier.size() > 0) {
			taille = panier.size();
		}

		donnees = new Object[taille][2];
		int compteur = 0;
		for (String[] strings : panier) {
			donnees[compteur][0] = strings[0];
			donnees[compteur][1] = strings[1];
			compteur++;
		}

	}

	/**
	 * Cette méthode actualise le tableau actuel à l'aide du panier d'articles
	 * mis en paramètre
	 * 
	 * @param panierCourant
	 *            Un ArrayList contenant l'ensemble des articles associés leurs
	 *            quantités commandées
	 */
	public void actualiserLignes(ArrayList<String[]> panierCourant) {
		int compteur = 0;
		for (String[] strings : panierCourant) {
			donnees[compteur][0] = strings[0];
			donnees[compteur][1] = strings[1];
			compteur++;
		}
	}

	/**
	 * Retourne le nombre de colonnes dans le tableau
	 * 
	 * @return Le nombre de colonnes dans le tableau
	 */
	public int getColumnCount() {
		return entetes.length;
	}

	/**
	 * Retourne le nombre de lignes dans le tableau
	 * 
	 * @return Le nombre de lignes dans le tableau
	 */
	public int getRowCount() {
		return donnees.length;
	}

	/**
	 * Retourne l'objet situé dans une colonne et une ligne toutes deux
	 * précisées en paramètres
	 * 
	 * @param rowIndex
	 *            Integer indiquant le numéro de la ligne de l'élément choisi
	 * @param colIndex
	 *            Integer indiquant le numéro de la colonne de l'élément choisi
	 *            
	 * @return L'objet situé dans la ligne numéro rowIndex et la 
	 *         colonne numéro colIndex du tableau des messages
	 */
	public Object getValueAt(int rowIndex, int colIndex) {
		return donnees[rowIndex][colIndex];
	}

	/**
	 * Retourne le nom de la colonne à l'index précisé en paramètre
	 * 
	 * @return Le nom de la colonne à l'index columnIndex
	 * 
	 * @param columnIndex
	 *            L'index de la colonne dans le tableau
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

}
