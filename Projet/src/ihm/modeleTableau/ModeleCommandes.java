package ihm.modeleTableau;
import javax.swing.table.AbstractTableModel;

import metier.Article;
import metier.LigneCommande;


public class ModeleCommandes extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Object[][] donnees;

    private final String[] entetes={" Numéro Article","Quantité en stock"} ;
	
	
	public ModeleCommandes(){
		super();
		
		
		donnees= new Object[][]{
				{"ART001",1},
				{"ART002",0}
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