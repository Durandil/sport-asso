package ihm.Gerant;

import ihm.modeleTableau.ModeleTableauStatCommande;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import basededonnees.SGBD;

public class FenetreStatistiqueCommande extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// cette classe permettra d'afficher les détails de commandes particulières pour le gérant
	
	public FenetreStatistiqueCommande(JFrame parent, String title, boolean modal,String numCommande){
		super(parent,title,modal);
		this.setSize(700, 300);
        this.setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(numCommande);
		
	}

	private void initComponent(String idCommande) {
		JPanel panneauHautInformations = new JPanel();
		panneauHautInformations.setLayout(new GridLayout(3,1,0,5));
		
		String date = SGBD.selectStringConditionString("COMMANDE", "DATECOMMANDE", "IDCOMMANDE", idCommande);
		JLabel dateCommandeLabel = new JLabel("Date : "+ date);
		JLabel referenceCommandeLabel = new JLabel("Référence commande : " + idCommande);
		JLabel montantCommandeLabel = new JLabel("Montant de la commande : "+ SGBD.selectStringConditionString("COMMANDE", "MONTANTCOMMANDE", "IDCOMMANDE", idCommande)+" €");
		panneauHautInformations.add(dateCommandeLabel);
		panneauHautInformations.add(referenceCommandeLabel);
		panneauHautInformations.add(montantCommandeLabel);
		
		ModeleTableauStatCommande modele = new ModeleTableauStatCommande(idCommande);
		JTable tableau = new JTable(modele);
		JScrollPane tableauCommande = new JScrollPane(tableau);
		tableau.setVisible(true);
		tableau.setEnabled(false);
		
		JButton boutonRetour = new JButton("Retour à la fiche client");
		boutonRetour.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		getContentPane().add(panneauHautInformations,"North");
		getContentPane().add(tableauCommande,"Center");
		getContentPane().add(boutonRetour,"South");
	}
	
}
