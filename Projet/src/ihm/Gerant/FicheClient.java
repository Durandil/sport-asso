package ihm.Gerant;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Dimension dimensionpanneauInformationsPersonnelles =  new Dimension(300,60);
	private Dimension dimensionPanneauStatistique =  new Dimension(300,40);
	private JLabel icon;
	private static String etatCompte="";
	private static final String identifiantClient = "";
	private static String numeroCommande=" ";
	
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
		panneauDroite.setLayout(new GridLayout(6,1,0,5));
		panneauDroite.setBorder(BorderFactory.createTitledBorder("Statistiques"));
		panneauDroite.setBackground(Color.YELLOW);
		
		// Implémenter des méthodes (interrogation tables) pour obtenir les statistiques sur un client
		JLabel totalMoyenCommandes=new JLabel("Total moyen des commandes : ");
		JLabel totalMaxCommande=new JLabel("Plus grosse commande effectuée : ");
		JLabel articleMaxCommande= new JLabel("Article le plus commandé : ");
		JLabel quantiteTotaleArticleMaxCommande = new JLabel("Quantité commandée de cet article : ");
		JLabel dateDernierAchatArticleMaxCommande = new JLabel("Dernier achat de cet article : ");
		JComboBox comboAfficherCommande = new JComboBox();
		JButton boutonValidation = new JButton("OK");
		
		JPanel panStat1 = new JPanel();
		JPanel panStat2 = new JPanel();
		JPanel panStat3 = new JPanel();
		JPanel panStat4 = new JPanel();
		JPanel panStat5 = new JPanel();
		JPanel panStatCommande = new JPanel();
		
		panStat1.setBackground(new Color(0,0,0,0));
		panStat2.setBackground(new Color(0,0,0,0));
		panStat3.setBackground(new Color(0,0,0,0));
		panStat4.setBackground(new Color(0,0,0,0));
		panStat5.setBackground(new Color(0,0,0,0));
		
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
		
		panStatCommande.setLayout(new GridLayout(1,2,2,0));
		// remplissage du ComboBox avec les commandes du client
		ArrayList<String> listeCommandesArticles=new ArrayList<String>();
		listeCommandesArticles= SGBD.selectListeStringOrdonneCondition("COMMANDE", "IDCOMMANDE", "IDCOMMANDE", "IDCLIENT='"+idClient+"'");
		if(listeCommandesArticles.size()>0){
			for (String commande : listeCommandesArticles) {
				comboAfficherCommande.addItem(commande);
			}
			comboAfficherCommande.setSelectedIndex(0);
		}
		
		
		comboAfficherCommande.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				numeroCommande =(String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		// définition de l'action du bouton qui ouvrira la fenetre des statistiques d'une commande
		boutonValidation.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!numeroCommande.equals(" ")){
					FenetreStatistiqueCommande fenetre = new FenetreStatistiqueCommande(null,"Détails de la commande : "+numeroCommande,true,numeroCommande);
					fenetre.setVisible(true);
				}
			}
		});
		// définition du panneau des statistique commande
		panStatCommande.add(comboAfficherCommande);
		panStatCommande.add(boutonValidation);
		
		panneauDroite.add(panStat1);
		panneauDroite.add(panStat2);
		panneauDroite.add(panStat3);
		panneauDroite.add(panStat4);
		panneauDroite.add(panStat5);
		panneauDroite.add(panStatCommande);
		
		panneauDroite.setBounds(552,350,350,300);
		
		this.getContentPane().add(panneauDroite);
			
		// Déclaration du panneau de fond de l'écran
		JPanel panneauGauche= new JPanel();
		icon = new JLabel(new ImageIcon("src/images/fond_fiche2.jpg"));
		panneauGauche.setBackground(Color.white);
		panneauGauche.add(icon);	
		
		//Identifiant
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(new Color(0, 0, 0, 0));
		panIdentifiant.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panIdentifiant.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email : ");
		final String identifiantClient=SGBD.selectStringConditionString("CLIENT", "IDCLIENT", "IDCLIENT", idClient);
		identifiant = new JTextField(identifiantClient);
		identifiant.setEnabled(false);
		identifiant.setPreferredSize(new Dimension(120, 25));
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		panIdentifiant.setBounds(40,75,300,60);
		this.add(panIdentifiant);
		
		// La denomination
		JPanel panDenomination= new JPanel();
		panDenomination.setBackground(new Color(0, 0, 0, 0));
		panDenomination.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		final String denominationClient = SGBD.selectStringConditionString("CLIENT", "DENOMINATIONCLIENT", "IDCLIENT", idClient);
		denomination=new JTextField(denominationClient);
		denomination.setPreferredSize(new Dimension(110/*denominationClient.length()*/, 25));
		panDenomination.setBorder(BorderFactory.createTitledBorder("Denomination"));
		denominationLabel=new JLabel("Denomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);
		panDenomination.setBounds(40,140,300,60);
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
		panNom.setBounds(40,205,300,60);
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
		panPrenom.setBounds(40,270,300,60);
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
		panTypeCompte.setBounds(40,10,300,60);
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
		panAdresse.setBounds(40,335,300,60);
		this.add(panAdresse);
		
		//Idville
		String idVille = SGBD.selectStringConditionString("CLIENT", "IDVILLE", "IDCLIENT", idClient);

		//ville
		JPanel panVille = new JPanel();
		panVille.setBackground(new Color(0, 0, 0, 0));
		panVille.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");
		ville = new JTextField(SGBD.selectStringConditionString("VILLE", "NOMVILLE", "IDVILLE", idVille));
		ville.setPreferredSize(new Dimension(90, 25));
		ville.setEnabled(false);
		panVille.add(villeLabel);
		panVille.add(ville);
		panVille.setBounds(40,465,300,60);
		this.add(panVille);
		
		
		//Code Postal
		JPanel panCP = new JPanel();
		panCP.setBackground(new Color(0, 0, 0, 0));
		panCP.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panCP.setBorder(BorderFactory.createTitledBorder("Code Postal"));
		cpLabel = new JLabel("Code Postal : ");
		codePostal = new JTextField(SGBD.selectStringConditionString("VILLE", "CODEPOSTAL", "IDVILLE", idVille));
		codePostal.setPreferredSize(new Dimension(100,25));
		panCP.add(cpLabel);
		panCP.add(codePostal);
		panCP.setBounds(40,400,300,60);
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
		panTelephone.setBounds(40,530,300,60);
		this.add(panTelephone);
		
		
		// Activation du compte
		JPanel panActivationCompte = new JPanel();
		panActivationCompte.setBackground(new Color(0,0,0,0));
		panActivationCompte.setPreferredSize(new Dimension(220, 60));
		panActivationCompte.setBorder(BorderFactory.createTitledBorder("Activation du compte"));
		etatCompte =SGBD.selectStringConditionString("CLIENT", "ETATCOMPTE", "IDCLIENT", idClient);
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
		panActivationCompte.setBounds(40,595,200,60);
		this.add(panActivationCompte);
		
		// Declaration d'un panneau qui contiendra les infos concernant la fidelite
		JPanel panneauFidelite = new JPanel();
		panneauFidelite.setLayout(new GridLayout(2,1,0,5));
		panneauFidelite.setBackground(new Color(0,0,0,0));
		panneauFidelite.setBorder(BorderFactory.createTitledBorder("Informations sur la fidélité"));
		panneauFidelite.setPreferredSize(new Dimension(300,150));
		panneauFidelite.setBounds(552,10,350,160);
		this.add(panneauFidelite);
		
		// Panneau Compte Fidelite
		JPanel panCompteFidelite = new JPanel();
		panCompteFidelite.setBackground(new Color(0,0,0,0));
		panCompteFidelite.setPreferredSize(new Dimension(290, 60));
		panCompteFidelite.setBorder(BorderFactory.createEmptyBorder());
		compteFidelLabel = new JLabel("Possesion d'un compte Fidelité :");
		
		ArrayList<String> fideliteClient= new ArrayList<String>();
		fideliteClient=SGBD.recupererInformationFideliteClient(idClient);
		String estFidele=fideliteClient.get(0);
		String fidelite="" ;
		
		if(estFidele == "false"){
			fidelite="Non";
		}
		else 
			fidelite="Oui";
		
		compteFidelite = new JTextField(fidelite);
		compteFidelite.setEnabled(false);
		compteFidelite.setPreferredSize(new Dimension(90, 25));
		panCompteFidelite.add(compteFidelLabel);
		panCompteFidelite.add(compteFidelite);
		panneauFidelite.add(panCompteFidelite);
		
		// Panneau Nbre Points Compte Fidelite
		JPanel panPointsFidelite = new JPanel();
		panPointsFidelite.setBackground(new Color(0,0,0,0));
		panPointsFidelite.setPreferredSize(new Dimension(290, 60));
		panPointsFidelite.setBorder(BorderFactory.createEmptyBorder());
		
		String labelNbreDePoints="Nombre de points ";
		String nbPointsCarte="";
		
		if(fidelite.equals("Oui")){
			labelNbreDePoints = labelNbreDePoints +" sur la carte de fidelite "+ SGBD.selectStringConditionString("CARTE_FIDELITE", "IDCARTEFIDELITE", "IDCLIENT", idClient)+ " :"  ;
			nbPointsCarte=fideliteClient.get(1);
		}
		
		nbPointsLabel = new JLabel(labelNbreDePoints);
		nbrePoints = new JTextField(nbPointsCarte);
		nbrePoints.setEnabled(false);
		nbrePoints.setPreferredSize(new Dimension(90, 25));
		panPointsFidelite.add(nbPointsLabel);
		panPointsFidelite.add(nbrePoints);
		panneauFidelite.add(panPointsFidelite);
		

		this.getContentPane().add(panneauGauche);

		// Définition du panneau accueillant les boutons situés en bas de fenêtre
		JPanel panneauBouton=new JPanel();
		JButton boutonValider=new JButton("Confirmer");
			
		boutonValider.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			// TODO enregistrer l'éventuelle modification par le client des données
			if(denominationClient.equals(" ")){
				Particulier.modifierBDDclient(identifiantClient,etatCompte);
				Particulier.modifierBDDparticulier(identifiantClient, nom.getText(), prenom.getText(), adresse.getText(), codePostal.getText(), telephone.getText());
			}
			else{
				Association.modifierBDDclient(identifiantClient,etatCompte);
				Association.modifierBDDassoc(identifiantClient, denomination.getText(), adresse.getText(), codePostal.getText(), telephone.getText());
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