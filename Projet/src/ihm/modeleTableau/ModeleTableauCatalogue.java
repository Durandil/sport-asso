package ihm.modeleTableau;
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

    private final String[] entetes={"Référence","Denomination","Quantite en stock","Prix Initial"} ;
	
    
	
	public ModeleTableauCatalogue() {
		super();
		// TODO Auto-generated constructor stub
		donnees= new Object[1000][5];
	}
	
	
	

	public ModeleTableauCatalogue(boolean pourReapprovisionnement,boolean pourTableauGerant){
		super();
		if( pourReapprovisionnement == false){
			
			if(pourTableauGerant == true){
				// tableau qui  accueillera le catalogue  pour le gérant et qui doit afficher 
				// tous les articles contrairement au client
				
				//Quatre listes sont créées pour récupérer les informations de la table ARTICLE
				ArrayList<String> listeIdentifiants = SGBD.selectListeStringOrdonneCondition("ARTICLE","IDARTICLE","IDARTICLE","ETATARTICLE !='Supprimé'");
				ArrayList<String> listeDescriptions = SGBD.selectListeStringOrdonneCondition("ARTICLE", "DESCRIPTION","IDARTICLE","ETATARTICLE !='Supprimé'");
				ArrayList<Integer> listeStocks = SGBD.selectListeIntOrdonneCondition("ARTICLE", "STOCK","IDARTICLE","ETATARTICLE !='Supprimé'");
				ArrayList<Integer> listeEtats = SGBD.selectListeIntOrdonneCondition("ARTICLE", "PRIXINITIAL","IDARTICLE","ETATARTICLE !='Supprimé'");
			
				
				donnees = new Object[listeIdentifiants.size()][5];
	
				//On ajoute les informations dans l'objet donnees
				for(int i=0;i<listeIdentifiants.size();i++){
					donnees[i][0] = listeIdentifiants.get(i);
					donnees[i][1] = listeDescriptions.get(i);
					donnees[i][2] = listeStocks.get(i);
					donnees[i][3] = listeEtats.get(i);
				
				}
				
			}
			else{ 
				// tableau qui  accueillera le catalogue  pour les clients et qui doit afficher 
				// que les articles sont stock > 0
				
				//Quatre listes sont créées pour récupérer les informations de la table ARTICLE
				ArrayList<String> listeIdentifiants = SGBD.selectListeStringOrdonneCondition("ARTICLE","IDARTICLE","IDARTICLE","STOCK>0 and ETATARTICLE !='Supprimé'");
				ArrayList<String> listeDescriptions = SGBD.selectListeStringOrdonneCondition("ARTICLE", "DESCRIPTION","IDARTICLE","STOCK>0 AND ETATARTICLE !='Supprimé'");
				ArrayList<Integer> listeStocks = SGBD.selectListeIntOrdonneCondition("ARTICLE", "STOCK","IDARTICLE","STOCK>0 AND ETATARTICLE !='Supprimé'");
				ArrayList<Integer> listeEtats = SGBD.selectListeIntOrdonneCondition("ARTICLE", "PRIXINITIAL","IDARTICLE","STOCK>0 AND ETATARTICLE !='Supprimé'");
			
				
				donnees = new Object[listeIdentifiants.size()][5];
	
				//On ajoute les informations dans l'objet donnees
				for(int i=0;i<listeIdentifiants.size();i++){
					donnees[i][0] = listeIdentifiants.get(i);
					donnees[i][1] = listeDescriptions.get(i);
					donnees[i][2] = listeStocks.get(i);
					donnees[i][3] = listeEtats.get(i);
				
				}
			}
			
		}
		else{
			// création des listes pour récupérer les informations des articles qui ont besoin d'être
			// reapprovisionnés
			ArrayList<ArrayList<String>> listeArticles = new ArrayList<ArrayList<String>>() ;
			listeArticles = SGBD.selectArticlesReapprovisionnement();
			ArrayList<String >listeIdentifiants = listeArticles.get(0);
			ArrayList<String >listeDescriptions = listeArticles.get(1);
			ArrayList<String >listeStocks = listeArticles.get(2);
			ArrayList<String >listeEtats = listeArticles.get(3);
			
			donnees = new Object[listeIdentifiants.size()][4];
			
			// on ajoute les informations dans l'objet
			for(int i=0;i< listeIdentifiants.size();i++){
					donnees[i][0] = listeIdentifiants.get(i);
					donnees[i][1] = listeDescriptions.get(i);
					donnees[i][2] = listeStocks.get(i);
					donnees[i][3] = listeEtats.get(i);
			}
			
		
		}
	}
	

	
	
