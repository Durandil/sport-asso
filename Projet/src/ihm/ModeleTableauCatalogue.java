package ihm;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

import metier.Article;
import metier.LigneCommande;


public class ModeleTableauCatalogue extends AbstractTableModel {
	
	private final Object[][] donnees;

    private final String[] entetes={"Numero","Denomination","Quantite en stock","Etat"} ;
	
	
	public ModeleTableauCatalogue(/*ArrayList<Article> ensArticles*/){
		super();
		
		//Quatre listes sont créées pour récupérer les informations de la table ARTICLES
		ArrayList<String> listeNumeros = SGBD.selectListeString("ARTICLES", "IDENTIFIANT");
		ArrayList<String> listeDescriptions = SGBD.selectListeString("ARTICLES", "DESCRIPTION");
		ArrayList<Integer> listeStocks = SGBD.selectListeInt("ARTICLES", "STOCK");
		ArrayList<String> listeEtats = SGBD.selectListeString("ARTICLES", "ETAT");
		
		
		donnees = new Object[1000][4];
		
		//On ajoute les informations dans l'objet donnees
		for(int i=0;i<listeDescriptions.size();i++){
			donnees[i][0] = listeNumeros.get(i);
			donnees[i][1] = listeDescriptions.get(i);
			donnees[i][2] = listeStocks.get(i);
			donnees[i][3] = listeEtats.get(i);
		}
		/*
		for( Article element : ensArticles){
				
		 */
		
		/*donnees= new Object[][]{
				{"ART1","Maillot de foot",3,"En stock"},
				{"ART2","Maillot de rugby",6,"En stock"},
				{"ART3","Short de foot",9,"En stock"},
				{"ART4","Crosse de hockey sur glace",4,"En stock"},
				{"ART5","Ballon de basket-ball",7,"En stock"}
		};
		donnees[5][5] = 5;
		*/
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

