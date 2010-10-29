package ihm;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.Client;

public class FicheClient extends JDialog {
	
	private JLabel typeCompteLabel,identifiantLabel,denominationLabel,nomLabel,prenomLabel,adresseLabel,villeLabel,cpLabel,telLabel,compteFidelLabel,nbPointsLabel;
	private JTextField identifiant,denomination,nom,prenom,adresse,ville,codePostal,telephone,compteFidelite,nbrePoints;
	private JSplitPane split;


	public FicheClient(JFrame parent, String title, boolean modal,Client clientCourant){
		super(parent, title, modal);
		this.setSize(550, 850);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(clientCourant);
	}
	
	private void initComponent(Client client){
		
		// Déclaration du panneau qui contiendra les statistiques sur le client
		JPanel panneauDroite=new JPanel();
		
		// Implémenter des méthodes (interrogation tables) pour obtenir les statistiques sur un client
		JLabel totalMoyenCommandes=new JLabel("Total moyen des commandes : ");
		JLabel totalMaxCommande=new JLabel("Plus grosse commande effectuée : ");
		JLabel articleMaxCommande= new JLabel("Article le plus commandé : ");
		JLabel quantiteTotaleArticleMaxCommande = new JLabel("Quantité commandée de cet article : ");
		JLabel dateDernierAchatArticleMaxCommande = new JLabel("Dernier achat de cet article : ");
		
		panneauDroite.add(totalMoyenCommandes);
		panneauDroite.add(totalMaxCommande);
		
		panneauDroite.add(articleMaxCommande);
		panneauDroite.add(quantiteTotaleArticleMaxCommande);
		panneauDroite.add(dateDernierAchatArticleMaxCommande);
		
		// Déclaration du panneau qui contiendra les informations personnelles du client
		JPanel panneauGauche= new JPanel();
		
		JPanel panneauCentral=new JPanel();
		panneauCentral.setLayout(new GridLayout(8,1,5,5));
		
		// Le type de Compte
		JPanel panTypeCompte=new JPanel();
		panTypeCompte.setBackground(Color.white);
		panTypeCompte.setPreferredSize(new Dimension(220, 60));
		panTypeCompte.setBorder(BorderFactory.createTitledBorder("Type de compte"));
		typeCompteLabel = new JLabel("Statut Compte : " + client.getParticulierAssociation());
		panneauCentral.add(panTypeCompte);
		
		//Identifiant
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(Color.white);
		panIdentifiant.setPreferredSize(new Dimension(220, 60));
		panIdentifiant.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email : ");
		identifiant = new JTextField(client.getMail());
		identifiant.setPreferredSize(new Dimension(120, 25));
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		panneauCentral.add(panIdentifiant);
		
		if(client.getParticulierAssociation()=="Association"){
			// La denomination
			JPanel panDenomination= new JPanel();
			panDenomination.setBackground(Color.white);
			panDenomination.setPreferredSize(new Dimension(220, 60));
			//String name = ((Association) client).getDenomination();
			denomination=new JTextField();
			denomination.setPreferredSize(new Dimension(90, 25));
			panDenomination.setBorder(BorderFactory.createTitledBorder("Denomination"));
			denominationLabel=new JLabel("Denomination");
			panDenomination.add(denominationLabel);
			panDenomination.add(denomination);
			panneauCentral.add(panDenomination);
		}
		else{
			//Le nom
			JPanel panNom = new JPanel();
			panNom.setBackground(Color.white);
			panNom.setPreferredSize(new Dimension(220, 60));
			//String name = ((Particulier) client).getNom();
			nom = new JTextField();
			nom.setPreferredSize(new Dimension(90, 25));
			panNom.setBorder(BorderFactory.createTitledBorder("Nom"));
			nomLabel = new JLabel("Nom :");
			panNom.add(nomLabel);
			panNom.add(nom);
			panneauCentral.add(panNom);
			
			//Le prenom
			JPanel panPrenom = new JPanel();
			panPrenom.setBackground(Color.white);
			panPrenom.setPreferredSize(new Dimension(220, 60));
			panPrenom.setBorder(BorderFactory.createTitledBorder("Prenom"));
			prenomLabel = new JLabel("Prenom : ");
			//String name = ((Particulier) client).getPrenom();
			prenom = new JTextField();
			prenom.setPreferredSize(new Dimension(90, 25));
			panPrenom.add(prenomLabel);
			panPrenom.add(prenom);
			panneauCentral.add(panPrenom);
		}
		
		//L'adresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(Color.white);
		panAdresse.setPreferredSize(new Dimension(220, 60));
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse"));
		adresseLabel = new JLabel("Adresse : ");
		adresse = new JTextField(client.getAdresse());
		adresse.setPreferredSize(new Dimension(100, 25));
		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);
		panneauCentral.add(panAdresse);

