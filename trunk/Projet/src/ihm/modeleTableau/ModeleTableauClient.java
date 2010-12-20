package ihm.modeleTableau;
import ihm.Gerant.FenetreAffichageRecherche;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

/**
 * <p>La classe ModeleTableauClient hérite de la classe AbstractTableModel
 * Sa spécificité est de pouvoir afficher uniquement les clients correspondants à la recherche 
 * d'un gérant selon plusieurs critères :<ul>
 * <li> l'identifiant du client</li>
 * <li> le nom pour les particuliers ou la dénomination pour les collectivités</li>
 * <li> la ville</li>
 * </ul>
 * 
 * Les tableaux générés à partir de cette classe sont utilisés dans la fenêtre {@link FenetreAffichageRecherche}
 * 
 */
public class ModeleTableauClient extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	/**
	 * donnees contenues à l'intérieur du tableau
	 * 
	 * @see ModeleTableauClient#getRowCount()
	 * @see ModeleTableauClient#ModeleTableauClient(String,String,String,String)
	 * @see ModeleTableauClient#getValueAt(int, int)
	 * 
	 */
	private final Object[][] donnees;
	
	/**
	 * liste des noms de colonnes du tableau
	 * 
	 * @see ModeleTableauClient#getColumnCount()
	 * @see ModeleTableauClient#getColumnName(int)
	 */
    private final String[] entetes={"Identifiant","Nom","Prénom","Dénomination"} ;
    
    /**
     * Construit des tableaux correspondants aux résultats de la recherche de clients du gérant
     * 
     * @see {@link SGBD#recupererInformationRechercheClient(String, String, String, String)}
     * 
     * @param idClient
     * 			String saisi dans le champ de recherche identifiant client
     * @param nom
     * 			String saisi dans le champ de recherche nom client
     * @param denom
     * 			String saisi dans le champ de recherche dénomination client
     * @param ville
     *			String saisi dans le champ de recherche ville client
     */
	public ModeleTableauClient(String idClient,String nom,String denom,String ville){
		super();
		//Quatre listes sont créées pour récupérer les informations de la table ARTICLES
		ArrayList<ArrayList<String>> listeClients = SGBD.recupererInformationRechercheClient(idClient,nom,denom,ville);
	
		donnees = new Object[listeClients.get(0).size()][4];
		
		if (listeClients.size()>0){
			ArrayList<String> listeIdentifiants = listeClients.get(0);
			ArrayList<String> listeNom = listeClients.get(1);
			ArrayList<String> listePrenom = listeClients.get(2);
			ArrayList<String> listeDenomination = listeClients.get(3);
			
			//On ajoute les informations dans l'objet donnees
			for(int i=0;i<listeIdentifiants.size();i++){
			
				donnees[i][0] = listeIdentifiants.get(i);	
				donnees[i][1] = listeNom.get(i);
				donnees[i][2] = listePrenom.get(i);
				donnees[i][3] = listeDenomination.get(i);
			}
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
	 * 			correspondant à la liste des clients correspondants à la recherche du gérant
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