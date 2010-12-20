package ihm.modeleTableau;
import ihm.Gerant.FenetreAffichageRecherche;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

/**
 * <p>La classe ModeleTableauClient h�rite de la classe AbstractTableModel
 * Sa sp�cificit� est de pouvoir afficher uniquement les clients correspondants � la recherche 
 * d'un g�rant selon plusieurs crit�res :<ul>
 * <li> l'identifiant du client</li>
 * <li> le nom pour les particuliers ou la d�nomination pour les collectivit�s</li>
 * <li> la ville</li>
 * </ul>
 * 
 * Les tableaux g�n�r�s � partir de cette classe sont utilis�s dans la fen�tre {@link FenetreAffichageRecherche}
 * 
 */
public class ModeleTableauClient extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	/**
	 * donnees contenues � l'int�rieur du tableau
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
    private final String[] entetes={"Identifiant","Nom","Pr�nom","D�nomination"} ;
    
    /**
     * Construit des tableaux correspondants aux r�sultats de la recherche de clients du g�rant
     * 
     * @see {@link SGBD#recupererInformationRechercheClient(String, String, String, String)}
     * 
     * @param idClient
     * 			String saisi dans le champ de recherche identifiant client
     * @param nom
     * 			String saisi dans le champ de recherche nom client
     * @param denom
     * 			String saisi dans le champ de recherche d�nomination client
     * @param ville
     *			String saisi dans le champ de recherche ville client
     */
	public ModeleTableauClient(String idClient,String nom,String denom,String ville){
		super();
		//Quatre listes sont cr��es pour r�cup�rer les informations de la table ARTICLES
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
	 * 			integer indiquant le num�ro de la ligne de l'�l�ment choisi	
	 * @param colIndex
	 * 			integer indiquant le num�ro de la colonne de l'�l�ment choisi 
	 * 
	 * @return l'objet situ� dans la rowIndex �me ligne et la colIndex �me colonne du tableau  
	 * 			correspondant � la liste des clients correspondants � la recherche du g�rant
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