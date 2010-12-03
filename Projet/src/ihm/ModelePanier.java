package ihm;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import metier.Article;
import metier.LigneCommande;


public class ModelePanier extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Object[][] donnees;

    private final String[] entetes={"Numero","Quantite désirée",} ;
	
	
	public ModelePanier(ArrayList<String[]> panier){
		super();
		int taille = 1000;
		if(panier.size()>0){
			taille=panier.size();
		}
		
		donnees= new Object[taille][2];
		int compteur=0;
		for (String[] strings : panier) {
			donnees[compteur][0]=strings[0];
			donnees[compteur][1]=strings[1];
			compteur++;
		}
		
	}
	
	public void actualiserLigne(int rowIndex,ArrayList<String[]> panierCourant){
		//this.donnees[rowIndex][1]=panierCourant.get(rowIndex)[1];
		int compteur=0;
		for (String[] strings : panierCourant) {
			donnees[compteur][0]=strings[0];
			donnees[compteur][1]=strings[1];
			compteur++;
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
    
    public void removeLigne(int rowIndex) {
       // donnees.remove(rowIndex);

        //fireTableRowsDeleted(rowIndex, rowIndex);
    }


}
