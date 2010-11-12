package ihm;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

import metier.Article;
import metier.LigneCommande;

public class ModelePromotionClient extends AbstractTableModel {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private final Object[][] donnees;

	private final String[] entetes = { " Promotions en cours", "Date de début",
			"Date de fin" };
	// Trois listes sont créées pour récupérer les informations de la table
	// PROMO
	ArrayList<String> listePromos = SGBD.selectListeString("PROMO", "NOMPROMO");
	ArrayList<String> listeDatesDebut = SGBD.selectListeString("PROMO",
			"DATEDEBUT");
	ArrayList<String> listeDatesFin = SGBD.selectListeString("PROMO", "DATEFIN");

	public ModelePromotionClient() {
		super();
		donnees = new Object[1000][4];
		for (int i = 0; i < listePromos.size(); i++) {
			donnees[i][0] = listePromos.get(i);
			donnees[i][1] = listeDatesDebut.get(i);
			donnees[i][2] = listeDatesFin.get(i);

		}
		;
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