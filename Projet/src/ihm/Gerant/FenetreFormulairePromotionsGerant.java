package ihm.Gerant;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exception.ExceptionChampVide;
import exception.ExceptionExcesDeCaracteres;
import exception.Promotion.ExceptionDateAvantAujourdhui;
import exception.Promotion.ExceptionDatesIdentiques;
import exception.Promotion.ExceptionMoisDeFevrier;
import exception.Promotion.ExceptionMoisDeFevrierAnneeBissextile;
import exception.Promotion.ExceptionMoisDeTrenteJours;
import exception.Promotion.ExceptionOrdreDeDeuxDates;
import exception.Promotion.ExceptionPourcentageAberrant;
import basededonnees.SGBD;

import metier.Promotion;

/**
 * <b> Cette classe devra permettre d'ouvrir le formulaire d'ajout ou de
 * modification d'une promotion. </b>
 * <p>
 * La fenêtre créée est utilisée dans {@link FenetrePromotionsGerant} dans les
 * boutons d'action d'ajout et de modification d'une promotion selectionnée dans
 * le tableau des promotions.
 * </p>
 * 
 * @see FenetrePromotionsGerant
 */

public class FenetreFormulairePromotionsGerant extends JDialog {

	private static final long serialVersionUID = 1L;
	//
	public Dimension dimensionStandard = new Dimension(220, 60);
	private JLabel descriptionLabel, populationLabel, articleLabel,
			pourcentLabel;
	private JComboBox articleBox, populationBox;
	private JTextField description, pourcentPromo;
	private JComboBox cbmoisDebut, cbjourDebut, cbanneeDebut;
	private JComboBox cbmoisFin, cbjourFin, cbanneeFin;

	private static String jourDebutSelectionne = "11";
	private static String moisDebutSelectionne = "07";
	private static String anneeDebutSelectionne = "2005";
	private static String jourFinSelectionne = "11";
	private static String moisFinSelectionne = "07";
	private static String anneeFinSelectionne = "2005";
	private static String populationPromo = "Promotion pour tous les clients";
	public static String articleSelectionne;

	private static String jourDebutSelectionneModification;
	private static String moisDebutSelectionneModification;
	private static String anneeDebutSelectionneModification;
	private static String jourFinSelectionneModification;
	private static String moisFinSelectionneModification;
	private static String anneeFinSelectionneModification;
	private static String populationPromoModification;
	public static String articleSelectionneModification;

