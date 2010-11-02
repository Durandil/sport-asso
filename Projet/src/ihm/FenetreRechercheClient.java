package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import basededonnees.SGBD;

public class FenetreRechercheClient extends JDialog{
	private JLabel numeroLabel,nomLabel,villeLabel,denominationLabel,clientLabel;
	private JTextField numero,nom,denomination,ville,clientIdentifiant;
	private JSplitPane split;
	private JOptionPane mailInexistant;

	// Le g�rant pourra rechercher un client en fonction de son num�ro, nom, ville
	
	
	public FenetreRechercheClient(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(700,600);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	private void initComponent(){
		
		// D�finition du panneau qui acceuillera tous les panneaux avec les principaux crit�res de recherche de clients
		JPanel panneauChampsRecherche = new JPanel();
		panneauChampsRecherche.setBorder(BorderFactory.createTitledBorder("Recherche de clients selon :"));
		panneauChampsRecherche.setLayout(new GridLayout(4,1,5,5));
		
		// D�finition d'un panneau avec le premier crit�re de recherche : le num�ro du client
		JPanel panneauNumero = new JPanel();
		panneauNumero.setBackground(Color.white);
		panneauNumero.setPreferredSize(new Dimension(110, 60));
		panneauNumero.setBorder(BorderFactory.createTitledBorder("Identifiant du client"));
		numeroLabel = new JLabel("Mail: ");
		numero = new JTextField();
		numero.setPreferredSize(new Dimension(90, 25));
		panneauNumero.add(numeroLabel);
		panneauNumero.add(numero);
		
		// D�finition d'un panneau avec un autre crit�re de recherche : le nom du client pour les particuliers
		JPanel panneauNom = new JPanel();
		panneauNom.setBackground(Color.white);
		panneauNom.setPreferredSize(new Dimension(110, 60));
		panneauNom.setBorder(BorderFactory.createTitledBorder("Nom du client pour les particuliers"));
		nomLabel = new JLabel("Nom: ");
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(90, 25));
		panneauNom.add(nomLabel);
		panneauNom.add(nom);
		
		// D�finition d'un panneau avec un autre crit�re de recherche : la d�nomination du client 
		// pour les collectivit�s ou associations
		JPanel panDenomination= new JPanel();
		panDenomination.setBackground(Color.white);
		panDenomination.setPreferredSize(new Dimension(110, 60));
		panDenomination.setBorder(BorderFactory.createTitledBorder("Denomination de l'association ou de la collectivit�"));
		denomination=new JTextField();
		denomination.setPreferredSize(new Dimension(90, 25));
		denominationLabel=new JLabel("Denomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);
		
		// D�finition d'un panneau avec le dernier crit�re de recherche : la ville du client
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(new Dimension(110, 60));
		panVille.setBorder(BorderFactory.createTitledBorder("Ville du client"));
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
		
		// Cr�ation du bouton de validation de la recherche
		JButton boutonValidationRecherche = new JButton("Rechercher");
		boutonValidationRecherche.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				//Auto-generated method stub
				// TODO afficher un tableau en dessous avec les r�sultats de la recherche
				// setVisible(=True) permettra d'afficher le tableau  avec les r�sultats de la recherche
				tableauRechercheClient.setVisible(true);
			}
		});
		
		
		// D�finition du panneau des boutons du bas
		JPanel panneauBasBoutons = new JPanel();
		panneauBasBoutons.setLayout(new GridLayout(1,2,5,5));
		
		// TODO Afficher un champ permettant au g�rant de rentrer l'identifiant de client que le g�rant souhaite consulter
		JPanel panneauRechercheClient = new JPanel();
		panneauRechercheClient.setLayout(new GridLayout(1,3,5,5));
		clientLabel = new JLabel("Client recherch� ?");
		clientIdentifiant = new JTextField();
		JButton validationRecherche = new JButton("OK");
		validationRecherche.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stud
				
				//listeMails recense l'ensemble des adresses mails pr�sentes dans la base
				//L'entier test passe � 1 si l'adresse renseign�e existe bien dans la base de donn�es
				//Si test reste � 0, on avertit le g�rant que l'adresse n'existe pas
				ArrayList<String> listeMails = new ArrayList<String>();
				listeMails = SGBD.selectListeString("CLIENTS", "MAIL");
				byte test = 0;
				
				for (int i = 0; i < listeMails.size(); i++) {
					if (clientIdentifiant.getText().equals(listeMails.get(i))) {
						test = 1;
					}
				}
				
				if (test == 0) {
					mailInexistant = new JOptionPane();
					ImageIcon image = new ImageIcon("src/images/warning.png");
					mailInexistant.showMessageDialog(null, "Cette adresse mail n'existe pas !", "Attention", JOptionPane.WARNING_MESSAGE, image);
				}
				else{
					// on affiche la fiche client correspondante � l'identifiant saisi
					ArrayList<String> client = new ArrayList<String>();
					client=SGBD.recupererAttributClient(clientIdentifiant.getText());
					//FicheClient ficheDuClient = new FicheClient(null, "Fiche du client : "+ clientIdentifiant.getText(), true, clientCourant);
					//ficheDuClient.setVisible(true);
				}
					
			}
		});
		panneauRechercheClient.add(clientLabel);
		panneauRechercheClient.add(clientIdentifiant);
		panneauRechercheClient.add(validationRecherche);
		panneauBasBoutons.add(panneauRechercheClient);
		
		// Creation du bouton de retour � la page pr�c�dente
		JButton boutonRetour= new JButton("Retour � la page pr�c�dente");
		
		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Auto-generated method stub
				setVisible(false);	
			}
		});
		panneauBasBoutons.add(boutonRetour);
		
		// Cr�ation d'un panneau qui regroupera : le bouton de recherche des clients selon les crit�res entr�s en param�tre
		// le tableau des r�sultats de la recherche
		// et le bouton de retour � la page pr�c�dante
		// cr�es et impl�ment�s au dessus
		JPanel panneauBas = new JPanel();
		panneauBas.setLayout(new BorderLayout());
		panneauBas.add(boutonValidationRecherche,"North");
		panneauBas.add(new JScrollPane(tableauRechercheClient),"Center");
		panneauBas.add(panneauBasBoutons,"South");
		
		 
		
		// Ajout d'une s�paration horizontale afin de s�parer les champs de recherche et l'affichage des r�sultats
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panneauChampsRecherche, panneauBas);
		this.getContentPane().add(split,"Center");
	}
	
}

