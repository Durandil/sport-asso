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

	// Le g�rant pourra rechercher un client en fonction de son num�ro, nom, ville
	
	
	public FenetreRechercheClient(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(400,800);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	private void initComponent(){
		
		JPanel panneauChampsRecherche = new JPanel();
		panneauChampsRecherche.setBorder(BorderFactory.createTitledBorder("Recherche de clients selon :"));
		panneauChampsRecherche.setLayout(new GridLayout(4,1,5,5));
		
		JPanel panneauNumero = new JPanel();
		panneauNumero.setBackground(Color.white);
		panneauNumero.setPreferredSize(new Dimension(220, 60));
		panneauNumero.setBorder(BorderFactory.createTitledBorder("Num�ro"));
		numeroLabel = new JLabel("Num�ro: ");
		numero = new JTextField();
		numero.setPreferredSize(new Dimension(90, 25));
		panneauNumero.add(numeroLabel);
		panneauNumero.add(numero);
		
		JPanel panneauNom = new JPanel();
		panneauNom.setBackground(Color.white);
		panneauNom.setPreferredSize(new Dimension(220, 60));
		panneauNom.setBorder(BorderFactory.createTitledBorder("Nom"));
		nomLabel = new JLabel("Nom: ");
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(90, 25));
		panneauNom.add(nomLabel);
		panneauNom.add(nom);
		
		JPanel panDenomination= new JPanel();
		panDenomination.setBackground(Color.white);
		panDenomination.setPreferredSize(new Dimension(220, 60));
		panDenomination.setBorder(BorderFactory.createTitledBorder("Denomination"));
		denomination=new JTextField();
		denomination.setPreferredSize(new Dimension(90, 25));
		denominationLabel=new JLabel("Denomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);
		
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(new Dimension(220, 60));
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");
		ville = new JTextField();
		ville.setPreferredSize(new Dimension(90, 25));
		panVille.add(villeLabel);
		panVille.add(ville);
		
		panneauChampsRecherche.add(panneauNumero);
		panneauChampsRecherche.add(panDenomination);
		panneauChampsRecherche.add(panneauNom);
		panneauChampsRecherche.add(panVille);
		
		
		
		// TODO Faire le panneau de la recherche client;
		final JTable tableauRechercheClient = new JTable(new ModeleTableauClient());
		tableauRechercheClient.setVisible(false);
		
		// Cr�ation du bouton de validation de la recherche
		JButton boutonValidationRecherche = new JButton("Rechercher");
		boutonValidationRecherche.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				//Auto-generated method stub
				// TODO afficher un tableau dessous avec les r�sultats de la recherche
				// setVisible du tableau (True) en affichant r�sultat
				tableauRechercheClient.setVisible(true);
			}
		});
		
		
		// Panneau des boutons du bas
		JButton boutonRetour= new JButton("Retour � la page pr�c�dente");
		
		boutonRetour.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				//TODO Auto-generated method stub
				setVisible(false);
				
			}
		});
		
		JPanel panneauBas = new JPanel();
		panneauBas.setLayout(new BorderLayout());
		panneauBas.add(boutonValidationRecherche,"North");
		panneauBas.add(new JScrollPane(tableauRechercheClient),"Center");
		panneauBas.add(boutonRetour,"South");
		
		// Ajout d'une s�paration verticale afin de s�parer les champs de recherche et l'affichage des r�sultats
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panneauChampsRecherche, panneauBas);
		this.getContentPane().add(split,"Center");
	}
	
}

