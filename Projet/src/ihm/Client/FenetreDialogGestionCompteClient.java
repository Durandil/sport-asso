package ihm.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
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

import metier.Association;
import metier.Particulier;

import basededonnees.SGBD;

/**
 * La classe FenetreDialogGestionCompteClient permet � un utilisateur client de
 * pouvoir consulter ses informations personnelles et aussi de pouvoir en
 * modifier certaines sous r�serve que les modifications effectu�es soient
 * correctes.
 * 
 * 
 */
public class FenetreDialogGestionCompteClient extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel denominationLabel, iconImage, nomLabel, prenomLabel,
			adresseLabel, villeLabel, cpLabel, telLabel, identifiantLabel;
	private JTextField nom, prenom, ville, codePostal, telephone, identifiant,
			denomination;
	private TextArea adresse;
	private JComboBox listeVille;

	/**
	 * Constructeur qui ouvre une fen�tre permettant la gestion d'un compte
	 * client avec diff�rents champs de saisie initialis�s dans
	 * {@link FenetreDialogGestionCompteClient#initComponent(String)}
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param title
	 *            String indiquant le titre de la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
	 * @param idclient
	 *            Identifiant unique du client qui veut consulter ses
	 *            informations personnelles
	 * 
	 */
	public FenetreDialogGestionCompteClient(JFrame parent, String title,
			boolean modal, String idclient) {
		super(parent, title, modal);
		this.setSize(550, 700);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(idclient);
	}

	/**
	 * <p>
	 * Initialisation du contenu de la fen�tre :
	 * <ul>
	 * <li>un JPanel pour afficher une image sur la fen�tre.</li>
	 * <li>un JPanel pour chacun des champs que l'on peut modifier
	 * (codePostal,t�l�phone,...)</li>
	 * <li>un JPanel contenant deux boutons : Confirmer les modifications et
	 * Annuler les modifications.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param idClient
	 *            Identifiant unique du client qui veut consulter ses
	 *            informations personnelles
	 */
	private void initComponent(String idClient) {
		final String numClient = idClient;

		// Cr�ation du JPanel qui accueillera l'image de la fen�tre //
		// ----------------------------------------------------------//
		iconImage = new JLabel(new ImageIcon("src/images/logos.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(iconImage);

		// Cr�ation du JPanel qui accueillera tous les champs //
		// ----------------------------------------------------//
		JPanel content = new JPanel();
		content.setBackground(Color.white);

		// Cr�ation du JPanel du champ de saisie non modifiable : Identifiant //
		// --------------------------------------------------------------------//
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(Color.white);
		panIdentifiant.setPreferredSize(new Dimension(260, 60));
		panIdentifiant.setBorder(BorderFactory
				.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email : ");

		identifiant = new JTextField(SGBD.selectStringConditionString("CLIENT",
				"IDCLIENT", "IDCLIENT", idClient));
		identifiant.setEnabled(false);

		identifiant.setPreferredSize(new Dimension(115, 25));
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		content.add(panIdentifiant);

		// Pour savoir si le client est un particulier ou une association, on
		// regarde le champ DENOMINATIONCLIENT dans la table,
		// s'il est vide c'est un particulier sinon c'est une association

		if (!SGBD.selectStringConditionString("CLIENT", "DENOMINATIONCLIENT",
				"IDCLIENT", idClient).equals(" ")) {

			// Cr�ation du JPanel du champ de saisie : Denomination //
			// ------------------------------------------------------//
			JPanel panDenomination = new JPanel();
			panDenomination.setBackground(Color.white);
			panDenomination.setPreferredSize(new Dimension(260, 60));
			panDenomination.setBorder(BorderFactory
					.createTitledBorder("D�nomination"));
			denominationLabel = new JLabel("D�nomination");

			denomination = new JTextField(SGBD.selectStringConditionString(
					"CLIENT", "DENOMINATIONCLIENT", "IDCLIENT", idClient));
			denomination.setPreferredSize(new Dimension(90, 25));

			panDenomination.add(denominationLabel);
			panDenomination.add(denomination);
			content.add(panDenomination);

		} else {
			// Cr�ation du JPanel du champ de saisie : Nom //
			// ---------------------------------------------//
			JPanel panNom = new JPanel();
			panNom.setBackground(Color.white);
			panNom.setPreferredSize(new Dimension(260, 60));
			panNom.setBorder(BorderFactory.createTitledBorder("Nom"));
			nomLabel = new JLabel("Nom :");

			nom = new JTextField(SGBD.selectStringConditionString("CLIENT",
					"NOMCLIENT", "IDCLIENT", idClient));
			nom.setPreferredSize(new Dimension(90, 25));

			panNom.add(nomLabel);
			panNom.add(nom);
			content.add(panNom);

			// Cr�ation du JPanel du champ de saisie : Pr�nom //
			// ------------------------------------------------//
			JPanel panPrenom = new JPanel();
			panPrenom.setBackground(Color.white);
			panPrenom.setPreferredSize(new Dimension(260, 60));
			panPrenom.setBorder(BorderFactory.createTitledBorder("Pr�nom"));
			prenomLabel = new JLabel("Pr�nom : ");

			prenom = new JTextField(SGBD.selectStringConditionString("CLIENT",
					"PRENOMCLIENT", "IDCLIENT", idClient));
			prenom.setPreferredSize(new Dimension(90, 25));

			panPrenom.add(prenomLabel);
			panPrenom.add(prenom);
			content.add(panPrenom);
		}

		// Cr�ation du JPanel du champ de saisie : Adresse //
		// -------------------------------------------------//
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(Color.white);
		panAdresse.setPreferredSize(new Dimension(260, 90));
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse"));
		adresseLabel = new JLabel("Adresse : ");

		adresse = new TextArea(SGBD.selectStringConditionString("CLIENT",
				"ADRESSECLIENT", "IDCLIENT", idClient));
		adresse.setBackground(Color.LIGHT_GRAY);
		adresse.setPreferredSize(new Dimension(140, 50));

		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);
		content.add(panAdresse);

		// R�cup�ration de l'identifiant Idville du client pour l'afficher //
		// ---------------- dans le JPanel de la ville --------------------//
		// -----------------------------------------------------------------//
		String idVille = SGBD.selectStringConditionString("CLIENT", "IDVILLE",
				"IDCLIENT", idClient);

		// Cr�ation du JPanel du champ de saisie non modifiable : Ville //
		// --------------------------------------------------------------//
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(new Dimension(260, 60));
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");

		ville = new JTextField(SGBD.selectStringConditionString("VILLE",
				"NOMVILLE", "IDVILLE", idVille));
		ville.setEnabled(false);
		ville.setPreferredSize(new Dimension(90, 25));

		panVille.add(villeLabel);
		panVille.add(ville);
		content.add(panVille);

		// Cr�ation du JPanel du champ de saisie : Code Postal //
		// ----------------------------------------------------//
		JPanel panCP = new JPanel();
		panCP.setBackground(Color.white);
		panCP.setPreferredSize(new Dimension(260, 60));
		panCP.setBorder(BorderFactory.createTitledBorder("Code Postal"));
		cpLabel = new JLabel("Code Postal : ");

		codePostal = new JTextField(SGBD.selectStringConditionString("VILLE",
				"CODEPOSTAL", "IDVILLE", idVille));
		codePostal.setPreferredSize(new Dimension(100, 25));

		panCP.add(cpLabel);
		panCP.add(codePostal);
		content.add(panCP);

		// D�finition d'un menu d�roulant des villes de la base de donn�es
		// qui sera propos� � l'utilisateur qui a saisi un code postal
		// qui n'existe pas encore dans la base de donn�es 
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

		// Cr�ation du JPanel du champ de saisie : Telephone //
		// --------------------------------------------------//
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(Color.white);
		panTelephone.setPreferredSize(new Dimension(260, 60));
		panTelephone.setBorder(BorderFactory.createTitledBorder("T�l�phone"));
		telLabel = new JLabel("T�l�phone : ");

		telephone = new JTextField(SGBD.selectStringConditionString("CLIENT",
				"TELEPHONE", "IDCLIENT", idClient));
		telephone.setPreferredSize(new Dimension(90, 25));

		panTelephone.add(telLabel);
		panTelephone.add(telephone);
		content.add(panTelephone);

		// Cr�ation du JPanel qui accueillera les deux //
		// boutons de la fen�tre : Valider et Annuler //
		// -------------------------------------------//
		JPanel control = new JPanel();
		JButton validationBouton = new JButton("Valider");

		// D�finition de l'action du bouton Valider //
		// -----------------------------------------//
		validationBouton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					// V�rification de la validit� de la d�nomination saisie
					// pour les associations et du couple nom/pr�nom pour 
					// les particuliers 

					if (!SGBD.selectStringConditionString("CLIENT",
							"DENOMINATIONCLIENT", "IDCLIENT", numClient)
							.equals(" ")) {

						if (denomination.getText().length() > 40) {
							throw new ExceptionExcesDeCaracteres(
									"La d�nomination "
											+ "contient trop de caract�res !");
						}
					} else {

						if (prenom.getText().length() > 40
								| nom.getText().length() > 40) {
							throw new ExceptionExcesDeCaracteres(
									"Un des champs saisis"
											+ " contient trop de caract�res !");
						}
					}

					// V�rification de la validit� de l'adresse

					if (adresse.getText().length() > 40) {
						throw new ExceptionExcesDeCaracteres(
								"L'adresse saisie contient"
										+ " trop de caract�res !");
					}

					// V�rification de la longueur du num�ro de t�l�phone

					if (telephone.getText().length() != 10) {
						throw new ExceptionNumeroDeTelephoneDifferentDeDixChiffres(
								"Un num�ro de t�l�phone doit �tre compos� de 10 chiffres !");
					}

					// V�rification du num�ro de t�l�phone

					long tel = Long.parseLong(telephone.getText());
					if (tel < 100000000 | tel >= 800000000) {
						throw new ExceptionNumeroDeTelephoneIncorrect(
								"Le num�ro de t�l�phone saisi est incorrect !");
					}

					// V�rification de la longueur du code postal

					if (codePostal.getText().length() != 5) {
						throw new ExceptionCodePostalDifferentDeCinqChiffres(
								"Un code postal doit contenir 5 chiffres !");
					}

					// V�rification du code postal saisi

					int cp = Integer.parseInt(codePostal.getText());
					if (cp <= 999 | cp >= 96000) {
						throw new ExceptionCodePostalIncorrect(
								"Le code postal saisi est incorrect !");
					}

					// V�rification du code postal dans la base de donn�es

					if (!SGBD.verifierCodePostalExisteDansBase(codePostal
							.getText())) {
						throw new ExceptionCodePostalIncorrect(
								"Le code postal n'existe pas"
										+ " dans la base de donn�es actuelle !");
					}

					// Si aucune erreur d�tect�e, alors enregistrement des
					// modifications dans la base de donn�es

					if (!SGBD.selectStringConditionString("CLIENT",
							"DENOMINATIONCLIENT", "IDCLIENT", numClient)
							.equals(" ")) {
						Association.modifierBDDAssoc(numClient,
								denomination.getText(), adresse.getText(),
								codePostal.getText(), telephone.getText());
					} else {
						Particulier.modifierBDDparticulier(numClient,
								nom.getText(), prenom.getText(),
								adresse.getText(), codePostal.getText(),
								telephone.getText());
					}

					// Fermeture de la fen�tre une fois les modifications
					// enregistr�es
					setVisible(false);

				} catch (ExceptionExcesDeCaracteres e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage()
							+ " veuillez modifier le champ concern�",
							"Attention ", JOptionPane.WARNING_MESSAGE);

				} catch (ExceptionCodePostalDifferentDeCinqChiffres e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage()
							+ " veuillez modifier le champ concern�",
							"Attention ", JOptionPane.WARNING_MESSAGE);

				} catch (ExceptionNumeroDeTelephoneDifferentDeDixChiffres e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage()
							+ " veuillez modifier le champ concern�",
							"Attention ", JOptionPane.WARNING_MESSAGE);

				} catch (ExceptionCodePostalIncorrect e4) {
					JOptionPane.showMessageDialog(null, e4.getMessage(),
							"Attention ", JOptionPane.WARNING_MESSAGE);

					// Comme l'utilisateur a fait une erreur de saisie de code
					// postal, nous lui affichons le menu d�roulant � la
					// place du champ de saisie du code postal
					codePostal.setVisible(false);
					listeVille.setEnabled(true);
					listeVille.setVisible(true);

				} catch (ExceptionNumeroDeTelephoneIncorrect e5) {
					JOptionPane.showMessageDialog(null, e5.getMessage()
							+ " veuillez modifier le champ concern�",
							"Attention ", JOptionPane.WARNING_MESSAGE);
				}

			}

		});

		// D�finition de l'action du bouton Annuler //
		// ---- qui ferme la fen�tre du compte -----//
		// -----------------------------------------//
		JButton annulationBouton = new JButton("Annuler");
		annulationBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		// Ajout des boutons au JPanel des boutons : control //
		// ---------------------------------------------------//
		control.add(validationBouton);
		control.add(annulationBouton);

		// Ajout des composants principaux au conteneur de la fen�tre //
		// ------------------------------------------------------------//
		this.getContentPane().add(panIcon, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

}
