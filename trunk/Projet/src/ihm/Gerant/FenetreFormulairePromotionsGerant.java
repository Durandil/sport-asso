package ihm.Gerant;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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

import basededonnees.SGBD;

import metier.Promotion;

public class FenetreFormulairePromotionsGerant extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// cette classe devra permettre d'ouvrir le formulaire d'ajout 
	// ou de modification d'une promotion
	public Dimension dimensionStandard = new Dimension(220, 60);
	private JLabel descriptionLabel,populationLabel,articleLabel,pourcentLabel;
	private JComboBox articleBox,populationBox;
	private JTextField description,pourcentPromo;
	private JComboBox cbmoisDebut, cbjourDebut,cbanneeDebut ;
	private JComboBox cbmoisFin, cbjourFin,cbanneeFin ;
	
	private static String jourDebutSelectionne="11";
	private static String moisDebutSelectionne="07";
	private static String anneeDebutSelectionne="2005";
	private static String jourFinSelectionne="11";
	private static String moisFinSelectionne="07";
	private static String anneeFinSelectionne="2005";
	private static String populationPromo="Promotion pour tous les clients";
	public static String articleSelectionne;
	
	private static String jourDebutSelectionneModification;
	private static String moisDebutSelectionneModification;
	private static String anneeDebutSelectionneModification;
	private static String jourFinSelectionneModification;
	private static String moisFinSelectionneModification;
	private static String anneeFinSelectionneModification;
	private static String populationPromoModification;
	public static String articleSelectionneModification;
	
	// Constructeur pour l'ajout d'une promotion
	public FenetreFormulairePromotionsGerant(JFrame parent, String title, boolean modal ){
		super(parent, title, modal);
		this.setSize(450, 650);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	// Constructeur pour la modification d'une promotion
	public FenetreFormulairePromotionsGerant(JFrame parent, String title, boolean modal,String promotion ) throws Exception{
		super(parent, title, modal);
		this.setSize(500, 650);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(promotion);
	}
	
	private void initComponent(){
		JPanel panneauCentralFenetre = new JPanel();
		panneauCentralFenetre.setLayout(new GridLayout(6, 1,5,5));
		
		JPanel panDescriptionPromotion = new JPanel();
		JPanel panPopulation = new JPanel();
		JPanel panDateDebut = new JPanel();
		JPanel panDateFin = new JPanel();
		JPanel panArticle =  new JPanel();
		JPanel panPourcentPromo =  new JPanel();
		
		panDateDebut.setPreferredSize(dimensionStandard);
		panDescriptionPromotion.setPreferredSize(dimensionStandard);
		panPopulation.setPreferredSize(dimensionStandard);
		panDateFin.setPreferredSize(dimensionStandard);
		panArticle.setPreferredSize(dimensionStandard);
		panPourcentPromo.setPreferredSize(dimensionStandard);
		
		panDateDebut.setBorder(BorderFactory.createTitledBorder("Date début Promotion"));
		panDescriptionPromotion.setBorder(BorderFactory.createEmptyBorder());
		panPopulation.setBorder(BorderFactory.createEmptyBorder());
		panDateFin.setBorder(BorderFactory.createTitledBorder("Date Fin Promotion"));
		panArticle.setBorder(BorderFactory.createEmptyBorder());
		panPourcentPromo.setBorder(BorderFactory.createEmptyBorder());
		
		descriptionLabel = new JLabel("Description de la promotion : ");
		populationLabel = new JLabel(" Promotion adhérent ? ");
		articleLabel = new JLabel("Article concerné : ");
		pourcentLabel= new JLabel("Pourcentage de promotion :");
		
		pourcentPromo = new JFormattedTextField(NumberFormat.getNumberInstance());
		description = new JTextField();
		
		pourcentPromo.setPreferredSize(new Dimension(90,20));
		description.setPreferredSize(new Dimension(90,20));
		
		populationBox = new JComboBox();
		populationBox.addItem("Promotion pour les adhérents");
		populationBox.addItem("Promotion pour tous les clients");
		populationBox.setSelectedItem("Promotion pour tous les clients");
		
		populationBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				populationPromo = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		articleBox = new JComboBox();
		// Récupération liste tous les articles
		ArrayList<String> listeArticles = new ArrayList<String>();
		listeArticles = SGBD.selectListeStringOrdonneCondition("ARTICLE", "IDARTICLE","IDARTICLE","ETATARTICLE != 'Supprimé'");
		
		for (String article : listeArticles) {
			articleBox.addItem(article);
		}
		articleBox.setSelectedIndex(0);
		
		articleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				articleSelectionne =(String) ((JComboBox) e.getSource()).getSelectedItem();
				
				String descriptionArticleSelectionne = SGBD.selectStringConditionString("ARTICLE", "DESCRIPTION","IDARTICLE",articleSelectionne);
				JOptionPane.showMessageDialog(null,"vous avez sélectionné l'article : " + descriptionArticleSelectionne,"Information",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		cbjourDebut = new JComboBox();
		cbjourFin = new JComboBox();
		
		for(int i=1 ; i<32;i++){
			String chaine="";
			if(i<10){
				chaine="0";
			}
			cbjourDebut.addItem(chaine+i);
			cbjourFin.addItem(chaine+i);
		}

		cbmoisDebut = new JComboBox();
		cbmoisFin = new JComboBox();
		
		for(int j=1 ; j<13;j++){
			String chaine="";
			if(j<10){
				chaine="0";
			}
			cbmoisDebut.addItem(chaine+j);
			cbmoisFin.addItem(chaine+j);
		}

		
		cbanneeDebut = new JComboBox();
		cbanneeFin = new JComboBox();
		
		for(int k=2010 ; k<2040;k++){
			cbanneeDebut.addItem(k+"");
			cbanneeFin.addItem(k+"");
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
				
				jourDebutSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		cbjourFin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				jourFinSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbmoisDebut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				moisDebutSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbmoisFin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				moisFinSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbanneeDebut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				anneeDebutSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbanneeFin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				anneeFinSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		
		panDateDebut.setLayout(new GridLayout(1,4,5,5));
		panDateFin.setLayout(new GridLayout(1,4,5,5));
		
		panDescriptionPromotion.add(descriptionLabel);
		panPopulation.add(populationLabel);
		panArticle.add(articleLabel);
		panPourcentPromo.add(pourcentLabel);
		
		
		panDescriptionPromotion.add(description);
		panPopulation.add(populationBox);
		panArticle.add(articleBox);
		panPourcentPromo.add(pourcentPromo);
		
		panDateDebut.add(cbjourDebut);
		panDateDebut.add(cbmoisDebut);
		panDateDebut.add(cbanneeDebut);
		
		panDateFin.add(cbjourFin);
		panDateFin.add(cbmoisFin);
		panDateFin.add(cbanneeFin);
		
		panneauCentralFenetre.add(panDescriptionPromotion);
		panneauCentralFenetre.add(panPopulation);
		panneauCentralFenetre.add(panPourcentPromo);
		panneauCentralFenetre.add(panArticle);
		panneauCentralFenetre.add(panDateDebut);
		panneauCentralFenetre.add(panDateFin);
		
		this.getContentPane().add(panneauCentralFenetre,"Center");
		
		JPanel panneauBasFenetre = new JPanel();
		
		JButton boutonConfirmation = new JButton("Confirmer");
		JButton boutonRetour = new JButton("Retour à la page précédente");
		
		boutonConfirmation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Enregistrer la création d'une promotion

				int verificationChampPromotion;
				try {
					verificationChampPromotion = Promotion.verifierChampPromotion(anneeDebutSelectionne, moisDebutSelectionne, jourDebutSelectionne,
							anneeFinSelectionne, moisFinSelectionne, jourFinSelectionne, 
							description.getText(), pourcentPromo.getText());
				
				
				switch (verificationChampPromotion) {
				case 0:
							String dateDeb = jourDebutSelectionne+moisDebutSelectionne+anneeDebutSelectionne;
							Date dateDebut= SGBD.stringToDate(dateDeb,"ddMMyyyy");
							
							String dateEnd = jourFinSelectionne+moisFinSelectionne+anneeFinSelectionne;
							Date dateFin= SGBD.stringToDate(dateEnd,"ddMMyyyy");

							boolean promoAdherent=true;
							
							if(populationPromo.equals("Promotion pour tous les clients")){
								promoAdherent=false;
							}
							
							Promotion promo = new Promotion(description.getText(), dateDebut, dateFin, Double.parseDouble(pourcentPromo.getText()), promoAdherent);
							
							
							String requete = "INSERT INTO LISTING_PROMOS_ARTICLES(IDPROMO,IDARTICLE) values('"
								+ promo.getIdPromotion() +"', '" + articleSelectionne+"')";
							
							System.out.println(requete);
							
							SGBD.executeUpdate(requete);
							
							dispose();
							FenetrePromotionsGerant fen = new FenetrePromotionsGerant();
							fen.setVisible(true);
					break;
				case 1 :
					JOptionPane.showMessageDialog(null,"Une des dates sélectionnées n'est pas valide, modifiez cette date","Attention",JOptionPane.ERROR_MESSAGE);
					
					break;
				case 2 :
					JOptionPane.showMessageDialog(null,"La date de début de promotion est plus récente que celle de fin de la promotion, modifiez ce champ","Attention",JOptionPane.ERROR_MESSAGE);
					
					break;
				case 3 :
					JOptionPane.showMessageDialog(null,"Un des champs remplis est vide, remplissez ce champ","Attention",JOptionPane.ERROR_MESSAGE);
					
					break;
				case 4 :
					JOptionPane.showMessageDialog(null,"Un pourcentage est compris entre 0 et 100, modifiez ce champ","Attention",JOptionPane.ERROR_MESSAGE);
					
					break;
				case 5 :
					JOptionPane.showMessageDialog(null,"Il y a trop de caractères dans le champ de description de la promotion, modifiez ce champ","Attention",JOptionPane.ERROR_MESSAGE);
					
					break;
				default:
					break;
				}
					
				} catch (Exception e1) {
					System.out.println(e1.getMessage());;
				}
				
				
			}
		});
		
		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Permet le retour à la page precedente
				setVisible(false);
			}
		});
		
		panneauBasFenetre.add(boutonConfirmation);
		panneauBasFenetre.add(boutonRetour);
		
		this.getContentPane().add(panneauBasFenetre,"South");
	}
	
	
	// surchage de la méthode initComponent pour la modification d'une promotion
	private void initComponent(String idPromo) throws Exception{
		final String identifiantPromotion = idPromo ; 
		String nomPromotion = SGBD.selectStringConditionString("PROMO", "NOMPROMO", "IDPROMO", idPromo);
		String promotionPopulation = SGBD.selectStringConditionString("PROMO","PROMOFIDELITE","IDPROMO",idPromo);

		populationPromoModification = "Promotion pour tous les clients";
		if(promotionPopulation.equals("1")){
			populationPromoModification="Promotion pour les adhérents";
		}
		
		String pourcentagePromotion = SGBD.selectStringConditionString("PROMO", "POURCENTAGEPROMO", "IDPROMO", idPromo);
		articleSelectionneModification = SGBD.selectStringConditionString("LISTING_PROMOS_ARTICLES","IDARTICLE","IDPROMO",idPromo);
		String dateDe = SGBD.selectDateConditionString("PROMO", "DATEDEBUT", "IDPROMO", idPromo,"dd/MM/yyyy");
		String dateFi = SGBD.selectDateConditionString("PROMO", "DATEFIN", "IDPROMO", idPromo,"dd/MM/yyyy");
		
		Date dateD= SGBD.stringToDate(dateDe,"dd/MM/yyyy");
		Date dateF= SGBD.stringToDate(dateFi,"dd/MM/yyyy");
		Date dateJour = new Date(System.currentTimeMillis());
		
		boolean dateDebutAvantToday = dateD.before(dateJour) ;	
		
		JPanel panneauCentralFenetre = new JPanel();
		panneauCentralFenetre.setLayout(new GridLayout(6,1,5,5));
		
		JPanel panDescriptionPromotion = new JPanel();
		JPanel panPopulation = new JPanel();
		JPanel panDateDebut = new JPanel();
		JPanel panDateFin = new JPanel();
		JPanel panArticle =  new JPanel();
		JPanel panPourcentPromo =  new JPanel();
		
		panDateDebut.setPreferredSize(dimensionStandard);
		panDescriptionPromotion.setPreferredSize(dimensionStandard);
		panPopulation.setPreferredSize(dimensionStandard);
		panDateFin.setPreferredSize(dimensionStandard);
		panArticle.setPreferredSize(dimensionStandard);
		panPourcentPromo.setPreferredSize(dimensionStandard);
		
		panDateDebut.setBorder(BorderFactory.createTitledBorder("Date début Promotion"));
		panDescriptionPromotion.setBorder(BorderFactory.createEmptyBorder());
		panPopulation.setBorder(BorderFactory.createEmptyBorder());
		panDateFin.setBorder(BorderFactory.createTitledBorder("Date Fin Promotion"));
		panArticle.setBorder(BorderFactory.createEmptyBorder());
		panPourcentPromo.setBorder(BorderFactory.createEmptyBorder());
		
		descriptionLabel = new JLabel("Description de la promotion : ");
		populationLabel = new JLabel(" Promotion adhérent ? ");
		articleLabel = new JLabel("Article concerné : ");
		pourcentLabel= new JLabel("Pourcentage de promotion :");
		
		pourcentPromo = new JFormattedTextField(NumberFormat.getNumberInstance());
		pourcentPromo.setText(pourcentagePromotion);
		
		description = new JTextField(nomPromotion);
		
		pourcentPromo.setPreferredSize(new Dimension(90,20));
		description.setPreferredSize(new Dimension(90,20));
		
		populationBox = new JComboBox();
		populationBox.addItem("Promotion pour les adhérents");
		populationBox.addItem("Promotion pour tous les clients");
		populationBox.setSelectedItem(populationPromoModification);
		
		populationBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				populationPromoModification = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		articleBox = new JComboBox();
		//récupération liste tous les articles
		ArrayList<String> listeArticles = new ArrayList<String>();
		listeArticles = SGBD.selectListeStringOrdonneCondition("ARTICLE", "IDARTICLE","IDARTICLE","ETATARTICLE != 'Supprimé'");
		
		for (String article : listeArticles) {
			articleBox.addItem(article);
		}
		articleBox.setSelectedItem(articleSelectionneModification);
		
		articleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				articleSelectionneModification =(String) ((JComboBox) e.getSource()).getSelectedItem();
				String descriptionArticleSelectionne = SGBD.selectStringConditionString("ARTICLE", "DESCRIPTION","IDARTICLE",articleSelectionneModification);
				JOptionPane.showMessageDialog(null,"vous avez sélectionné l'article : " + descriptionArticleSelectionne,"Information",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		cbjourDebut = new JComboBox();
		cbjourFin = new JComboBox();
		
		for(int i=1 ; i<32;i++){
			String chaine="";
			if(i<10){
				chaine="0";
			}
			cbjourDebut.addItem(chaine+i);
			cbjourFin.addItem(chaine+i);
		}

		cbmoisDebut = new JComboBox();
		cbmoisFin = new JComboBox();
		
		for(int j=1 ; j<13;j++){
			String chaine="";
			if(j<10){
				chaine="0";
			}
			cbmoisDebut.addItem(chaine+j);
			cbmoisFin.addItem(chaine+j);
		}

		
		cbanneeDebut = new JComboBox();
		cbanneeFin = new JComboBox();
		
		for(int k=2010 ; k<2040;k++){
			cbanneeDebut.addItem(k+"");
			cbanneeFin.addItem(k+"");
		}

		cbanneeDebut.setVisible(true);
		cbmoisDebut.setVisible(true);
		cbjourDebut.setVisible(true);
		cbanneeFin.setVisible(true);
		cbmoisFin.setVisible(true);
		cbjourFin.setVisible(true);
		
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
				jourDebutSelectionneModification = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		cbjourFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jourFinSelectionneModification = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbmoisDebut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moisDebutSelectionneModification = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbmoisFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moisFinSelectionneModification = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbanneeDebut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anneeDebutSelectionneModification = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbanneeFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anneeFinSelectionneModification = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		
		panDateDebut.setLayout(new GridLayout(1,4,5,5));
		panDateFin.setLayout(new GridLayout(1,4,5,5));
		
		panDescriptionPromotion.add(descriptionLabel);
		panPopulation.add(populationLabel);
		panArticle.add(articleLabel);
		panPourcentPromo.add(pourcentLabel);
		
		
		panDescriptionPromotion.add(description);
		panPopulation.add(populationBox);
		panArticle.add(articleBox);
		panPourcentPromo.add(pourcentPromo);
		
		panDateDebut.add(cbjourDebut);
		panDateDebut.add(cbmoisDebut);
		panDateDebut.add(cbanneeDebut);
		
		panDateFin.add(cbjourFin);
		panDateFin.add(cbmoisFin);
		panDateFin.add(cbanneeFin);
		
		panneauCentralFenetre.add(panDescriptionPromotion);
		panneauCentralFenetre.add(panPopulation);
		panneauCentralFenetre.add(panPourcentPromo);
		panneauCentralFenetre.add(panArticle);
		
		panneauCentralFenetre.add(panDateDebut);
		panneauCentralFenetre.add(panDateFin);
		
		this.getContentPane().add(panneauCentralFenetre,"Center");
		
		JPanel panneauBasFenetre = new JPanel();
		
		JButton boutonConfirmation = new JButton("Confirmer Modification");
		JButton boutonRetour = new JButton("Retour à la page précédente");
		
		boutonConfirmation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Enregistrement la modification d'une promotion
				int verificationChampPromotion;
				try {
					verificationChampPromotion = Promotion.verifierChampPromotion(anneeDebutSelectionneModification, moisDebutSelectionneModification, jourDebutSelectionneModification,
							anneeFinSelectionneModification, moisFinSelectionneModification, jourFinSelectionneModification, 
							description.getText(), pourcentPromo.getText());
				
				
				switch (verificationChampPromotion) {
				case 0:
							String dateDeb = jourDebutSelectionneModification+moisDebutSelectionneModification+anneeDebutSelectionneModification;
							Date dateDebut= SGBD.stringToDate(dateDeb,"ddMMyyyy");
							
							String dateEnd = jourFinSelectionneModification+moisFinSelectionneModification+anneeFinSelectionneModification;
							Date dateFin= SGBD.stringToDate(dateEnd,"ddMMyyyy");

							boolean promoAdherent=true;
							
							if(populationPromoModification.equals("Promotion pour tous les clients")){
								promoAdherent=false;
							}
							
							// METHODE DE MODIFICATION D'UNE PROMOTION
							Promotion.modifierPromoBDD(identifiantPromotion,description.getText(), dateDebut, dateFin, pourcentPromo.getText(),promoAdherent);
							
							String requete = "UPDATE LISTING_PROMOS_ARTICLES SET IDARTICLE='"+articleSelectionneModification+"'";
							
							System.out.println(requete);
							
							SGBD.executeUpdate(requete);
							
							dispose();
							FenetrePromotionsGerant fen = new FenetrePromotionsGerant();
							fen.setVisible(true);
					break;
				case 1 :
					JOptionPane.showMessageDialog(null,"Une des dates sélectionnées n'est pas valide, modifiez cette date","Attention",JOptionPane.ERROR_MESSAGE);
					
					break;
				case 2 :
					JOptionPane.showMessageDialog(null,"La date de début de promotion est plus récente que celle de fin de la promotion, modifiez ce champ","Attention",JOptionPane.ERROR_MESSAGE);
					
					break;
				case 3 :
					JOptionPane.showMessageDialog(null,"Un des champs remplis est vide, remplissez ce champ","Attention",JOptionPane.ERROR_MESSAGE);
					
					break;
				case 4 :
					JOptionPane.showMessageDialog(null,"Un pourcentage est compris entre 0 et 100, modifiez ce champ","Attention",JOptionPane.ERROR_MESSAGE);
					
					break;
				case 5 :
					JOptionPane.showMessageDialog(null,"Il y a trop de caractères dans le champ de description de la promotion, modifiez ce champ","Attention",JOptionPane.ERROR_MESSAGE);
					
					break;
				default:
					break;
				}
					
				} catch (Exception e1) {
					System.out.println(e1.getMessage());;
				}						

			}
		});
		
		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		panneauBasFenetre.add(boutonConfirmation);
		panneauBasFenetre.add(boutonRetour);
		
		this.getContentPane().add(panneauBasFenetre,"South");
	
	}
	
}
