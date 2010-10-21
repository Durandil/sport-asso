package ihm;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import metier.Article;
import metier.LigneCommande;


public class ModeleTableauCatalogue extends AbstractTableModel {
	
	private final Object[][] donnees;

    private final String[] entetes={"Numero","Denomination","Quantite en stock","Etat"} ;
	
	
	public ModeleTableauCatalogue(/*ArrayList<Article> ensArticles*/){
		super();
		
		/*
		for( Article element : ensArticles){
				
		 */
		
		donnees= new Object[][]{
				{"ART1","Maillot de foot",3,"En stock"},
				{"ART2","Maillot de rugby",6,"En stock"},
				{"ART3","Short de foot",9,"En stock"},
				{"ART4","Crosse de hockey sur glace",4,"En stock"},
				{"ART5","Ballon de basket-ball",7,"En stock"}
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

