package ihm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.AbstractTableModel;

public class ModeleTableauClient extends AbstractTableModel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Object[][] donnees;

    private final String[] entetes={"Identifiant","Dénomination","Nom","Prénom","Ville"} ;
	
	
	public ModeleTableauClient(){
		super();
		
		
		donnees= new Object[][]{
				{"senghor@gmail.com","","Senghor","Leopold","Dakar"}
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


	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		
	}
    

}