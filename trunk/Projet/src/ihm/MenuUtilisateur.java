package ihm;

import ihm.Accueil.FenetreCompte;
import ihm.Accueil.FenetreDialogIdentification;
import ihm.Client.FenetreCommandeArticle;
import ihm.Client.FenetreContactVendeur;
import ihm.Client.FenetreDialogGestionCompteClient;
import ihm.Client.FenetreFideliteClient;
import ihm.Client.FenetreInformationsClient;
import ihm.Client.FenetrePromotions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import basededonnees.SGBD;

import exception.Client.ExceptionMailsDifferents;

/**
 * Cette classe MenuUtilisateur permet d'initialiser la page d'accueil des
 * clients dans laquelle ils ont accès aux fonctionnalités définies dans le cas
 * d'utilisation des clients. Cette fenêtre apparaîtra avec une barre de menus
 * dans lequel le client pourra accéder aux différents sous-menus.
 * 
 * {@link MenuUtilisateur#MenuUtilisateur()}
 */
public class MenuUtilisateur extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Creation d'une instance de barre de menus dans laquelle nous allons
	 * insérer les quatre JMenu
	 */
	private JMenuBar menuBar = new JMenuBar();
	/**
	 * Initialisation des instances des menus dans lesquels nous allons insérer
	 * les sous-menus JMenuItem correspondant à chaque menu JMenu
	 */
	private JMenu menuCompte = new JMenu("Mon Compte");
	private JMenu menuCatalogue = new JMenu("Catalogue");
	private JMenu menuPromotions = new JMenu("Promotions");
	private JMenu menuContact = new JMenu("Contact");

	/**
	 * Initialisation des instances des sous-menus qui permettront à terme
	 * l'accès aux différentes fonctionnalités du menu utilisateur
	 */
	private JMenuItem itemFermer = new JMenuItem("Fermer compte utilisateur");
	private JMenuItem itemMesInformations = new JMenuItem("Mes informations");
	private JMenuItem itemProgFidelite = new JMenuItem("Mon programme fidélité");
	private JMenuItem itemMessagerieClient = new JMenuItem("Messagerie interne");
	private JMenuItem itemContact = new JMenuItem("Nous Contacter");
	private JMenuItem itemInformations = new JMenuItem("Informations");
	private JMenuItem itemArticles = new JMenuItem("Articles");
	private JMenuItem itemPromotions = new JMenuItem("Promotions en cours");

	private JLabel icon;

	/**
	 * <p>
	 * Constructeur de la fenêtre MenuUtilisateur où nous allons :
	 * <ul>
	 * <li>ajouter une image de fond pour la fenêtre.</li>
	 * <li>initialiser tous nos sous-menus avec leurs actions correspondantes.</li>
	 * <li>ajouter les sous-menus à leur menu respectif.</li>
	 * <li>ajouter les menus à la barre de menus.</li>
	 * </ul>
	 * </p>
	 */
	public MenuUtilisateur() {
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// Récupération des dimensions de l'écran et centrage de la fenêtre //
		//------------------------------------------------------------------//
		Dimension dimensionEcran = this.getToolkit().getScreenSize();
		this.setLocation((dimensionEcran.width - this.getSize().width) / 2,
				(dimensionEcran.height - this.getSize().height) / 2);

		// Récupération du nom et prénom pour les particuliers et //
		// la dénomination pour les collectivités et associations //
		//--------------------------------------------------------//
		String nom = SGBD.selectStringConditionString("CLIENT", "NOMCLIENT",
				"IDCLIENT", FenetreDialogIdentification.clientUserIdentifiant);
		String prenom = SGBD.selectStringConditionString("CLIENT",
				"PRENOMCLIENT", "IDCLIENT",
				FenetreDialogIdentification.clientUserIdentifiant);
		String denomination = SGBD.selectStringConditionString("CLIENT",
				"DENOMINATIONCLIENT", "IDCLIENT",
				FenetreDialogIdentification.clientUserIdentifiant);

		this.setTitle("Bienvenue : " + nom + " " + prenom + denomination);
		this.setLayout(new BorderLayout());

		// Ajout d'une image de fond pour la fenêtre //
		// ------------------------------------------//
		icon = new JLabel(new ImageIcon("src/images/nba.jpg"));
		JPanel panImage = new JPanel();
		panImage.setBackground(Color.white);
		panImage.setLayout(new BorderLayout());
		panImage.add(icon);

		// Ajout de la fenêtre au conteneur de la fenêtre //
		// -----------------------------------------------//
		this.getContentPane().add(panImage, "Center");
		this.repaint();
		this.pack();

		// On initialise nos sous-menus (JMenuItem) avec leurs actions correspondantes// 
		// ---------------------------------------------------------------------------//

		itemMesInformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ouvrir la fenetre permettant accès au compte
				FenetreDialogGestionCompteClient fenetreGestionCompte = new FenetreDialogGestionCompteClient(
						null, "Informations client", true,
						FenetreDialogIdentification.clientUserIdentifiant);
				fenetreGestionCompte.setVisible(true);
			}
		});

		itemProgFidelite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ouverture de la fenetre permettant l'accès aux informations
				// sur le
				// programme de fidelite
				FenetreFideliteClient fenetreProgrammeFidelite = new FenetreFideliteClient(
						null, "programme fidelité", true);
				fenetreProgrammeFidelite.setVisible(true);
			}
		});

		itemMessagerieClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ouverture de la fenetre permettant l'accès à la messagerie du
				// client
				FenetreMessagerie fenetre = new FenetreMessagerie(false);
				fenetre.setVisible(true);
			}
		});

		itemFermer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Nous demandons au client s'il souhait fermer sa session
				int res = JOptionPane.showConfirmDialog(null,
						"Confirmez-vous la déconnexion de votre compte ?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				
				// S'il répond OK, nous allons fermer la fenêtre puis afficher
				// de nouveau la fenêtre d'accueil
				if (res == JOptionPane.OK_OPTION) {
					
					dispose();
					FenetreCompte fenAccueil = new FenetreCompte();
					fenAccueil.setVisible(true);

				}
			}
		});

		itemArticles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ouverture de la fenêtre permettant l'achat d'articles
				FenetreCommandeArticle catalogue = new FenetreCommandeArticle();
				catalogue.setVisible(true);
			}
		});

		itemPromotions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ouverture de la fenêtre de consultations des promotions
				FenetrePromotions promotion = new FenetrePromotions();
				promotion.setVisible(true);
			}
		});

		itemInformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				// Ouverture de la fenêtre permettant l'accès aux informations
				// concernant le magasin
				FenetreInformationsClient fenInformations = new FenetreInformationsClient();
			}
		});
		
		itemContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ouverture de la fenêtre permettant d'envoyer un message au
				// gérant
				FenetreContactVendeur contactVendeur = new FenetreContactVendeur(
						null, "Nous Contacter", true);
				contactVendeur.setVisible(true);
			}
		});
		
		// Ajout des sous-menus à leur menu respectif dans l'ordre descendant //
		// ----------------------- choisi à l'affichage ----------------------//
		// -------------------------------------------------------------------//
		this.menuCompte.add(itemMesInformations);
		this.menuCompte.add(itemProgFidelite);
		this.menuCompte.add(itemMessagerieClient);
		this.menuCompte.add(itemFermer);

		this.menuCatalogue.add(itemArticles);

		this.menuPromotions.add(itemPromotions);

		this.menuContact.add(itemInformations);
		this.menuContact.add(itemContact);

		// L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de   //
		// gauche à droite. Le premier ajouté sera tout à gauche de la barre de //
		// -------------- menu et inversement pour le dernier ------------------//
		// ---------------------------------------------------------------------//
		this.menuBar.add(menuCompte);
		this.menuBar.add(menuCatalogue);
		this.menuBar.add(menuPromotions);
		this.menuBar.add(menuContact);

		// Ajout de la barre de menu à notre objet MenuUtilisateur//
		// -------------------------------------------------------//
		this.setJMenuBar(menuBar);
		this.setVisible(true);
	}

}
