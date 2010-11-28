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
    	
    	// D�finition du tableau qui affichera l'ensemble des promotions en cours pour les diff�rents clients
    	// (adh�rents ou non adh�rents) apr�s interrogation de la base de donn�es dans ModelePromotionClient
	    JTable tableauPromotions = new JTable(new ModelePromotionClient());     
	    this.getContentPane().add(new JScrollPane(tableauPromotions), BorderLayout.CENTER);
	    
	    //Cr�ation du panneau qui se situera en haut de la fenetre cr��e
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	// Cr�ation d'un "sous-panneau" accueillant tous les boutons relatifs aux actions
    	// que le g�rant peut faire sur les promotions en cours : soit en ajouter, soit en modifier soit en supprimer
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
				// TODO supprimer la promotion selectionn�e dans la base de donn�e
				//Promotion.supprimerPromoBDD(idPromotion)
			}
		});
    	
    	boutonModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO modifier la promotion s�lection�e gr�ce � un formulaire
				
			}
		});
    	
    	// Ajout de ses boutons au "sous-panneau" et de celui au "panneau du haut"
    	panneauTitle.add(boutonAjouter);
    	panneauTitle.add(boutonSupprimer);
    	panneauTitle.add(boutonModifier);
    	panneauHaut.add(panneauTitle,"Center");
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
	    
	    
	    // D�finition du panneau panneauBouton qui accueillera le bouton
	    // permettant de retourner � la page pr�c�dente 
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

