package ihm.modeleTableau;

import ihm.Gerant.FicheClient;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

/**
 * <p>La classe ModeleTableauStatCommande h�rite de la classe AbstractTableModel
 * Sa sp�cificit� est de pouvoir afficher uniquement des donn�es relatives � une commande donn�e
 * c'est � dire pour chaque article de la commande :<ul>
 * <li> Sa r�f�rence.</li>
 * <li> Sa description.</li>
 * <li> La quantit� command�e par le client.</li>
 * </ul>
 * Elle est utilis�e dans {@link FicheClient} pour afficher les d�tails d'une commande selectionn�e dans un
 * JComboBox
 * </p>
 * 
 * @see FicheClient
 *
 */
public class ModeleTableauStatCommande extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	/**
	 * donnees contenues � l'int�rieur du tableau
	 * 
	 * @see ModeleTableauStatCommande#getRowCount()
	 * @see ModeleTableauStatCommande#ModeleTableauStatCommande(String)
	 * @see ModeleTableauStatCommande#getValueAt(int, int)
	 * 
	 */
	private final Object[][] donnees;
	
	/**
	 * liste des noms de colonnes du tableau
	 * 
	 * @see ModeleTableauStatCommande#getColumnCount()
	 * @see ModeleTableauStatCommande#getColumnName(int)
	 */
    private final String[] entetes={"R�f�rence Article","Description","Quantit� command�e"} ;
	
	/**
	 * Construit un tableau avec l'identifiant, la description et la quantit� command�e de chaque article
	 * de la commande entr�e en param�tre
	 * 
	 * @param idCommande
	 * 			Identifiant de la commande dont il faut afficher les informations dans le tableau
	 */
	public ModeleTableauStatCommande(String idCommande) {
		super();

		/**
		 * Contient la liste des identifiants des articles de la commande entr�e en param�tre du constructeur
		 */
		ArrayList<String> listeArticles = SGBD.selectListeStringOrdonneCondition("LISTING_ARTICLES_COMMANDES", "IDARTICLE", "IDARTICLE", "IDCOMMANDE='"+idCommande+"'");
		/**
		 * Contient la liste des quantit�s command�es associ�es � chaque article de la commande
		 */
		ArrayList<String> listeQuantitesCorrespondantes = SGBD.selectListeStringOrdonneCondition("LISTING_ARTICLES_COMMANDES", "QUANTITECOMMANDEE", "IDARTICLE", "IDCOMMANDE='"+idCommande+"'");
		
		donnees = new Object[listeArticles.size()][this.getColumnCount()];
		
		//On ajoute les informations dans l'objet donnees
		for(int i=0;i<listeArticles.size();i++){
			donnees[i][0] = listeArticles.get(i);	
			donnees[i][1] = SGBD.selectStringConditionString("ARTICLE", "DESCRIPTION", "IDARTICLE", listeArticles.get(i) );
			donnees[i][2] = listeQuantitesCorrespondantes.get(i);
			
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
	 * 			correspondant � la commande effectu�e par le client
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