//	
//	public void ajouterLigne(){
//		ArrayList<String> listeIdentifiants = SGBD.selectListeStringOrdonneCondition("ARTICLE","IDARTICLE","IDARTICLE","STOCK>0");
//		ArrayList<String> listeDescriptions = SGBD.selectListeStringOrdonneCondition("ARTICLE", "DESCRIPTION","IDARTICLE","STOCK>0");
//		ArrayList<Integer> listeStocks = SGBD.selectListeIntOrdonneCondition("ARTICLE", "STOCK","IDARTICLE","STOCK>0");
//		ArrayList<Integer> listeEtats = SGBD.selectListeIntOrdonneCondition("ARTICLE", "PRIXINITIAL","IDARTICLE","STOCK>0");
//		
//		System.out.println("nombre d'articles après ajout : "+ listeIdentifiants.size());
//		System.out.println(listeIdentifiants.get(listeIdentifiants.size()-1));
//		
//		donnees[listeIdentifiants.size()][0]= listeIdentifiants.get(listeIdentifiants.size()-1);
//		donnees[listeIdentifiants.size()][1]= listeDescriptions.get(listeIdentifiants.size()-1);
//		donnees[listeIdentifiants.size()][2]= listeStocks.get(listeIdentifiants.size()-1);
//		donnees[listeIdentifiants.size()][3]= listeEtats.get(listeIdentifiants.size()-1);
//	}
//	
//	public void actualiserTableau(boolean pourReapprovisionnement){
//		if(pourReapprovisionnement==false){
//			ArrayList<String> listeIdentifiants = SGBD.selectListeStringOrdonneCondition("ARTICLE","IDARTICLE","IDARTICLE","STOCK>0");
//			ArrayList<String> listeDescriptions = SGBD.selectListeStringOrdonneCondition("ARTICLE", "DESCRIPTION","IDARTICLE","STOCK>0");
//			ArrayList<Integer> listeStocks = SGBD.selectListeIntOrdonneCondition("ARTICLE", "STOCK","IDARTICLE","STOCK>0");
//			ArrayList<Integer> listeEtats = SGBD.selectListeIntOrdonneCondition("ARTICLE", "PRIXINITIAL","IDARTICLE","STOCK>0");
//			
//			for(int i=0;i<listeIdentifiants.size();i++){
//				donnees[i][0] = listeIdentifiants.get(i);
//				donnees[i][1] = listeDescriptions.get(i);
//				donnees[i][2] = listeStocks.get(i);
//				donnees[i][3] = listeEtats.get(i);
//			
//			}
//		}
//		else{
//			ArrayList<ArrayList<String>> listeArticles = new ArrayList<ArrayList<String>>() ;
//			listeArticles = SGBD.selectArticlesReapprovisionnement();
//			ArrayList<String >listeIdentifiants = listeArticles.get(0);
//			ArrayList<String >listeDescriptions = listeArticles.get(1);
//			ArrayList<String >listeStocks = listeArticles.get(2);
//			ArrayList<String >listeEtats = listeArticles.get(3);
//			
//			for(int i=0;i< listeIdentifiants.size();i++){
//				donnees[i][0] = listeIdentifiants.get(i);
//				donnees[i][1] = listeDescriptions.get(i);
//				donnees[i][2] = listeStocks.get(i);
//				donnees[i][3] = listeEtats.get(i);
//			}
//		}
//	}
//	
//	public void updateLigne(int numeroDernierLigneTableauAvant, boolean pourReapprovisionnement){
//		
//		if(pourReapprovisionnement==true){
//			// il faudrait updater le nombre de lignes présentes avant dans le tableau
//			// puis deeleter le nombre de lignes ( si en trop par rapport ancien tableau)
//
//			
//			ArrayList<ArrayList<String>> listeArticles = new ArrayList<ArrayList<String>>() ;
//			listeArticles = SGBD.selectArticlesReapprovisionnement();
//			ArrayList<String >listeIdentifiants = listeArticles.get(0);
//			ArrayList<String >listeDescriptions = listeArticles.get(1);
//			ArrayList<String >listeStocks = listeArticles.get(2);
//			ArrayList<String >listeEtats = listeArticles.get(3);
//			
//			for(int i=0;i< listeIdentifiants.size();i++){
//				donnees[i][0] = listeIdentifiants.get(i);
//				donnees[i][1] = listeDescriptions.get(i);
//				donnees[i][2] = listeStocks.get(i);
//				donnees[i][3] = listeEtats.get(i);
//			}
//			
//			if(numeroDernierLigneTableauAvant>listeIdentifiants.size()){
//				
//				int difference = numeroDernierLigneTableauAvant-listeIdentifiants.size();
//				for(int j= 0 ; j< difference;j++){
//					this.fireTableRowsDeleted(j, j);
//				}
//				
//			}
//			else{
//				
//				for (int i = 0; i <= numeroDernierLigneTableauAvant; i++) {
//					this.fireTableRowsUpdated(i, i);
//				}
//			}
//
//		}
//		
//	}
	
	
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

