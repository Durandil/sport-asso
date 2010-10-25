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


public class FenetreMessagerie extends JFrame {
	

	public FenetreMessagerie(){
		super();
		this.setTitle("Boite de R�ception");
		this.setSize(500, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         

        
    private void initComponent(){
    	
    	//Cr�ation du panneau qui se situera en haut de la fenetre cr��e
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	// Cr�ation d'un "sous-panneau" accueillant tous les boutons relatifs aux actions
    	// que le g�rant peut faire sur les messages de ses clients
    	JPanel panneauTitle=new JPanel();
    	JButton boutonAjouter=new JButton("Lire");
    	JButton boutonSupprimer=new JButton("Supprimer");
    	JButton boutonModifier=new JButton("Supprimer tout");
    	
    	// Ajout de ses boutons au "sous-panneau" et de celui au "panneau du haut"
    	panneauTitle.add(boutonAjouter);
    	panneauTitle.add(boutonSupprimer);
    	panneauTitle.add(boutonModifier);
    	panneauHaut.add(panneauTitle,"Center");
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	// Cr�ation d'une table contenant tous les messages envoy�s par les clients au g�rant
    	// apr�s interrogation de la base de donn�es
	    JTable tableauMessage = new JTable(new ModeleMessagerie());     
	    this.getContentPane().add(new JScrollPane(tableauMessage), BorderLayout.CENTER);
	    
	    
	    // D�finition du panneau panneauBouton qui accueillera le bouton
	    // permettant de retourner � la page pr�c�dente 
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
