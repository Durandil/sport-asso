package ihm.modeleTableau;

import ihm.Accueil.FenetreDialogIdentification;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

import metier.CarteFidelite;
import metier.Commande;
import metier.LigneCommande;

public class ModeleTableauCommande extends AbstractTableModel{
	
	private final Object[][] donnees;

    private final String[] entetes={"Identifiant","Description","Quantité","Total"} ;
	
	
	public ModeleTableauCommande(ArrayList<LigneCommande> panier,Commande com,String numClient ) throws SQLException{
		super();

		donnees= new Object[panier.size()][4];
		
		ArrayList<String> fideliteClient= SGBD.recupererInformationFideliteClient(FenetreDialogIdentification.clientUserIdentifiant);
		int fidelite=0;
		if(fideliteClient.get(0).equals("Oui")){
			fidelite=1;
		}
		
		int compteurArticle = 0;
		
		for (LigneCommande ligneCommande : panier) {
			donnees[compteurArticle][0] = ligneCommande.getIdArticle();
			donnees[compteurArticle][1] = SGBD.selectStringConditionString("ARTICLE", "DESCRIPTION", "IDARTICLE", ligneCommande.getIdArticle());
			donnees[compteurArticle][2] = ligneCommande.getQuantite();
			donnees[compteurArticle][3] = (int)com.montantCommandePourUnArticle(numClient, ligneCommande, fidelite)+" €";
			compteurArticle++;
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
	
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    

}
