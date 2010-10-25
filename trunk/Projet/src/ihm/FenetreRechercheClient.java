package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FenetreRechercheClient extends JDialog{
	private JLabel numeroLabel;
	private JTextField numero;
	private JLabel nomLabel;
	private JTextField nom;
	private JLabel villeLabel;
	private JTextField ville;
	private JTextField denomination;
	private JLabel denominationLabel;
	private JSplitPane split;

	// Le gérant pourra rechercher un client en fonction de son numéro, nom, ville
	
	
	public FenetreRechercheClient(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(400,800);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	private void initComponent(){
		
		// Définition du panneau qui acceuillera tous les panneaux avec les principaux critères de recherche de clients
		JPanel panneauChampsRecherche = new JPanel();
		panneauChampsRecherche.setBorder(BorderFactory.createTitledBorder("Recherche de clients selon :"));
		panneauChampsRecherche.setLayout(new GridLayout(4,1,5,5));
		
		// Définition d'un panneau avec le premier critère de recherche : le numéro du client
		JPanel panneauNumero = new JPanel();
		panneauNumero.setBackground(Color.white);
		panneauNumero.setPreferredSize(new Dimension(220, 60));
		panneauNumero.setBorder(BorderFactory.createTitledBorder("Numéro"));
		numeroLabel = new JLabel("Numéro: ");
		numero = new JTextField();
		numero.setPreferredSize(new Dimension(90, 25));
		panneauNumero.add(numeroLabel);
		panneauNumero.add(numero);
		
		// Définition d'un panneau avec un autre critère de recherche : le nom du client pour les particuliers
		JPanel panneauNom = new JPanel();
		panneauNom.setBackground(Color.white);
		panneauNom.setPreferredSize(new Dimension(220, 60));
		panneauNom.setBorder(BorderFactory.createTitledBorder("Nom"));
		nomLabel = new JLabel("Nom: ");
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(90, 25));
		panneauNom.add(nomLabel);
		panneauNom.add(nom);
		
		// Définition d'un panneau avec un autre critère de recherche : la dénomination du client 
		// pour les collectivités ou associations
		JPanel panDenomination= new JPanel();
		panDenomination.setBackground(Color.white);
		panDenomination.setPreferredSize(new Dimension(220, 60));
		panDenomination.setBorder(BorderFactory.createTitledBorder("Denomination"));
		denomination=new JTextField();
		denomination.setPreferredSize(new Dimension(90, 25));
		denominationLabel=new JLabel("Denomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);
		
		// Définition d'un panneau avec le dernier critère de recherche : la ville du client
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(new Dimension(220, 60));
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");
		ville = new JTextField();
		ville.setPreferredSize(new Dimension(90, 25));
		panVille.add(villeLabel);
		panVille.add(ville);
		
		// Pour finir, nous ajoutons ces panneaux au grand panneau de recherche d'un client
		panneauChampsRecherche.add(panneauNumero);
		panneauChampsRecherche.add(panDenomination);
		panneauChampsRecherche.add(panneauNom);
		panneauChampsRecherche.add(panVille);
		
		
		
		// TODO Faire le panneau de la recherche client;
		final JTable tableauRechercheClient = new JTable(new ModeleTableauClient());
		tableauRechercheClient.setVisible(false);
		
		// Création du bouton de validation de la recherche
		JButton boutonValidationRecherche = new JButton("Rechercher");
		boutonValidationRecherche.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				//Auto-generated method stub
				// TODO afficher un tableau en dessous avec les résultats de la recherche
				// setVisible(=True) permettra d'afficher le tableau  avec les résultats de la recherche
				tableauRechercheClient.setVisible(true);
			}
		});
		
		
		// Définition du panneau des boutons du bas
		JButton boutonRetour= new JButton("Retour à la page précédente");
		
		boutonRetour.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				//TODO Auto-generated method stub
				setVisible(false);
				
			}
		});
		
		// Création d'un panneau qui regroupera : le bouton de recherche des clients selon les critères entrés en paramètre
		// le tableau des résultats de la recherche
		// et le bouton de retour à la page précédante
		// crées et implémentés au dessus
		JPanel panneauBas = new JPanel();
		panneauBas.setLayout(new BorderLayout());
		panneauBas.add(boutonValidationRecherche,"North");
		panneauBas.add(new JScrollPane(tableauRechercheClient),"Center");
		panneauBas.add(boutonRetour,"South");
		
		// TODO Afficher un champ permettant au gérant de rentrer le numero de client que le gérant souhaite consulter
		
		// Ajout d'une séparation horizontale afin de séparer les champs de recherche et l'affichage des résultats
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panneauChampsRecherche, panneauBas);
		this.getContentPane().add(split,"Center");
	}
	
}

