package ihm;
import javax.swing.table.AbstractTableModel;

public class ModeleMessagerie extends AbstractTableModel {
	
	private final Object[][] donnees;

    private final String[] entetes={" Expéditeur","Sujet","Date"} ;
	
	
	public ModeleMessagerie(){
		super();
		
		
		donnees= new Object[][]{
				{"Robert","Article 1","11/05/2010"},
				{"Paul","Destockage de chaussettes de football","12/07/2010"}
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