package ihm.modeleTableau;

import ihm.Client.FenetreCommandeArticle;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * La classe ModelePanier h�rite de la classe AbstractTableModel. Sa sp�cificit�
 * est de pouvoir afficher uniquement les articles qui sont dans l'�tat
 * "En stock" en face de la quantit� command�e pr�sente dans le panier du client
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
	private final String[] entetes = { "Numero", "Quantite d�sir�e", };

	/**
	 * Constructeur de la classe ModelePanier
	 * 
	 * @param panier
	 *            ArrayList associant les identifiants d'article � leurs
	 *            quantit�s command�es
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
	 * Cette m�thode actualise le tableau actuel � l'aide du panier d'articles
	 * mis en param�tre
	 * 
	 * @param panierCourant
	 *            Un ArrayList contenant l'ensemble des articles associ�s leurs
	 *            quantit�s command�es
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
	 * Retourne l'objet situ� dans une colonne et une ligne toutes deux
	 * pr�cis�es en param�tres
	 * 
	 * @param rowIndex
	 *            Integer indiquant le num�ro de la ligne de l'�l�ment choisi
	 * @param colIndex
	 *            Integer indiquant le num�ro de la colonne de l'�l�ment choisi
	 *            
	 * @return L'objet situ� dans la ligne num�ro rowIndex et la 
	 *         colonne num�ro colIndex du tableau des messages
	 */
	public Object getValueAt(int rowIndex, int colIndex) {
		return donnees[rowIndex][colIndex];
	}

	/**
	 * Retourne le nom de la colonne � l'index pr�cis� en param�tre
	 * 
	 * @return Le nom de la colonne � l'index columnIndex
	 * 
	 * @param columnIndex
	 *            L'index de la colonne dans le tableau
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

}
