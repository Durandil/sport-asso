package ihm;

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

public class FenetreFormulairePromotionsGerant extends JDialog {
	// cette classe devra permettre d'ouvrir le formulaire d'ajout 
	// ou de modification d'une promotion
	public Dimension dimensionStandard = new Dimension(220, 60);
	private JLabel numPromotionLabel,descriptionLabel,populationLabel,categorieLabel;
	private JComboBox categoriePromoBox;
	private JTextField numPromotion,description,population;
	private String itemCategoriePromoSelectionne="";
	
	
	// Constructeur pour l'ajout d'une promotion
	public FenetreFormulairePromotionsGerant(JFrame parent, String title, boolean modal ){
		super(parent, title, modal);
		this.setSize(300, 650);
		this.setLocationRelativeTo(null);
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
		
		JPanel panNumPromotion = new JPanel();
		JPanel panDescriptionPromotion = new JPanel();
		JPanel panPopulation = new JPanel();
		JPanel panCategoriePromotion = new JPanel();
		//JPanel panArticleConcerne = new JPanel();

		panNumPromotion.setPreferredSize(dimensionStandard);
		panDescriptionPromotion.setPreferredSize(dimensionStandard);
		panPopulation.setPreferredSize(dimensionStandard);
		panCategoriePromotion.setPreferredSize(dimensionStandard);
		
		panNumPromotion.setBorder(BorderFactory.createEmptyBorder());
		panDescriptionPromotion.setBorder(BorderFactory.createEmptyBorder());
		panPopulation.setBorder(BorderFactory.createEmptyBorder());
		panCategoriePromotion.setBorder(BorderFactory.createEmptyBorder());
		
		numPromotionLabel = new JLabel();
		descriptionLabel = new JLabel();
		populationLabel = new JLabel();
		categorieLabel = new JLabel();
		
		numPromotion = new JTextField();
		description = new JTextField();
		population = new JTextField();
		categoriePromoBox = new JComboBox();
		
		categoriePromoBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				itemCategoriePromoSelectionne=(String) ((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		
		panNumPromotion.add(numPromotionLabel);
		panDescriptionPromotion.add(descriptionLabel);
		panPopulation.add(populationLabel);
		panCategoriePromotion.add(categorieLabel);
		
		panNumPromotion.add(numPromotion);
		panDescriptionPromotion.add(description);
		panPopulation.add(population);
		panCategoriePromotion.add(categoriePromoBox);
		
		panneauCentralFenetre.add(panNumPromotion);
		panneauCentralFenetre.add(panDescriptionPromotion);
		panneauCentralFenetre.add(panPopulation);
		panneauCentralFenetre.add(panCategoriePromotion);
		
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
