package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.Article;

public class FenetreFormulaireArticleGerant extends JDialog{
	// cette classe devra permettre d'ouvrir le formulaire d'ajout 
	// ou de modification d'un article dans le catalogue
	
	JLabel numArticleLabel,descriptionLabel,poidsLabel,catPrixLabel,catSportLabel,prixLabel,stockLabel;
	JTextField numArticle,description,poids,prix,stock;
	JComboBox catPrixBox,catSportBox;
	
	// Constructeur pour l'ajout d'un article
	public FenetreFormulaireArticleGerant(JFrame parent, String title, boolean modal ){
		super(parent, title, modal);
		this.setSize(300, 650);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	// Constructeur pour la modification d'un article
	public FenetreFormulaireArticleGerant(JFrame parent, String title, boolean modal,Article article ){
		super(parent, title, modal);
		this.setSize(300, 650);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(article);
	}
	
	
	private void initComponent(){
		JPanel panneauCentral = new JPanel();
		
		JPanel panNumArticle = new JPanel();
		JPanel panDescription = new JPanel();
		JPanel panPoids = new JPanel();
		JPanel panCategoriePrix = new JPanel();
		JPanel panCategorieSport = new JPanel();
		JPanel panStock = new JPanel();
		JPanel panPrixInitial = new JPanel();
		
		panNumArticle.setPreferredSize(new Dimension(220, 60));
		panDescription.setPreferredSize(new Dimension(220, 60));
		panPoids.setPreferredSize(new Dimension(220, 60));
		panCategoriePrix.setPreferredSize(new Dimension(220, 60));
		panCategorieSport.setPreferredSize(new Dimension(220, 60));
		panStock.setPreferredSize(new Dimension(220, 60));
		panPrixInitial.setPreferredSize(new Dimension(220, 60));
		
		
		panNumArticle.setBorder(BorderFactory.createEmptyBorder());
		panDescription.setBorder(BorderFactory.createEmptyBorder());
		panPoids.setBorder(BorderFactory.createEmptyBorder());
		panCategoriePrix.setBorder(BorderFactory.createEmptyBorder());
		panCategorieSport.setBorder(BorderFactory.createEmptyBorder());
		panStock.setBorder(BorderFactory.createEmptyBorder());
		panPrixInitial.setBorder(BorderFactory.createEmptyBorder());
		
		numArticleLabel = new JLabel("Num�ro d'article : ");
		descriptionLabel = new JLabel("Description : ");
		poidsLabel = new JLabel("Poids : ");
		catPrixLabel = new JLabel("Cat�gorie de Prix : ");
		catSportLabel = new JLabel("Cat�gorie de sport : ");
		prixLabel = new JLabel("Prix Initial : ");
		stockLabel = new JLabel("Stock : ");
		
		numArticle = new JTextField();
		description = new JTextField();
		poids = new JTextField();
		prix = new JTextField();
		stock = new JTextField();
		
		catPrixBox = new JComboBox();
		catSportBox = new JComboBox();
		
		// il faudra impl�menter une m�thode permettant de r�cup�rer les diff�rents items
		// ou choix de cat�gorie de sport et de cat�gorie de prix
		// dans une autre classe et l'ajouter au comboBox avec addItem()
		
		
		catPrixBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		catSportBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		panNumArticle.add(numArticleLabel);
		panDescription.add(descriptionLabel);
		panPoids.add(poidsLabel);
		panCategoriePrix.add(catPrixLabel);
		panCategorieSport.add(catSportLabel);
		panStock.add(stockLabel);
		panPrixInitial.add(prixLabel);
		
		panNumArticle.add(numArticle);
		panDescription.add(description);
		panPoids.add(poids);
		panStock.add(stock);
		panPrixInitial.add(prix);
		panCategoriePrix.add(catPrixBox);
		panCategorieSport.add(catSportBox);
		
		panneauCentral.add(panNumArticle);
		panneauCentral.add(panDescription);
		panneauCentral.add(panPoids);
		panneauCentral.add(panCategoriePrix);
		panneauCentral.add(panCategorieSport);
		panneauCentral.add(panStock);
		panneauCentral.add(panPrixInitial);
		
		this.getContentPane().add(panneauCentral,"Center");
		
		JPanel panneauBoutonsBas = new JPanel();
		
		JButton boutonConfirmation = new JButton("Confirmer");
		JButton boutonRetour = new JButton("Retour � la page pr�c�dente");
		
		boutonConfirmation.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//  TODO enregistrer la cr�ation d'article
				
				// puis fermer la page
				setVisible(false);
			}
		});
		
		boutonRetour.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// Permet le retour � la page precedente
				setVisible(false);
			}
		});
		
		panneauBoutonsBas.add(boutonConfirmation);
		panneauBoutonsBas.add(boutonRetour);
		
		this.getContentPane().add(panneauBoutonsBas,"South");
	}
	
	private void initComponent(Article article){
		//Pour le moment on ne modifie pas la cat�gorie de prix et de sport
		JPanel panneauCentral = new JPanel();
		
		JPanel panNumArticle = new JPanel();
		JPanel panDescription = new JPanel();
		JPanel panPoids = new JPanel();
		JPanel panStock = new JPanel();
		JPanel panPrixInitial = new JPanel();
		
		panNumArticle.setPreferredSize(new Dimension(220, 60));
		panDescription.setPreferredSize(new Dimension(220, 60));
		panPoids.setPreferredSize(new Dimension(220, 60));
		panStock.setPreferredSize(new Dimension(220, 60));
		panPrixInitial.setPreferredSize(new Dimension(220, 60));
		
		
		panNumArticle.setBorder(BorderFactory.createEmptyBorder());
		panDescription.setBorder(BorderFactory.createEmptyBorder());
		panPoids.setBorder(BorderFactory.createEmptyBorder());
		panStock.setBorder(BorderFactory.createEmptyBorder());
		panPrixInitial.setBorder(BorderFactory.createEmptyBorder());
		
		numArticleLabel = new JLabel("Num�ro Article : ");
		descriptionLabel = new JLabel("Description : ");
		poidsLabel = new JLabel("Poids : ");
		prixLabel = new JLabel("Prix Initial : ");
		stockLabel = new JLabel("Stock : ");
		
		numArticle = new JTextField(article.getIdArticle());
		description = new JTextField(article.getDescription());
		poids = new JTextField(String.valueOf(article.getPoids()));
		prix = new JTextField(String.valueOf(article.getPrixInitial()));
		stock = new JTextField(String.valueOf(article.getStock()));
		
		panNumArticle.add(numArticleLabel);
		panDescription.add(descriptionLabel);
		panPoids.add(poidsLabel);
		panStock.add(stockLabel);
		panPrixInitial.add(prixLabel);
		
		panNumArticle.add(numArticle);
		panDescription.add(description);
		panPoids.add(poids);
		panStock.add(stock);
		panPrixInitial.add(prix);
		
		panneauCentral.add(panNumArticle);
		panneauCentral.add(panDescription);
		panneauCentral.add(panPoids);
		panneauCentral.add(panStock);
		panneauCentral.add(panPrixInitial);
		
		this.getContentPane().add(panneauCentral,"Center");
		
		JPanel panneauBoutonsBas = new JPanel();
		
		JButton boutonConfirmation = new JButton("Confirmer");
		JButton boutonRetour = new JButton("Retour � la page pr�c�dente");
		
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
				// TODO Auto-generated method stub
				// Permet le retour � la page precedente
				setVisible(false);
				
			}
		});
		
		panneauBoutonsBas.add(boutonConfirmation);
		panneauBoutonsBas.add(boutonRetour);
		
		this.getContentPane().add(panneauBoutonsBas,"South");
	}
	
	
}
