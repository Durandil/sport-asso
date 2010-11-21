package ihm;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.Article;

public class FenetreFormulairePromotionsGerant extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// cette classe devra permettre d'ouvrir le formulaire d'ajout 
	// ou de modification d'une promotion
	public Dimension dimensionStandard = new Dimension(220, 60);
	private JLabel descriptionLabel,populationLabel,articleLabel,dateDebutLabel,dateFinLabel,pourcentLabel;
	private JComboBox articleBox,populationBox;
	private JTextField description,pourcentPromo;
	private String itemArticlePromo="";
	
	
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
		
		panDateDebut.setBorder(BorderFactory.createEmptyBorder());
		panDescriptionPromotion.setBorder(BorderFactory.createEmptyBorder());
		panPopulation.setBorder(BorderFactory.createEmptyBorder());
		panDateFin.setBorder(BorderFactory.createEmptyBorder());
		panArticle.setBorder(BorderFactory.createEmptyBorder());
		panPourcentPromo.setBorder(BorderFactory.createEmptyBorder());
		
		descriptionLabel = new JLabel("Description de la promotion : ");
		populationLabel = new JLabel(" Promotion adhérent ? ");
		articleLabel = new JLabel("Article concerné : ");
		dateDebutLabel = new JLabel("Date début Promotion : ");
		dateFinLabel = new JLabel("Date fin Promotion : ");
		pourcentLabel= new JLabel("Pourcenatge de promotion :");
		
		pourcentPromo = new JFormattedTextField(NumberFormat.getNumberInstance());
		description = new JTextField();
		
		pourcentPromo.setPreferredSize(new Dimension(90,20));
		description.setPreferredSize(new Dimension(90,20));
		
		populationBox = new JComboBox();
		populationBox.addItem("Promotion pour les adhérents");
		populationBox.addItem("Promotion pour tous les clients");
		populationBox.setSelectedItem("Promotion pour tous les clients");
		
		articleBox = new JComboBox();
		//TODO récupération liste tous les articles
		
		articleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				itemArticlePromo=(String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		
		panDescriptionPromotion.add(descriptionLabel);
		panPopulation.add(populationLabel);
		panArticle.add(articleLabel);
		panDateDebut.add(dateDebutLabel);
		panDateFin.add(dateFinLabel);
		panPourcentPromo.add(pourcentLabel);
		
		
		panDescriptionPromotion.add(description);
		panPopulation.add(populationBox);
		panArticle.add(articleBox);
		panPourcentPromo.add(pourcentPromo);
		
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
