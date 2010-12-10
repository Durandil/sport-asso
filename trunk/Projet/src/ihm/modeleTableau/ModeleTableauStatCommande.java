package ihm.modeleTableau;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

public class ModeleTableauStatCommande extends AbstractTableModel{

	private static final long serialVersionUID = 1L;

	private final Object[][] donnees;

    private final String[] entetes={"Référence Article","Quantité commandée"} ;
	
	
	public ModeleTableauStatCommande(String idCommande) {
		super();
		// TODO Auto-generated constructor stub
		ArrayList<String> listeArticles = new ArrayList<String>();
		ArrayList<String> listeQuantitesCorrespondantes = new ArrayList<String>();
		
		listeArticles = SGBD.selectListeStringOrdonneCondition("LISTING_ARTICLES_COMMANDES", "IDARTICLE", "IDARTICLE", "IDCOMMANDE='"+idCommande+"'");
		listeQuantitesCorrespondantes = SGBD.selectListeStringOrdonneCondition("LISTING_ARTICLES_COMMANDES", "QUANTITECOMMANDEE", "IDARTICLE", "IDCOMMANDE='"+idCommande+"'");
		
		// TODO éventuellement rechercher description article associé à l'article
		// si l'article existe encore dans la base de données
		
		donnees = new Object[listeArticles.size()][4];
		
		//On ajoute les informations dans l'objet donnees
		for(int i=0;i<listeArticles.size();i++){
			donnees[i][0] = listeArticles.get(i);	
			donnees[i][1] = listeQuantitesCorrespondantes.get(i);
			
		}
		
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

}
