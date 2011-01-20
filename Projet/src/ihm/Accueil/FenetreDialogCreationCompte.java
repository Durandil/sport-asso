package ihm.Accueil;

import ihm.MenuUtilisateur;

import javax.swing.JDialog;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exception.ExceptionExcesDeCaracteres;
import exception.Client.ExceptionCaractereInterdit;
import exception.Client.ExceptionCodePostalDifferentDeCinqChiffres;
import exception.Client.ExceptionCodePostalIncorrect;
import exception.Client.ExceptionAucunItemSelectionneDansJComboBox;
import exception.Client.ExceptionMailDejaExistant;
import exception.Client.ExceptionMailSansArobase;
import exception.Client.ExceptionMailsDifferents;
import exception.Client.ExceptionNumeroDeTelephoneDifferentDeDixChiffres;
import exception.Client.ExceptionNumeroDeTelephoneIncorrect;

import basededonnees.SGBD;
import metier.Association;
import metier.Message;
import metier.Particulier;

/**
 * Classe repr�sentant la fen�tre s'affichant lors de la cr�ation de compte.
 */
public class FenetreDialogCreationCompte extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel typeCompteLabel, denominationLabel, identifiantVerifLabel,
			image, nomLabel, prenomLabel, adresseLabel, cpLabel, telLabel,
			fideliteLabel, identifiantLabel;
	private JTextField nom, prenom, adresse, identifiantVerification,
			codePostal, telephone, identifiant, denomination;
	private JComboBox compte, fidelite, ville;
	// Par d�faut, le client ne d�sire pas une carte de fid�lit�,
	// le bool�en associ� vaut ainsi "faux" � l'origine
	private boolean estFidele = false;
	private JOptionPane creationCorrecte, creationMotDePasse, erreurCreation;
	public static String itemSelectionne;
	public static String itemFidelite;
	public static String codePostalSelectionne;
	// Bool�ens permettant de controler que le client a bien cliqu� dans les
	// deux JComboBox de la fen�tre
	private static boolean itemTypeCompteSelectionne = false;
	private static boolean itemFideliteSelectionne = false;

	/**
	 * Constructeur
	 * 
	 * @param parent
	 * 
	 * @param title
	 *            nom donn� � la fen�tre
	 * @param modal
	 *            bool�en indiquant si la fenetre bloque ou non les interactions
	 *            avec les autres fen�tres
	 * 
	 * @throws ExceptionMailsDifferents
	 * @throws ExceptionMailDejaExistant
	 * @throws ExceptionCaractereInterdit
	 * @throws ExceptionExcesDeCaracteres
	 * @throws ExceptionCodePostalDifferentDeCinqChiffres
	 * @throws ExceptionNumeroDeTelephoneDifferentDeDixChiffres
	 * @throws ExceptionCodePostalIncorrect
	 * @throws ExceptionNumeroDeTelephoneIncorrect
	 */
	public FenetreDialogCreationCompte(JFrame parent, String title,
			boolean modal) throws ExceptionMailSansArobase,
			ExceptionMailsDifferents, ExceptionMailDejaExistant,
			ExceptionCaractereInterdit, ExceptionExcesDeCaracteres,
			ExceptionCodePostalDifferentDeCinqChiffres,
			ExceptionNumeroDeTelephoneDifferentDeDixChiffres,
			ExceptionCodePostalIncorrect, ExceptionNumeroDeTelephoneIncorrect {
		super(parent, title, modal);
		this.setSize(400, 720);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	/**
	 * Initialise le contenu de la bo�te
	 */
	private void initComponent() throws ExceptionMailsDifferents,
			ExceptionMailDejaExistant, ExceptionCaractereInterdit,
			ExceptionExcesDeCaracteres,
			ExceptionCodePostalDifferentDeCinqChiffres,
			ExceptionNumeroDeTelephoneDifferentDeDixChiffres,
			ExceptionCodePostalIncorrect, ExceptionNumeroDeTelephoneIncorrect {

		// R�cup�ration de l'image qui sera affich�e sur la fen�tre
		image = new JLabel(new ImageIcon("Ressources/images/logos.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(image);

		// Cr�ation d'un JPanel pour le type de compte //
		// ---------------------------------------------//
		JPanel panTypeCompte = new JPanel();
		panTypeCompte.setBackground(Color.white);
		panTypeCompte.setPreferredSize(new Dimension(220, 60));
		panTypeCompte.setBorder(BorderFactory
				.createTitledBorder("Type de compte"));
		typeCompteLabel = new JLabel("Statut Compte : ");

		// D�finition d'un menu d�roulant permettant
		// � l'utilisateur de choisir son type de compte
		compte = new JComboBox();
		compte.addItem("Compte Particulier");
		compte.addItem("Compte Collectivites");
		compte.add(typeCompteLabel);
		compte.setVisible(true);
		compte.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// R�cup�ration du type de compte s�lectionn�
				itemSelectionne = (String) ((JComboBox) e.getSource())
						.getSelectedItem();

				// Changement de l'�tat du bool�en pour controler le clic
				// dans le menu d�roulant du client
				itemTypeCompteSelectionne = true;

				if (itemSelectionne == "Compte Particulier") {
					nom.setEnabled(true);
					prenom.setEnabled(true);
					denomination.setEnabled(false);
					denomination.setText("");

				} else {
					nom.setEnabled(false);
					prenom.setEnabled(false);
					nom.setText("");
					prenom.setText("");
					denomination.setEnabled(true);
				}
			}
		});

		panTypeCompte.add(compte);

		// Cr�ation d'un JPanel pour la saisie de la d�nomination //
		// --------------------------------------------------------//
		JPanel panDenomination = new JPanel();
		panDenomination.setBackground(Color.white);
		panDenomination.setPreferredSize(new Dimension(220, 60));

		// Mise en place du champ de saisie de la d�nomination
		denomination = new JTextField();
		denomination.setPreferredSize(new Dimension(90, 25));

		panDenomination.setBorder(BorderFactory
				.createTitledBorder("D�nomination"));
		denominationLabel = new JLabel("D�nomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);

		// Cr�ation d'un JPanel pour la saisie du nom //
		// --------------------------------------------//
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(220, 60));

		// Mise en place du champ de saisie du nom
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(90, 25));

		panNom.setBorder(BorderFactory.createTitledBorder("Nom"));
		nomLabel = new JLabel("Nom :");
		panNom.add(nomLabel);
		panNom.add(nom);

		// Cr�ation d'un JPanel pour la saisie du pr�nom //
		// -----------------------------------------------//
		JPanel panPrenom = new JPanel();
		panPrenom.setBackground(Color.white);
		panPrenom.setPreferredSize(new Dimension(220, 60));
		panPrenom.setBorder(BorderFactory.createTitledBorder("Pr�nom"));
		prenomLabel = new JLabel("Pr�nom : ");

		// Mise en place du champ de saisie du pr�nom
		prenom = new JTextField();
		prenom.setPreferredSize(new Dimension(90, 25));

		panPrenom.add(prenomLabel);
		panPrenom.add(prenom);

		// Cr�ation d'un JPanel pour la saisie de l'adresse //
		// --------------------------------------------------//
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(Color.white);
		panAdresse.setPreferredSize(new Dimension(220, 60));
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse"));
		adresseLabel = new JLabel("Adresse : ");

		// Mise en place du champ de saisie de l'adresse
		adresse = new JTextField();
		adresse.setPreferredSize(new Dimension(100, 25));

		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);

		// Cr�ation d'un JPanel pour la saisie du Code Postal //
		// ----------------------------------------------------//
		final JPanel panCP = new JPanel();
		panCP.setBackground(Color.white);
		panCP.setPreferredSize(new Dimension(220, 90));
		panCP.setBorder(BorderFactory.createTitledBorder("Code Postal"));
		cpLabel = new JLabel("Num�ro : ");

		// Mise en place du champ de saisie du code postal
		codePostal = new JTextField();
		codePostal.setPreferredSize(new Dimension(100, 25));

		panCP.add(cpLabel);
		panCP.add(codePostal);

		// D�finition d'un menu d�roulant des villes de la base de donn�es
		// qui sera propos� aux utilisateurs qui ont saisi un code postal
		// qui n'existe pas encore dans la base de donn�es
		ville = new JComboBox();
		ArrayList<String> listeVille = SGBD.selectListeStringOrdonne("VILLE",
				"NOMVILLE", "NOMVILLE");

		for (String nomVille : listeVille) {
			ville.addItem(nomVille);
		}
		ville.setSelectedIndex(0);
		ville.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String nomVille = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
				codePostalSelectionne = SGBD.selectStringConditionString(
						"VILLE", "CODEPOSTAL", "NOMVILLE", nomVille);
				codePostal.setText(codePostalSelectionne);
			}
		});

		ville.setEnabled(false);
		ville.setVisible(false);
		panCP.add(ville);

		// Cr�ation d'un JPanel pour la saisie du t�l�phone //
		// -------------------------------------------------//
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(Color.white);
		panTelephone.setPreferredSize(new Dimension(220, 60));
		panTelephone.setBorder(BorderFactory.createTitledBorder("T�l�phone"));
		telLabel = new JLabel("Num�ro : ");

		// Mise en place du champ de saisie du num�ro de t�l�phone
		telephone = new JTextField();
		telephone.setPreferredSize(new Dimension(90, 25));

		panTelephone.add(telLabel);
		panTelephone.add(telephone);

		// Cr�ation d'un JPanel pour la saisie de l'identifiant //
		// -----------------------------------------------------//
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setLayout(new GridLayout(2, 2, 2, 2));
		panIdentifiant.setBackground(Color.white);
		panIdentifiant.setPreferredSize(new Dimension(250, 90));
		panIdentifiant.setBorder(BorderFactory
				.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email : ");

		// Mise en place du champ de saisie de l'adresse identifiant
		identifiant = new JTextField();
		identifiant.setPreferredSize(new Dimension(90, 20));

		// Mise en place du champ de saisie de confirmation de l'adresse
		identifiantVerification = new JTextField();
		identifiantVerification.setPreferredSize(new Dimension(90, 20));
		identifiantVerifLabel = new JLabel("Reconfirmer");

		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		panIdentifiant.add(identifiantVerifLabel);
		panIdentifiant.add(identifiantVerification);

		// Cr�ation d'un JPanel pour la demande de carte de fidelit�
		JPanel panFidelite = new JPanel();
		panFidelite.setBackground(Color.white);
		panFidelite.setPreferredSize(new Dimension(220, 80));
		fideliteLabel = new JLabel("D�sirez-vous une carte de fidelit� ? : ");
		panFidelite.add(fideliteLabel);

		// D�finition d'un menu d�roulant permettant � l'utilisateur
		// de choisir s'il souhaite devenir adh�rent ou non
		fidelite = new JComboBox();
		fidelite.addItem("Oui");
		fidelite.addItem("Non");
		fidelite.setVisible(true);
		fidelite.setSelectedItem("Non");
		fidelite.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				itemFidelite = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
				// Changement de l'�tat du bool�en pour controler le clic
				// dans le menu d�roulant du client
				itemFideliteSelectionne = true;

				// Le bool�en estFidele passe � faux si l'utilisateur r�pond
				// "Non" � la question
				// Il reste � vrai s'il r�pond "Oui"

				if (itemFidelite == "Non") {
					estFidele = false;
				} else {
					estFidele = true;
				}
			}
		});

		panFidelite.add(fidelite);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panTypeCompte);
		content.add(panIdentifiant);
		content.add(panDenomination);
		content.add(panNom);
		content.add(panPrenom);
		content.add(panAdresse);
		content.add(panCP);
		content.add(panTelephone);
		content.add(panFidelite);

		// D�finition du JPanel qui accueillera //
		// les deux boutons de la fen�tre ------//
		// -------------------------------------//
		JPanel control = new JPanel();

		// D�finition de l'action du bouton Valider //
		// qui v�rifie d'abord que les champs ont �t� //
		// correctement saisis puis enregistre les //
		// informations du compte dans la base de donn�es //
		// ----------------------------------------------//
		JButton validationBouton = new JButton("Valider");

		validationBouton.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				/**
				 * Au d�but, le type de compte est sur "Compte Particulier" par
				 * d�faut Mais le champ D�nomination n'est pas gris� il faut
				 * res�lectionner Compte Particulier pour que D�nomination se
				 * grise.
				 */

				erreurCreation = new JOptionPane();
				ImageIcon image = new ImageIcon("Ressources/images/warning.png");

				try {
					// V�rification des champs
					if (!identifiant.getText().equals(
							identifiantVerification.getText())) {
						throw new ExceptionMailsDifferents(
								"L'adresse de confirmation est diff�rente de la premi�re saisie !");
					}
					ArrayList<String> listeMails = new ArrayList<String>();
					listeMails = SGBD.selectListeString("CLIENT", "IDCLIENT");

					// V�rification de la pr�sence de
					// l'adresse mail dans la base
					int test = 0;
					for (int i = 0; i < listeMails.size(); i++) {
						if (identifiant.getText().equals(listeMails.get(i))) {
							test = 1;
						}
					}

					if (test == 1) {
						throw new ExceptionMailDejaExistant(
								"Cette adresse mail est d�j� utilis�e par un autre utilisateur !");
					}

					// V�rification de l'absence du caract�re ' dans
					// l'adresse mail

					if (identifiant.getText().contains("'")) {
						throw new ExceptionCaractereInterdit(
								"Votre adresse mail ne peut pas contenir d'apostrophe !");
					}

					// V�rification du nombre de caract�res

					if (denomination.getText().length() > 40

					| nom.getText().length() > 40
							| prenom.getText().length() > 40
							| identifiant.getText().length() > 40
							| adresse.getText().length() > 60) {
						throw new ExceptionExcesDeCaracteres(
								"Un des champs saisis comporte trop de caract�res !");
					}
					// V�rification de la longueur du code postal

					if (codePostal.getText().length() != 5) {

						throw new ExceptionCodePostalDifferentDeCinqChiffres(
								"Un code postal doit contenir 5 chiffres !");
					}

					// V�rification de la longueur du num�ro de
					// t�l�phone

					if (telephone.getText().length() != 10) {

						throw new ExceptionNumeroDeTelephoneDifferentDeDixChiffres(
								"Un num�ro de t�l�phone doit �tre compos� de 10 chiffres !");
					}
					// V�rification de la valeur du code
					// postal

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
										+ " dans la base de donn�es actuelle !");
					}

					// V�rification de la coh�rence du
					// num�ro de t�l�phone

					long tel = Long.parseLong(telephone.getText());
					if (tel < 100000000 | tel >= 800000000) {
						throw new ExceptionNumeroDeTelephoneIncorrect(
								"Le num�ro de t�l�phone saisi est incorrect !");
					}
					// V�rification de la pr�sence
					// d'un @ dans l'adresse mail

					if (!identifiant.getText().contains("@")) {
						throw new ExceptionMailSansArobase(
								"Votre adresse mail ne comporte pas d'arobase !");
					}

					// V�rification de la s�lection d'un item dans chacun des
					// deux menus d�roulants

					if (!itemTypeCompteSelectionne) {
						throw new ExceptionAucunItemSelectionneDansJComboBox(
								"Vous n'avez pas s�lectionn� votre type de compte !");
					}

					if (!itemFideliteSelectionne) {
						throw new ExceptionAucunItemSelectionneDansJComboBox(
								"Vous n'avez pas choisi si vous d�siriez un compte "
										+ "fid�lit� dans le magasin !");
					}

					// Si aucune erreur relev�e,
					// la cr�ation de compte s'effectue

					String idVille = SGBD.selectStringConditionString("VILLE",
							"IDVILLE", "CODEPOSTAL", codePostal.getText());

					if (itemSelectionne == "Compte Particulier") {

						Particulier p = new Particulier(nom.getText(), prenom
								.getText(), identifiant.getText(), adresse
								.getText(), idVille, telephone.getText(),
								estFidele);
					} else {
						Association a = new Association(denomination.getText(),
								identifiant.getText(), adresse.getText(),
								idVille, telephone.getText(), estFidele);
					}

					creationCorrecte = new JOptionPane();
					ImageIcon imageInformation = new ImageIcon(
							"Ressources/images/information.jpg");
					creationCorrecte.showMessageDialog(null,
							"Un nouveau compte a �t� cr�e, votre identifiant est : "
									+ identifiant.getText(), "Information",
							JOptionPane.INFORMATION_MESSAGE, imageInformation);

					FenetreDialogIdentification.clientUserIdentifiant = identifiant
							.getText();

					// Nous recherchons le mot de passe
					// dans la base avant de l'afficher
					String motDePasse = SGBD.selectStringConditionString(
							"CLIENT", "MOTDEPASSE", "IDCLIENT",
							identifiant.getText());
					creationMotDePasse = new JOptionPane();

					creationMotDePasse
							.showMessageDialog(
									null,
									"Votre mot de passe a �t� envoy� sur votre messagerie interne.",
									"Information",
									JOptionPane.INFORMATION_MESSAGE,
									imageInformation);

					dispose();

					// envoi du message avec
					// le mot de passe
					java.util.Date date = new java.util.Date();

					@SuppressWarnings("deprecation")
					java.sql.Date dateJour = new java.sql.Date(date.getYear(),
							date.getMonth(), date.getDate());
					Message messageMotPasse = new Message(
							"Envoi des identifiants",
							"Votre compte sport-asso a �t� activ� avec succ�s. Pour rappel, votre identifiant est "
									+ identifiant.getText()
									+ " et votre mot de passe est "
									+ motDePasse
									+ ". Cordialement, M. Poirier ",
							FenetreDialogIdentification.clientUserIdentifiant,
							dateJour, false);

					// Essai d'ouverture du
					// menu Utilisateur apr�s une
					// cr�ation de compte correcte
					MenuUtilisateur men = new MenuUtilisateur();

					// Changement de la valeur des bool�ens permettant de g�rer
					// les clics sur les menus d�roulants afin qu'ils soient
					// bien
					// � false si l'utilisateur veut cr�er un nouveau compte
					itemFideliteSelectionne = false;
					itemTypeCompteSelectionne = false;

				} catch (ExceptionMailsDifferents e1) {

					erreurCreation
							.showMessageDialog(
									null,
									"L'adresse de confirmation est diff�rente de la premi�re saisie. V�rifiez ce que vous avez saisi.",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);
				} catch (ExceptionMailDejaExistant e2) {

					erreurCreation
							.showMessageDialog(
									null,
									"Cette adresse mail est d�j� utilis�e par un autre utilisateur !",
									"Attention", JOptionPane.WARNING_MESSAGE,
									image);
				} catch (ExceptionCaractereInterdit e3) {

					erreurCreation
							.showMessageDialog(
									null,
									"Votre adresse mail comporte une apostrophe ! (caract�re interdit) V�rifiez ce que vous avez saisi.",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);
				} catch (ExceptionExcesDeCaracteres e4) {

					erreurCreation
							.showMessageDialog(
									null,
									"Un des champs saisis comporte trop de caract�res. V�rifiez ce que vous avez saisi.",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);
				} catch (ExceptionCodePostalDifferentDeCinqChiffres e5) {

					erreurCreation
							.showMessageDialog(
									null,
									"Un code postal doit contenir 5 chiffres. V�rifiez ce que vous avez saisi.",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);
				} catch (ExceptionNumeroDeTelephoneDifferentDeDixChiffres e6) {

					erreurCreation
							.showMessageDialog(
									null,
									"Le num�ro de t�l�phone doit �tre compos� de 10 chiffres. V�rifiez ce que vous avez saisi.",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);
				} catch (ExceptionCodePostalIncorrect e7) {

					erreurCreation.showMessageDialog(null, e7.getMessage(),
							"Attention !", JOptionPane.WARNING_MESSAGE, image);

					// Comme l'utilisateur a fait une erreur de saisie de code
					// postal, nous lui affichons le menu d�roulant � la place
					// du champ de saisie
					codePostal.setVisible(false);
					ville.setEnabled(true);
					ville.setVisible(true);
					cpLabel.setText("Choix de votre ville ");

				} catch (ExceptionNumeroDeTelephoneIncorrect e8) {

					erreurCreation
							.showMessageDialog(
									null,
									"Le num�ro de t�l�phone est incorrect. V�rifiez ce que vous avez saisi.",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);
				} catch (ExceptionMailSansArobase e9) {

					erreurCreation
							.showMessageDialog(
									null,
									"Votre adresse mail ne comporte pas de caract�re @",
									"Attention !", JOptionPane.WARNING_MESSAGE,
									image);

				} catch (ExceptionAucunItemSelectionneDansJComboBox e10) {

					erreurCreation.showMessageDialog(null, e10.getMessage(),
							"Attention !", JOptionPane.WARNING_MESSAGE, image);

				}
			}

		});

		// D�finition de l'action du bouton Annuler qui ferme la fen�tre et //
		// ------ retourne sur la fen�tre d'accueil de l'application -------//
		JButton annulationBouton = new JButton("Annuler");
		annulationBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FenetreCompte fen = new FenetreCompte();
				fen.setVisible(true);

			}
		});

		// Ajout des deux boutons au JPanel des boutons : control //
		// -------------------------------------------------------//
		control.add(validationBouton);
		control.add(annulationBouton);

		// Ajout des composants au conteneur de la fen�tre //
		// ------------------------------------------------//
		this.getContentPane().add(panIcon, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

}
