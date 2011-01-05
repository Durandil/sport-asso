package ihm.Gerant;

import ihm.modeleTableau.ModelePromotion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import metier.Promotion;

/**
 * Cette classe permet au gérant de gérer l'ensemble des promotions
 * exceptionnelles du magasin
 * 
 * 
 * @see FenetreFormulairePromotionsGerant
 */
public class FenetrePromotionsGerant extends JDialog {

	private static final long serialVersionUID = 1L;
	/**
	 * Entier static permettant de récupérer le numéro de la ligne selectionnée
	 * dans le tableau
	 */
	public static int ligneTableau = 0;

	/**
	 * <b> Constructeur de la classe {@link FenetrePromotionsGerant}. </b>
	 * <p>
	 * La fenetre sera créée selon les instructions de la méthode
	 * initComponent().
	 * </p>
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * 
	 */
	public FenetrePromotionsGerant(JFrame parent, boolean modal) {
		super(parent, "Promotions en cours", modal);
		this.setSize(500, 600);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	/**
	 * <p>
	 * Initialisation des composants de la fenêtre :
	 * <ul>
	 * <li>un JPanel accueillant les boutons qui permettent de gérer l'ensemble
	 * des promotions (Ajouter, modifier et supprimer une promotion).</li>
	 * <li>un JPanel accueillant un tableau avec la liste des promotions.</li>
	 * <li>un JPanel accueillant le boutons de retour à la page précédente.</li>
	 * </ul>
	 * </p>
	 * 
	 * @throws Exception
	 * 
	 * @see FenetreFormulairePromotionsGerant
	 * @see ModelePromotion
	 * @see ModelePromotion#ModelePromotion(boolean)
	 */
	private void initComponent() {

		// Définition du tableau qui affichera l'ensemble des promotions en
		// cours pour //
		// les différents clients (adhérents ou non adhérents) après
		// interrogation de //
		// -------------- la base de données dans ModelePromotionClient
		// ---------------//
		// -----------------------------------------------------------------------------//

		final ModelePromotion modele = new ModelePromotion(true);
		final JTable tableauPromotions = new JTable(modele);

		// Création d'un JPanel accueillant tous les boutons relatifs aux
		// actions //
		// que le gérant peut faire sur les promotions en cours : soit en
		// ajouter //
		// --------------- soit en modifier soit en supprimer
		// ------------------- //
		JPanel panneauTitle = new JPanel();

		JButton boutonAjouter = new JButton("Ajouter");
		JButton boutonSupprimer = new JButton("Supprimer");
		JButton boutonModifier = new JButton("Modifier");

		if (modele.getRowCount() == 0) {
			boutonSupprimer.setEnabled(false);
			boutonModifier.setEnabled(false);
		}

		// Définition de l'action spécifique du bouton ajouter pour lequel nous
		// ouvrons //
		// --------------- juste un formulaire de création de promotion
		// -----------------//

		boutonAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ajout d'une promotion
				FenetreFormulairePromotionsGerant nouvellePromo = new FenetreFormulairePromotionsGerant(
						null, "Ajout d'une nouvelle promotion", true);
				nouvellePromo.setVisible(true);
				dispose();
			}
		});

		// Définition du bouton permettant de supprimer une promotion de la base
		// //
		// Nous vérifions d'abord qu'une ligne a bien été selectionnée et nous
		// //
		// demandons l'accord du gérant avant de supprimer la promotion //
		// -----------------------------------------------------------------------//
		boutonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Supprimer la promotion selectionnée dans la base de donnée
				ligneTableau = tableauPromotions.getSelectedRow();
				if (ligneTableau == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Aucune ligne sélectionnée. Veuillez en sélectionner une",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} else {
					String idPromo = tableauPromotions.getValueAt(ligneTableau,
							0).toString();
					String descriptionPromo = tableauPromotions.getValueAt(
							ligneTableau, 1).toString();

					int res = JOptionPane.showConfirmDialog(null,
							"Confimez-vous la suppression de la promotion "
									+ idPromo + " : " + descriptionPromo,
							"Confirmation", JOptionPane.YES_NO_OPTION);

					if (res == JOptionPane.YES_OPTION) {
						Promotion.supprimerListing_PromoBDD(idPromo);
						Promotion.supprimerPromoBDD(idPromo);
					}
				}
			}
		});

		// Définition du bouton permettant de modifier une promotion //
		// -----------------------------------------------------------//
		boutonModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modifier la promotion sélectionée grâce à un formulaire
				ligneTableau = tableauPromotions.getSelectedRow();
				if (ligneTableau == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Aucune ligne sélectionnée. Veuillez en sélectionner une",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} else {
					String idPromo = tableauPromotions.getValueAt(ligneTableau,
							0).toString();

					FenetreFormulairePromotionsGerant modifierPromo;
					try {
						modifierPromo = new FenetreFormulairePromotionsGerant(
								null, "Modification d'une nouvelle promotion",
								true, idPromo);
						modifierPromo.setVisible(true);
						dispose();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}

				}
			}
		});

		// Ajout de ses boutons de gestion des promotions au JPanel panneauTitle
		// //
		// -----------------------------------------------------------------------//
		panneauTitle.add(boutonAjouter);
		panneauTitle.add(boutonSupprimer);
		panneauTitle.add(boutonModifier);

		// Définition du panneau panneauBouton qui accueillera le bouton
		// permettant de //
		// ------ retourner à la page précédente en fermant celle en cours
		// ------------//
		// -----------------------------------------------------------------------------//
		JPanel panneauBouton = new JPanel();

		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panneauBouton.add(retourBouton);

		// Ajout des composants au conteneur de la fenêtre //
		// -------------------------------------------------//
		this.getContentPane().add(panneauTitle, BorderLayout.NORTH);
		this.getContentPane().add(new JScrollPane(tableauPromotions),
				BorderLayout.CENTER);
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);

		pack();

	}

}
