package ihm.Client;

import ihm.modeleTableau.ModelePromotion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import basededonnees.SGBD;

/**
 * La classe FenetrePromotions permet d'afficher dans un tableau l'ensemble des
 * promotions disponibles pour les clients
 * 
 * 
 */
public class FenetrePromotions extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel promotionsLabel;

	/**
	 * Constructeur de la classe FenetrePromotions qui affiche un tableau de
	 * l'ensemble des promotions de la base de données grâce à
	 * {@link ModelePromotion} dans lequel on peut cliquer pour afficher les
	 * détails d'une promotion
	 * 
	 * @see ModelePromotion
	 */
	public FenetrePromotions() {
		super();
		this.setTitle("Promotions en cours");
		this.setSize(500, 900);
		this.setLocation(50, 50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	/**
	 * <p>
	 * Initialisation des composants de la fenetre :
	 * <ul>
	 * <li>le JPanel contenant le texte introductif de la fenetre</li>
	 * <li>le JPanel contenant le tableau des promotions</li>
	 * <li>le JPanel contenant le bouton de retour à la page précédente.</li>
	 * </ul>
	 * </p>
	 * 
	 * 
	 */
	private void initComponent() {

		// Création du JPanel panneauHaut qui se situera en haut de la fenetre
		// créée
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BorderLayout());

		// Création d'un JPanel panneauTitle qui accueillera un texte
		// introductif en haut de
		// la fenêtre
		JPanel panneauTitle = new JPanel();
		promotionsLabel = new JLabel(
				"Decouvrez les promotions en cours dans le tableau ci-dessous");
		panneauTitle.add(promotionsLabel);
		panneauHaut.add(panneauTitle, "Center");

		// Définition du tableau qui affichera l'ensemble des promotions en
		// cours pour le client
		// qui utilise l'application après interrogation de la base de données
		// dans ModelePromotionClient
		ModelePromotion modele = new ModelePromotion(false);
		final JTable tableauPromotions = new JTable(modele);

		// ajout d'un detecteur des actions de la souris pour afficher les
		// détails d'une
		// promotion quand le client clique dessus
		tableauPromotions.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				int ligne = tableauPromotions.getSelectedRow();
				if (ligne != -1) {
					String numPromotion = tableauPromotions
							.getValueAt(ligne, 0).toString();
					String description = SGBD.selectStringConditionString(
							"PROMO", "NOMPROMO", "IDPROMO", numPromotion);
					String pourcentagePromo = SGBD.selectStringConditionString(
							"PROMO", "POURCENTAGEPROMO", "IDPROMO",
							numPromotion);
					String identifiantArticleCorrespondant = SGBD
							.selectStringConditionString(
									"LISTING_PROMOS_ARTICLES", "IDARTICLE",
									"IDPROMO", numPromotion);
					String article = SGBD.selectStringConditionString(
							"ARTICLE", "DESCRIPTION", "IDARTICLE",
							identifiantArticleCorrespondant);
					String dateDebut = SGBD.selectDateConditionString("PROMO",
							"DATEDEBUT", "IDPROMO", numPromotion, "dd/mm/yyyy");
					String dateFin = SGBD.selectDateConditionString("PROMO",
							"DATEFIN", "IDPROMO", numPromotion, "dd/mm/yyyy");

					JOptionPane.showMessageDialog(null,
							"Bénéficiez de la promotion : " + description
									+ "\n" + " sur l'article " + article
									+ " référencé par "
									+ identifiantArticleCorrespondant + "\n"
									+ " avec une remise de " + pourcentagePromo
									+ " %" + "\n" + " du " + dateDebut
									+ " jusqu'au " + dateFin + " inclus");
				}
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		this.getContentPane().add(new JScrollPane(tableauPromotions),
				BorderLayout.CENTER);

		// Définition du JPanel panneauBouton qui accueillera le bouton
		// permettant de retourner à la page précédente
		JPanel panneauBouton = new JPanel();

		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		// Ajout du bouton au JPanel accueillant les boutons
		panneauBouton.add(retourBouton);

		// AJout des JPanel crées au desssus dans le conteneur de la fenêtre
		this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);

		pack();

	}

}
