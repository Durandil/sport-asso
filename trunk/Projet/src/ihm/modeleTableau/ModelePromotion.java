package ihm.modeleTableau;

import ihm.Client.FenetrePromotions;
import ihm.Gerant.FenetrePromotionsGerant;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

import metier.Article;
import metier.LigneCommande;


/**
 * <p>La classe ModelePromotion hérite de la classe AbstractTableModel
 * Sa spécificité est de pouvoir afficher uniquement les promotions présentes dans la base de données
 * avec les informations suivantes pour chaque promotion : <ul> 
 * <li> son identifiant unique</li>
 * <li> sa description </li>
 * <li> sa date de début</li>
 * <li> sa date de fin</li>
 * </ul>
 * <ul>Elle est utilisée dans {@link FenetrePromotionsGerant} pour afficher au gérant l'ensemble des promotions
 * ainsi que pour en ajouter,modifier ou supprimer une.</ul>
 * <ul>Elle est utilisée dans {@link FenetrePromotions} pour afficher l'ensemble des promotions au client pour pouvoir 
 * éventuellement dans ses commandes.</ul>
 * </p>
 */
public class ModelePromotion extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * donnees contenues à l'intérieur du tableau
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
	private final String[] entetes = {"Numéro"," Promotions en cours", "Date de début",
			"Date de fin" };


	/**
	 * <p>Construit un tableau répertoriant l'ensemble des promotions présentes dans la base de données
	 * avec les informations suivantes pour chaque promotion : <ul> 
	 * <li> son identifiant unique</li>
	 * <li> sa description </li>
	 * <li> sa date de début</li>
	 * <li> sa date de fin</li>
	 * 
	 * {@link SGBD#selectListeStringOrdonne(String, String, String)}
	 * {@link SGBD#selectListeDatesOrdonne(String, String, String, String)}
	 * 
	 */
	public ModelePromotion() {
		super();
		// Quatre listes sont créées pour récupérer les informations de la table PROMO
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
	 * 			integer indiquant le numéro de la ligne de l'élément choisi	
	 * @param colIndex
	 * 			integer indiquant le numéro de la colonne de l'élément choisi 
	 * 
	 * @return l'objet situé dans la rowIndex ème ligne et la colIndex ème colonne du tableau  
	 * 			correspondant à l'ensemble des promotions présentes dans la base de données
	 */
	public Object getValueAt(int rowIndex, int colIndex) {
		return donnees[rowIndex][colIndex];
	}
	
	/**
	 * @return le nom de la colonne à l'index columnIndex
	 * 
	 * @param columnIndex
	 * 				l'index de la colonne dans le tableau
	 */
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    
}