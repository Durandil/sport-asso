package ihm;
import ihm.ModelePromotionClient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import metier.Promotion;


public class FenetrePromotionsGerant extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public FenetrePromotionsGerant(){
		super();
		this.setTitle("Promotions en cours");
		this.setSize(500, 600);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         

        
    private void initComponent(){
    	
    	// Définition du tableau qui affichera l'ensemble des promotions en cours pour les différents clients
    	// (adhérents ou non adhérents) après interrogation de la base de données dans ModelePromotionClient
	    JTable tableauPromotions = new JTable(new ModelePromotionClient());     
	    this.getContentPane().add(new JScrollPane(tableauPromotions), BorderLayout.CENTER);
	    
	    //Création du panneau qui se situera en haut de la fenetre créée
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	// Création d'un "sous-panneau" accueillant tous les boutons relatifs aux actions
    	// que le gérant peut faire sur les promotions en cours : soit en ajouter, soit en modifier soit en supprimer
    	JPanel panneauTitle=new JPanel();
    	JButton boutonAjouter=new JButton("Ajouter");
    	JButton boutonSupprimer=new JButton("Supprimer");
    	JButton boutonModifier=new JButton("Modifier");
    	
    	boutonAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Ajout d'une promotion
				FenetreFormulairePromotionsGerant nouvellePromo = new FenetreFormulairePromotionsGerant(null,"Ajout d'une nouvelle promotion",true);
				nouvellePromo.setVisible(true);
			}
		});
    	
    	boutonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO supprimer la promotion selectionnée dans la base de donnée
				//Promotion.supprimerPromoBDD(idPromotion)
			}
		});
    	
    	boutonModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO modifier la promotion sélectionée grâce à un formulaire
				
			}
		});
    	
    	// Ajout de ses boutons au "sous-panneau" et de celui au "panneau du haut"
    	panneauTitle.add(boutonAjouter);
    	panneauTitle.add(boutonSupprimer);
    	panneauTitle.add(boutonModifier);
    	panneauHaut.add(panneauTitle,"Center");
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
	    
	    
	    // Définition du panneau panneauBouton qui accueillera le bouton
	    // permettant de retourner à la page précédente 
	    JPanel panneauBouton=new JPanel();
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
			
		panneauBouton.add(retourBouton);
	
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }
	
}