	/**
	 * <b> Constructeur pour ajouter une promotion dans la base de données. </b>
	 * <p>
	 * Elle sera instanciée avec des JTextField vides prêts à être rempli par le
	 * gérant.
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
	 *            {@link FenetreFormulairePromotionsGerant#initComponent()}
	 */
	public FenetreFormulairePromotionsGerant(JFrame parent, String title,
			boolean modal) {
		super(parent, title, modal);
		this.setSize(450, 650);
		this.setLocation(50, 50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	//
	/**
	 * <b> Constructeur pour modifier les paramètres d'une promotion en cours.
	 * </b>
	 * 
	 * <p>
	 * La fenêtre qui affiche plusieurs JTextField et JComboBox initialisés avec
	 * les attributs actuels de la promotion et qui sont prêts à être modifiés.
	 * </p>
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param title
	 *            String indiquant le titre de la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * @param promotion
	 *            Identifant unique de la promotion à modifier
	 */
	public FenetreFormulairePromotionsGerant(JFrame parent, String title,
			boolean modal, String promotion) throws Exception {
		super(parent, title, modal);
		this.setSize(500, 650);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(promotion);
	}

	/**
	 * <p>
	 * Initialisation des composants de la fenêtre d'ajout d'une promotion :
	 * <ul>
	 * <li>un JPanel qui accueillir les 6 sous-JPanels contenant les JTextField
	 * et JComboBox vides permettant la création d'une nouvelle promotion.</li>
	 * <li>un JPanel qui va accueillir les boutons permettant de valider les
	 * champs saisis et d'annuler les saisies des champs (fermeture de la
	 * fenêtre).</li>
	 * </ul>
	 * </p>
	 * 
	 */
	private void initComponent() {
		// Définition du grand JPanel qui va accueillir les sous JPanel contenant les //
		// JTextField et les JComboBox pour sélectionner les dates de début et de fin //
		// ---------------------------------------------------------------------------//
		JPanel panneauCentralFenetre = new JPanel();
		panneauCentralFenetre.setLayout(new GridLayout(6, 1, 5, 5));

		// Définition de chacun des sous JPanel //
		// -------------------------------------//
		JPanel panDescriptionPromotion = new JPanel();
		JPanel panPopulation = new JPanel();
		JPanel panDateDebut = new JPanel();
		JPanel panDateFin = new JPanel();
		JPanel panArticle = new JPanel();
		JPanel panPourcentPromo = new JPanel();

		panDateDebut.setPreferredSize(dimensionStandard);
		panDescriptionPromotion.setPreferredSize(dimensionStandard);
		panPopulation.setPreferredSize(dimensionStandard);
		panDateFin.setPreferredSize(dimensionStandard);
		panArticle.setPreferredSize(dimensionStandard);
		panPourcentPromo.setPreferredSize(dimensionStandard);

		panDateDebut.setBorder(BorderFactory
				.createTitledBorder("Date début Promotion"));
		panDescriptionPromotion.setBorder(BorderFactory.createEmptyBorder());
		panPopulation.setBorder(BorderFactory.createEmptyBorder());
		panDateFin.setBorder(BorderFactory
				.createTitledBorder("Date Fin Promotion"));
		panArticle.setBorder(BorderFactory.createEmptyBorder());
		panPourcentPromo.setBorder(BorderFactory.createEmptyBorder());

		descriptionLabel = new JLabel("Description de la promotion : ");
		populationLabel = new JLabel(" Promotion adhérent ? ");
		articleLabel = new JLabel("Article concerné : ");
		pourcentLabel = new JLabel("Pourcentage de promotion :");

		pourcentPromo = new JFormattedTextField(
				NumberFormat.getNumberInstance());
		description = new JTextField();

		pourcentPromo.setPreferredSize(new Dimension(90, 20));
		description.setPreferredSize(new Dimension(90, 20));

		// Définition du JComboBox dans lequel le gérant spécifie à quel type de client //
		// --------------------- est destiné la promotion ------------------------------//
		//------------------------------------------------------------------------------//
		populationBox = new JComboBox();
		populationBox.addItem("Promotion pour les adhérents");
		populationBox.addItem("Promotion pour tous les clients");
		populationBox.setSelectedItem("Promotion pour tous les clients");

		populationBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				populationPromo = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});

		// Définition du JComboBox dans lequel le gérant spécifie l'article sur lequel //
		// --------------------- est destiné la promotion -----------------------------//
		//-----------------------------------------------------------------------------//
		articleBox = new JComboBox();
		// Récupération de la liste tous les articles
		ArrayList<String> listeArticles = new ArrayList<String>();
		listeArticles = SGBD.selectListeStringOrdonneCondition("ARTICLE",
				"IDARTICLE", "IDARTICLE", "ETATARTICLE != 'Supprimé'");

		for (String article : listeArticles) {
			articleBox.addItem(article);
		}
		articleBox.setSelectedIndex(0);

		articleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				articleSelectionne = (String) ((JComboBox) e.getSource())
						.getSelectedItem();

				String descriptionArticleSelectionne = SGBD
						.selectStringConditionString("ARTICLE", "DESCRIPTION",
								"IDARTICLE", articleSelectionne);
				JOptionPane.showMessageDialog(null,
						"vous avez sélectionné l'article : "
								+ descriptionArticleSelectionne, "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Définition des JComboBox pour permettre de sélectionner le jour,le mois //
		// ----------- et l'année de début et de fin d'une promotion --------------//
		//-------------------------------------------------------------------------//
		cbjourDebut = new JComboBox();
		cbjourFin = new JComboBox();

		for (int i = 1; i < 32; i++) {
			String chaine = "";
			if (i < 10) {
				chaine = "0";
			}
			cbjourDebut.addItem(chaine + i);
			cbjourFin.addItem(chaine + i);
		}

		cbmoisDebut = new JComboBox();
		cbmoisFin = new JComboBox();

		for (int j = 1; j < 13; j++) {
			String chaine = "";
			if (j < 10) {
				chaine = "0";
			}
			cbmoisDebut.addItem(chaine + j);
			cbmoisFin.addItem(chaine + j);
		}

		cbanneeDebut = new JComboBox();
		cbanneeFin = new JComboBox();

		for (int k = 2010; k < 2040; k++) {
			cbanneeDebut.addItem(k + "");
			cbanneeFin.addItem(k + "");
		}

		cbanneeDebut.setVisible(true);
		cbanneeFin.setVisible(true);
		cbmoisFin.setVisible(true);
		cbmoisDebut.setVisible(true);
		cbjourFin.setVisible(true);
		cbjourDebut.setVisible(true);

		cbanneeDebut.setPreferredSize(new Dimension(5, 7));
		cbanneeFin.setPreferredSize(new Dimension(5, 7));
		cbmoisFin.setPreferredSize(new Dimension(5, 5));
		cbmoisDebut.setPreferredSize(new Dimension(5, 5));
		cbjourFin.setPreferredSize(new Dimension(5, 5));
		cbjourDebut.setPreferredSize(new Dimension(5, 5));

		cbjourDebut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jourDebutSelectionne = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});

