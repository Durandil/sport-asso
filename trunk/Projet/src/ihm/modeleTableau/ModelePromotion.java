package ihm.modeleTableau;

import ihm.Client.FenetrePromotions;
import ihm.Gerant.FenetrePromotionsGerant;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

import metier.Article;
import metier.LigneCommande;


/**
 * <p>La classe ModelePromotion h�rite de la classe AbstractTableModel
 * Sa sp�cificit� est de pouvoir afficher uniquement les promotions pr�sentes dans la base de donn�es
 * avec les informations suivantes pour chaque promotion : <ul> 
 * <li> son identifiant unique</li>
 * <li> sa description </li>
 * <li> sa date de d�but</li>
 * <li> sa date de fin</li>
 * </ul>
 * <ul>Elle est utilis�e dans {@link FenetrePromotionsGerant} pour afficher au g�rant l'ensemble des promotions
 * ainsi que pour en ajouter,modifier ou supprimer une.</ul>
 * <ul>Elle est utilis�e dans {@link FenetrePromotions} pour afficher l'ensemble des promotions au client pour pouvoir 
 * �ventuellement dans ses commandes.</ul>
 * </p>
 */
public class ModelePromotion extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * donnees contenues � l'int�rieur du tableau
	 * 
	 * @see ModelePromotion#getRowCount()
	 * @see ModelePromotion#ModelePromotion()
	 * @see ModelePromotion#getValueAt(int, int)
	 * 
	 */
	private final Object[][] donnees;
	
	/**
	 * liste des noms de colonnes du tableau
	 * 
	 * @see ModelePromotion#getColumnCount()
	 * @see ModelePromotion#getColumnName(int)
	 */
	private final String[] entetes = {"Num�ro"," Promotions en cours", "Date de d�but",
			"Date de fin" };


	/**
	 * <p>Construit un tableau r�pertoriant l'ensemble des promotions pr�sentes dans la base de donn�es
	 * avec les informations suivantes pour chaque promotion : <ul> 
	 * <li> son identifiant unique</li>
	 * <li> sa description </li>
	 * <li> sa date de d�but</li>
	 * <li> sa date de fin</li>
	 * 
	 * {@link SGBD#selectListeStringOrdonne(String, String, String)}
	 * {@link SGBD#selectListeDatesOrdonne(String, String, String, String)}
	 * 
	 */
	public ModelePromotion() {
		super();
		// Quatre listes sont cr��es pour r�cup�rer les informations de la table PROMO
		ArrayList<String> listePromos = SGBD.selectListeStringOrdonne("PROMO", "NOMPROMO","IDPROMO");
		ArrayList<String> listeDatesDebut = SGBD.selectListeDatesOrdonne("PROMO",
				"DATEDEBUT","DD/MM/YYYY","IDPROMO");
		ArrayList<String> listeDatesFin = SGBD.selectListeDatesOrdonne("PROMO", "DATEFIN","DD/MM/YYYY","IDPROMO");
		ArrayList<String> listeIdentifiants = SGBD.selectListeStringOrdonne("PROMO", "IDPROMO","IDPROMO") ;
		
		// on ajoute les informations dans le tableau
		donnees = new Object[listeIdentifiants.size()][5];
		for (int i = 0; i < listePromos.size(); i++) {
			donnees[i][0] = listeIdentifiants.get(i);
			donnees[i][1] = listePromos.get(i);
			donnees[i][2] = listeDatesDebut.get(i);
			donnees[i][3] = listeDatesFin.get(i);
		};
		
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
	 * 			correspondant � l'ensemble des promotions pr�sentes dans la base de donn�es
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