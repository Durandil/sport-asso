package ihm;

import javax.swing.table.AbstractTableModel;

public class ModeleTableauCommande extends AbstractTableModel{
	
	private final Object[][] donnees;

    private final String[] entetes={"Identifiant","Description","Quantité","Prix Unitaire","% Remise","Remise","Total"} ;
	
	
	public ModeleTableauCommande(){
		super();
		
		
		donnees= new Object[][]{
				{"ART0001","Maillot foot","10","12,50","15%","0","125"}
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
