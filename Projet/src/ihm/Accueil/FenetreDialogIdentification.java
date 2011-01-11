package ihm.Accueil;

import ihm.MenuGerant;
import ihm.MenuUtilisateur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import exception.ExceptionCompteInexistant;
import exception.Client.ExceptionCompteDesactive;
import exception.Client.ExceptionMailsDifferents;
import exception.Client.ExceptionMotDePasseErrone;

import basededonnees.SGBD;

/**
 * Classe représentant la fenêtre s'affichant lors de l'identification.
 */
public class FenetreDialogIdentification extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel identifiantLabel, passwordLabel;
	private JTextField identifiant;
	private JPasswordField password;
	private JOptionPane erreurMotPasse, erreurCompte, identificationReussie,
			compteDesactive;

	/**
	 * Identifiant de l'utilisateur
	 */
	public static String clientUserIdentifiant = "";

	/**
	 * Identifiant et mot de passe du gérant inscrits "en dur" dans le programme
	 */
	public static String identifiantGerant = "gerant@sport-asso.fr";
	public static String motDePasseGerant = "1234";

	/**
	 * <p>
	 * Constructeur de la fenêtre permettant l'authentification des 2 types
	 * d'utilisateurs :
	 * <ul>
	 * <li>les clients</li>
	 * <li>le gérant</li>
	 * </ul>
	 * </p>
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param title
	 *            String indiquant le titre de la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * 
	 */
	public FenetreDialogIdentification(JFrame parent, String title,
			boolean modal) {
		super(parent, title, modal);
		this.setSize(550, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	/**
	 * Initialise le contenu de la boîte de dialogue en insérant les JTextField
	 * utiles à la saisie de l'identifiant et du mot de passe
	 * 
	 * @see FenetreDialogIdentification#FenetreDialogIdentification(JFrame,
	 *      String, boolean)
	 */
	private void initComponent() {
		// Création du JPanel pour la saisie de l'identifiant //
		//----------------------------------------------------//
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(new Color(0, 0, 0, 0));
		panIdentifiant.setPreferredSize(new Dimension(220, 60));
		identifiant = new JTextField();
		identifiant.setPreferredSize(new Dimension(100, 25));
		panIdentifiant.setBorder(BorderFactory
				.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email :");
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);

		// Création du JPanel pour la saisie du mot de passe //
		//---------------------------------------------------//
		JPanel panPassword = new JPanel();
		panPassword.setBackground(new Color(0, 0, 0, 0));
		panPassword.setPreferredSize(new Dimension(220, 60));
		panPassword.setBorder(BorderFactory
				.createTitledBorder("Mot de passe :"));
		passwordLabel = new JLabel("Mot de passe : ");
		password = new JPasswordField();
		password.setPreferredSize(new Dimension(90, 25));
		panPassword.add(passwordLabel);
		panPassword.add(password);

		// Définition du JPanel qui accueillera les // 
		// deux panneaux de saisie des champs ------//
		//------------------------------------------//
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panIdentifiant);
		content.add(panPassword);

		// Définition du JPanel qui accueillera // 
		// les 2 boutons : Valider et Annuler --//
		//--------------------------------------//
		JPanel panneauBoutons = new JPanel();

		// Création et définition de l'action du // 
		// bouton Valider dans ActionPerformed --//
		//---------------------------------------//
		JButton validationBouton = new JButton("Valider");

		validationBouton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				try {
					String etat;
					int present = 0;
					ArrayList<String[]> listeMailsMdps = new ArrayList<String[]>();
					listeMailsMdps = SGBD.selectDeuxChampsString("CLIENT",
							"IDCLIENT", "MOTDEPASSE");

					// Nous allons vérifier que le mail saisi est bien dans la
					// base de données des identifiants client
					for (int i = 0; i < listeMailsMdps.size(); i++) {

						if (identifiant.getText().equals(
								listeMailsMdps.get(i)[0])) {

							present = present + 1;
							// Nous vérifions en cas d'adresse correcte si le
							// mot de passe correspondant est le bon
							if (password.getText().equals(
									listeMailsMdps.get(i)[1])) {

								// Si l'identification est correcte, nous
								// affichons un message pour le signifier
								// puis nous ouvrons le menu utilisateur

								// Nous récupérons l'état du compte client
								// (Activé ou Désactivé)
								etat = SGBD.selectStringConditionString(
										"CLIENT", "ETATCOMPTE", "IDCLIENT",
										listeMailsMdps.get(i)[0]);

								// Si le compte est désactivé, le client est
								// averti
								if (etat.equals("Désactivé")) {
									throw new ExceptionCompteDesactive(
											"Ce compte est désactivé.");

								}
								// Si le compte est actif, l'identification est
								// réussie
								else {
									identificationReussie = new JOptionPane();

									ImageIcon imageInformation = new ImageIcon(
											"src/images/information.jpg");

									identificationReussie.showMessageDialog(
											null, "Identification réussie !",
											"Information",
											JOptionPane.INFORMATION_MESSAGE,
											imageInformation);

									clientUserIdentifiant = identifiant
											.getText();

									MenuUtilisateur men = new MenuUtilisateur();
								}
							} else {
								// Au contraire, nous indiquons à l'utilisateur
								// qu'il a saisi un mauvais mot de passe
								// et on lui affiche de nouveau la fenetre
								// d'accueil
								throw new ExceptionMotDePasseErrone(
										"Le mot de passe saisi est erroné.");

							}
						}

					}

					if (present == 0) {

						// Dans le cas où le gérant s'identifie,aucun mot de
						// passe et identifiant n'a une correspondance dans
						// la base de données, d'où le test ci dessous
						// pour voir si le gérant s'authentifie correctement
						if (identifiant.getText().equals(identifiantGerant)
								& password.getText().equals(motDePasseGerant)) {
							// Si le gérant s'authentifie correctement, nous
							// ouvrons
							// le menu du Gérant
							FenetreDialogIdentification.clientUserIdentifiant = identifiantGerant;
							MenuGerant menuGerant = new MenuGerant();
						}
						// Sinon, cela veut dire que l'adresse mail saisie
						// n'existe pas
						else {
							throw new ExceptionCompteInexistant(
									"Ce compte n'existe pas !");

						}
					}

					// Fermeture de la fenêtre si l'authentification est
					// correcte
					dispose();

				} catch (ExceptionCompteInexistant e1) {

					// Affichage d'un message d'erreur en cas de problème sur le
					// compte
					erreurCompte = new JOptionPane();
					
					ImageIcon image = new ImageIcon("src/images/warning.png");
					
					erreurCompte.showMessageDialog(null,
							"Ce compte n'existe pas, inscrivez-vous !",
							"Attention", JOptionPane.WARNING_MESSAGE, image);

				} catch (ExceptionMotDePasseErrone e2) {

					// Affichage d'un message d'erreur en cas de problème sur le
					// mot de passe
					erreurMotPasse = new JOptionPane();

					ImageIcon image = new ImageIcon("src/images/warning.png");

					erreurMotPasse.showMessageDialog(null,
							"Mot de passe erroné, veuillez réessayer.",
							"Attention", JOptionPane.WARNING_MESSAGE, image);

				} catch (ExceptionCompteDesactive e3) {

					// Affichage d'un message d'erreur en cas de problème sur le
					// compte
					compteDesactive = new JOptionPane();

					ImageIcon imageInformation = new ImageIcon(
							"src/images/information.jpg");

					compteDesactive
							.showMessageDialog(
									null,
									"Ce compte a été désactivé. Veuillez contacter le gérant à l'adresse suivante : "
											+ identifiantGerant
											+ " pour plus d'informations.",
									"Information",
									JOptionPane.INFORMATION_MESSAGE,
									imageInformation);

				}

			}

		});

		// Création et définition de l'action
		// du bouton Annuler dans ActionPerformed
		// Nous fermons la fenêtre et nous affichons
		// une nouvelle page d'accueil
		JButton annulationBouton = new JButton("Annuler");
		annulationBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				FenetreCompte fen = new FenetreCompte();
				fen.setVisible(true);

			}
		});

		// Ajout de chacun des boutons au panneau des boutons //
		//----------------------------------------------------//
		panneauBoutons.add(validationBouton);
		panneauBoutons.add(annulationBouton);

		// Ajout de tous les panneaux crées dans le conteneur de la fenêtre //
		//------------------------------------------------------------------//
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);

	}
}
