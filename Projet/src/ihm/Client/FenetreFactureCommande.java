package ihm.Client;

import ihm.modeleTableau.ModeleTableauCommande;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import basededonnees.SGBD;

public class FenetreFactureCommande extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JLabel nomPrenomLabel,adresseLabel,cpVilleLabel;
	private JLabel dateCommandeLabel;
	private JLabel numCommandeLabel;
	private JLabel totalLabel;
	
	public FenetreFactureCommande(JFrame parent, String title, boolean modal, String identifiantClient, String identifantCommande ){
		super(parent, title, modal);
		this.setSize(600, 650);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent(identifiantClient,identifantCommande);
	}
	
	private void initComponent(String idCommande, String idClient){
		// faire des m�thodes de r�cup�ration du listing des articles de la commande n�idCommande 
		// avec leur quantit�, de r�cup�ration des caract�ristiques de la commande (date)
		// calculer le total par article (en fonction de la quantit� appliquer % reduction )
		// et le montant total ( faire utiliser bon achat eventuellement)
		// en d�duire le nombre de points � rajouter sur carte fidelit�
		

		String nom = SGBD.selectStringConditionString("CLIENT", "NOMCLIENT", "IDCLIENT", idClient);
		String prenom = SGBD.selectStringConditionString("CLIENT", "PRENOMCLIENT", "IDCLIENT", idClient);
		String denomination = SGBD.selectStringConditionString("CLIENT", "DENOMINATIONCLIENT", "IDCLIENT", idClient);
		String adresse = SGBD.selectStringConditionString("CLIENT", "NOMCLIENT", "ADRESSECLIENT", idClient);
		String codePostal = SGBD.selectStringConditionString("CLIENT", "NOMCLIENT", "IDCLIENT", idClient);
		String ville = SGBD.selectStringConditionString("CLIENT", "NOMCLIENT", "IDCLIENT", idClient);
		
		// TODO compl�ter informationsCommande dans SGBD
		
		// creation du panneau du haut avec caract�ristiques client
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new GridLayout(1,2,5,5));
		
		JPanel panneauCaracteristiquesClient = new JPanel();
		nomPrenomLabel = new JLabel(nom.toUpperCase()+" "+prenom+denomination.toUpperCase());
		adresseLabel = new JLabel(adresse);
		cpVilleLabel = new JLabel(codePostal+" "+ville);
		
		panneauCaracteristiquesClient.add(nomPrenomLabel);
		panneauCaracteristiquesClient.add(adresseLabel);
		panneauCaracteristiquesClient.add(cpVilleLabel);
		
		
		JPanel panneauDateCommande = new JPanel();
		dateCommandeLabel = new JLabel("Date commande : ");
		numCommandeLabel = new JLabel("Num�ro de commande : ");
		panneauDateCommande.add(dateCommandeLabel);
		panneauDateCommande.add(numCommandeLabel);
		
		panneauHaut.add(panneauCaracteristiquesClient);
		panneauHaut.add(panneauDateCommande);
		this.getContentPane().add(panneauHaut,"North");
		
		// creation du tableau avec le listing de la commande
		JTable tableau = new JTable(new ModeleTableauCommande());
		this.getContentPane().add(new JScrollPane(tableau),"Center");
		
		// panneau de l'affichage du total
		JPanel panneauBas = new JPanel();
		panneauBas.setBorder(BorderFactory.createLineBorder(Color.gray));
		totalLabel = new JLabel("Total commande : ");
		panneauBas.add(totalLabel);
		this.getContentPane().add(panneauBas,"South");
		
	}

}
