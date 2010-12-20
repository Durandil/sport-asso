package ihm.modeleTableau;

import ihm.Accueil.FenetreDialogIdentification;
import ihm.Client.FenetreCommandeArticle;
import ihm.Gerant.FicheClient;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

import metier.CarteFidelite;
import metier.Commande;
import metier.LigneCommande;

/**
 * <p>La classe ModeleTableauCommande h�rite de la classe AbstractTableModel
 * Sa sp�cificit� est de pouvoir afficher uniquement des donn�es relatives � la commande qui vient d'�tre effectu�e par un client
 * c'est � dire pour chaque article de la commande :<ul>
 * <li> Sa r�f�rence.</li>
 * <li> Sa description.</li>
 * <li> La quantit� command�e par le client.</li>
 * </ul>
 * Elle est utilis�e pour afficher les d�tails d'une commande apr�s avoir confirm� sa commande dans la
 * fen�tre {@link FenetreCommandeArticle}
 * </p>
 * 
 * @see FenetreCommandeArticle
 *
 */
public class ModeleTableauCommande extends AbstractTableModel{
	

	private static final long serialVersionUID = 1L;
	/**
	 * donnees contenues � l'int�rieur du tableau
	 * 
	 * @see ModeleTableauCommande#getRowCount()
	 * @see ModeleTableauCommande#ModeleTableauCommande(ArrayList, Commande, String)(String)
	 * @see ModeleTableauCommande#getValueAt(int, int)
	 * 
	 */
	private final Object[][] donnees;
	
	/**
	 * liste des noms de colonnes du tableau
	 * 
	 * @see ModeleTableauCommande#getColumnCount()
	 * @see ModeleTableauCommande#getColumnName(int)
	 */
    private final String[] entetes={"Identifiant","Description","Quantit�","Total"} ;
	
	/**
	 * <p>Construit un tableau qui affiche les informations d'une commande comme dans une facture avec :<ul>
	 * <li> la r�f�rence de l'article.</li>
	 * <li> la description de l'article. </li>
	 * <li> la quantit� command�e de l'article.</li>
	 * <li> le prix initial de l'article.</li>
	 * </ul>
	 * <p>
	 * 
	 * @param panier
	 * 			ArrayList<LigneCommande> contenant l'ensemble des articles command�s avec leur quantit�s command�es
	 * @param com
	 * 			Instance de la commande effectu�e par le client
	 * @param numClient
	 * 			Identifiant du client venant de valider sa commande
	 * @throws SQLException
	 * 
	 * @see {@link SGBD#recupererInformationFideliteClient(String)}
	 * @see {@link SGBD#selectStringConditionString(String, String, String, String)}
	 * @see {@link Commande}
	 * 
	 */
	public ModeleTableauCommande(ArrayList<LigneCommande> panier,Commande com,String numClient) throws SQLException{
		super();

		donnees= new Object[panier.size()][4];
		
		ArrayList<String> fideliteClient= SGBD.recupererInformationFideliteClient(FenetreDialogIdentification.clientUserIdentifiant);
		int fidelite=0;
		if(fideliteClient.get(0).equals("Oui")){
			fidelite=1;
		}
		
		int compteurArticle = 0;
		
		for (LigneCommande ligneCommande : panier) {
			donnees[compteurArticle][0] = ligneCommande.getIdArticle();
			donnees[compteurArticle][1] = SGBD.selectStringConditionString("ARTICLE", "DESCRIPTION", "IDARTICLE", ligneCommande.getIdArticle());
			donnees[compteurArticle][2] = ligneCommande.getQuantite();
			donnees[compteurArticle][3] = (int)com.montantCommandePourUnArticle(numClient, ligneCommande, fidelite)+" �";
			compteurArticle++;
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
