package ihm;
import javax.swing.table.AbstractTableModel;

import metier.Article;
import metier.LigneCommande;


public class ModelePanier extends AbstractTableModel {
	
	private final Object[][] donnees;

    private final String[] entetes={"Numero","Denomination","Quantite désirée",} ;
	
	
	public ModelePanier(){
		super();
		
		/*
		for( Article element : ensArticles){
				
		 */
		
		donnees= new Object[][]{
				{"ART1","Maillot de foot",3},
				{"ART2","Maillot de rugby",2},
				{"ART3","Short de foot",1},
				{"ART4","Crosse de hockey sur glace",2},
				{"ART5","Ballon de basket-ball",1}
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
    
    public void removeLigne(int rowIndex) {
       // donnees.remove(rowIndex);

        //fireTableRowsDeleted(rowIndex, rowIndex);
    }


}
