package ihm;
import javax.swing.table.AbstractTableModel;

public class ModeleTableauClient extends AbstractTableModel {
	
	private final Object[][] donnees;

    private final String[] entetes={"Numéro","Dénomination","Nom","Prénom","Ville"} ;
	
	
	public ModeleTableauClient(){
		super();
		
		
		donnees= new Object[][]{
				{"001","","Senghor","Leopold","Dakar"}
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