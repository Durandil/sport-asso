package ihm.Gerant;

import ihm.modeleTableau.ModeleTableauStatCommande;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import basededonnees.SGBD;

public class FenetreStatistiqueCommande extends JFrame {
	// cette classe permettra d'afficher les détails de commandes particulières pour le gérant
	
	public FenetreStatistiqueCommande(String numCommande){
		super();
		this.setSize(700, 300);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); 
		this.initComponent(numCommande);
		
	}

	private void initComponent(String idCommande) {
		// TODO Auto-generated method stub
		String date = SGBD.selectStringConditionString("COMMANDE", "DATECOMMANDE", "IDCOMMANDE", idCommande);
		
		ModeleTableauStatCommande modele = new ModeleTableauStatCommande(idCommande);
		JTable tableau = new JTable(modele);
		JScrollPane tableauCommande = new JScrollPane(tableau);
		
		
		
	}
	
}
