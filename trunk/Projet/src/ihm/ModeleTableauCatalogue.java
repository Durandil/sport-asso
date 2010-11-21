package ihm;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

import metier.Article;
import metier.LigneCommande;


public class ModeleTableauCatalogue extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Object[][] donnees;

    private final String[] entetes={"Numero","Denomination","Quantite en stock","Prix Initial"} ;
	
	
	public ModeleTableauCatalogue(boolean pourReapprovisionnement){
		super();
		if( pourReapprovisionnement == false){
			//Quatre listes sont créées pour récupérer les informations de la table ARTICLE
			ArrayList<String> listeIdentifiants = SGBD.selectListeString("ARTICLE","IDARTICLE");
			ArrayList<String> listeDescriptions = SGBD.selectListeString("ARTICLE", "DESCRIPTION");
			ArrayList<Integer> listeStocks = SGBD.selectListeInt("ARTICLE", "STOCK");
			ArrayList<Integer> listeEtats = SGBD.selectListeInt("ARTICLE", "PRIXINITIAL");
		
			
			donnees = new Object[listeIdentifiants.size()][5];
			System.out.println("test l1 " + listeIdentifiants.size());
			System.out.println("test l2 " + listeDescriptions.size());
			System.out.println("test l3 " + listeStocks.size());
			System.out.println("test l4 " + listeEtats.size());
			//On ajoute les informations dans l'objet donnees
			for(int i=0;i<listeIdentifiants.size();i++){
				donnees[i][0] = listeIdentifiants.get(i);
				donnees[i][1] = listeDescriptions.get(i);
				donnees[i][2] = listeStocks.get(i);
				donnees[i][3] = listeEtats.get(i);
			
			}
		}
		else{
			// création des listes pour récupérer les informations des articles qui ont besoin d'être
			// reapprovisionnés
			ArrayList<ArrayList<String>> listeArticles = new ArrayList<ArrayList<String>>() ;
			
			donnees = new Object[1000][4];
			
			// on ajoute les informations dans l'objet
			int j=0;
			for (ArrayList<String> arrayList : listeArticles) {
				for(int i=0;i< arrayList.size();i++){
					donnees[i][j] = arrayList.get(i);
				}
				j++;
			}
		
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

