package ihm;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

public class ModeleMessagerie extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Object[][] donnees;

    private final String[] entetes={"Num�ro Message","Exp�diteur","Sujet","Contenu","Date"} ;
	
	
	public ModeleMessagerie(){
		super();
		//Quatre listes sont cr��es pour r�cup�rer les informations de la table ARTICLES
		ArrayList<String> listeIdentifiants = SGBD.selectListeString("MESSAGE", "IDMESSAGE");
		ArrayList<String> listeContenus = SGBD.selectListeString("MESSAGE", "CONTENUMESSAGE");
		ArrayList<String> listeExpediteur = SGBD.selectListeString("MESSAGE", "IDCLIENT");
		ArrayList<String> listeSujet = SGBD.selectListeString("MESSAGE", "SUJETMESSAGE");
		ArrayList<String> listeDate = SGBD.selectListeString("MESSAGE", "DATEMESSAGE");
		
		
		donnees = new Object[listeSujet.size()][5];
		
		//On ajoute les informations dans l'objet donnees
		for(int i=0;i<listeExpediteur.size();i++){
			donnees[i][0] = listeIdentifiants.get(i);
			donnees[i][1] = listeExpediteur.get(i);
			donnees[i][2] = listeSujet.get(i);
			donnees[i][3] = listeContenus.get(i);
			donnees[i][4] = listeDate.get(i);

		}
	}
	
	
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return donnees.length;
	}

	public Object getValueAt(int rowIndex, int colIndex) {
		// TODO Auto-generated method stub
		return donnees[rowIndex][colIndex];
	}
	
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    
    public void removeAllMessage() {
    	// m�thode pour supprimer tous les messages de la table
        for(int i=0;i==donnees.length;i++){
        	fireTableRowsDeleted(i, i);
        }
        
        
    }

}