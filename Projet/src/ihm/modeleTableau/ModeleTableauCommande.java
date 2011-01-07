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
 * <p>
 * La classe ModeleTableauCommande hérite de la classe AbstractTableModel Sa
 * spécificité est de pouvoir afficher uniquement des données relatives à la
 * commande qui vient d'être effectuée par un client c'est à dire pour chaque
 * article de la commande :
 * <ul>
 * <li>Sa référence.</li>
 * <li>Sa description.</li>
 * <li>La quantité commandée par le client.</li>
 * </ul>
 * Elle est utilisée pour afficher les détails d'une commande après avoir
 * confirmé sa commande dans la fenêtre {@link FenetreCommandeArticle}
 * </p>
 * 
 * @see FenetreCommandeArticle
 * 
 */
public class ModeleTableauCommande extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	/**
	 * Données contenues à l'intérieur du tableau
	 * 
	 * @see ModeleTableauCommande#getRowCount()
	 * @see ModeleTableauCommande#ModeleTableauCommande(ArrayList, Commande,
	 *      String)
	 * @see ModeleTableauCommande#getValueAt(int, int)
	 * 
	 */
	private final Object[][] donnees;

	/**
	 * Liste des noms de colonnes du tableau
	 * 
	 * @see ModeleTableauCommande#getColumnCount()
	 * @see ModeleTableauCommande#getColumnName(int)
	 */
	private final String[] entetes = { "Identifiant", "Description",
			"Quantité", "Total" };

	/**
	 * <p>
	 * Construit un tableau qui affiche les informations d'une commande comme
	 * dans une facture avec :
	 * <ul>
	 * <li>la référence de l'article.</li>
	 * <li>la description de l'article.</li>
	 * <li>la quantité commandée de l'article.</li>
	 * <li>le prix initial de l'article.</li>
	 * </ul>
	 * <p>
	 * 
	 * @param panier
	 *            ArrayList<LigneCommande> contenant l'ensemble des articles
	 *            commandés avec leur quantités commandées
	 * @param com
	 *            Instance de la commande effectuée par le client
	 * @param numClient
	 *            Identifiant du client venant de valider sa commande
	 * @throws SQLException
	 * 
	 *             {@link SGBD#recupererInformationFideliteClient(String)}
	 *             {@link SGBD#selectStringConditionString(String, String, String, String)}
	 *             {@link Commande}
	 * 
	 */
	public ModeleTableauCommande(ArrayList<LigneCommande> panier, Commande com,
			String numClient) throws SQLException {
		super();

		// Initialisation du tableau des donnees
		donnees = new Object[panier.size()][4];

		// Récupération des informations sur la fidélité du client pour
		// faire le calcul du montant de la commande pour chaque article

		ArrayList<String> fideliteClient = SGBD
				.recupererInformationFideliteClient(FenetreDialogIdentification.clientUserIdentifiant);
		int fidelite = 0;
		if (fideliteClient.get(0).equals("Oui")) {
			fidelite = 1;
		}

		int compteurArticle = 0;

		for (LigneCommande ligneCommande : panier) {
			donnees[compteurArticle][0] = ligneCommande.getIdArticle();
			donnees[compteurArticle][1] = SGBD.selectStringConditionString(
					"ARTICLE", "DESCRIPTION", "IDARTICLE",
					ligneCommande.getIdArticle());
			donnees[compteurArticle][2] = ligneCommande.getQuantite();
			donnees[compteurArticle][3] = (int) com
					.montantCommandePourUnArticle(numClient, ligneCommande,
							fidelite)
					+ " €";

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
	 * @return Indique le nombre de lignes dans le tableau
	 */
	public int getRowCount() {
		return donnees.length;
	}

	/**
	 * @param rowIndex
	 *            Integer indiquant le numéro de la ligne de l'élément choisi
	 * @param colIndex
	 *            Integer indiquant le numéro de la colonne de l'élément choisi
	 * 
	 * @return l'objet situé dans la rowIndex ème ligne et la colIndex ème
	 *         colonne du tableau correspondant à la commande effectuée par le
	 *         client
	 */
	public Object getValueAt(int rowIndex, int colIndex) {
		return donnees[rowIndex][colIndex];
	}

	/**
	 * @return le nom de la colonne à l'index columnIndex
	 * 
	 * @param columnIndex
	 *            l'index de la colonne dans le tableau
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

}
