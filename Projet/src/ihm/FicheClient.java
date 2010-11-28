package ihm;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import basededonnees.SGBD;

import metier.Client;

public class FicheClient extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel typeCompteLabel,identifiantLabel,denominationLabel,nomLabel,prenomLabel,adresseLabel,villeLabel,cpLabel,telLabel,compteFidelLabel,nbPointsLabel;
	private JTextField identifiant,denomination,nom,prenom,adresse,ville,codePostal,telephone,compteFidelite,nbrePoints;
	private JSplitPane split;
	private JComboBox ActivationCompteBox;
	private JLabel ActifCompteLabel;


	public FicheClient(JFrame parent, String title, boolean modal,String identifiantClient){
		super(parent, title, modal);
		this.setSize(600, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(identifiantClient);
	}
	
	private void initComponent(String idClient){
		
		// Déclaration du panneau qui contiendra les statistiques sur le client
		JPanel panneauDroite=new JPanel();
		panneauDroite.setLayout(new GridLayout(5, 1));
		
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
		panneauCentral.setLayout(new GridLayout(8,1));
		
		//Identifiant
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(Color.white);
		panIdentifiant.setPreferredSize(new Dimension(300, 60));
		panIdentifiant.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email : ");
		identifiant = new JTextField(SGBD.selectStringConditionString("CLIENT", "IDCLIENT", "IDCLIENT", idClient));
		identifiant.setPreferredSize(new Dimension(120, 25));
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		
		
		// La denomination
		JPanel panDenomination= new JPanel();
		panDenomination.setBackground(Color.white);
		panDenomination.setPreferredSize(new Dimension(300, 60));
		String denominationClient = SGBD.selectStringConditionString("CLIENT", "DENOMINATIONCLIENT", "IDCLIENT", idClient);
		denomination=new JTextField(denominationClient);
		denomination.setPreferredSize(new Dimension(90, 25));
		panDenomination.setBorder(BorderFactory.createTitledBorder("Denomination"));
		denominationLabel=new JLabel("Denomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);
		
		
		//Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(300, 60));
		String name = SGBD.selectStringConditionString("CLIENT", "NOMCLIENT", "IDCLIENT", idClient);
		nom = new JTextField(name);
		nom.setPreferredSize(new Dimension(90, 25));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom"));
		nomLabel = new JLabel("Nom :");
		panNom.add(nomLabel);
		panNom.add(nom);
		
			
		//Le prenom
		JPanel panPrenom = new JPanel();
		panPrenom.setBackground(Color.white);
		panPrenom.setPreferredSize(new Dimension(300, 60));
		panPrenom.setBorder(BorderFactory.createTitledBorder("Prenom"));
		prenomLabel = new JLabel("Prenom : ");
		String prenomClient = SGBD.selectStringConditionString("CLIENT", "PRENOMCLIENT", "IDCLIENT", idClient);
		prenom = new JTextField(prenomClient);
		prenom.setPreferredSize(new Dimension(90, 25));
		panPrenom.add(prenomLabel);
		panPrenom.add(prenom);
		
		
		if(denominationClient.equals(" ")){
			denomination.setEnabled(false);
			nom.setEnabled(true);
			prenom.setEnabled(true);
		}
		else{
			denomination.setEnabled(true);
			nom.setEnabled(false);
			prenom.setEnabled(false);
		}
		
		// Le type de Compte
		JPanel panTypeCompte=new JPanel();
		panTypeCompte.setBackground(Color.white);
		panTypeCompte.setPreferredSize(new Dimension(300, 60));
		panTypeCompte.setBorder(BorderFactory.createTitledBorder("Type de compte"));
		String compte = "";
		if(denominationClient.equals(" ")){
			compte = "Particulier";
		}
		else{
			compte="Association";
		}
		typeCompteLabel = new JLabel("Statut Compte : " + compte);
		
		
		
		//L'adresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(Color.white);
		panAdresse.setPreferredSize(new Dimension(300, 60));
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse"));
		adresseLabel = new JLabel("Adresse : ");
		adresse = new JTextField(SGBD.selectStringConditionString("CLIENT", "ADRESSECLIENT", "IDCLIENT", idClient));
		adresse.setPreferredSize(new Dimension(100, 25));
		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);
		

		//ville
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(new Dimension(300, 60));
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");
		ville = new JTextField(SGBD.selectStringConditionString("CLIENT", "NOMVILLE", "IDCLIENT", idClient));
		ville.setPreferredSize(new Dimension(90, 25));
		panVille.add(villeLabel);
		panVille.add(ville);
		
		
		//Code Postal
		JPanel panCP = new JPanel();
		panCP.setBackground(Color.white);
		panCP.setPreferredSize(new Dimension(300, 60));
		panCP.setBorder(BorderFactory.createTitledBorder("Code Postal"));
		cpLabel = new JLabel("Code Postal : ");
		codePostal = new JTextField(SGBD.selectStringConditionString("CLIENT", "CODEPOSTAL", "IDCLIENT", idClient));
		codePostal.setPreferredSize(new Dimension(100,25));
		panCP.add(cpLabel);
		panCP.add(codePostal);
		
		
		// Telephone
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(Color.white);
		panTelephone.setPreferredSize(new Dimension(300, 60));
		panTelephone.setBorder(BorderFactory.createTitledBorder("Telephone"));
		telLabel = new JLabel("Numéro : ");
		telephone = new JTextField(SGBD.selectStringConditionString("CLIENT", "TELEPHONE", "IDCLIENT", idClient));
		telephone.setPreferredSize(new Dimension(90, 25));
		panTelephone.add(telLabel);
		panTelephone.add(telephone);
		
		
		// Activation du compte
//		JPanel panActivationCompte = new JPanel();
//		panActivationCompte.setBackground(Color.white);
//		panActivationCompte.setPreferredSize(new Dimension(220, 60));
//		panActivationCompte.setBorder(BorderFactory.createTitledBorder("Activation du compte"));
//		ActifCompteLabel = new JLabel("Compte Actif ? ");
//		ActivationCompteBox = new JComboBox();
//		ActivationCompteBox.addItem("True");
//		ActivationCompteBox.addItem("False");
//		String activite = Boolean.toString();
//		ActivationCompteBox.setSelectedItem(activite);
//		panActivationCompte.add(ActifCompteLabel);
//		panActivationCompte.add(ActivationCompteBox);
//		panneauCentral.add(panActivationCompte);
//		
//		// Panneau Compte Fidelite
//		JPanel panCompteFidelite = new JPanel();
//		panCompteFidelite.setBackground(Color.white);
//		panCompteFidelite.setPreferredSize(new Dimension(220, 60));
//		panCompteFidelite.setBorder(BorderFactory.createEmptyBorder());
//		compteFidelLabel = new JLabel("Possesion d'un compte Fidelité :");
//		
//		String fidelite="" ;
//		if(client.isEstFidele()==true){
//			fidelite="Oui";
//		}
//		else 
//			fidelite="Non";
//		
//		compteFidelite = new JTextField(fidelite);
//		compteFidelite.setPreferredSize(new Dimension(90, 25));
//		panCompteFidelite.add(compteFidelLabel);
//		panCompteFidelite.add(compteFidelite);
//		panneauCentral.add(panCompteFidelite);
//		
//		// Panneau Nbre Points Compte Fidelite
//		JPanel panPointsFidelite = new JPanel();
//		panPointsFidelite.setBackground(Color.white);
//		panPointsFidelite.setPreferredSize(new Dimension(220, 60));
//		panPointsFidelite.setBorder(BorderFactory.createEmptyBorder());
//		nbPointsLabel = new JLabel("Nombre de points :");
//		nbrePoints = new JTextField();
//		nbrePoints.setPreferredSize(new Dimension(90, 25));
//		panPointsFidelite.add(nbPointsLabel);
//		panPointsFidelite.add(nbrePoints);
//		
//		if(client.isEstFidele()==true){
//			panneauCentral.add(panPointsFidelite);
//		}
		
		
		// Nous ajoutons tous les panneaux contenant les champs d'informations sur le client dans le panneau de gauche
		panneauCentral.add(panTypeCompte);
		panneauCentral.add(panIdentifiant);
		panneauCentral.add(panDenomination);
		panneauCentral.add(panNom);
		panneauCentral.add(panPrenom);
		panneauCentral.add(panAdresse);
		panneauCentral.add(panVille);
		panneauCentral.add(panCP);
		panneauCentral.add(panTelephone);
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
		
		pack();
		
	}
}