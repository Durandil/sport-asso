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
 * La fen�tre cr��e est utilis�e dans {@link FenetrePromotionsGerant} dans les
 * boutons d'action d'ajout et de modification d'une promotion selectionn�e dans
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
	 * <b> Constructeur pour ajouter une promotion dans la base de donn�es. </b>
	 * <p>
	 * Elle sera instanci�e avec des JTextField vides pr�ts � �tre rempli par le
	 * g�rant.
	 * </p>
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param title
	 *            String indiquant le titre de la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
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
	 * <b> Constructeur pour modifier les param�tres d'une promotion en cours.
	 * </b>
	 * 
	 * <p>
	 * La fen�tre qui affiche plusieurs JTextField et JComboBox initialis�s avec
	 * les attributs actuels de la promotion et qui sont pr�ts � �tre modifi�s.
	 * </p>
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param title
	 *            String indiquant le titre de la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
	 * @param promotion
	 *            Identifant unique de la promotion � modifier
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
	 * Initialisation des composants de la fen�tre d'ajout d'une promotion :
	 * <ul>
	 * <li>un JPanel qui accueillir les 6 sous-JPanels contenant les JTextField
	 * et JComboBox vides permettant la cr�ation d'une nouvelle promotion.</li>
	 * <li>un JPanel qui va accueillir les boutons permettant de valider les
	 * champs saisis et d'annuler les saisies des champs (fermeture de la
	 * fen�tre).</li>
	 * </ul>
	 * </p>
	 * 
	 */
	private void initComponent() {
		// D�finition du grand JPanel qui va accueillir les sous JPanel contenant les //
		// JTextField et les JComboBox pour s�lectionner les dates de d�but et de fin //
		// ---------------------------------------------------------------------------//
		JPanel panneauCentralFenetre = new JPanel();
		panneauCentralFenetre.setLayout(new GridLayout(6, 1, 5, 5));

		// D�finition de chacun des sous JPanel //
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
				.createTitledBorder("Date d�but Promotion"));
		panDescriptionPromotion.setBorder(BorderFactory.createEmptyBorder());
		panPopulation.setBorder(BorderFactory.createEmptyBorder());
		panDateFin.setBorder(BorderFactory
				.createTitledBorder("Date Fin Promotion"));
		panArticle.setBorder(BorderFactory.createEmptyBorder());
		panPourcentPromo.setBorder(BorderFactory.createEmptyBorder());

		descriptionLabel = new JLabel("Description de la promotion : ");
		populationLabel = new JLabel(" Promotion adh�rent ? ");
		articleLabel = new JLabel("Article concern� : ");
		pourcentLabel = new JLabel("Pourcentage de promotion :");

		pourcentPromo = new JFormattedTextField(
				NumberFormat.getNumberInstance());
		description = new JTextField();

		pourcentPromo.setPreferredSize(new Dimension(90, 20));
		description.setPreferredSize(new Dimension(90, 20));

		// D�finition du JComboBox dans lequel le g�rant sp�cifie � quel type de client //
		// --------------------- est destin� la promotion ------------------------------//
		//------------------------------------------------------------------------------//
		populationBox = new JComboBox();
		populationBox.addItem("Promotion pour les adh�rents");
		populationBox.addItem("Promotion pour tous les clients");
		populationBox.setSelectedItem("Promotion pour tous les clients");

		populationBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				populationPromo = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});

		// D�finition du JComboBox dans lequel le g�rant sp�cifie l'article sur lequel //
		// --------------------- est destin� la promotion -----------------------------//
		//-----------------------------------------------------------------------------//
		articleBox = new JComboBox();
		// R�cup�ration de la liste tous les articles
		ArrayList<String> listeArticles = new ArrayList<String>();
		listeArticles = SGBD.selectListeStringOrdonneCondition("ARTICLE",
				"IDARTICLE", "IDARTICLE", "ETATARTICLE != 'Supprim�'");

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
						"vous avez s�lectionn� l'article : "
								+ descriptionArticleSelectionne, "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// D�finition des JComboBox pour permettre de s�lectionner le jour,le mois //
		// ----------- et l'ann�e de d�but et de fin d'une promotion --------------//
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

		// Ajout des titres � chacun des JPanel contenant les champs � remplir //
		// --------------------------------------------------------------------//
		panDescriptionPromotion.add(descriptionLabel);
		panPopulation.add(populationLabel);
		panArticle.add(articleLabel);
		panPourcentPromo.add(pourcentLabel);

		// Ajout des JTextField et JComboBox � chacun des JPanel contenant les champs � remplir //
		// -------------------------------------------------------------------------------------//
		panDescriptionPromotion.add(description);
		panPopulation.add(populationBox);
		panArticle.add(articleBox);
		panPourcentPromo.add(pourcentPromo);

		// Ajout des JComboBox � chacun des 2 JPanel contenant les dates � s�lectionner //
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

		// D�finition du JPanel qui va accueillir les boutons de confirmation et d'annulation //
		// -----------------------------------------------------------------------------------//
		JPanel panneauBasFenetre = new JPanel();

		// D�finition de l'action du bouton confirmer qui va v�rifier que les champs saisis //
		// -------------------- et selectionn�s soient corrects ----------------------------//
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
					
					// V�rifie si la date de d�but de la promotion est
					// post�rieure � la date d'aujourd'hui
					if (dateDebutPromotion.before(dateJour)) {
						position = "d�but";
						throw new ExceptionDateAvantAujourdhui(
								"La date de d�but de la promotion est d�j� pass�e.");
					}

					// V�rifie si la date de fin de la promotion est post�rieure
					// � la date d'aujourd'hui
					if (dateFinPromotion.before(dateJour)) {
						position = "fin";
						throw new ExceptionDateAvantAujourdhui(
								"La date de fin de la promotion est d�j� pass�e.");
					}

					// V�rifie si la date de d�but de la promotion est coh�rente
					// (mois de 30 jours)
					if ((moisDateDebutPromotion == 4
							| moisDateDebutPromotion == 6
							| moisDateDebutPromotion == 9 | moisDateDebutPromotion == 11)
							& jourDateDebutPromotion == 31) {
						position = "d�but";
						throw new ExceptionMoisDeTrenteJours(
								"Le 31�me jour de ce mois a �t� saisi alors que ce dernier n'en poss�de que 30");
					}

					// V�rifie si la date de fin de la promotion est coh�rente
					// (mois de 30 jours)
					if ((moisDateFinPromotion == 4 | moisDateFinPromotion == 6
							| moisDateFinPromotion == 9 | moisDateFinPromotion == 11)
							& jourDateFinPromotion == 31) {
						position = "fin";
						throw new ExceptionMoisDeTrenteJours(
								"Le 31�me jour de ce mois a �t� saisi alors que ce dernier n'en poss�de que 30");
					}

					// V�rifie si la date de d�but de la promotion est coh�rente
					// (mois de f�vrier)
					if (moisDateDebutPromotion == 2
							& jourDateDebutPromotion > 28) {
						position = "d�but";
						if (anneeDateDebutPromotion % 4 == 0
								&& jourDateDebutPromotion > 29) {
							throw new ExceptionMoisDeFevrierAnneeBissextile(
									"Le "
											+ jourDateDebutPromotion
											+ "�me jour du mois de F�vrier a �t�"
											+ " saisi alors que ce dernier n'en poss�de que 29");
						} else {
							if (anneeDateDebutPromotion % 4 != 0) {
								throw new ExceptionMoisDeFevrier(
										"Le "
												+ jourDateDebutPromotion
												+ "�me jour du mois de F�vrier a �t�"
												+ " saisi alors que ce dernier n'en poss�de que 28");
							}

						}
					}

					// V�rifie si la date de fin de la promotion est coh�rente
					// (mois de f�vrier)
					if (moisDateFinPromotion == 2 & jourDateFinPromotion > 28) {
						position = "fin";
						if (anneeDateFinPromotion % 4 == 0
								&& jourDateFinPromotion > 29) {
							throw new ExceptionMoisDeFevrierAnneeBissextile(
									"Le "
											+ jourDateFinPromotion
											+ "�me jour du mois de F�vrier a �t�"
											+ " saisi alors que ce dernier n'en poss�de que 29");
						} else {
							if (anneeDateFinPromotion % 4 != 0) {
								throw new ExceptionMoisDeFevrier(
										"Le "
												+ jourDateFinPromotion
												+ "�me jour du mois de F�vrier a �t�"
												+ " saisi alors que ce dernier n'en poss�de que 28");
							}

						}
					}

					// V�rifie si la date de d�but de promotion pr�c�de celle de
					// fin
					if (dateDebutPromotion.after(dateFinPromotion)) {
						throw new ExceptionOrdreDeDeuxDates(
								"La date de d�but de promotion est post�rieure � celle de fin");
					}

					// V�rifie que les dates de d�but et de fin sont diff�rentes
					if (dateDebutPromotion.equals(dateFinPromotion)) {
						throw new ExceptionDatesIdentiques(
								"La date de d�but de promotion est identique � celle de fin");
					}

					// V�rifie que tous les champs sont remplis
					if (description.getText().length() == 0
							| pourcentPromo.getText().length() == 0) {
						throw new ExceptionChampVide(
								"Au moins l'un des champs est vide");
					}

					// V�rifie la taille de la description
					if (description.getText().length() > 40) {
						throw new ExceptionExcesDeCaracteres(
								"La description de la promotion est trop longue");
					}
					int pourcentage = Integer.parseInt(pourcentPromo.getText());
					// V�rifie le pourcentage saisi par le g�rant
					if (pourcentage < 0 | pourcentage > 100) {
						throw new ExceptionPourcentageAberrant(
								"Le pourcentage de la promotion est aberrant");
					}

					// Si aucune Exception n'est lev�e, la cr�ation de la promotion
					// exceptionelle s'op�re
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
					
					// Fermeture de la fen�tre et retour vers la fen�tre d'accueil 
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
											+ " de la promotion est d�j� pass�e, modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionMoisDeTrenteJours e2) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi le 31�me jour d'un mois qui n'en poss�de que 30, modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionMoisDeFevrier e3) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi un jour inexistant pour le mois de f�vrier (28 jours), modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionMoisDeFevrierAnneeBissextile e4) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi un jour inexistant pour le mois de f�vrier (29 jours), modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionOrdreDeDeuxDates e5) {

					JOptionPane
							.showMessageDialog(
									null,
									"La date de fin de promotion est plus r�cente que celle de d�but de la promotion, v�rifiez vos dates",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionDatesIdentiques e6) {

					JOptionPane
							.showMessageDialog(
									null,
									"Les dates de d�but et de fin de promotion sont identiques, v�rifiez les",
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
									"La description de la promotion est trop longue (40 caract�res maximum), veuillez la raccourcir",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionPourcentageAberrant e9) {

					JOptionPane
							.showMessageDialog(
									null,
									"Le pourcentage appliqu� au prix initial doit �tre compris entre 0 et 100, modifiez ce champ",
									"Attention", JOptionPane.ERROR_MESSAGE);
				}

				catch (Exception e10) {

					e10.printStackTrace();
				}
			}
		});

		// D�finition de l'action du bouton retour � la page pr�c�dente qui annule //
		// --------- tout ce qui a pu �tre fait sur la page et la ferme -----------//
		//-------------------------------------------------------------------------//

		JButton boutonRetour = new JButton("Retour � la page pr�c�dente");

		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Permet le retour � la page pr�c�dente
				dispose();
				FenetrePromotionsGerant fen = new FenetrePromotionsGerant(null,
						true);
				fen.setVisible(true);
			}
		});

		// Ajout des boutons au JPanel panneauBasFenetre destin� � accueillir les boutons //
		// -------------------------------------------------------------------------------//
		panneauBasFenetre.add(boutonConfirmation);
		panneauBasFenetre.add(boutonRetour);

		// Ajout des 2 JPanels principaux au conteneur de la fen�tre //
		// ----------------------------------------------------------//
		this.getContentPane().add(panneauCentralFenetre, "Center");
		this.getContentPane().add(panneauBasFenetre, "South");
	}

	/**
	 * <b> Surchage de la m�thode initComponent pour la modification d'une
	 * promotion </b>
	 * 
	 * <p>
	 * Initialisation des composants de la fen�tre de modification d'une promotion :
	 * <ul>
	 * <li>un JPanel qui accueillir les 6 sous-JPanels contenant les JTextField
	 * et JComboBox remplis avec les attributs de la promotion s�lectionn�e.</li>
	 * <li>un JPanel qui va accueillir les boutons permettant de valider les
	 * champs saisis et d'annuler les saisies des champs(fermeture de la
	 * fen�tre).</li>
	 * </ul>
	 * </p>
	 * 
	 * @param idPromo
	 *            Identifiant unique de la promotion pr�te � �tre modifi�e
	 */
	private void initComponent(String idPromo) throws Exception {
		final String identifiantPromotion = idPromo;

		// R�cup�ration dans la base de donn�es des attributs de la promotion afin de   //
		// pr�-remplir les JTextField avec la valeur enregistr�e dans la base de donn�es//
		// ----------- et de compl�ter la valeur par d�faut des JComboBox ------------- //
		// -----------------------------------------------------------------------------//
		String nomPromotion = SGBD.selectStringConditionString("PROMO",
				"NOMPROMO", "IDPROMO", idPromo);
		String promotionPopulation = SGBD.selectStringConditionString("PROMO",
				"PROMOFIDELITE", "IDPROMO", idPromo);

		populationPromoModification = "Promotion pour tous les clients";
		if (promotionPopulation.equals("1")) {
			populationPromoModification = "Promotion pour les adh�rents";
		}

		String pourcentagePromotion = SGBD.selectStringConditionString("PROMO",
				"POURCENTAGEPROMO", "IDPROMO", idPromo);
		articleSelectionneModification = SGBD.selectStringConditionString(
				"LISTING_PROMOS_ARTICLES", "IDARTICLE", "IDPROMO", idPromo);
		String dateDe = SGBD.selectDateConditionString("PROMO", "DATEDEBUT",
				"IDPROMO", idPromo, "dd/MM/yyyy");
		String dateFi = SGBD.selectDateConditionString("PROMO", "DATEFIN",
				"IDPROMO", idPromo, "dd/MM/yyyy");

		// D�finition du grand JPanel qui va accueillir les sous JPanel contenant les //
		// JTextField et les JComboBox pour s�lectionner les dates de d�but et de fin //
		// ---------------------------------------------------------------------------//
		JPanel panneauCentralFenetre = new JPanel();
		panneauCentralFenetre.setLayout(new GridLayout(6, 1, 5, 5));

		// D�finition de chacun des sous JPanel //
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
				.createTitledBorder("Date d�but Promotion"));
		panDescriptionPromotion.setBorder(BorderFactory.createEmptyBorder());
		panPopulation.setBorder(BorderFactory.createEmptyBorder());
		panDateFin.setBorder(BorderFactory
				.createTitledBorder("Date Fin Promotion"));
		panArticle.setBorder(BorderFactory.createEmptyBorder());
		panPourcentPromo.setBorder(BorderFactory.createEmptyBorder());

		descriptionLabel = new JLabel("Description de la promotion : ");
		populationLabel = new JLabel(" Promotion adh�rent ? ");
		articleLabel = new JLabel("Article concern� : ");
		pourcentLabel = new JLabel("Pourcentage de promotion :");

		pourcentPromo = new JFormattedTextField(
				NumberFormat.getNumberInstance());
		pourcentPromo.setText(pourcentagePromotion);

		description = new JTextField(nomPromotion);

		pourcentPromo.setPreferredSize(new Dimension(90, 20));
		description.setPreferredSize(new Dimension(90, 20));

		// D�finition du JComboBox dans lequel le g�rant peut modifier � quel type de client //
		// --------------------- est destin� la promotion -----------------------------------//
		//-----------------------------------------------------------------------------------//
		populationBox = new JComboBox();
		populationBox.addItem("Promotion pour les adh�rents");
		populationBox.addItem("Promotion pour tous les clients");
		populationBox.setSelectedItem(populationPromoModification);

		populationBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				populationPromoModification = (String) ((JComboBox) e
						.getSource()).getSelectedItem();
			}
		});

		// D�finition du JComboBox dans lequel le g�rant peut modifier l'article sur lequel //
		// ------------------------- est destin� la promotion ------------------------------//
		//----------------------------------------------------------------------------------//
		articleBox = new JComboBox();
		// R�cup�ration de la liste de tous les articles pr�sents dans le magasin
		ArrayList<String> listeArticles = new ArrayList<String>();
		listeArticles = SGBD.selectListeStringOrdonneCondition("ARTICLE",
				"IDARTICLE", "IDARTICLE", "ETATARTICLE != 'Supprim�'");

		for (String article : listeArticles) {
			articleBox.addItem(article);
		}
		articleBox.setSelectedItem(articleSelectionneModification);

		articleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// R�cup�ration de l'identifiant de l'article s�lectionn� dans le menu d�roulant
				articleSelectionneModification = (String) ((JComboBox) e
						.getSource()).getSelectedItem();
				
				// R�cup�ration de la description de l'article s�lectionn�
				String descriptionArticleSelectionne = SGBD
						.selectStringConditionString("ARTICLE", "DESCRIPTION",
								"IDARTICLE", articleSelectionneModification);
				
				// Affichage d'une fen�tre pour indiquer au g�rant la description de l'article 
				// qu'il a s�lectionn�
				JOptionPane.showMessageDialog(null,
						"vous avez s�lectionn� l'article : "
								+ descriptionArticleSelectionne, "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// D�finition des JComboBox pour permettre de modifier le jour, le mois //
		// ----------- et l'ann�e de d�but et de fin d'une promotion -----------//
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

		// Nous allons emp�cher le g�rant de pouvoir modifier
		// une date de d�but de promotion
		// qui est d�j� pass�e
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

		// Ajout des titres � chacun des JPanel contenant les champs d�j� remplis //
		// -----------------------------------------------------------------------//
		panDescriptionPromotion.add(descriptionLabel);
		panPopulation.add(populationLabel);
		panArticle.add(articleLabel);
		panPourcentPromo.add(pourcentLabel);

		// Ajout des JTextField et JComboBox � chacun des JPanel contenant les champs d�j� remplis //
		// ----------------------------------------------------------------------------------------//
		panDescriptionPromotion.add(description);
		panPopulation.add(populationBox);
		panArticle.add(articleBox);
		panPourcentPromo.add(pourcentPromo);

		// Ajout des JComboBox � chacun des 2 JPanel contenant les dates � s�lectionner //
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

		// D�finition du JPanel qui va accueillir les boutons de confirmation et d'annulation //
		// -----------------------------------------------------------------------------------//
		JPanel panneauBasFenetre = new JPanel();

		// D�finition de l'action du bouton confirmer qui va v�rifier que les champs saisis //
		// --------------------et selectionn�s soient corrects -----------------------------//
		//----------------------------------------------------------------------------------//
		JButton boutonConfirmation = new JButton("Confirmer Modification");

		boutonConfirmation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Phase de v�rification des champs remplis et s�lectionn�s par le g�rant
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
					// V�rifie si la date de d�but de la promotion est
					// post�rieure � la date d'aujourd'hui
					if (dateDebutPromotion.before(dateJour)) {
						position = "d�but";
						throw new ExceptionDateAvantAujourdhui(
								"La date de d�but de la promotion est d�j� pass�e.");
					}

					// V�rifie si la date de fin de la promotion est post�rieure
					// � la date d'aujourd'hui
					if (dateFinPromotion.before(dateJour)) {
						position = "fin";
						throw new ExceptionDateAvantAujourdhui(
								"La date de fin de la promotion est d�j� pass�e.");
					}

					// V�rifie si la date de d�but de la promotion est coh�rente
					// (mois de 30 jours)
					if ((moisDateDebutPromotion == 4
							| moisDateDebutPromotion == 6
							| moisDateDebutPromotion == 9 | moisDateDebutPromotion == 11)
							& jourDateDebutPromotion == 31) {
						position = "d�but";
						throw new ExceptionMoisDeTrenteJours(
								"Le 31�me jour de ce mois a �t� saisi alors que ce dernier n'en poss�de que 30");
					}

					// V�rifie si la date de fin de la promotion est coh�rente
					// (mois de 30 jours)
					if ((moisDateFinPromotion == 4 | moisDateFinPromotion == 6
							| moisDateFinPromotion == 9 | moisDateFinPromotion == 11)
							& jourDateFinPromotion == 31) {
						position = "fin";
						throw new ExceptionMoisDeTrenteJours(
								"Le 31�me jour de ce mois a �t� saisi alors que ce dernier n'en poss�de que 30");
					}

					// V�rifie si la date de d�but de la promotion est coh�rente
					// (mois de f�vrier)
					if (moisDateDebutPromotion == 2
							& jourDateDebutPromotion > 28) {
						position = "d�but";
						if (anneeDateDebutPromotion % 4 == 0
								&& jourDateDebutPromotion > 29) {
							throw new ExceptionMoisDeFevrierAnneeBissextile(
									"Le "
											+ jourDateDebutPromotion
											+ "�me jour du mois de F�vrier a �t�"
											+ " saisi alors que ce dernier n'en poss�de que 29");
						} else {
							if (anneeDateDebutPromotion % 4 != 0) {
								throw new ExceptionMoisDeFevrier(
										"Le "
												+ jourDateDebutPromotion
												+ "�me jour du mois de F�vrier a �t�"
												+ " saisi alors que ce dernier n'en poss�de que 28");
							}

						}
					}

					// V�rifie si la date de fin de la promotion est coh�rente
					// (mois de f�vrier)
					if (moisDateFinPromotion == 2 & jourDateFinPromotion > 28) {
						position = "fin";
						if (anneeDateFinPromotion % 4 == 0
								&& jourDateFinPromotion > 29) {
							throw new ExceptionMoisDeFevrierAnneeBissextile(
									"Le "
											+ jourDateFinPromotion
											+ "�me jour du mois de F�vrier a �t�"
											+ " saisi alors que ce dernier n'en poss�de que 29");
						} else {
							if (anneeDateFinPromotion % 4 != 0) {
								throw new ExceptionMoisDeFevrier(
										"Le "
												+ jourDateFinPromotion
												+ "�me jour du mois de F�vrier a �t�"
												+ " saisi alors que ce dernier n'en poss�de que 28");
							}

						}
					}

					// V�rifie si la date de d�but de promotion pr�c�de celle de fin
					if (dateDebutPromotion.after(dateFinPromotion)) {
						throw new ExceptionOrdreDeDeuxDates(
								"La date de d�but de promotion est post�rieure � celle de fin");
					}

					// V�rifie que les dates de d�but et de fin sont diff�rentes
					if (dateDebutPromotion.equals(dateFinPromotion)) {
						throw new ExceptionDatesIdentiques(
								"La date de d�but de promotion est identique � celle de fin");
					}

					// V�rifie que tous les champs sont remplis
					if (description.getText().length() == 0
							| pourcentPromo.getText().length() == 0) {
						throw new ExceptionChampVide(
								"Au moins l'un des champs est vide");
					}

					// V�rifie la taille de la description
					if (description.getText().length() > 40) {
						throw new ExceptionExcesDeCaracteres(
								"La description de la promotion est trop longue");
					}

					// V�rifie le pourcentage saisi par le g�rant
					if (pourcentage < 0 | pourcentage > 100) {
						throw new ExceptionPourcentageAberrant(
								"Le pourcentage de la promotion est aberrant");
					}

					// Si aucune Exception n'est lev�e, la modification de la 
					// promotion exceptionnelle s'op�re
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
					
					// Puis fermeture de la fen�tre et affichage de la fen�tre d'accueil
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
											+ " de la promotion est d�j� pass�e, modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionMoisDeTrenteJours e2) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi le 31�me jour d'un mois qui n'en poss�de que 30, modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionMoisDeFevrier e3) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi un jour inexistant pour le mois de f�vrier (28 jours), modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionMoisDeFevrierAnneeBissextile e4) {

					JOptionPane
							.showMessageDialog(
									null,
									"Concernant la date de "
											+ position
											+ " de la promotion : vous avez saisi un jour inexistant pour le mois de f�vrier (29 jours), modifiez cette date",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionOrdreDeDeuxDates e5) {

					JOptionPane
							.showMessageDialog(
									null,
									"La date de fin de promotion est plus r�cente que celle de d�but de la promotion, v�rifiez vos dates",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionDatesIdentiques e6) {

					JOptionPane
							.showMessageDialog(
									null,
									"Les dates de d�but et de fin de promotion sont identiques, v�rifiez les",
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
									"La description de la promotion est trop longue (40 caract�res maximum), veuillez la raccourcir",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} catch (ExceptionPourcentageAberrant e9) {

					JOptionPane
							.showMessageDialog(
									null,
									"Le pourcentage appliqu� au prix initial doit �tre compris entre 0 et 100, modifiez ce champ",
									"Attention", JOptionPane.ERROR_MESSAGE);
				}

				catch (Exception e10) {

					e10.printStackTrace();
				}

			}
		});

		// D�finition de l'action du bouton retour � la page pr�c�dente qui annule //
		// --------- tout ce qui a pu �tre fait sur la page et la ferme -----------//
		//-------------------------------------------------------------------------//
		JButton boutonRetour = new JButton("Retour � la page pr�c�dente");
		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Permet le retour � la page pr�c�dente
				dispose();
				FenetrePromotionsGerant fen = new FenetrePromotionsGerant(null,
						true);
				fen.setVisible(true);
			}
		});

		// D�finition du JPanel qui va accueillir les boutons de confirmation et d'annulation //
		// -----------------------------------------------------------------------------------//
		panneauBasFenetre.add(boutonConfirmation);
		panneauBasFenetre.add(boutonRetour);

		// Ajout des 2 JPanels principaux au conteneur de la fen�tre //
		// ----------------------------------------------------------//
		this.getContentPane().add(panneauCentralFenetre, "Center");
		this.getContentPane().add(panneauBasFenetre, "South");

	}

}
