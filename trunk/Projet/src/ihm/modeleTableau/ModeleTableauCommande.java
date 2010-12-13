package ihm.modeleTableau;

import javax.swing.table.AbstractTableModel;

public class ModeleTableauCommande extends AbstractTableModel{
	
	private final Object[][] donnees;

    private final String[] entetes={"Identifiant",/*"Description",*/"Quantité","Total"} ;
	
	
	public ModeleTableauCommande(){
		super();
		
		
		donnees= new Object[1000][4];
		
		
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
