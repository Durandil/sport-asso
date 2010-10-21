package ihm;
import javax.swing.table.AbstractTableModel;

import metier.Article;
import metier.LigneCommande;


public class ModelePromotionClient extends AbstractTableModel {
	
	private final Object[][] donnees;

    private final String[] entetes={" Promotions en cours"} ;
	
	
	public ModelePromotionClient(){
		super();
		
		
		donnees= new Object[][]{
				{"Promotion sur les chaussures running"},
				{"Destockage de chaussettes de football"}
		};
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
    

}