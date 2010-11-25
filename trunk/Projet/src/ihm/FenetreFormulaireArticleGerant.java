package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

public class FenetreFormulaireArticleGerant extends JDialog{
	// cette classe devra permettre d'ouvrir le formulaire d'ajout 
	// ou de modification d'un article dans le catalogue
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel numArticleLabel,descriptionLabel,poidsLabel,catPrixLabel,catSportLabel,prixLabel,stockLabel;
	JTextField numArticle,description,poids,prix,stock;
	JComboBox catPrixBox,catSportBox;
	String itemSportSelectionne="";
	String itemPrixSelectionne="";
	Dimension dimensionStandard = new Dimension(220, 60);
	
	// Constructeur pour l'ajout d'un article
	public FenetreFormulaireArticleGerant(JFrame parent, String title, boolean modal ){
		super(parent, title, modal);
		this.setSize(450, 650);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	// Constructeur pour la modification d'un article
	public FenetreFormulaireArticleGerant(JFrame parent, String title, boolean modal,String idArticle){
		super(parent, title, modal);
		this.setSize(450, 650);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(idArticle);
	}
	
	
	private void initComponent(){
		JPanel panneauCentral = new JPanel();
		
		//JPanel panNumArticle = new JPanel();
		JPanel panDescription = new JPanel();
		JPanel panPoids = new JPanel();
		JPanel panCategoriePrix = new JPanel();
		JPanel panCategorieSport = new JPanel();
		JPanel panStock = new JPanel();
		JPanel panPrixInitial = new JPanel();
		
		
		//panNumArticle.setPreferredSize(dimensionStandard);
		panDescription.setPreferredSize(dimensionStandard);
		panPoids.setPreferredSize(dimensionStandard);
		panCategoriePrix.setPreferredSize(dimensionStandard);
		panCategorieSport.setPreferredSize(dimensionStandard);
		panStock.setPreferredSize(dimensionStandard);
		panPrixInitial.setPreferredSize(dimensionStandard);
		
		
		//panNumArticle.setBorder(BorderFactory.createEmptyBorder());
		panDescription.setBorder(BorderFactory.createEmptyBorder());
		panPoids.setBorder(BorderFactory.createEmptyBorder());
		panCategoriePrix.setBorder(BorderFactory.createEmptyBorder());
		panCategorieSport.setBorder(BorderFactory.createEmptyBorder());
		panStock.setBorder(BorderFactory.createEmptyBorder());
		panPrixInitial.setBorder(BorderFactory.createEmptyBorder());
		
		//numArticleLabel = new JLabel("Numéro d'article : ");
		descriptionLabel = new JLabel("Description : ");
		poidsLabel = new JLabel("Poids : ");
		catPrixLabel = new JLabel("Catégorie de Prix : ");
		catSportLabel = new JLabel("Catégorie de sport : ");
		prixLabel = new JLabel("Prix Initial : ");
		stockLabel = new JLabel("Stock : ");
		
		//numArticle = new JFormattedTextField();
		description = new JTextField();
		poids = new JFormattedTextField(NumberFormat.getIntegerInstance());
		prix = new JFormattedTextField(NumberFormat.getNumberInstance());
		stock = new JFormattedTextField(NumberFormat.getIntegerInstance());
		
		//numArticle.setPreferredSize(new Dimension(90, 25));
		description.setPreferredSize(new Dimension(90, 25));
		poids.setPreferredSize(new Dimension(90, 25));
		prix.setPreferredSize(new Dimension(90, 25));
		stock.setPreferredSize(new Dimension(90, 25));
		
		catPrixBox = new JComboBox();
		catSportBox = new JComboBox();
		
		ArrayList<String> listeTypeSport = new ArrayList<String>();
		listeTypeSport= SGBD.selectListeString("TYPE_SPORT", "NOMTYPE");
		if(listeTypeSport.size()>0){
			for (int i = 0; i < listeTypeSport.size(); i++) {
			catSportBox.addItem(listeTypeSport.get(i));
			}
			catSportBox.setSelectedIndex(0);
		}
		
		ArrayList<String> listeCategoriePrix = new ArrayList<String>();
		listeCategoriePrix= SGBD.selectListeString("CATEGORIE", "NOMCATEGORIE");
		 if(listeCategoriePrix.size()>0){ 
			 for (int i = 0; i < listeCategoriePrix.size(); i++) {
				 catPrixBox.addItem(listeCategoriePrix.get(i));
			 }
			 catPrixBox.setSelectedIndex(0);
		 }
		
		catPrixBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				itemPrixSelectionne=(String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		catSportBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				itemSportSelectionne=(String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		//panNumArticle.add(numArticleLabel);
		panDescription.add(descriptionLabel);
		panPoids.add(poidsLabel);
		panCategoriePrix.add(catPrixLabel);
		panCategorieSport.add(catSportLabel);
		panStock.add(stockLabel);
		panPrixInitial.add(prixLabel);
		
		//panNumArticle.add(numArticle);
		panDescription.add(description);
		panPoids.add(poids);
		panStock.add(stock);
		panPrixInitial.add(prix);
		panCategoriePrix.add(catPrixBox);
		panCategorieSport.add(catSportBox);
		
		//panneauCentral.add(panNumArticle);
		panneauCentral.add(panDescription);
		panneauCentral.add(panPoids);
		panneauCentral.add(panCategoriePrix);
		panneauCentral.add(panCategorieSport);
		panneauCentral.add(panStock);
		panneauCentral.add(panPrixInitial);
		
		this.getContentPane().add(panneauCentral,"Center");
		
		JPanel panneauBoutonsBas = new JPanel();
		
		JButton boutonConfirmation = new JButton("Confirmer");
		JButton boutonRetour = new JButton("Retour à la page précédente");
		
		boutonConfirmation.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//  TODO enregistrer la création d'article
				String p =poids.getText();
				String st= stock.getText();
				String prx=prix.getText();
				/** ATTENTION : Si on ne touche pas aux Jcombobox, les items seront vides !**/
				String typ = SGBD.selectStringConditionString("TYPE_SPORT", "IDTYPE", "NOMTYPE", itemSportSelectionne);
				String cat = SGBD.selectStringConditionString("CATEGORIE", "IDCATEGORIE", "NOMCATEGORIE", itemPrixSelectionne);
				/** TODO Gérer la génération d'un id article**/
				
				Article art = new Article(null,description.getText(),typ,Float.parseFloat(p),Double.parseDouble(prx),Integer.parseInt(st),"En stock",cat);
				
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
		
		panneauBoutonsBas.add(boutonConfirmation);
		panneauBoutonsBas.add(boutonRetour);
		
		this.getContentPane().add(panneauBoutonsBas,"South");
	}
	
	private void initComponent(String idArticle){
		//Pour le moment on ne modifie pas la catégorie de prix et de sport
		String descriptionA = SGBD.selectStringConditionString("ARTICLE", "DESCRIPTION", "IDARTICLE", idArticle);
		String poidsA = SGBD.selectStringConditionString("ARTICLE", "POIDS", "IDARTICLE", idArticle);
		String prixA = SGBD.selectStringConditionString("ARTICLE", "PRIXINITIAL", "IDARTICLE", idArticle);
		String stockA = SGBD.selectStringConditionString("ARTICLE", "STOCK", "IDARTICLE", idArticle);
		
		
		JPanel panneauCentral = new JPanel();
		
		JPanel panDescription = new JPanel();
		JPanel panPoids = new JPanel();
		JPanel panStock = new JPanel();
		JPanel panPrixInitial = new JPanel();
		
		panDescription.setPreferredSize(dimensionStandard);
		panPoids.setPreferredSize(dimensionStandard);
		panStock.setPreferredSize(dimensionStandard);
		panPrixInitial.setPreferredSize(dimensionStandard);
		
		panDescription.setBorder(BorderFactory.createEmptyBorder());
		panPoids.setBorder(BorderFactory.createEmptyBorder());
		panStock.setBorder(BorderFactory.createEmptyBorder());
		panPrixInitial.setBorder(BorderFactory.createEmptyBorder());
		
		descriptionLabel = new JLabel("Description : ");
		poidsLabel = new JLabel("Poids : ");
		prixLabel = new JLabel("Prix Initial : ");
		stockLabel = new JLabel("Stock : ");
		
		description = new JTextField(descriptionA);
		poids = new JTextField(poidsA);
		prix = new JTextField(prixA);
		stock = new JTextField(stockA);
		
		description.setPreferredSize(new Dimension(100,20));
		poids.setPreferredSize(new Dimension(100,20));
		prix.setPreferredSize(new Dimension(100,20));
		stock.setPreferredSize(new Dimension(100,20));
		
		panDescription.add(descriptionLabel);
		panPoids.add(poidsLabel);
		panStock.add(stockLabel);
		panPrixInitial.add(prixLabel);
		
		panDescription.add(description);
		panPoids.add(poids);
		panStock.add(stock);
		panPrixInitial.add(prix);
		
		panneauCentral.add(panDescription);
		panneauCentral.add(panPoids);
		panneauCentral.add(panStock);
		panneauCentral.add(panPrixInitial);
		
		this.getContentPane().add(panneauCentral,"Center");
		
		JPanel panneauBoutonsBas = new JPanel();
		
		JButton boutonConfirmation = new JButton("Confirmer modification");
		JButton boutonRetour = new JButton("Retour à la page précédente");
		
		boutonConfirmation.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// enregistrer la modification d'article 
				
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
		
		panneauBoutonsBas.add(boutonConfirmation);
		panneauBoutonsBas.add(boutonRetour);
		
		this.getContentPane().add(panneauBoutonsBas,"South");
	}
	
	
}
