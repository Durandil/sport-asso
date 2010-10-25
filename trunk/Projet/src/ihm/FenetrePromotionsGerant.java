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


public class FenetrePromotionsGerant extends JFrame {
	

	public FenetrePromotionsGerant(){
		super();
		this.setTitle("Promotions en cours");
		this.setSize(500, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         

        
    private void initComponent(){
    	
    	//Cr�ation du panneau qui se situera en haut de la fenetre cr��e
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	// Cr�ation d'un "sous-panneau" accueillant tous les boutons relatifs aux actions
    	// que le g�rant peut faire sur les promotions en cours : soit en ajouter, soit en modifier soit en supprimer
    	JPanel panneauTitle=new JPanel();
    	JButton boutonAjouter=new JButton("Ajouter");
    	JButton boutonSupprimer=new JButton("Supprimer");
    	JButton boutonModifier=new JButton("Modifier");
    	
    	// Ajout de ses boutons au "sous-panneau" et de celui au "panneau du haut"
    	panneauTitle.add(boutonAjouter);
    	panneauTitle.add(boutonSupprimer);
    	panneauTitle.add(boutonModifier);
    	panneauHaut.add(panneauTitle,"Center");
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	// D�finition du tableau qui affichera l'ensemble des promotions en cours pour les diff�rents clients
    	// (adh�rents ou non adh�rents) apr�s interrogation de la base de donn�es dans ModelePromotionClient
	    JTable tableauPromotions = new JTable(new ModelePromotionClient());     
	    this.getContentPane().add(new JScrollPane(tableauPromotions), BorderLayout.CENTER);
	    
	    
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

