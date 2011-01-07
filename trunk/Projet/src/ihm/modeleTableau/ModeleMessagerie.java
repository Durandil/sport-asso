package ihm.modeleTableau;

import ihm.Accueil.FenetreDialogIdentification;
import ihm.Client.FenetreContactVendeur;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

/**
 * La classe ModeleMessagerie h�rite de la classe AbstractTableModel permettant
 * l'utilisation de tableau de donn�es. Nous allons utiliser cette classe pour
 * afficher l'ensemble des messages pour un utilisateur donn�.
 * 
 * @see FenetreContactVendeur
 * @see ihm.FenetreMessagerie
 */
public class ModeleMessagerie extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	/**
	 * Tableau qui contiendra les messages
	 * 
	 */
	private final Object[][] donnees;

	/**
	 * Liste des noms de colonnes du tableau
	 * 
	 * @see ModeleMessagerie#getColumnCount()
	 * @see ModeleMessagerie#getColumnName(int)
	 */
	private final String[] entetes = { "Num�ro Message", "Exp�diteur", "Sujet",
			"Contenu", "Date" };

	/**
	 * Constructeur de la classe ModeleMessagerie qui permet de cr�er une boite
	 * de r�ception ou encore une messagerie pour les diff�rents utilisateurs de
	 * l'application.
	 * 
	 * @param messagerieGerant
	 *            <p>
	 *            Bool�en indiquant si la messagerie est celle du g�rant ou
	 *            celle d'un client :
	 *            <ul>
	 *            <li>true pour le g�rant.</li>
	 *            <li>false pour les clients.</li>
	 *            </ul>
	 *            <p>
	 * 
	 * @see FenetreContactVendeur
	 * @see ihm.FenetreMessagerie
	 */
	public ModeleMessagerie(boolean messagerieGerant) {
		super();

		// Premier cas : Nous voulons afficher la messagerie du g�rant
		if (messagerieGerant == true) {
			
			// Cinq listes sont cr��es pour r�cup�rer les informations de la table MESSAGE

			ArrayList<String> listeIdentifiants = SGBD
					.selectListeStringOrdonneCondition("MESSAGE", "IDMESSAGE",
							"IDMESSAGE", "ESTENVOYEAUGERANT=1");
			ArrayList<String> listeContenus = SGBD
					.selectListeStringOrdonneCondition("MESSAGE",
							"CONTENUMESSAGE", "IDMESSAGE",
							"ESTENVOYEAUGERANT=1");
			ArrayList<String> listeExpediteur = SGBD
					.selectListeStringOrdonneCondition("MESSAGE", "IDCLIENT",
							"IDMESSAGE", "ESTENVOYEAUGERANT=1");
			ArrayList<String> listeSujet = SGBD
					.selectListeStringOrdonneCondition("MESSAGE",
							"SUJETMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=1");
			ArrayList<String> listeDate = SGBD
					.selectListeStringOrdonneCondition("MESSAGE",
							"DATEMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=1");

			donnees = new Object[listeIdentifiants.size()][5];

			// Nous ajoutons les informations dans l'objet donnees
			for (int i = 0; i < listeExpediteur.size(); i++) {
				donnees[i][0] = listeIdentifiants.get(i);

				donnees[i][1] = listeExpediteur.get(i);
				donnees[i][2] = listeSujet.get(i);
				donnees[i][3] = listeContenus.get(i);
				donnees[i][4] = listeDate.get(i);
			}

		}
		// Second cas : Nous voulons afficher la messagerie d'un client
		else {
			// Cinq listes sont cr��es pour r�cup�rer les informations de la
			// table MESSAGES
			ArrayList<String> listeIdentifiants = SGBD
					.selectListeStringOrdonneCondition("MESSAGE", "IDMESSAGE",
							"IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeContenus = SGBD
					.selectListeStringOrdonneCondition("MESSAGE",
							"CONTENUMESSAGE", "IDMESSAGE",
							"ESTENVOYEAUGERANT=0");
			ArrayList<String> listeDestinataire = SGBD
					.selectListeStringOrdonneCondition("MESSAGE", "IDCLIENT",
							"IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeSujet = SGBD
					.selectListeStringOrdonneCondition("MESSAGE",
							"SUJETMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeDate = SGBD
					.selectListeStringOrdonneCondition("MESSAGE",
							"DATEMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=0");

			int compteurNombreMessages = 0;

			// Calcul du nombre de messages du client qui utilise l'application
			for (int i = 0; i < listeDestinataire.size(); i++) {
				if (listeDestinataire.get(i).equals(
						FenetreDialogIdentification.clientUserIdentifiant)) {
					compteurNombreMessages++;
				}
			}

			donnees = new Object[compteurNombreMessages][5];

			// Nous ajoutons les informations des messages du client dans l'objet
			// donnees

			int affichageMessage = 0;
			for (int i = 0; i < listeDestinataire.size(); i++) {

				if (listeDestinataire.get(i).equals(
						FenetreDialogIdentification.clientUserIdentifiant)) {

					donnees[affichageMessage][0] = listeIdentifiants.get(i);
					donnees[affichageMessage][1] = FenetreDialogIdentification.identifiantGerant;
					donnees[affichageMessage][2] = listeSujet.get(i);
					donnees[affichageMessage][3] = listeContenus.get(i);
					donnees[affichageMessage][4] = listeDate.get(i);
					affichageMessage++;
				}
			}

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
	 * @return L'objet situ� dans la ligne num�ro rowIndex et la colonne num�ro
	 *         colIndex du tableau des messages
	 */
	public Object getValueAt(int rowIndex, int colIndex) {

		return donnees[rowIndex][colIndex];
	}

	/**
	 * Retourne le libell� de la colonne situ� � l'index sp�cifi� en param�tre
	 * 
	 * @param columnIndex
	 *            num�ro de la colonne dont on veut conna�tre le nom
	 * 
	 * @return Le libell� de la colonne situ� � l'index sp�cifi� en param�tre
	 *         (int)
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	/**
	 * M�thode qui supprime tous les messages de la table
	 */
	public void removeAllMessage() {

		for (int i = 0; i == donnees.length; i++) {
			fireTableRowsDeleted(i, i);
		}

	}

}