package ihm.modeleTableau;
import ihm.Accueil.FenetreDialogIdentification;
import ihm.Client.FenetreContactVendeur;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;


/**
 * La classe ModeleMessagerie h�rite de la classe AbstractTableModel permettant l'utilisation
 * de tableau de donn�es. Nous allons utiliser cette classe pour afficher l'ensemble des messages pour 
 * un utilisateur donn�
 * 
 * @see FenetreContactVendeur
 * @see FenetreMessagerie
 */
public class ModeleMessagerie extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	/**
	 * donnees contenues � l'int�rieur du tableau qui contiendra les messages
	 * 
	 */
	private final Object[][] donnees;
	
	/**
	 * liste des noms de colonnes du tableau
	 * 
	 * @see ModeleMessagerie#getColumnCount()
	 * @see ModeleMessagerie#getColumnName(int)
	 */
    private final String[] entetes={"Num�ro Message","Exp�diteur","Sujet","Contenu","Date"} ;
	
	/**
	 * Constructeur de la classe ModeleMessagerie qui permet de cr�er une boite de r�ception 
	 * ou encore une messagerie pour les diff�rents utilisateurs de l'application
	 * 
	 * @param messagerieGerant
	 * 		<p> Bool�en indiquant si la messagerie est celle du g�rant ou celle d'un client :<ul> 
	 * 		<li> true pour le g�rant.</li>
	 * 		<li> false pour les clients.</li>
	 * 		</ul>
	 * 		<p>
	 * 
	 * @see FenetreContactVendeur
	 * @see FenetreMessagerie
	 */
	public ModeleMessagerie(boolean messagerieGerant){
		super();
		
		
		if(messagerieGerant == true){
			//Cinq listes sont cr��es pour r�cup�rer les informations de la table MESSAGE
			ArrayList<String> listeIdentifiants = SGBD.selectListeStringOrdonneCondition("MESSAGE", "IDMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=1");
			ArrayList<String> listeContenus = SGBD.selectListeStringOrdonneCondition("MESSAGE", "CONTENUMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=1");
			ArrayList<String> listeExpediteur = SGBD.selectListeStringOrdonneCondition("MESSAGE", "IDCLIENT", "IDMESSAGE", "ESTENVOYEAUGERANT=1");
			ArrayList<String> listeSujet = SGBD.selectListeStringOrdonneCondition("MESSAGE", "SUJETMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=1");
			ArrayList<String> listeDate = SGBD.selectListeStringOrdonneCondition("MESSAGE", "DATEMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=1");
			
			
			
			donnees = new Object[listeIdentifiants.size()][5];
			
			//On ajoute les informations dans l'objet donnees
			for(int i=0;i<listeExpediteur.size();i++){
				donnees[i][0] = listeIdentifiants.get(i);
				
				donnees[i][1] = listeExpediteur.get(i);
				donnees[i][2] = listeSujet.get(i);
				donnees[i][3] = listeContenus.get(i);
				donnees[i][4] = listeDate.get(i);
			}

		}
		else{
			//Cinq listes sont cr��es pour r�cup�rer les informations de la table MESSAGES
			ArrayList<String> listeIdentifiants = SGBD.selectListeStringOrdonneCondition("MESSAGE", "IDMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeContenus = SGBD.selectListeStringOrdonneCondition("MESSAGE", "CONTENUMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeExpediteur = SGBD.selectListeStringOrdonneCondition("MESSAGE", "IDCLIENT", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeSujet = SGBD.selectListeStringOrdonneCondition("MESSAGE", "SUJETMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeDate = SGBD.selectListeStringOrdonneCondition("MESSAGE", "DATEMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			
			int compteurNombreMessages = 0;
			
			// Calcul du nombre de messages du client qui utilise l'application
			for(int i=0;i<listeExpediteur.size();i++){			
				if(listeExpediteur.get(i).equals(FenetreDialogIdentification.clientUserIdentifiant)){
					compteurNombreMessages++;			
				}
			}
			
			donnees = new Object[compteurNombreMessages][5];
			
			//On ajoute les informations des messages du client dans l'objet donnees

			int affichageMessage = 0;
			for(int i=0;i<listeExpediteur.size();i++){
				
				if(listeExpediteur.get(i).equals(FenetreDialogIdentification.clientUserIdentifiant)){
					
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
	 * @return le nombre de colonnes dans le tableau
	 */
	public int getColumnCount() {
		
		return entetes.length;
	}
	
	/**
	 * @return le nombre de lignes dans le tableau
	 */
	public int getRowCount() {
		
		return donnees.length;
	}
	
	/**
	 * @return l'objet situ� dans la rowIndex �me ligne et la colIndex �me colonne du tableau des 
	 * 			messages
	 */
	public Object getValueAt(int rowIndex, int colIndex) {
		
		return donnees[rowIndex][colIndex];
	}
	
	/**
	 * @param columnIndex num�ro de la colonne dont on veut conna�tre le nom
	 * 
	 * @return le libell� de la colonne situ� � l'index sp�cifi� en param�tre (int)
	 */
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    
    /**
     * m�thode pour supprimer tous les messages de la table
     */
    public void removeAllMessage() {

        for(int i=0;i==donnees.length;i++){
        	fireTableRowsDeleted(i, i);
        }
       
    }

}