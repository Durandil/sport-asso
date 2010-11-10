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

import metier.Message;


public class FenetreMessagerie extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public FenetreMessagerie(){
		super();
		this.setTitle("Boite de Réception");
		this.setSize(500, 900);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         

        
    private void initComponent(){
    	
    	// Création d'une table contenant tous les messages envoyés par les clients au gérant
    	// après interrogation de la base de données
	    JTable tableauMessage = new JTable(new ModeleMessagerie());     
	    this.getContentPane().add(new JScrollPane(tableauMessage), BorderLayout.CENTER);
    	
    	
    	//Création du panneau qui se situera en haut de la fenetre créée
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	// Création d'un "sous-panneau" accueillant tous les boutons relatifs aux actions
    	// que le gérant peut faire sur les messages de ses clients
    	JPanel panneauTitle=new JPanel();
    	JButton boutonAjouter=new JButton("Lire");
    	//JButton boutonSupprimer=new JButton("Supprimer");
    	JButton boutonSupprimerTout=new JButton("Supprimer tout");
    	
    	boutonSupprimerTout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Message.supprimerAllBDD(); //suppression message de la DB
				//tableauMessage.removeAllMessage(); // suppression message tableau
			}
		});
    	
    	
    	// Ajout de ses boutons au "sous-panneau" et de celui au "panneau du haut"
    	panneauTitle.add(boutonAjouter);
    	//panneauTitle.add(boutonSupprimer);
    	panneauTitle.add(boutonSupprimerTout);
    	panneauHaut.add(panneauTitle,"Center");
   
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
	    // Définition du panneau panneauBouton qui accueillera le bouton
	    // permettant de retourner à la page précédente 
	    JPanel panneauBouton=new JPanel();
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			// l'instruction permet de femrer la fenetre en cours	
			setVisible(false);
			}			
		});
			
		panneauBouton.add(retourBouton);
			
	
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }
	
}
