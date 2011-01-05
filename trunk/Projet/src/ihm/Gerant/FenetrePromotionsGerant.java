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
 * Cette classe permet au g�rant de g�rer l'ensemble des promotions
 * exceptionnelles du magasin
 * 
 * 
 * @see FenetreFormulairePromotionsGerant
 */
public class FenetrePromotionsGerant extends JDialog {

	private static final long serialVersionUID = 1L;
	/**
	 * Entier static permettant de r�cup�rer le num�ro de la ligne selectionn�e
	 * dans le tableau
	 */
	public static int ligneTableau = 0;

	/**
	 * <b> Constructeur de la classe {@link FenetrePromotionsGerant}. </b>
	 * <p>
	 * La fenetre sera cr��e selon les instructions de la m�thode
	 * initComponent().
	 * </p>
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
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
	 * Initialisation des composants de la fen�tre :
	 * <ul>
	 * <li>un JPanel accueillant les boutons qui permettent de g�rer l'ensemble
	 * des promotions (Ajouter, modifier et supprimer une promotion).</li>
	 * <li>un JPanel accueillant un tableau avec la liste des promotions.</li>
	 * <li>un JPanel accueillant le boutons de retour � la page pr�c�dente.</li>
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

		// D�finition du tableau qui affichera l'ensemble des promotions en
		// cours pour //
		// les diff�rents clients (adh�rents ou non adh�rents) apr�s
		// interrogation de //
		// -------------- la base de donn�es dans ModelePromotionClient
		// ---------------//
		// -----------------------------------------------------------------------------//

		final ModelePromotion modele = new ModelePromotion(true);
		final JTable tableauPromotions = new JTable(modele);

		// Cr�ation d'un JPanel accueillant tous les boutons relatifs aux
		// actions //
		// que le g�rant peut faire sur les promotions en cours : soit en
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

		// D�finition de l'action sp�cifique du bouton ajouter pour lequel nous
		// ouvrons //
		// --------------- juste un formulaire de cr�ation de promotion
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

		// D�finition du bouton permettant de supprimer une promotion de la base
		// //
		// Nous v�rifions d'abord qu'une ligne a bien �t� selectionn�e et nous
		// //
		// demandons l'accord du g�rant avant de supprimer la promotion //
		// -----------------------------------------------------------------------//
		boutonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Supprimer la promotion selectionn�e dans la base de donn�e
				ligneTableau = tableauPromotions.getSelectedRow();
				if (ligneTableau == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Aucune ligne s�lectionn�e. Veuillez en s�lectionner une",
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

		// D�finition du bouton permettant de modifier une promotion //
		// -----------------------------------------------------------//
		boutonModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modifier la promotion s�lection�e gr�ce � un formulaire
				ligneTableau = tableauPromotions.getSelectedRow();
				if (ligneTableau == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Aucune ligne s�lectionn�e. Veuillez en s�lectionner une",
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

		// D�finition du panneau panneauBouton qui accueillera le bouton
		// permettant de //
		// ------ retourner � la page pr�c�dente en fermant celle en cours
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

		// Ajout des composants au conteneur de la fen�tre //
		// -------------------------------------------------//
		this.getContentPane().add(panneauTitle, BorderLayout.NORTH);
		this.getContentPane().add(new JScrollPane(tableauPromotions),
				BorderLayout.CENTER);
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);

		pack();

	}

}
