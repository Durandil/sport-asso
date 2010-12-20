package ihm.modeleTableau;
import ihm.Client.FenetreCommandeArticle;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


/**
 * La classe ModelePanier h�rite de la classe AbstractTableModel
 * Sa sp�cificit� est de pouvoir afficher uniquement les articles qui sont dans l'�tat "En stock"
 * en face de la quantit� command�e pr�sente dans le panier du client
 * 
 * @see FenetreCommandeArticle
 *
 */
public class ModelePanier extends AbstractTableModel  {
	
	private static final long serialVersionUID = 1L;

	/**
	 * donnees contenues � l'int�rieur du tableau
	 * 
	 * @see ModelePanier#getRowCount()
	 * @see ModelePanier#ModelePanier(ArrayList)
	 * @see ModelePanier#getValueAt(int, int)
	 * 
	 */
	private final Object[][] donnees;
	
	/**
	 * liste des noms de colonnes du tableau
	 * 
	 * @see ModelePanier#getColumnCount()
	 * @see ModelePanier#getColumnName(int)
	 */
    private final String[] entetes={"Numero","Quantite d�sir�e",} ;
	
	/**
	 * Constructeur de la classe ModelePanier
	 * 
	 * @param panier 
	 * 			ArrayList associant les identifiants d'article � leurs quantit�s command�es
	 * 
	 * @see FenetreCommandeArticle
	 * @see FenetreCommandeArticle#initComponent()
	 */
	public ModelePanier(ArrayList<String[]> panier){
		super();
		int taille = 1000;
		if(panier.size()>0){
			taille=panier.size();
		}
		
		donnees= new Object[taille][2];
		int compteur=0;
		for (String[] strings : panier) {
			donnees[compteur][0]=strings[0];
			donnees[compteur][1]=strings[1];
			compteur++;
		}
		
	}
	
	/**
	 * Cette m�thode actualise le tableau actuel � l'aide du panier d'articles mis en param�tre
	 * 
	 * @param panierCourant 
	 * 				un ArrayList contenant l'ensemble des articles associ�s leurs quantit�s command�es
	 */
	public void actualiserLignes(ArrayList<String[]> panierCourant){
		int compteur=0;
		for (String[] strings : panierCourant) {
			donnees[compteur][0]=strings[0];
			donnees[compteur][1]=strings[1];
			compteur++;
		}
	}
	
	/**
	 * @return Indique le nombre de colonnes dans le tableau
	 */
	public int getColumnCount() {
		return entetes.length;
	}
	
	/**
	 * @return Indique  le nombre de lignes dans le	tableau
	 */
	public int getRowCount() {
		return donnees.length;
	}
	
	/**
	 * @param rowIndex 
	 * 			integer indiquant le num�ro de la ligne de l'�l�ment choisi	
	 * @param colIndex
	 * 			integer indiquant le num�ro de la colonne de l'�l�ment choisi 
	 * 
	 * @return l'objet situ� dans la rowIndex �me ligne et la colIndex �me colonne du tableau  
	 * 			correspondant au panier en cours
	 */
	public Object getValueAt(int rowIndex, int colIndex) {
		return donnees[rowIndex][colIndex];
	}
	
	/**
	 * @return le nom de la colonne � l'index columnIndex
	 * 
	 * @param columnIndex
	 * 				l'index de la colonne dans le tableau
	 */
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    

}
