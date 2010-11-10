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

    private final String[] entetes={" Expéditeur","Sujet","Date"} ;
	
	
	public ModeleMessagerie(){
		super();
		//Quatre listes sont créées pour récupérer les informations de la table ARTICLES
		ArrayList<String> listeExpediteur = SGBD.selectListeString("MESSAGE", "EXPEDITEUR");
		ArrayList<String> listeSujet = SGBD.selectListeString("MESSAGE", "SUJET");
		ArrayList<Integer> listeDate = SGBD.selectListeInt("MESSAGE", "DATE");
		
		
		donnees = new Object[1000][3];
		
		//On ajoute les informations dans l'objet donnees
		for(int i=0;i<listeExpediteur.size();i++){
			donnees[i][0] = listeExpediteur.get(i);
			donnees[i][1] = listeSujet.get(i);
			donnees[i][2] = listeDate.get(i);

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
    	// méthode pour supprimer tous les messages de la table
        for(int i=0;i==donnees.length;i++){
        	fireTableRowsDeleted(i, i);
        }
        
        
    }

}