		cbjourFin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jourFinSelectionne = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});

		cbmoisDebut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				moisDebutSelectionne = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});

		cbmoisFin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				moisFinSelectionne = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});

		cbanneeDebut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				anneeDebutSelectionne = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});

		cbanneeFin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				anneeFinSelectionne = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});

		panDateDebut.setLayout(new GridLayout(1, 4, 5, 5));
		panDateFin.setLayout(new GridLayout(1, 4, 5, 5));

		// Ajout des titres à chacun des JPanel contenant les champs à remplir //
		// --------------------------------------------------------------------//
		panDescriptionPromotion.add(descriptionLabel);
		panPopulation.add(populationLabel);
		panArticle.add(articleLabel);
		panPourcentPromo.add(pourcentLabel);

		// Ajout des JTextField et JComboBox à chacun des JPanel contenant les champs à remplir //
		// -------------------------------------------------------------------------------------//
		panDescriptionPromotion.add(description);
		panPopulation.add(populationBox);
		panArticle.add(articleBox);
		panPourcentPromo.add(pourcentPromo);

		// Ajout des JComboBox à chacun des 2 JPanel contenant les dates à sélectionner //
		// -----------------------------------------------------------------------------//
		panDateDebut.add(cbjourDebut);
		panDateDebut.add(cbmoisDebut);
		panDateDebut.add(cbanneeDebut);

		panDateFin.add(cbjourFin);
		panDateFin.add(cbmoisFin);
		panDateFin.add(cbanneeFin);

		// Ajout des sous JPanel au "grand" JPanel //
		// ----------------------------------------//
		panneauCentralFenetre.add(panDescriptionPromotion);
		panneauCentralFenetre.add(panPopulation);
		panneauCentralFenetre.add(panPourcentPromo);
		panneauCentralFenetre.add(panArticle);
		panneauCentralFenetre.add(panDateDebut);
		panneauCentralFenetre.add(panDateFin);

		// Définition du JPanel qui va accueillir les boutons de confirmation et d'annulation //
		// -----------------------------------------------------------------------------------//
		JPanel panneauBasFenetre = new JPanel();

		// Définition de l'action du bouton confirmer qui va vérifier que les champs saisis //
		// -------------------- et selectionnés soient corrects ----------------------------//
		//----------------------------------------------------------------------------------//
		JButton boutonConfirmation = new JButton("Confirmer");

		boutonConfirmation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String position = null;
				String StringDateDebutPromotion = jourDebutSelectionne
						+ moisDebutSelectionne + anneeDebutSelectionne;

				int jourDateDebutPromotion = Integer
						.parseInt(jourDebutSelectionne);
				int moisDateDebutPromotion = Integer
						.parseInt(moisDebutSelectionne);
				int anneeDateDebutPromotion = Integer
						.parseInt(anneeDebutSelectionne);

				String StringDateFinPromotion = jourFinSelectionne
						+ moisFinSelectionne + anneeFinSelectionne;

				int jourDateFinPromotion = Integer.parseInt(jourFinSelectionne);
				int moisDateFinPromotion = Integer.parseInt(moisFinSelectionne);
				int anneeDateFinPromotion = Integer
						.parseInt(anneeFinSelectionne);

				Date dateJour = new Date(System.currentTimeMillis());


				try {

					Date dateDebutPromotion = SGBD.stringToDate(
							StringDateDebutPromotion, "ddMMyyyy");
					Date dateFinPromotion = SGBD.stringToDate(
							StringDateFinPromotion, "ddMMyyyy");
					
					// Vérifie si la date de début de la promotion est
					// postérieure à la date d'aujourd'hui
					if (dateDebutPromotion.before(dateJour)) {
						position = "début";
						throw new ExceptionDateAvantAujourdhui(
								"La date de début de la promotion est déjà passée.");
					}

					// Vérifie si la date de fin de la promotion est postérieure
					// à la date d'aujourd'hui
					if (dateFinPromotion.before(dateJour)) {
						position = "fin";
						throw new ExceptionDateAvantAujourdhui(
								"La date de fin de la promotion est déjà passée.");
					}

					// Vérifie si la date de début de la promotion est cohérente
					// (mois de 30 jours)
					if ((moisDateDebutPromotion == 4
							| moisDateDebutPromotion == 6
							| moisDateDebutPromotion == 9 | moisDateDebutPromotion == 11)
							& jourDateDebutPromotion == 31) {
						position = "début";
						throw new ExceptionMoisDeTrenteJours(
								"Le 31ème jour de ce mois a été saisi alors que ce dernier n'en possède que 30");
					}

					// Vérifie si la date de fin de la promotion est cohérente
					// (mois de 30 jours)
					if ((moisDateFinPromotion == 4 | moisDateFinPromotion == 6
							| moisDateFinPromotion == 9 | moisDateFinPromotion == 11)
							& jourDateFinPromotion == 31) {
						position = "fin";
						throw new ExceptionMoisDeTrenteJours(
								"Le 31ème jour de ce mois a été saisi alors que ce dernier n'en possède que 30");
					}

					// Vérifie si la date de début de la promotion est cohérente
					// (mois de février)
					if (moisDateDebutPromotion == 2
							& jourDateDebutPromotion > 28) {
						position = "début";
						if (anneeDateDebutPromotion % 4 == 0
								&& jourDateDebutPromotion > 29) {
							throw new ExceptionMoisDeFevrierAnneeBissextile(
									"Le "
											+ jourDateDebutPromotion
											+ "ème jour du mois de Février a été"
											+ " saisi alors que ce dernier n'en possède que 29");
						} else {
							if (anneeDateDebutPromotion % 4 != 0) {
								throw new ExceptionMoisDeFevrier(
										"Le "
												+ jourDateDebutPromotion
												+ "ème jour du mois de Février a été"
												+ " saisi alors que ce dernier n'en possède que 28");
							}

						}
					}

					// Vérifie si la date de fin de la promotion est cohérente
					// (mois de février)
					if (moisDateFinPromotion == 2 & jourDateFinPromotion > 28) {
						position = "fin";
						if (anneeDateFinPromotion % 4 == 0
								&& jourDateFinPromotion > 29) {
							throw new ExceptionMoisDeFevrierAnneeBissextile(
									"Le "
											+ jourDateFinPromotion
											+ "ème jour du mois de Février a été"
											+ " saisi alors que ce dernier n'en possède que 29");
						} else {
							if (anneeDateFinPromotion % 4 != 0) {
								throw new ExceptionMoisDeFevrier(
										"Le "
												+ jourDateFinPromotion
												+ "ème jour du mois de Février a été"
												+ " saisi alors que ce dernier n'en possède que 28");
							}

						}
					}

					// Vérifie si la date de début de promotion précède celle de
					// fin
					if (dateDebutPromotion.after(dateFinPromotion)) {
						throw new ExceptionOrdreDeDeuxDates(
								"La date de début de promotion est postérieure à celle de fin");
					}

					// Vérifie que les dates de début et de fin sont différentes
					if (dateDebutPromotion.equals(dateFinPromotion)) {
						throw new ExceptionDatesIdentiques(
								"La date de début de promotion est identique à celle de fin");
					}

					// Vérifie que tous les champs sont remplis
					if (description.getText().length() == 0
							| pourcentPromo.getText().length() == 0) {
						throw new ExceptionChampVide(
								"Au moins l'un des champs est vide");
					}

					// Vérifie la taille de la description
					if (description.getText().length() > 40) {
						throw new ExceptionExcesDeCaracteres(
								"La description de la promotion est trop longue");
					}
					int pourcentage = Integer.parseInt(pourcentPromo.getText());
					// Vérifie le pourcentage saisi par le gérant
					if (pourcentage < 0 | pourcentage > 100) {
						throw new ExceptionPourcentageAberrant(
								"Le pourcentage de la promotion est aberrant");
					}

					// Si aucune Exception n'est levée, la création de la promotion
					// exceptionelle s'opère
					boolean promoAdherent = true;

					if (populationPromo
							.equals("Promotion pour tous les clients")) {
						promoAdherent = false;
					}

					Promotion promo = new Promotion(description.getText(),
							dateDebutPromotion, dateFinPromotion, Double
									.parseDouble(pourcentPromo.getText()),
							promoAdherent);

					String requete = "INSERT INTO LISTING_PROMOS_ARTICLES(IDPROMO,IDARTICLE) values('"
							+ promo.getIdPromotion()
							+ "', '"
							+ articleSelectionne + "')";

					SGBD.executeUpdate(requete);
					
					// Fermeture de la fenêtre et retour vers la fenêtre d'accueil 
					// de gestion des promotions exceptionnelles
					dispose();
					FenetrePromotionsGerant fen = new FenetrePromotionsGerant(
							null, true);
					fen.setVisible(true);

				} catch (ExceptionDateAvantAujourdhui e1) {

					JOptionPane
							.showMessageDialog(
									null,
									"La date de "
											+ position
											+ " de la promotion est déjà passée, modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionMoisDeTrenteJours e2) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi le 31ème jour d'un mois qui n'en possède que 30, modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionMoisDeFevrier e3) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi un jour inexistant pour le mois de février (28 jours), modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionMoisDeFevrierAnneeBissextile e4) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi un jour inexistant pour le mois de février (29 jours), modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionOrdreDeDeuxDates e5) {

					JOptionPane
							.showMessageDialog(
									null,
									"La date de fin de promotion est plus récente que celle de début de la promotion, vérifiez vos dates",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionDatesIdentiques e6) {

					JOptionPane
							.showMessageDialog(
									null,
									"Les dates de début et de fin de promotion sont identiques, vérifiez les",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionChampVide e7) {

					JOptionPane
							.showMessageDialog(
									null,
									"Au moins l'un des champs est vide, veuillez renseigner tous les champs",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionExcesDeCaracteres e8) {

					JOptionPane
							.showMessageDialog(
									null,
									"La description de la promotion est trop longue (40 caractères maximum), veuillez la raccourcir",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionPourcentageAberrant e9) {

					JOptionPane
							.showMessageDialog(
									null,
									"Le pourcentage appliqué au prix initial doit être compris entre 0 et 100, modifiez ce champ",
									"Attention", JOptionPane.ERROR_MESSAGE);
				}

				catch (Exception e10) {

					e10.printStackTrace();
				}
			}
		});

		// Définition de l'action du bouton retour à la page précédente qui annule //
		// --------- tout ce qui a pu être fait sur la page et la ferme -----------//
		//-------------------------------------------------------------------------//

		JButton boutonRetour = new JButton("Retour à la page précédente");

		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Permet le retour à la page précédente
				dispose();
				FenetrePromotionsGerant fen = new FenetrePromotionsGerant(null,
						true);
				fen.setVisible(true);
			}
		});

		// Ajout des boutons au JPanel panneauBasFenetre destiné à accueillir les boutons //
		// -------------------------------------------------------------------------------//
		panneauBasFenetre.add(boutonConfirmation);
		panneauBasFenetre.add(boutonRetour);

		// Ajout des 2 JPanels principaux au conteneur de la fenêtre //
		// ----------------------------------------------------------//
		this.getContentPane().add(panneauCentralFenetre, "Center");
		this.getContentPane().add(panneauBasFenetre, "South");
	}

	/**
	 * <b> Surchage de la méthode initComponent pour la modification d'une
	 * promotion </b>
	 * 
	 * <p>
	 * Initialisation des composants de la fenêtre de modification d'une promotion :
	 * <ul>
	 * <li>un JPanel qui accueillir les 6 sous-JPanels contenant les JTextField
	 * et JComboBox remplis avec les attributs de la promotion sélectionnée.</li>
	 * <li>un JPanel qui va accueillir les boutons permettant de valider les
	 * champs saisis et d'annuler les saisies des champs(fermeture de la
	 * fenêtre).</li>
	 * </ul>
	 * </p>
	 * 
	 * @param idPromo
	 *            Identifiant unique de la promotion prête à être modifiée
	 */
	private void initComponent(String idPromo) throws Exception {
		final String identifiantPromotion = idPromo;

		// Récupération dans la base de données des attributs de la promotion afin de   //
		// pré-remplir les JTextField avec la valeur enregistrée dans la base de données//
		// ----------- et de compléter la valeur par défaut des JComboBox ------------- //
		// -----------------------------------------------------------------------------//
		String nomPromotion = SGBD.selectStringConditionString("PROMO",
				"NOMPROMO", "IDPROMO", idPromo);
		String promotionPopulation = SGBD.selectStringConditionString("PROMO",
				"PROMOFIDELITE", "IDPROMO", idPromo);

		populationPromoModification = "Promotion pour tous les clients";
		if (promotionPopulation.equals("1")) {
			populationPromoModification = "Promotion pour les adhérents";
		}

		String pourcentagePromotion = SGBD.selectStringConditionString("PROMO",
				"POURCENTAGEPROMO", "IDPROMO", idPromo);
		articleSelectionneModification = SGBD.selectStringConditionString(
				"LISTING_PROMOS_ARTICLES", "IDARTICLE", "IDPROMO", idPromo);
		String dateDe = SGBD.selectDateConditionString("PROMO", "DATEDEBUT",
				"IDPROMO", idPromo, "dd/MM/yyyy");
		String dateFi = SGBD.selectDateConditionString("PROMO", "DATEFIN",
				"IDPROMO", idPromo, "dd/MM/yyyy");

		// Définition du grand JPanel qui va accueillir les sous JPanel contenant les //
		// JTextField et les JComboBox pour sélectionner les dates de début et de fin //
		// ---------------------------------------------------------------------------//
		JPanel panneauCentralFenetre = new JPanel();
		panneauCentralFenetre.setLayout(new GridLayout(6, 1, 5, 5));

		// Définition de chacun des sous JPanel //
		// --------------------------------------//
		JPanel panDescriptionPromotion = new JPanel();
		JPanel panPopulation = new JPanel();
		JPanel panDateDebut = new JPanel();
		JPanel panDateFin = new JPanel();
		JPanel panArticle = new JPanel();
		JPanel panPourcentPromo = new JPanel();

		panDateDebut.setPreferredSize(dimensionStandard);
		panDescriptionPromotion.setPreferredSize(dimensionStandard);
		panPopulation.setPreferredSize(dimensionStandard);
		panDateFin.setPreferredSize(dimensionStandard);
		panArticle.setPreferredSize(dimensionStandard);
		panPourcentPromo.setPreferredSize(dimensionStandard);

		panDateDebut.setBorder(BorderFactory
				.createTitledBorder("Date début Promotion"));
		panDescriptionPromotion.setBorder(BorderFactory.createEmptyBorder());
		panPopulation.setBorder(BorderFactory.createEmptyBorder());
		panDateFin.setBorder(BorderFactory
				.createTitledBorder("Date Fin Promotion"));
		panArticle.setBorder(BorderFactory.createEmptyBorder());
		panPourcentPromo.setBorder(BorderFactory.createEmptyBorder());

		descriptionLabel = new JLabel("Description de la promotion : ");
		populationLabel = new JLabel(" Promotion adhérent ? ");
		articleLabel = new JLabel("Article concerné : ");
		pourcentLabel = new JLabel("Pourcentage de promotion :");

		pourcentPromo = new JFormattedTextField(
				NumberFormat.getNumberInstance());
		pourcentPromo.setText(pourcentagePromotion);

		description = new JTextField(nomPromotion);

		pourcentPromo.setPreferredSize(new Dimension(90, 20));
		description.setPreferredSize(new Dimension(90, 20));

		// Définition du JComboBox dans lequel le gérant peut modifier à quel type de client //
		// --------------------- est destiné la promotion -----------------------------------//
		//-----------------------------------------------------------------------------------//
		populationBox = new JComboBox();
		populationBox.addItem("Promotion pour les adhérents");
		populationBox.addItem("Promotion pour tous les clients");
		populationBox.setSelectedItem(populationPromoModification);

		populationBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				populationPromoModification = (String) ((JComboBox) e
						.getSource()).getSelectedItem();
			}
		});

		// Définition du JComboBox dans lequel le gérant peut modifier l'article sur lequel //
		// ------------------------- est destiné la promotion ------------------------------//
		//----------------------------------------------------------------------------------//
		articleBox = new JComboBox();
		// Récupération de la liste de tous les articles présents dans le magasin
		ArrayList<String> listeArticles = new ArrayList<String>();
		listeArticles = SGBD.selectListeStringOrdonneCondition("ARTICLE",
				"IDARTICLE", "IDARTICLE", "ETATARTICLE != 'Supprimé'");

		for (String article : listeArticles) {
			articleBox.addItem(article);
		}
		articleBox.setSelectedItem(articleSelectionneModification);

		articleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération de l'identifiant de l'article sélectionné dans le menu déroulant
				articleSelectionneModification = (String) ((JComboBox) e
						.getSource()).getSelectedItem();
				
				// Récupération de la description de l'article sélectionné
				String descriptionArticleSelectionne = SGBD
						.selectStringConditionString("ARTICLE", "DESCRIPTION",
								"IDARTICLE", articleSelectionneModification);
				
				// Affichage d'une fenêtre pour indiquer au gérant la description de l'article 
				// qu'il a sélectionné
				JOptionPane.showMessageDialog(null,
						"vous avez sélectionné l'article : "
								+ descriptionArticleSelectionne, "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Définition des JComboBox pour permettre de modifier le jour, le mois //
		// ----------- et l'année de début et de fin d'une promotion -----------//
		//----------------------------------------------------------------------//
		cbjourDebut = new JComboBox();
		cbjourFin = new JComboBox();

		for (int i = 1; i < 32; i++) {
			String chaine = "";
			if (i < 10) {
				chaine = "0";
			}
			cbjourDebut.addItem(chaine + i);
			cbjourFin.addItem(chaine + i);
		}

		cbmoisDebut = new JComboBox();
		cbmoisFin = new JComboBox();

		for (int j = 1; j < 13; j++) {
			String chaine = "";
			if (j < 10) {
				chaine = "0";
			}
			cbmoisDebut.addItem(chaine + j);
			cbmoisFin.addItem(chaine + j);
		}

		cbanneeDebut = new JComboBox();
		cbanneeFin = new JComboBox();

		for (int k = 2010; k < 2040; k++) {
			cbanneeDebut.addItem(k + "");
			cbanneeFin.addItem(k + "");
		}

		cbanneeDebut.setVisible(true);
		cbmoisDebut.setVisible(true);
		cbjourDebut.setVisible(true);
		cbanneeFin.setVisible(true);
		cbmoisFin.setVisible(true);
		cbjourFin.setVisible(true);

		// Nous allons empêcher le gérant de pouvoir modifier
		// une date de début de promotion
		// qui est déjà passée
		Date dateDebutPromotion = SGBD.stringToDate(dateDe, "dd/MM/yyyy");
		;
		Date dateJour = new Date(System.currentTimeMillis());

		if (dateDebutPromotion.before(dateJour)) {
			panDateDebut.setVisible(false);
		}

		jourDebutSelectionneModification = dateDe.substring(0, 2);
		moisDebutSelectionneModification = dateDe.substring(3, 5);
		anneeDebutSelectionneModification = dateDe.substring(6, 10);

		jourFinSelectionneModification = dateFi.substring(0, 2);
		moisFinSelectionneModification = dateFi.substring(3, 5);
		anneeFinSelectionneModification = dateFi.substring(6, 10);

		cbanneeFin.setSelectedItem(anneeFinSelectionneModification);
		cbmoisFin.setSelectedItem(moisFinSelectionneModification);
		cbjourFin.setSelectedItem(jourFinSelectionneModification);

		cbanneeDebut.setSelectedItem(anneeDebutSelectionneModification);
		cbmoisDebut.setSelectedItem(moisDebutSelectionneModification);
		cbjourDebut.setSelectedItem(jourDebutSelectionneModification);

		cbanneeDebut.setPreferredSize(new Dimension(5, 7));
		cbanneeFin.setPreferredSize(new Dimension(5, 7));
		cbmoisFin.setPreferredSize(new Dimension(5, 5));
		cbmoisDebut.setPreferredSize(new Dimension(5, 5));
		cbjourFin.setPreferredSize(new Dimension(5, 5));
		cbjourDebut.setPreferredSize(new Dimension(5, 5));

		cbjourDebut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jourDebutSelectionneModification = (String) ((JComboBox) e
						.getSource()).getSelectedItem();
			}
		});

		cbjourFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jourFinSelectionneModification = (String) ((JComboBox) e
						.getSource()).getSelectedItem();
			}
		});

		cbmoisDebut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moisDebutSelectionneModification = (String) ((JComboBox) e
						.getSource()).getSelectedItem();
			}
		});

		cbmoisFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moisFinSelectionneModification = (String) ((JComboBox) e
						.getSource()).getSelectedItem();
			}
		});

		cbanneeDebut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anneeDebutSelectionneModification = (String) ((JComboBox) e
						.getSource()).getSelectedItem();
			}
		});

		cbanneeFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anneeFinSelectionneModification = (String) ((JComboBox) e
						.getSource()).getSelectedItem();
			}
		});

		panDateDebut.setLayout(new GridLayout(1, 4, 5, 5));
		panDateFin.setLayout(new GridLayout(1, 4, 5, 5));

		// Ajout des titres à chacun des JPanel contenant les champs déjà remplis //
		// -----------------------------------------------------------------------//
		panDescriptionPromotion.add(descriptionLabel);
		panPopulation.add(populationLabel);
		panArticle.add(articleLabel);
		panPourcentPromo.add(pourcentLabel);

		// Ajout des JTextField et JComboBox à chacun des JPanel contenant les champs déjà remplis //
		// ----------------------------------------------------------------------------------------//
		panDescriptionPromotion.add(description);
		panPopulation.add(populationBox);
		panArticle.add(articleBox);
		panPourcentPromo.add(pourcentPromo);

		// Ajout des JComboBox à chacun des 2 JPanel contenant les dates à sélectionner //
		// -----------------------------------------------------------------------------//
		panDateDebut.add(cbjourDebut);
		panDateDebut.add(cbmoisDebut);
		panDateDebut.add(cbanneeDebut);

		panDateFin.add(cbjourFin);
		panDateFin.add(cbmoisFin);
		panDateFin.add(cbanneeFin);

		// Ajout des sous JPanel au "grand" JPanel //
		// ----------------------------------------//
		panneauCentralFenetre.add(panDescriptionPromotion);
		panneauCentralFenetre.add(panPopulation);
		panneauCentralFenetre.add(panPourcentPromo);
		panneauCentralFenetre.add(panArticle);

		panneauCentralFenetre.add(panDateDebut);
		panneauCentralFenetre.add(panDateFin);

		// Définition du JPanel qui va accueillir les boutons de confirmation et d'annulation //
		// -----------------------------------------------------------------------------------//
		JPanel panneauBasFenetre = new JPanel();

		// Définition de l'action du bouton confirmer qui va vérifier que les champs saisis //
		// --------------------et selectionnés soient corrects -----------------------------//
		//----------------------------------------------------------------------------------//
		JButton boutonConfirmation = new JButton("Confirmer Modification");

		boutonConfirmation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Phase de vérification des champs remplis et sélectionnés par le gérant
				String position = null;
				String StringDateDebutPromotion = jourDebutSelectionneModification
						+ moisDebutSelectionneModification
						+ anneeDebutSelectionneModification;

				int jourDateDebutPromotion = Integer
						.parseInt(jourDebutSelectionneModification);
				int moisDateDebutPromotion = Integer
						.parseInt(moisDebutSelectionneModification);
				int anneeDateDebutPromotion = Integer
						.parseInt(anneeDebutSelectionneModification);

				String StringDateFinPromotion = jourFinSelectionneModification
						+ moisFinSelectionneModification
						+ anneeFinSelectionneModification;

				int jourDateFinPromotion = Integer
						.parseInt(jourFinSelectionneModification);
				int moisDateFinPromotion = Integer
						.parseInt(moisFinSelectionneModification);
				int anneeDateFinPromotion = Integer
						.parseInt(anneeFinSelectionneModification);

				Date dateJour = new Date(System.currentTimeMillis());

				int pourcentage = Integer.parseInt(pourcentPromo.getText());
				// int verificationChampPromotion;
				try {

					Date dateDebutPromotion = SGBD.stringToDate(
							StringDateDebutPromotion, "ddMMyyyy");
					Date dateFinPromotion = SGBD.stringToDate(
							StringDateFinPromotion, "ddMMyyyy");
					// Vérifie si la date de début de la promotion est
					// postérieure à la date d'aujourd'hui
					if (dateDebutPromotion.before(dateJour)) {
						position = "début";
						throw new ExceptionDateAvantAujourdhui(
								"La date de début de la promotion est déjà passée.");
					}

					// Vérifie si la date de fin de la promotion est postérieure
					// à la date d'aujourd'hui
					if (dateFinPromotion.before(dateJour)) {
						position = "fin";
						throw new ExceptionDateAvantAujourdhui(
								"La date de fin de la promotion est déjà passée.");
					}

					// Vérifie si la date de début de la promotion est cohérente
					// (mois de 30 jours)
					if ((moisDateDebutPromotion == 4
							| moisDateDebutPromotion == 6
							| moisDateDebutPromotion == 9 | moisDateDebutPromotion == 11)
							& jourDateDebutPromotion == 31) {
						position = "début";
						throw new ExceptionMoisDeTrenteJours(
								"Le 31ème jour de ce mois a été saisi alors que ce dernier n'en possède que 30");
					}

					// Vérifie si la date de fin de la promotion est cohérente
					// (mois de 30 jours)
					if ((moisDateFinPromotion == 4 | moisDateFinPromotion == 6
							| moisDateFinPromotion == 9 | moisDateFinPromotion == 11)
							& jourDateFinPromotion == 31) {
						position = "fin";
						throw new ExceptionMoisDeTrenteJours(
								"Le 31ème jour de ce mois a été saisi alors que ce dernier n'en possède que 30");
					}

					// Vérifie si la date de début de la promotion est cohérente
					// (mois de février)
					if (moisDateDebutPromotion == 2
							& jourDateDebutPromotion > 28) {
						position = "début";
						if (anneeDateDebutPromotion % 4 == 0
								&& jourDateDebutPromotion > 29) {
							throw new ExceptionMoisDeFevrierAnneeBissextile(
									"Le "
											+ jourDateDebutPromotion
											+ "ème jour du mois de Février a été"
											+ " saisi alors que ce dernier n'en possède que 29");
						} else {
							if (anneeDateDebutPromotion % 4 != 0) {
								throw new ExceptionMoisDeFevrier(
										"Le "
												+ jourDateDebutPromotion
												+ "ème jour du mois de Février a été"
												+ " saisi alors que ce dernier n'en possède que 28");
							}

						}
					}

					// Vérifie si la date de fin de la promotion est cohérente
					// (mois de février)
					if (moisDateFinPromotion == 2 & jourDateFinPromotion > 28) {
						position = "fin";
						if (anneeDateFinPromotion % 4 == 0
								&& jourDateFinPromotion > 29) {
							throw new ExceptionMoisDeFevrierAnneeBissextile(
									"Le "
											+ jourDateFinPromotion
											+ "ème jour du mois de Février a été"
											+ " saisi alors que ce dernier n'en possède que 29");
						} else {
							if (anneeDateFinPromotion % 4 != 0) {
								throw new ExceptionMoisDeFevrier(
										"Le "
												+ jourDateFinPromotion
												+ "ème jour du mois de Février a été"
												+ " saisi alors que ce dernier n'en possède que 28");
							}

						}
					}

					// Vérifie si la date de début de promotion précède celle de fin
					if (dateDebutPromotion.after(dateFinPromotion)) {
						throw new ExceptionOrdreDeDeuxDates(
								"La date de début de promotion est postérieure à celle de fin");
					}

					// Vérifie que les dates de début et de fin sont différentes
					if (dateDebutPromotion.equals(dateFinPromotion)) {
						throw new ExceptionDatesIdentiques(
								"La date de début de promotion est identique à celle de fin");
					}

					// Vérifie que tous les champs sont remplis
					if (description.getText().length() == 0
							| pourcentPromo.getText().length() == 0) {
						throw new ExceptionChampVide(
								"Au moins l'un des champs est vide");
					}

					// Vérifie la taille de la description
					if (description.getText().length() > 40) {
						throw new ExceptionExcesDeCaracteres(
								"La description de la promotion est trop longue");
					}

					// Vérifie le pourcentage saisi par le gérant
					if (pourcentage < 0 | pourcentage > 100) {
						throw new ExceptionPourcentageAberrant(
								"Le pourcentage de la promotion est aberrant");
					}

					// Si aucune Exception n'est levée, la modification de la 
					// promotion exceptionnelle s'opère
					boolean promoAdherent = true;

					if (populationPromoModification
							.equals("Promotion pour tous les clients")) {
						promoAdherent = false;
					}
					Promotion.modifierPromoBDD(identifiantPromotion,
							description.getText(), dateDebutPromotion,
							dateFinPromotion, pourcentPromo.getText(),
							promoAdherent);

					String requete = "UPDATE LISTING_PROMOS_ARTICLES SET IDARTICLE='"
							+ articleSelectionneModification + "'";

					System.out.println(requete);

					SGBD.executeUpdate(requete);
					
					// Puis fermeture de la fenêtre et affichage de la fenêtre d'accueil
					// de gestion des promotions exceptionnelles
					dispose();
					FenetrePromotionsGerant fen = new FenetrePromotionsGerant(
							null, true);
					fen.setVisible(true);

				} catch (ExceptionDateAvantAujourdhui e1) {

					JOptionPane
							.showMessageDialog(
									null,
									"La date de "
											+ position
											+ " de la promotion est déjà passée, modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionMoisDeTrenteJours e2) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi le 31ème jour d'un mois qui n'en possède que 30, modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionMoisDeFevrier e3) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi un jour inexistant pour le mois de février (28 jours), modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionMoisDeFevrierAnneeBissextile e4) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi un jour inexistant pour le mois de février (29 jours), modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionOrdreDeDeuxDates e5) {

					JOptionPane
							.showMessageDialog(
									null,
									"La date de fin de promotion est plus récente que celle de début de la promotion, vérifiez vos dates",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionDatesIdentiques e6) {

					JOptionPane
							.showMessageDialog(
									null,
									"Les dates de début et de fin de promotion sont identiques, vérifiez les",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionChampVide e7) {

					JOptionPane
							.showMessageDialog(
									null,
									"Au moins l'un des champs est vide, veuillez renseigner tous les champs",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionExcesDeCaracteres e8) {

					JOptionPane
							.showMessageDialog(
									null,
									"La description de la promotion est trop longue (40 caractères maximum), veuillez la raccourcir",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionPourcentageAberrant e9) {

					JOptionPane
							.showMessageDialog(
									null,
									"Le pourcentage appliqué au prix initial doit être compris entre 0 et 100, modifiez ce champ",
									"Attention", JOptionPane.ERROR_MESSAGE);
				}

				catch (Exception e10) {

					e10.printStackTrace();
				}

			}
		});

		// Définition de l'action du bouton retour à la page précédente qui annule //
		// --------- tout ce qui a pu être fait sur la page et la ferme -----------//
		//-------------------------------------------------------------------------//
		JButton boutonRetour = new JButton("Retour à la page précédente");
		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Permet le retour à la page précédente
				dispose();
				FenetrePromotionsGerant fen = new FenetrePromotionsGerant(null,
						true);
				fen.setVisible(true);
			}
		});

		// Définition du JPanel qui va accueillir les boutons de confirmation et d'annulation //
		// -----------------------------------------------------------------------------------//
		panneauBasFenetre.add(boutonConfirmation);
		panneauBasFenetre.add(boutonRetour);

		// Ajout des 2 JPanels principaux au conteneur de la fenêtre //
		// ----------------------------------------------------------//
		this.getContentPane().add(panneauCentralFenetre, "Center");
		this.getContentPane().add(panneauBasFenetre, "South");

	}

}
