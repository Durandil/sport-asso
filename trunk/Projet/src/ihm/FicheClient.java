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
	private JComboBox ActivationCompteBox;
	private JLabel ActifCompteLabel;
	private Dimension dimensionpanneauInformationsPersonnelles =  new Dimension(250,60);

	public FicheClient(JFrame parent, String title, boolean modal,String identifiantClient){
		super(parent, title, modal);
		this.setSize(1024, 900);
		this.setLocation(0,0);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(identifiantClient);
	}
	
	private void initComponent(String idClient){
		
		// Déclaration du panneau qui contiendra les statistiques sur le client
		JPanel panneauDroite=new JPanel();
		//panneauDroite.setPreferredSize(new Dimension(300,500));
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
		
		this.getContentPane().add(panneauDroite, BorderLayout.EAST);
		
		// Déclaration du panneau qui contiendra les informations personnelles du client
		JPanel panneauGauche= new JPanel();
		//panneauGauche.setPreferredSize(new Dimension(300,500));
		
		JPanel panneauInformationsPersonnelles=new JPanel();
		panneauInformationsPersonnelles.setLayout(new GridLayout(8,0));
		
		//Identifiant
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(Color.white);
		panIdentifiant.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panIdentifiant.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email : ");
		identifiant = new JTextField(SGBD.selectStringConditionString("CLIENT", "IDCLIENT", "IDCLIENT", idClient));
		identifiant.setPreferredSize(new Dimension(120, 25));
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		
		
		// La denomination
		JPanel panDenomination= new JPanel();
		panDenomination.setBackground(Color.white);
		panDenomination.setPreferredSize(dimensionpanneauInformationsPersonnelles);
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
		panNom.setPreferredSize(dimensionpanneauInformationsPersonnelles);
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
		panPrenom.setPreferredSize(dimensionpanneauInformationsPersonnelles);
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
		panTypeCompte.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panTypeCompte.setBorder(BorderFactory.createTitledBorder("Type de compte"));
		String compte = "";
		if(denominationClient.equals(" ")){
			compte = "Particulier";
		}
		else{
			compte="Association";
		}
		typeCompteLabel = new JLabel("Statut Compte : " + compte);
		panTypeCompte.add(typeCompteLabel);
		
		
		//L'adresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(Color.white);
		panAdresse.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse"));
		adresseLabel = new JLabel("Adresse : ");
		adresse = new JTextField(SGBD.selectStringConditionString("CLIENT", "ADRESSECLIENT", "IDCLIENT", idClient));
		adresse.setPreferredSize(new Dimension(100, 25));
		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);
		

		//ville
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");
		ville = new JTextField(SGBD.selectStringConditionString("CLIENT", "NOMVILLE", "IDCLIENT", idClient));
		ville.setPreferredSize(new Dimension(90, 25));
		panVille.add(villeLabel);
		panVille.add(ville);
		
		
		//Code Postal
		JPanel panCP = new JPanel();
		panCP.setBackground(Color.white);
		panCP.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panCP.setBorder(BorderFactory.createTitledBorder("Code Postal"));
		cpLabel = new JLabel("Code Postal : ");
		codePostal = new JTextField(SGBD.selectStringConditionString("CLIENT", "CODEPOSTAL", "IDCLIENT", idClient));
		codePostal.setPreferredSize(new Dimension(100,25));
		panCP.add(cpLabel);
		panCP.add(codePostal);
		
		
		// Telephone
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(Color.white);
		panTelephone.setPreferredSize(dimensionpanneauInformationsPersonnelles);
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
		panneauInformationsPersonnelles.add(panTypeCompte);
		panneauInformationsPersonnelles.add(panIdentifiant);
		panneauInformationsPersonnelles.add(panDenomination);
		panneauInformationsPersonnelles.add(panNom);
		panneauInformationsPersonnelles.add(panPrenom);
		panneauInformationsPersonnelles.add(panAdresse);
		panneauInformationsPersonnelles.add(panVille);
		panneauInformationsPersonnelles.add(panCP);
		panneauInformationsPersonnelles.add(panTelephone);
		panneauGauche.add(panneauInformationsPersonnelles, BorderLayout.CENTER);
		

		this.getContentPane().add(panneauGauche, BorderLayout.WEST);

		// Définition du panneau accueillant les boutons situés en bas de fenêtre
		JPanel panneauBouton=new JPanel();
		JButton boutonValider=new JButton("Confirmer");
			
		boutonValider.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			// enregistrer l'éventuelle modification par le client des données
				setVisible(false);
			}
		});
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			// bouton permettant le retour à la page précédante et l'annulation des modifications en cours 
			// tout en fermant la fenêtre
				setVisible(false);
			}			
		});
			
		panneauBouton.add(boutonValider);
		panneauBouton.add(retourBouton);
		
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
		
		//pack();
		repaint();
	}
}