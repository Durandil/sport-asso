package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import basededonnees.SGBD;

import metier.Article;
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
	/*public FenetreFormulairePromotionsGerant(JFrame parent, String title, boolean modal,Promotion promotion ){
		super(parent, title, modal);
		this.setSize(300, 650);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(promotion);
	}*/
	
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
				// TODO Auto-generated method stub
				populationPromo = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		articleBox = new JComboBox();
		//TODO récupération liste tous les articles
		ArrayList<String> listeArticles = new ArrayList<String>();
		listeArticles = SGBD.selectListeString("ARTICLE", "IDARTICLE");
		
		for (String article : listeArticles) {
			articleBox.addItem(article);
		}
		articleBox.setSelectedIndex(0);
		
		articleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				articleSelectionne =(String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		cbjourDebut = new JComboBox();
		cbjourFin = new JComboBox();
		
		for(int i=1 ; i<32;i++){
			cbjourDebut.addItem(i+"");
			cbjourFin.addItem(i+"");
		}

		
		cbmoisDebut = new JComboBox();
		cbmoisFin = new JComboBox();
		
		for(int j=1 ; j<13;j++){
			cbmoisDebut.addItem(j+"");
			cbmoisFin.addItem(j+"");
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
				// TODO Auto-generated method stub
				jourDebutSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		cbjourFin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jourFinSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbmoisDebut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				moisDebutSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbmoisFin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				moisFinSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbanneeDebut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				anneeDebutSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});

		cbanneeFin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
				//  TODO enregistrer la création d'une promotion
				// vérifier que la date est possible ( par exemple qu'on ait pas un 31 février ou un 31 novembre)
				boolean dateDebutPossible=Promotion.verifierDatePromotion(anneeDebutSelectionne, moisDebutSelectionne, jourDebutSelectionne);
				boolean dateFinPossible=Promotion.verifierDatePromotion(anneeFinSelectionne, moisFinSelectionne, jourFinSelectionne);
				boolean comparaisonDeuxDates;
				if(dateDebutPossible==true & dateFinPossible==true){
					comparaisonDeuxDates = Promotion.verifierOrdreDeuxDate(anneeDebutSelectionne, moisDebutSelectionne, jourDebutSelectionne, anneeFinSelectionne, moisFinSelectionne, jourFinSelectionne);
				}
				
				//Promotion promo = new Promotion(null, description.getText(), dateDebut, dateFin, pourcentagePromo, promoFidelite);
				
				// puis fermer la page
				setVisible(false);
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
	
}