		//ville
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(new Dimension(220, 60));
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");
		ville = new JTextField(client.getVille());
		ville.setPreferredSize(new Dimension(90, 25));
		panVille.add(villeLabel);
		panVille.add(ville);
		panneauCentral.add(panVille);
		
		//Code Postal
		JPanel panCP = new JPanel();
		panCP.setBackground(Color.white);
		panCP.setPreferredSize(new Dimension(220, 60));
		panCP.setBorder(BorderFactory.createTitledBorder("Code Postal"));
		cpLabel = new JLabel("Code Postal : ");
		codePostal = new JTextField(client.getCodePostal());
		codePostal.setPreferredSize(new Dimension(100,25));
		panCP.add(cpLabel);
		panCP.add(codePostal);
		panneauCentral.add(panCP);
		
		// Telephone
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(Color.white);
		panTelephone.setPreferredSize(new Dimension(220, 60));
		panTelephone.setBorder(BorderFactory.createTitledBorder("Telephone"));
		telLabel = new JLabel("Numéro : ");
		telephone = new JTextField(client.getTelephone());
		telephone.setPreferredSize(new Dimension(90, 25));
		panTelephone.add(telLabel);
		panTelephone.add(telephone);
		panneauCentral.add(panTelephone);
		
		// Panneau Compte Fidelite
		JPanel panCompteFidelite = new JPanel();
		panCompteFidelite.setBackground(Color.white);
		panCompteFidelite.setPreferredSize(new Dimension(220, 60));
		panCompteFidelite.setBorder(BorderFactory.createEmptyBorder());
		compteFidelLabel = new JLabel("Possesion d'un compte Fidelité :");
		
		String fidelite="" ;
		if(client.isEstFidele()==true){
			fidelite="Oui";
		}
		else 
			fidelite="Non";
		
		compteFidelite = new JTextField(fidelite);
		compteFidelite.setPreferredSize(new Dimension(90, 25));
		panCompteFidelite.add(compteFidelLabel);
		panCompteFidelite.add(compteFidelite);
		panneauCentral.add(panCompteFidelite);
		
		// Panneau Nbre Points Compte Fidelite
		JPanel panPointsFidelite = new JPanel();
		panPointsFidelite.setBackground(Color.white);
		panPointsFidelite.setPreferredSize(new Dimension(220, 60));
		panPointsFidelite.setBorder(BorderFactory.createEmptyBorder());
		nbPointsLabel = new JLabel("Nombre de points :");
		nbrePoints = new JTextField(client.getNbPointsFidelite());
		nbrePoints.setPreferredSize(new Dimension(90, 25));
		panPointsFidelite.add(nbPointsLabel);
		panPointsFidelite.add(nbrePoints);
		
		if(client.isEstFidele()==true){
			panneauCentral.add(panPointsFidelite);
		}
		
		
		// Nous ajoutons tous les panneaux contenant les champs d'informations sur le client dans le panneau de gauche
		panneauGauche.add(panneauCentral, BorderLayout.CENTER);
		
		//On construit enfin notre séparateur de page
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panneauGauche, panneauDroite);
		
		/* On le passe ensuite au contentPane de notre objet FicheClient 
		 * afin de séparer la partie informations personnelles 
		 * et la partie statistique sur le client
		*/ 
		this.getContentPane().add(split, BorderLayout.CENTER);

		// Définition du panneau accueillant les boutons situés en bas de fenêtre
		JPanel panneauBouton=new JPanel();
		JButton boutonValider=new JButton("Confirmer");
			
		boutonValider.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			// enregistrer l'éventuelle modification par le client des données
				setVisible(false);
			}
		});
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			// bouton permettant le retour à la page précédante et l'annulation des modifications en cours 
			// tout en fermant la fenêtre
				setVisible(false);
			}			
		});
			
		panneauBouton.add(boutonValider);
		panneauBouton.add(retourBouton);
		
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
		
		
	}
}