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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel mailLabel,nomLabel,villeLabel,denominationLabel,clientLabel;
	private JTextField mail,nom,denomination,ville,clientIdentifiant;
	private JSplitPane split;
	private JOptionPane mailInexistant;

	// Le gérant pourra rechercher un client en fonction de son numéro, nom, ville
	
	
	public FenetreRechercheClient(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(700,600);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	private void initComponent(){
		
		// Création d'un panneau qui regroupera : le bouton de recherche des clients selon les critères entrés en paramètre
		// le tableau des résultats de la recherche
		// et le bouton de retour à la page précédante
		// crées et implémentés au dessus
		final JPanel panneauBas = new JPanel();
		panneauBas.setLayout(new BorderLayout());
		
		// Définition du panneau qui acceuillera tous les panneaux avec les principaux critères de recherche de clients
		JPanel panneauChampsRecherche = new JPanel();
		panneauChampsRecherche.setBorder(BorderFactory.createTitledBorder("Recherche de clients selon :"));
		panneauChampsRecherche.setLayout(new GridLayout(4,1,5,5));
		
		// Définition d'un panneau avec le premier critère de recherche : le mail du client
		JPanel panneauMail = new JPanel();
		panneauMail.setBackground(Color.white);
		panneauMail.setPreferredSize(new Dimension(110, 60));
		panneauMail.setBorder(BorderFactory.createTitledBorder("Identifiant du client"));
		mailLabel = new JLabel("Mail: ");
		mail = new JTextField();
		mail.setPreferredSize(new Dimension(90, 25));
		panneauMail.add(mailLabel);
		panneauMail.add(mail);
		
		// Définition d'un panneau avec un autre critère de recherche : le nom du client pour les particuliers
		JPanel panneauNom = new JPanel();
		panneauNom.setBackground(Color.white);
		panneauNom.setPreferredSize(new Dimension(110, 60));
		panneauNom.setBorder(BorderFactory.createTitledBorder("Nom du client pour les particuliers"));
		nomLabel = new JLabel("Nom: ");
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(90, 25));
		panneauNom.add(nomLabel);
		panneauNom.add(nom);
		
		// Définition d'un panneau avec un autre critère de recherche : la dénomination du client 
		// pour les collectivités ou associations
		JPanel panDenomination= new JPanel();
		panDenomination.setBackground(Color.white);
		panDenomination.setPreferredSize(new Dimension(110, 60));
		panDenomination.setBorder(BorderFactory.createTitledBorder("Denomination de l'association ou de la collectivité"));
		denomination=new JTextField();
		denomination.setPreferredSize(new Dimension(90, 25));
		denominationLabel=new JLabel("Denomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);
		
		// Définition d'un panneau avec le dernier critère de recherche : la ville du client
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
		panneauChampsRecherche.add(panneauMail);
		panneauChampsRecherche.add(panDenomination);
		panneauChampsRecherche.add(panneauNom);
		panneauChampsRecherche.add(panVille);
		
		
		
		// TODO Faire le panneau de la recherche client;
		
		
		// Création du bouton de validation de la recherche
		JButton boutonValidationRecherche = new JButton("Rechercher");
		boutonValidationRecherche.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				//Auto-generated method stub
				// TODO afficher un tableau en dessous avec les résultats de la recherche
				// setVisible(=True) permettra d'afficher le tableau  avec les résultats de la recherche
				final JTable tableauRechercheClient = new JTable(new ModeleTableauClient(mail.getText(),nom.getText(),denomination.getText(), ville.getText()));
				tableauRechercheClient.setVisible(true);
				panneauBas.add(new JScrollPane(tableauRechercheClient),"Center");
			}
		});
		
		
		// Définition du panneau des boutons du bas
		JPanel panneauBasBoutons = new JPanel();
		panneauBasBoutons.setLayout(new GridLayout(1,2,5,5));
		
		// TODO Afficher un champ permettant au gérant de rentrer l'identifiant de client que le gérant souhaite consulter
		JPanel panneauRechercheClient = new JPanel();
		panneauRechercheClient.setLayout(new GridLayout(1,3,5,5));
		clientLabel = new JLabel("Client recherché ?");
		clientIdentifiant = new JTextField();
		JButton validationRecherche = new JButton("OK");
		validationRecherche.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stud
				
				//listeMails recense l'ensemble des adresses mails présentes dans la base
				//L'entier test passe à 1 si l'adresse renseignée existe bien dans la base de données
				//Si test reste à 0, on avertit le gérant que l'adresse n'existe pas
				ArrayList<String> listeMails = new ArrayList<String>();
				listeMails = SGBD.selectListeString("CLIENT", "IDCLIENT");
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
					// on affiche la fiche client correspondante à l'identifiant saisi
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
		
		// Creation du bouton de retour à la page précédente
		JButton boutonRetour= new JButton("Retour à la page précédente");
		
		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Auto-generated method stub
				setVisible(false);	
			}
		});
		panneauBasBoutons.add(boutonRetour);
		panneauBas.add(panneauBasBoutons,"South");
		panneauBas.add(boutonValidationRecherche,"North");
		
		
		
		 
		
		// Ajout d'une séparation horizontale afin de séparer les champs de recherche et l'affichage des résultats
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panneauChampsRecherche, panneauBas);
		this.getContentPane().add(split,"Center");
	}
	
}

