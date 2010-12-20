package ihm.modeleTableau;

import ihm.Gerant.FicheClient;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

/**
 * <p>La classe ModeleTableauStatCommande hérite de la classe AbstractTableModel
 * Sa spécificité est de pouvoir afficher uniquement des données relatives à une commande donnée
 * c'est à dire pour chaque article de la commande :<ul>
 * <li> Sa référence.</li>
 * <li> Sa description.</li>
 * <li> La quantité commandée par le client.</li>
 * </ul>
 * Elle est utilisée dans {@link FicheClient} pour afficher les détails d'une commande selectionnée dans un
 * JComboBox
 * </p>
 * 
 * @see FicheClient
 *
 */
public class ModeleTableauStatCommande extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	/**
	 * donnees contenues à l'intérieur du tableau
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
    private final String[] entetes={"Référence Article","Description","Quantité commandée"} ;
	
	/**
	 * Construit un tableau avec l'identifiant, la description et la quantité commandée de chaque article
	 * de la commande entrée en paramètre
	 * 
	 * @param idCommande
	 * 			Identifiant de la commande dont il faut afficher les informations dans le tableau
	 */
	public ModeleTableauStatCommande(String idCommande) {
		super();

		/**
		 * Contient la liste des identifiants des articles de la commande entrée en paramètre du constructeur
		 */
		ArrayList<String> listeArticles = SGBD.selectListeStringOrdonneCondition("LISTING_ARTICLES_COMMANDES", "IDARTICLE", "IDARTICLE", "IDCOMMANDE='"+idCommande+"'");
		/**
		 * Contient la liste des quantités commandées associées à chaque article de la commande
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
	 * 			integer indiquant le numéro de la ligne de l'élément choisi	
	 * @param colIndex
	 * 			integer indiquant le numéro de la colonne de l'élément choisi 
	 * 
	 * @return l'objet situé dans la rowIndex ème ligne et la colIndex ème colonne du tableau  
	 * 			correspondant à la commande effectuée par le client
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
