package ihm.modeleTableau;
import ihm.Accueil.FenetreDialogIdentification;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

public class ModeleMessagerie extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private final Object[][] donnees;

    private final String[] entetes={"Numéro Message","Expéditeur","Sujet","Contenu","Date"} ;
	
	
	public ModeleMessagerie(boolean messagerieGerant){
		super();
		
		
		if(messagerieGerant == true){
			//Quatre listes sont créées pour récupérer les informations de la table ARTICLES
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
			//Quatre listes sont créées pour récupérer les informations de la table MESSAGES
			ArrayList<String> listeIdentifiants = SGBD.selectListeStringOrdonneCondition("MESSAGE", "IDMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeContenus = SGBD.selectListeStringOrdonneCondition("MESSAGE", "CONTENUMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeExpediteur = SGBD.selectListeStringOrdonneCondition("MESSAGE", "IDCLIENT", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeSujet = SGBD.selectListeStringOrdonneCondition("MESSAGE", "SUJETMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			ArrayList<String> listeDate = SGBD.selectListeStringOrdonneCondition("MESSAGE", "DATEMESSAGE", "IDMESSAGE", "ESTENVOYEAUGERANT=0");
			
			int compteurNombreMessages = 0;
			
			for(int i=0;i<listeExpediteur.size();i++){
				
				if(listeExpediteur.get(i).equals(FenetreDialogIdentification.clientUserIdentifiant)){
					compteurNombreMessages++;
					
				}
			}
			
			donnees = new Object[compteurNombreMessages][5];
			
			//On ajoute les informations dans l'objet donnees
			
			System.out.println("liste exp" + listeExpediteur.size());
			int affichageMessage = 0;
			for(int i=0;i<listeExpediteur.size();i++){
				
				if(listeExpediteur.get(i).equals(FenetreDialogIdentification.clientUserIdentifiant)){
					
					
					donnees[affichageMessage][0] = listeIdentifiants.get(i);
					donnees[affichageMessage][1] = listeExpediteur.get(i);
					donnees[affichageMessage][2] = listeSujet.get(i);
					donnees[affichageMessage][3] = listeContenus.get(i);
					donnees[affichageMessage][4] = listeDate.get(i);
					affichageMessage++;
				}
			}
			
			
		}
		
	}
	
	
	public int getColumnCount() {
		
		return entetes.length;
	}

	public int getRowCount() {
		
		return donnees.length;
	}

	public Object getValueAt(int rowIndex, int colIndex) {
		
		return donnees[rowIndex][colIndex];
	}
	
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    
    public void removeAllMessage() {
    	// méthode pour supprimer tous les messages de la table
        for(int i=0;i==donnees.length;i++){
        	fireTableRowsDeleted(i, i);
        }
        
        
    }

}