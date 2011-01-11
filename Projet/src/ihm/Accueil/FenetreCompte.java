package ihm.Accueil;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exception.ExceptionExcesDeCaracteres;
import exception.Client.ExceptionCaractereInterdit;
import exception.Client.ExceptionCodePostalDifferentDeCinqChiffres;
import exception.Client.ExceptionCodePostalIncorrect;
import exception.Client.ExceptionMailDejaExistant;
import exception.Client.ExceptionMailSansArobase;
import exception.Client.ExceptionMailsDifferents;
import exception.Client.ExceptionNumeroDeTelephoneDifferentDeDixChiffres;
import exception.Client.ExceptionNumeroDeTelephoneIncorrect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Initialisation de la fenêtre qui servira de page d'accueil à notre
 * application
 * 
 */
public class FenetreCompte extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Initialisation des composants JButton pour la fenêtre
	 */
	private JButton boutonCreation = new JButton(" Créer un compte");
	private JButton boutonIdentificationClient = new JButton(
			" Identification Client");
	private JButton boutonIdentificationGerant = new JButton(
			"Identification Gerant");
	private JButton boutonDeconnexion = new JButton("Déconnexion");
	/**
	 * Initialisation des autres composants
	 */
	private JPanel pan = new JPanel();
	private JLabel icon, iconEast, iconWest, accueilLabel, heureLabel,
			dateLabel;
	/**
	 * Initialisation de l'instance Date pour récupérer plus tard la date du
	 * système
	 */
	private Date heure;

	/**
	 * Constructeur de la fenetre d'accueil de l'application dans lequel on
	 * retrouve les boutons de création de compte, d'authentification et de
	 * déconnexion
	 * 
	 * @throws ExceptionMailsDifferents
	 */
	public FenetreCompte() {

		this.setTitle("Informations client");
		this.setSize(900, 550);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// Définition de la couleur de fond //
		// ----------------------------------//
		pan.setBackground(Color.white);
		// On prévient notre JFrame que ce sera notre JPanel qui sera son
		// contentPane
		this.setContentPane(pan);

		// Nous allons scinder l'écran en plusieurs parties pour pouvoir insérer
		// des images, des boutons
		// à différents endroits de la fenetre
		this.setLayout(new BorderLayout());

		// Définition du JPanel qui se trouvera en haut de la fenêtre //
		// ------------------------------------------------------------//
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BorderLayout());

		// Création d'un sous JPanel accueillant le logo du magasin //
		// ----------------------------------------------------------//
		icon = new JLabel(new ImageIcon("src/images/logo.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(icon);

		// Création du JPanel avec l'affichage du texte "Bienvenue" //
		// ----------------------------------------------------------//
		JPanel panAccueil = new JPanel();
		panAccueil.setBackground(Color.white);
		accueilLabel = new JLabel("Bienvenue");
		accueilLabel.setFont(new Font("Times", Font.BOLD, 36));
		panAccueil.add(accueilLabel);

		// Création du JPanel contenant l'heure et la date //
		// -------------------------------------------------//
		JPanel panHeure = new JPanel();
		panHeure.setBackground(Color.white);
		panHeure.setLayout(new GridLayout(2, 1, 5, 5));

		// Pour récupérer la date et l'heure, nous avons //
		// besoin d'un objet Date où l'on récupérera ses //
		// informations grâce aux méthodes de cette classe //
		// ------------------------------------------------//
		heure = new Date();

		JPanel panneauDate = new JPanel();
		panneauDate.setBackground(Color.WHITE);
		dateLabel = new JLabel("Jour : " + heure.toGMTString().substring(0, 11));
		dateLabel.setFont(new Font("Times", Font.BOLD, 20));
		panneauDate.add(dateLabel);

		JPanel panneauHeure = new JPanel();
		panneauHeure.setBackground(Color.white);

		String heures = heure.getHours() + "";
		if (heure.getHours() < 10) {
			heures = "0" + heures;
		}

		String minutes = heure.getMinutes() + "";
		if (heure.getMinutes() < 10) {
			minutes = "0" + minutes;
		}

		heureLabel = new JLabel("Heure : " + heures + "h" + minutes);
		heureLabel.setFont(new Font("Times", Font.BOLD, 20));
		panneauHeure.add(heureLabel);

		panHeure.add(panneauDate);
		panHeure.add(panneauHeure);

		// Ajout des 3 JPanel qui viennent d'être crées //
		// dans le JPanel qui sera en haut de la fenêtre//
		// ----------------------------------------------//
		panneauHaut.add("West", panIcon);
		panneauHaut.add("Center", panAccueil);
		panneauHaut.add("East", panHeure);

		// Définition du JPanel qui se trouvera au centre avec les boutons //
		// -----------------------------------------------------------------//
		JPanel panBoutons = new JPanel();
		panBoutons.setLayout(new GridLayout(4, 1, 10, 10));
		panBoutons.add(boutonCreation);
		panBoutons.add(boutonIdentificationClient);
		panBoutons.add(boutonIdentificationGerant);
		panBoutons.add(boutonDeconnexion);

		// Définition du JPanel qui se trouvera à droite //
		// -----------------------------------------------//
		iconEast = new JLabel(new ImageIcon("src/images/trophee.jpg"));
		JPanel panIconEast = new JPanel();
		panIconEast.setBackground(Color.white);
		panIconEast.add(iconEast);

		// Définition du JPanel qui se trouvera à gauche //
		// -----------------------------------------------//
		iconWest = new JLabel(new ImageIcon("src/images/france.jpg"));
		JPanel panIconWest = new JPanel();
		panIconWest.setBackground(Color.white);
		panIconWest.add(iconWest);

		// Ajout des différents JPanel au conteneur de la fenêtre //
		// --------------------------------------------------------//
		this.getContentPane().add("North", panneauHaut);
		this.getContentPane().add("Center", panBoutons);
		this.getContentPane().add("West", panIconWest);
		this.getContentPane().add("East", panIconEast);

		// Définition des actions de chacun des boutons présents sur la fenêtre
		boutonCreation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Affichage de la fenêtre de creation de compte pour le client
				FenetreDialogCreationCompte compte = null;
				try {
					compte = new FenetreDialogCreationCompte(null,
							"Création Compte Client", true);
					compte.setVisible(true);
					dispose();

				} catch (ExceptionMailSansArobase e1) {
					System.out.println(e1.getMessage());
				} catch (ExceptionMailsDifferents e2) {
					System.out.println(e2.getMessage());
				} catch (ExceptionMailDejaExistant e3) {
					System.out.println(e3.getMessage());
				} catch (ExceptionCaractereInterdit e4) {
					System.out.println(e4.getMessage());
				} catch (ExceptionExcesDeCaracteres e5) {
					System.out.println(e5.getMessage());
				} catch (ExceptionCodePostalDifferentDeCinqChiffres e6) {
					System.out.println(e6.getMessage());
				} catch (ExceptionNumeroDeTelephoneDifferentDeDixChiffres e7) {
					System.out.println(e7.getMessage());
				} catch (ExceptionCodePostalIncorrect e8) {
					System.out.println(e8.getMessage());
				} catch (ExceptionNumeroDeTelephoneIncorrect e9) {
					System.out.println(e9.getMessage());
				}

			}
		});

		boutonIdentificationClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Affichage du contenu de la fenêtre d'identifiaction du client
				// dispose();
				FenetreDialogIdentification identificationClient = new FenetreDialogIdentification(
						null, "Identification client", true);
				identificationClient.setVisible(true);
				dispose();
			}
		});

		boutonIdentificationGerant.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Affichage du contenu de la fenêtre d'identification du gérant
				// avec la même fenêtre que le gérant car on considère qu'un
				// gérant est une extension d'un utilisateur
				FenetreDialogIdentification identificationGerant = new FenetreDialogIdentification(
						null, "Identification gérant", true);
				identificationGerant.setVisible(true);
				dispose();
			}
		});

		boutonDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Demande à l'utilisateur de confirmer son choix
				int res = JOptionPane.showConfirmDialog(null,
						"Confirmez-vous la fermeture du logiciel ?",
						"Confirmation", JOptionPane.YES_NO_OPTION);

				// S'il clique sur l'option OK, nous fermons toutes les fenêtres
				// Dans le cas contraire, nous laissons l'utilisateur sur
				// cette fenêtre
				if (res == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		this.setVisible(true);

	}

}
