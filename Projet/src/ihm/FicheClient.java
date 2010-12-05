package ihm;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import basededonnees.SGBD;

import metier.Association;
import metier.Client;
import metier.Particulier;

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
	private Dimension dimensionPanneauStatistique =  new Dimension(200,50);
	private JLabel icon;
	private static String etatCompte="";
	
	public FicheClient(JFrame parent, String title, boolean modal,String identifiantClient){
		super(parent, title, modal);
		this.setSize(1024, 900);
		this.setLocation(0,0);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(identifiantClient);
	}
	
	private void initComponent(String idClient){
		etatCompte =SGBD.selectStringConditionString("CLIENT", "ETATCOMPTE", "IDCLIENT", idClient); 
		// Déclaration du panneau qui contiendra les statistiques sur le client
		JPanel panneauDroite=new JPanel();
		panneauDroite.setLayout(new GridLayout(5, 1));
		
		// Implémenter des méthodes (interrogation tables) pour obtenir les statistiques sur un client
		JLabel totalMoyenCommandes=new JLabel("Total moyen des commandes : ");
		JLabel totalMaxCommande=new JLabel("Plus grosse commande effectuée : ");
		JLabel articleMaxCommande= new JLabel("Article le plus commandé : ");
		JLabel quantiteTotaleArticleMaxCommande = new JLabel("Quantité commandée de cet article : ");
		JLabel dateDernierAchatArticleMaxCommande = new JLabel("Dernier achat de cet article : ");
		
		JPanel panStat1 = new JPanel();
		JPanel panStat2 = new JPanel();
		JPanel panStat3 = new JPanel();
		JPanel panStat4 = new JPanel();
		JPanel panStat5 = new JPanel();
		
		panStat1.setBackground(Color.white);
		panStat2.setBackground(Color.white);
		panStat3.setBackground(Color.white);
		panStat4.setBackground(Color.white);
		panStat5.setBackground(Color.white);
		
		panStat1.setPreferredSize(dimensionPanneauStatistique);
		panStat2.setPreferredSize(dimensionPanneauStatistique);
		panStat3.setPreferredSize(dimensionPanneauStatistique);
		panStat4.setPreferredSize(dimensionPanneauStatistique);
		panStat5.setPreferredSize(dimensionPanneauStatistique);
		
		panStat1.add(totalMoyenCommandes);
		panStat2.add(totalMaxCommande);
		panStat3.add(articleMaxCommande);
		panStat4.add(quantiteTotaleArticleMaxCommande);
		panStat5.add(dateDernierAchatArticleMaxCommande);
		
		panStat1.setBounds(552, 90, 200,50);
		panStat2.setBounds(552, 160,200,50);
		panStat3.setBounds(552, 230,200,50);
		panStat4.setBounds(552, 300,200,50);
		panStat5.setBounds(552, 370,200,50);
		
		
		this.getContentPane().add(panStat1);
		this.getContentPane().add(panStat2);
		this.getContentPane().add(panStat3);
		this.getContentPane().add(panStat4);
		this.getContentPane().add(panStat5);
		

		
		// Déclaration du panneau de fond de l'écran
		JPanel panneauGauche= new JPanel();
		icon = new JLabel(new ImageIcon("src/images/fond_fiche.jpg"));
		panneauGauche.setBackground(Color.white);
		panneauGauche.add(icon);	
		
		//Identifiant
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(new Color(0, 0, 0, 0));
		panIdentifiant.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panIdentifiant.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email : ");
		String id=SGBD.selectStringConditionString("CLIENT", "IDCLIENT", "IDCLIENT", idClient);
		identifiant = new JTextField(id);
		identifiant.setPreferredSize(new Dimension(120, 25));
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		panIdentifiant.setBounds(40,120,220,60);
		this.add(panIdentifiant);
		
		// La denomination
		JPanel panDenomination= new JPanel();
		panDenomination.setBackground(new Color(0, 0, 0, 0));
		panDenomination.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		final String denominationClient = SGBD.selectStringConditionString("CLIENT", "DENOMINATIONCLIENT", "IDCLIENT", idClient);
		denomination=new JTextField(denominationClient);
		denomination.setPreferredSize(new Dimension(90/*denominationClient.length()*/, 25));
		panDenomination.setBorder(BorderFactory.createTitledBorder("Denomination"));
		denominationLabel=new JLabel("Denomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);
		panDenomination.setBounds(40,200,220,60);
		this.add(panDenomination);
		
		//Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(new Color(0, 0, 0, 0));
		panNom.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		String name = SGBD.selectStringConditionString("CLIENT", "NOMCLIENT", "IDCLIENT", idClient);
		nom = new JTextField(name);
		nom.setPreferredSize(new Dimension(90, 25));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom"));
		nomLabel = new JLabel("Nom :");
		panNom.add(nomLabel);
		panNom.add(nom);
		panNom.setBounds(40,280,220,60);
		this.add(panNom);
			
		//Le prenom
		JPanel panPrenom = new JPanel();
		panPrenom.setBackground(new Color(0, 0, 0, 0));
		panPrenom.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panPrenom.setBorder(BorderFactory.createTitledBorder("Prenom"));
		prenomLabel = new JLabel("Prenom : ");
		String prenomClient = SGBD.selectStringConditionString("CLIENT", "PRENOMCLIENT", "IDCLIENT", idClient);
		prenom = new JTextField(prenomClient);
		prenom.setPreferredSize(new Dimension(90, 25));
		panPrenom.add(prenomLabel);
		panPrenom.add(prenom);
		panPrenom.setBounds(40,360,220,60);
		this.add(panPrenom);
		
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
		panTypeCompte.setBackground(new Color(0, 0, 0, 0));
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
		panTypeCompte.setBounds(40,40,220,60);
		this.add(panTypeCompte);
		
		//L'adresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(new Color(0, 0, 0, 0));
		panAdresse.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse"));
		adresseLabel = new JLabel("Adresse : ");
		String adresseClient=SGBD.selectStringConditionString("CLIENT", "ADRESSECLIENT", "IDCLIENT", idClient);
		adresse = new JTextField(adresseClient);
		adresse.setPreferredSize(new Dimension(/*adresseClient.length()*/90, 25));
		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);
		panAdresse.setBounds(40,440,220,60);
		this.add(panAdresse);
		

		//ville
		JPanel panVille = new JPanel();
		panVille.setBackground(new Color(0, 0, 0, 0));
		panVille.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");
		ville = new JTextField(SGBD.selectStringConditionString("CLIENT", "NOMVILLE", "IDCLIENT", idClient));
		ville.setPreferredSize(new Dimension(90, 25));
		ville.setEnabled(false);
		panVille.add(villeLabel);
		panVille.add(ville);
		panVille.setBounds(40,600,220,60);
		this.add(panVille);
		
		
		//Code Postal
		JPanel panCP = new JPanel();
		panCP.setBackground(new Color(0, 0, 0, 0));
		panCP.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panCP.setBorder(BorderFactory.createTitledBorder("Code Postal"));
		cpLabel = new JLabel("Code Postal : ");
		codePostal = new JTextField(SGBD.selectStringConditionString("CLIENT", "CODEPOSTAL", "IDCLIENT", idClient));
		codePostal.setPreferredSize(new Dimension(100,25));
		panCP.add(cpLabel);
		panCP.add(codePostal);
		panCP.setBounds(40,520,220,60);
		this.add(panCP);
		
		
		// Telephone
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(new Color(0, 0, 0, 0));
		panTelephone.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panTelephone.setBorder(BorderFactory.createTitledBorder("Telephone"));
		telLabel = new JLabel("Numéro : ");
		telephone = new JTextField(SGBD.selectStringConditionString("CLIENT", "TELEPHONE", "IDCLIENT", idClient));
		telephone.setPreferredSize(new Dimension(90, 25));
		panTelephone.add(telLabel);
		panTelephone.add(telephone);
		panTelephone.setBounds(40,680,220,60);
		this.add(panTelephone);
		
		
		// Activation du compte
		JPanel panActivationCompte = new JPanel();
		panActivationCompte.setBackground(new Color(0,0,0,0));
		panActivationCompte.setPreferredSize(new Dimension(220, 60));
		panActivationCompte.setBorder(BorderFactory.createTitledBorder("Activation du compte"));
		String actifCompteClient=SGBD.selectStringConditionString("CLIENT", "ETATCOMPTE", "IDCLIENT", idClient);
		
		ActifCompteLabel = new JLabel("Compte Actif ? ");
		ActivationCompteBox = new JComboBox();
		ActivationCompteBox.addItem("Oui");
		ActivationCompteBox.addItem("Non");
		if(actifCompteClient.equals("Activé")){
			ActivationCompteBox.setSelectedItem("Oui");
		}
		else{
			ActivationCompteBox.setSelectedItem("Non");
		}
		
		ActivationCompteBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// Auto-generated method stub
				String choix =(String) ((JComboBox) e.getSource()).getSelectedItem();
				if(choix.equals("Oui")){
					etatCompte = "Activé";
				}
				else{
					etatCompte = "Desactivé";
				}
			}
		});
		
		
		panActivationCompte.add(ActifCompteLabel);
		panActivationCompte.add(ActivationCompteBox);
		panActivationCompte.setBounds(552, 550,200,50);
		this.add(panActivationCompte);
		
		
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
		

		this.getContentPane().add(panneauGauche);

		// Définition du panneau accueillant les boutons situés en bas de fenêtre
		JPanel panneauBouton=new JPanel();
		JButton boutonValider=new JButton("Confirmer");
			
		boutonValider.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			// TODO enregistrer l'éventuelle modification par le client des données
			if(denominationClient.equals(" ")){
				Particulier.modifierBDDclient(etatCompte);
			}
			else{
				Association.modifierBDDclient(etatCompte);
			}
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
	
		pack();
		repaint();
	}
}