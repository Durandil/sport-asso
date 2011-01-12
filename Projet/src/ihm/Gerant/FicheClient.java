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
import javax.swing.JPanel;
import javax.swing.JTextField;

import exception.ExceptionExcesDeCaracteres;
import exception.Client.ExceptionCodePostalDifferentDeCinqChiffres;
import exception.Client.ExceptionCodePostalIncorrect;
import exception.Client.ExceptionNumeroDeTelephoneDifferentDeDixChiffres;
import exception.Client.ExceptionNumeroDeTelephoneIncorrect;

import basededonnees.SGBD;

import metier.Association;
import metier.Particulier;

/**
 * <b> Cette classe permet d'afficher la fiche personnelle d'un client par le
 * gérant. </b>
 * <p>
 * Dans cette classe on peut retrouver les informations personnelles du client,
 * les informations concernant son programme fidélité et des statistiques sur
 * ces commandes.
 * </p>
 * 
 * 
 * @see FenetreAffichageRecherche
 */
public class FicheClient extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel typeCompteLabel, identifiantLabel, denominationLabel,
			nomLabel, prenomLabel, adresseLabel, villeLabel, cpLabel, telLabel,
			compteFidelLabel, nbPointsLabel;
	private JTextField identifiant, denomination, nom, prenom, adresse, ville,
			codePostal, telephone, compteFidelite, nbrePoints;
	private JOptionPane erreurModification;
	private JComboBox ActivationCompteBox;
	private JLabel ActifCompteLabel;
	private Dimension dimensionpanneauInformationsPersonnelles = new Dimension(
			300, 60);
	private Dimension dimensionPanneauStatistique = new Dimension(300, 40);
	private JLabel image;
	private JComboBox listeVille;
	private static String etatCompte = "";
	private static String numeroCommande = " ";

	/**
	 * Constructeur de la fiche client dont les composants sont initialisés dans
	 * {@link FicheClient#FicheClient(JFrame, String, boolean, String)}.
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param title
	 *            String indiquant le titre de la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * @param identifiantClient
	 *            Identifiant unique du client dont on va afficher la fiche
	 */
	public FicheClient(JFrame parent, String title, boolean modal,
			String identifiantClient) {
		super(parent, title, modal);
		this.setSize(1024, 900);
		this.setLocation(0, 0);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(identifiantClient);
	}

	/**
	 * <p>
	 * Initialisation de la fenêtre avec les composants :
	 * <ul>
	 * <li>un JPanel qui contiendra le fond d'écran de la fenêtre.</li>
	 * <li>Plusieurs JPanel situé à gauche de la fenêtre qui vont accueillir les
	 * informations personnelles (nom,prenom,adresse,tel,...).</li>
	 * <li>un JPanel situé en haut à droite avec les informations du programme
	 * fidélité du client.</li>
	 * <li>un JPanel situé en bas à droite avec les statistiques des commandes
	 * du client.</li>
	 * <li>un JPanel en bas contenant les 2 boutons de la fenêtre : Confirmer et
	 * Retour à lapage précédente.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param idClient
	 *            Identifiant unique du client dont on va afficher la fiche
	 * 
	 */
	private void initComponent(String idClient) {

		// Déclaration du JPanel du fond de l'écran //
		// -----------------------------------------//
		JPanel panneauGauche = new JPanel();
		image = new JLabel(new ImageIcon("Ressources/images/fond_fiche2.jpg"));
		panneauGauche.setBackground(Color.white);
		panneauGauche.add(image);

		// Déclaration du JPanel qui contiendra les statistiques sur le client

		JPanel panneauDroite = new JPanel();
		panneauDroite.setLayout(new GridLayout(6, 1, 0, 5));
		panneauDroite.setBorder(BorderFactory
				.createTitledBorder("Statistiques"));
		panneauDroite.setBackground(Color.YELLOW);

		// Interrogation des tables pour obtenir les statistiques sur un client

		ArrayList<String> listeInformationsPlusGrosseCommande = SGBD
				.statistiquePlusGrosseCommande(idClient);
		String referencePlusGrosseCommande = listeInformationsPlusGrosseCommande
				.get(0);
		String datePlusGrosseCommande = listeInformationsPlusGrosseCommande
				.get(1);
		if (!datePlusGrosseCommande.equals("NA")) {
			datePlusGrosseCommande = datePlusGrosseCommande.substring(0, 10);
		}

		JLabel stat1 = new JLabel("Total moyen des commandes : "
				+ Math.round(Float.parseFloat(SGBD.statistiqueClassiqueClient(
						idClient, "avg"))) + " €");
		JLabel stat3 = new JLabel("Plus grosse commande effectuée : "
				+ SGBD.statistiqueClassiqueClient(idClient, "max") + " €");
		JLabel stat2 = new JLabel("Nombre de commandes effectuées : "
				+ SGBD.nbreCommandeClient(idClient));
		JLabel stat4 = new JLabel("Référence de la plus grosse commande : "
				+ referencePlusGrosseCommande);
		JLabel stat5 = new JLabel("Date de la plus grosse commande : "
				+ datePlusGrosseCommande);

		// Création des 5 JPanel qui vont accueillir les résultats des requêtes
		// statistiques

		JPanel panStat1 = new JPanel();
		JPanel panStat2 = new JPanel();
		JPanel panStat3 = new JPanel();
		JPanel panStat4 = new JPanel();
		JPanel panStat5 = new JPanel();
		JPanel panStatCommande = new JPanel();
		panStatCommande.setLayout(new GridLayout(1, 2, 2, 0));

		panStat1.setBackground(new Color(0, 0, 0, 0));
		panStat2.setBackground(new Color(0, 0, 0, 0));
		panStat3.setBackground(new Color(0, 0, 0, 0));
		panStat4.setBackground(new Color(0, 0, 0, 0));
		panStat5.setBackground(new Color(0, 0, 0, 0));

		panStat1.setPreferredSize(dimensionPanneauStatistique);
		panStat2.setPreferredSize(dimensionPanneauStatistique);
		panStat3.setPreferredSize(dimensionPanneauStatistique);
		panStat4.setPreferredSize(dimensionPanneauStatistique);
		panStat5.setPreferredSize(dimensionPanneauStatistique);

		panStat1.add(stat1);
		panStat2.add(stat2);
		panStat3.add(stat3);
		panStat4.add(stat4);
		panStat5.add(stat5);

		// Création d'un JComboBox et d'un bouton pour pouvoir consulter les
		// anciennes factures d'un client

		JComboBox comboAfficherCommande = new JComboBox();

		// Remplissage du JComboBox avec les commandes du client
		ArrayList<String> listeCommandesArticles = new ArrayList<String>();
		listeCommandesArticles = SGBD.selectListeStringOrdonneCondition(
				"COMMANDE", "IDCOMMANDE", "IDCOMMANDE", "IDCLIENT='" + idClient
						+ "'");
		if (listeCommandesArticles.size() > 0) {
			for (String commande : listeCommandesArticles) {
				comboAfficherCommande.addItem(commande);
			}
			comboAfficherCommande.setSelectedIndex(0);
		}

		// Définition de l'action du JComboBox qui permet de récupérer le numéro
		// de la commande dont on souhaite afficher la facture

		comboAfficherCommande.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				numeroCommande = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});

		// Définition de l'action du bouton qui ouvrira la fenêtre des
		// statistiques d'une commande (facture)

		JButton boutonValidation = new JButton("OK");

		boutonValidation.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!numeroCommande.equals(" ")) {
					FenetreStatistiqueCommande fenetre = new FenetreStatistiqueCommande(
							null, "Détails de la commande : " + numeroCommande,
							true, numeroCommande);
					fenetre.setVisible(true);
				}
			}
		});

		// Ajout du JComboBox et du bouton au JPanel qui leur est destiné //
		// ---------------------------------------------------------------//
		panStatCommande.add(comboAfficherCommande);
		panStatCommande.add(boutonValidation);

		// Ajout des JPanel de chaque statistique au grand JPanel des
		// statistiques
		panneauDroite.add(panStat1);
		panneauDroite.add(panStat2);
		panneauDroite.add(panStat3);
		panneauDroite.add(panStat4);
		panneauDroite.add(panStat5);
		panneauDroite.add(panStatCommande);

		panneauDroite.setBounds(552, 350, 350, 300);

		// Ajout du JPanel des statistiques sur la fenêtre //
		// ------------------------------------------------//
		this.getContentPane().add(panneauDroite);

		// Création d'un JPanel pour l'identifiant //
		// ----------------------------------------//
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(new Color(0, 0, 0, 0));
		panIdentifiant
				.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panIdentifiant.setBorder(BorderFactory
				.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email : ");
		final String identifiantClient = SGBD.selectStringConditionString(
				"CLIENT", "IDCLIENT", "IDCLIENT", idClient);
		identifiant = new JTextField(identifiantClient);
		identifiant.setEnabled(false);
		identifiant.setPreferredSize(new Dimension(120, 25));
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		panIdentifiant.setBounds(40, 75, 300, 60);
		this.add(panIdentifiant);

		// Création d'un JPanel pour la dénomination //
		// ------------------------------------------//
		JPanel panDenomination = new JPanel();
		panDenomination.setBackground(new Color(0, 0, 0, 0));
		panDenomination
				.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		final String denominationClient = SGBD.selectStringConditionString(
				"CLIENT", "DENOMINATIONCLIENT", "IDCLIENT", idClient);
		
		denomination = new JTextField(denominationClient.replaceFirst(
				denominationClient.charAt(0) + "",
				(denominationClient.charAt(0) + "").toUpperCase()));
		denomination.setPreferredSize(new Dimension(110, 25));
		panDenomination.setBorder(BorderFactory
				.createTitledBorder("Denomination"));
		denominationLabel = new JLabel("Dénomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);
		panDenomination.setBounds(40, 140, 300, 60);
		this.add(panDenomination);

		// Création d'un JPanel pour le nom //
		// ---------------------------------//
		JPanel panNom = new JPanel();
		panNom.setBackground(new Color(0, 0, 0, 0));
		panNom.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		String name = SGBD.selectStringConditionString("CLIENT", "NOMCLIENT",
				"IDCLIENT", idClient);
		
		nom = new JTextField(name.replaceFirst(
				name.charAt(0) + "",
				(name.charAt(0) + "").toUpperCase()));
		nom.setPreferredSize(new Dimension(90, 25));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom"));
		nomLabel = new JLabel("Nom :");
		panNom.add(nomLabel);
		panNom.add(nom);
		panNom.setBounds(40, 205, 300, 60);
		this.add(panNom);

		// Création d'un JPanel pour le prénom //
		// ------------------------------------//
		JPanel panPrenom = new JPanel();
		panPrenom.setBackground(new Color(0, 0, 0, 0));
		panPrenom.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panPrenom.setBorder(BorderFactory.createTitledBorder("Prénom"));
		prenomLabel = new JLabel("Prénom : ");
		String prenomClient = SGBD.selectStringConditionString("CLIENT",
				"PRENOMCLIENT", "IDCLIENT", idClient);
		
		prenom = new JTextField(prenomClient.replaceFirst(
				prenomClient.charAt(0) + "",
				(prenomClient.charAt(0) + "").toUpperCase()));
		prenom.setPreferredSize(new Dimension(90, 25));
		panPrenom.add(prenomLabel);
		panPrenom.add(prenom);
		panPrenom.setBounds(40, 270, 300, 60);
		this.add(panPrenom);

		if (denominationClient.equals(" ")) {
			panDenomination.setVisible(false);
			panNom.setVisible(true);
			panPrenom.setVisible(true);
		} else {
			panDenomination.setVisible(true);
			panNom.setVisible(false);
			panPrenom.setVisible(false);
		}

		// Création d'un JPanel pour le type de Compte //
		// --------------------------------------------//
		JPanel panTypeCompte = new JPanel();
		panTypeCompte.setBackground(new Color(0, 0, 0, 0));
		panTypeCompte
				.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panTypeCompte.setBorder(BorderFactory
				.createTitledBorder("Type de compte"));
		String compte = "";
		if (denominationClient.equals(" ")) {
			compte = "Particulier";
		} else {
			compte = "Association";
		}
		typeCompteLabel = new JLabel("Statut Compte : " + compte);
		panTypeCompte.add(typeCompteLabel);
		panTypeCompte.setBounds(40, 10, 300, 60);
		this.add(panTypeCompte);

		// Création d'un JPanel pour l'adresse //
		// ------------------------------------//
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(new Color(0, 0, 0, 0));
		panAdresse.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse"));
		adresseLabel = new JLabel("Adresse : ");
		String adresseClient = SGBD.selectStringConditionString("CLIENT",
				"ADRESSECLIENT", "IDCLIENT", idClient);
		adresse = new JTextField(adresseClient);
		adresse.setPreferredSize(new Dimension(90, 25));
		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);
		panAdresse.setBounds(40, 335, 300, 60);
		this.add(panAdresse);

		// Récupération de l'identifiant de la ville du client pour l'insérer
		// dans le JPanel de la ville
		String idVille = SGBD.selectStringConditionString("CLIENT", "IDVILLE",
				"IDCLIENT", idClient);

		// Création d'un JPanel pour la ville //
		// -----------------------------------//
		JPanel panVille = new JPanel();
		panVille.setBackground(new Color(0, 0, 0, 0));
		panVille.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");
		ville = new JTextField(SGBD.selectStringConditionString("VILLE",
				"NOMVILLE", "IDVILLE", idVille));
		ville.setPreferredSize(new Dimension(90, 25));
		ville.setEnabled(false);
		panVille.add(villeLabel);
		panVille.add(ville);
		panVille.setBounds(40, 465, 300, 60);
		this.add(panVille);

		// Création d'un JPanel pour le Code Postal //
		// ------------------------------------------//
		JPanel panCP = new JPanel();
		panCP.setBackground(new Color(0, 0, 0, 0));
		panCP.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panCP.setBorder(BorderFactory.createTitledBorder("Code Postal"));
		cpLabel = new JLabel("Numéro : ");
		codePostal = new JTextField(SGBD.selectStringConditionString("VILLE",
				"CODEPOSTAL", "IDVILLE", idVille));
		codePostal.setPreferredSize(new Dimension(100, 25));
		panCP.add(cpLabel);
		panCP.add(codePostal);

		// Définition d'un menu déroulant des villes de la base de données
		// qui sera proposé au gérant s'il a saisi un code postal
		// qui n'existe pas encore dans la base de données

		listeVille = new JComboBox();
		ArrayList<String> listeVilles = SGBD.selectListeStringOrdonne("VILLE",
				"NOMVILLE", "NOMVILLE");

		for (String nomVille : listeVilles) {
			listeVille.addItem(nomVille);
		}
		listeVille.setSelectedIndex(0);
		listeVille.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String nomVille = (String) ((JComboBox) e.getSource())
						.getSelectedItem();

				String codePostalSelectionne = SGBD
						.selectStringConditionString("VILLE", "CODEPOSTAL",
								"NOMVILLE", nomVille);
				codePostal.setText(codePostalSelectionne);
			}
		});

		listeVille.setEnabled(false);
		listeVille.setVisible(false);
		panCP.add(listeVille);

		panCP.setBounds(40, 400, 300, 60);
		this.add(panCP);

		// Création d'un JPanel pour le Telephone //
		// ---------------------------------------//
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(new Color(0, 0, 0, 0));
		panTelephone.setPreferredSize(dimensionpanneauInformationsPersonnelles);
		panTelephone.setBorder(BorderFactory.createTitledBorder("Telephone"));
		telLabel = new JLabel("Numéro : ");
		telephone = new JTextField(SGBD.selectStringConditionString("CLIENT",
				"TELEPHONE", "IDCLIENT", idClient));
		telephone.setPreferredSize(new Dimension(90, 25));
		panTelephone.add(telLabel);
		panTelephone.add(telephone);
		panTelephone.setBounds(40, 530, 300, 60);
		this.add(panTelephone);

		// Création d'un JPanel pour l'Activation du compte //
		// -------------------------------------------------//
		JPanel panActivationCompte = new JPanel();
		panActivationCompte.setBackground(new Color(0, 0, 0, 0));
		panActivationCompte.setPreferredSize(new Dimension(220, 60));
		panActivationCompte.setBorder(BorderFactory
				.createTitledBorder("Activation du compte"));
		etatCompte = SGBD.selectStringConditionString("CLIENT", "ETATCOMPTE",
				"IDCLIENT", idClient);
		String actifCompteClient = SGBD.selectStringConditionString("CLIENT",
				"ETATCOMPTE", "IDCLIENT", idClient);

		// Définition d'un JComboBox pour modifier l'état d'un compte //
		// -----------------------------------------------------------//
		ActifCompteLabel = new JLabel("Compte Actif ? ");
		ActivationCompteBox = new JComboBox();
		ActivationCompteBox.addItem("Oui");
		ActivationCompteBox.addItem("Non");
		if (actifCompteClient.equals("Activé")) {
			ActivationCompteBox.setSelectedItem("Oui");
		} else {
			ActivationCompteBox.setSelectedItem("Non");
		}

		ActivationCompteBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String choix = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
				if (choix.equals("Oui")) {
					etatCompte = "Activé";
				} else {
					etatCompte = "Désactivé";
				}
			}
		});

		panActivationCompte.add(ActifCompteLabel);
		panActivationCompte.add(ActivationCompteBox);
		panActivationCompte.setBounds(40, 595, 200, 60);
		this.add(panActivationCompte);

		// Declaration d'un JPanel qui contiendra les infos concernant la
		// fidélité
		JPanel panneauFidelite = new JPanel();
		panneauFidelite.setLayout(new GridLayout(2, 1, 0, 5));
		panneauFidelite.setBackground(new Color(0, 0, 0, 0));
		panneauFidelite.setBorder(BorderFactory
				.createTitledBorder("Informations sur la fidélité"));
		panneauFidelite.setPreferredSize(new Dimension(300, 150));
		panneauFidelite.setBounds(552, 10, 350, 160);
		this.add(panneauFidelite);

		// Création d'un JPanel pour le Compte Fidélité //
		// ---------------------------------------------//
		JPanel panCompteFidelite = new JPanel();
		panCompteFidelite.setBackground(new Color(0, 0, 0, 0));
		panCompteFidelite.setPreferredSize(new Dimension(290, 60));
		panCompteFidelite.setBorder(BorderFactory.createEmptyBorder());
		compteFidelLabel = new JLabel("Possesion d'un compte Fidelité :");

		ArrayList<String> fideliteClient = new ArrayList<String>();
		fideliteClient = SGBD.recupererInformationFideliteClient(idClient);
		String estFidele = fideliteClient.get(0);
		String fidelite = "";

		if (estFidele == "false") {
			fidelite = "Non";
		} else
			fidelite = "Oui";

		compteFidelite = new JTextField(fidelite);
		compteFidelite.setEnabled(false);
		compteFidelite.setPreferredSize(new Dimension(90, 25));
		panCompteFidelite.add(compteFidelLabel);
		panCompteFidelite.add(compteFidelite);
		panneauFidelite.add(panCompteFidelite);

		// Création d'un JPanel pour le Nombre de Points sur le Compte Fidélité

		JPanel panPointsFidelite = new JPanel();
		panPointsFidelite.setBackground(new Color(0, 0, 0, 0));
		panPointsFidelite.setPreferredSize(new Dimension(290, 60));
		panPointsFidelite.setBorder(BorderFactory.createEmptyBorder());

		String labelNbreDePoints = "Nombre de points ";
		String nbPointsCarte = "";

		if (fidelite.equals("Oui")) {
			labelNbreDePoints = labelNbreDePoints
					+ " sur la carte de fidelite "
					+ SGBD.selectStringConditionString("CARTE_FIDELITE",
							"IDCARTEFIDELITE", "IDCLIENT", idClient) + " :";
			nbPointsCarte = fideliteClient.get(1);
		}

		nbPointsLabel = new JLabel(labelNbreDePoints);
		nbrePoints = new JTextField(nbPointsCarte);
		nbrePoints.setEnabled(false);
		nbrePoints.setPreferredSize(new Dimension(90, 25));
		panPointsFidelite.add(nbPointsLabel);
		panPointsFidelite.add(nbrePoints);
		panneauFidelite.add(panPointsFidelite);

		this.getContentPane().add(panneauGauche);

		// Définition du JPanel accueillant les boutons situés en bas de
		// fenêtre
		JPanel panneauBouton = new JPanel();

		// Définition d'un bouton qui permet de confirmer les modifications
		// faites dans la fiche client dans les JTextField et dans les JComboBox
		JButton boutonValider = new JButton("Confirmer");

		boutonValider.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				ImageIcon image = new ImageIcon("Ressources/images/warning.png");
				try {

					// Vérification du nombre de caractères

					if (denomination.getText().length() > 40
							| nom.getText().length() > 40
							| prenom.getText().length() > 40
							| identifiant.getText().length() > 40
							| adresse.getText().length() > 60) {
						throw new ExceptionExcesDeCaracteres(
								"Un des champs saisis comporte trop de caractères !");
					}

					// Vérification de la longueur du code postal

					if (codePostal.getText().length() != 5) {

						throw new ExceptionCodePostalDifferentDeCinqChiffres(
								"Un code postal doit contenir 5 chiffres !");
					}

					// Vérification de la longueur du numéro de téléphone

					if (telephone.getText().length() != 10) {

						throw new ExceptionNumeroDeTelephoneDifferentDeDixChiffres(
								"Un numéro de téléphone doit être composé de 10 chiffres !");
					}

					// Vérification de la valeur du code postal

					int cp = Integer.parseInt(codePostal.getText());
					if (cp <= 999 | cp >= 96000) {
						throw new ExceptionCodePostalIncorrect(
								"Le code postal saisi est incorrect !");
					}

					// Verification code postal base de donnees

					if (!SGBD.verifierCodePostalExisteDansBase(codePostal
							.getText())) {
						throw new ExceptionCodePostalIncorrect(
								"Le code postal n'existe pas"
										+ " dans la base de données actuelle !");
					}

					// Vérification de la cohérence du numéro de téléphone

					long tel = Long.parseLong(telephone.getText());
					if (tel < 100000000 | tel >= 800000000) {
						throw new ExceptionNumeroDeTelephoneIncorrect(
								"Le numéro de téléphone saisi est incorrect !");
					}

					// Si aucune erreur n'est détectée, nous allons modifier les
					// informations du client dans la base de données selon son
					// type de compte

					if (denominationClient.equals(" ")) {
						Particulier.modifierBDDclient(identifiantClient,
								etatCompte);

						Particulier.modifierBDDparticulier(identifiantClient,
								nom.getText(), prenom.getText(),
								adresse.getText(), codePostal.getText(),
								telephone.getText());
					} else {
						Association.modifierBDDclient(identifiantClient,
								etatCompte);
						Association.modifierBDDAssoc(identifiantClient,
								denomination.getText(), adresse.getText(),
								codePostal.getText(), telephone.getText());
					}

					// Une fois la modification faite, nous fermons la fenêtre
					dispose();

				} catch (ExceptionExcesDeCaracteres e1) {

					erreurModification
							.showMessageDialog(
									null,
									"Un des champs saisis comporte trop de caractères. Vérifiez ce que vous avez saisi.",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);
				} catch (ExceptionCodePostalDifferentDeCinqChiffres e5) {

					erreurModification
							.showMessageDialog(
									null,
									"Un code postal doit contenir 5 chiffres. Vérifiez ce que vous avez saisi.",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);
				} catch (ExceptionNumeroDeTelephoneDifferentDeDixChiffres e6) {

					erreurModification
							.showMessageDialog(
									null,
									"Le numéro de téléphone doit être composé de 10 chiffres. Vérifiez ce que vous avez saisi.",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);
				} catch (ExceptionCodePostalIncorrect e7) {

					erreurModification.showMessageDialog(
							null,
							e7.getMessage()+ "/n"
							+ "Veuillez sélectionner votre ville " +
							"dans le menu déroulant",
							"Attention !", JOptionPane.WARNING_MESSAGE, image);

					// Comme le gérant a fait une erreur de saisie de code
					// postal nous lui affichons le menu déroulant à la place du
					// champ de saisie
					codePostal.setVisible(false);
					listeVille.setEnabled(true);
					listeVille.setVisible(true);
					cpLabel.setText("Choix de la ville ");
					repaint();

				} catch (ExceptionNumeroDeTelephoneIncorrect e8) {

					erreurModification
							.showMessageDialog(
									null,
									"Le numéro de téléphone est incorrect. Vérifiez ce que vous avez saisi.",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);
				}

			}
		});

		// Définition du bouton permettant le retour à la page précédante et //
		// l'annulation des modifications en cours tout en fermant la fenêtre //
		// -------------------------------------------------------------------//

		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// Ajout des boutons au JPanel des boutons //
		// ----------------------------------------//
		panneauBouton.add(boutonValider);
		panneauBouton.add(retourBouton);

		// Ajout du JPanel en bas de la fenêtre //
		// -------------------------------------//
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);

		pack();
		repaint();
	}
}