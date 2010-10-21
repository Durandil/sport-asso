package ihm;
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


public class FenetreCommandeArticle extends JFrame{
	// Creer la base de données correspondante aux articles 
	
	private JLabel catalogueLabel;
	private JLabel panierLabel;


	public FenetreCommandeArticle(){
		super();
		this.setTitle("Catalogue Article");
		this.setSize(500, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         

        
    private void initComponent(){
    	
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	JPanel panneauCatalogue=new JPanel();
    	catalogueLabel= new JLabel("CATALOGUE");
    	panneauCatalogue.add(catalogueLabel);
    	panneauHaut.add(panneauCatalogue,"West");
    	
    	
    	JPanel panneauPanier=new JPanel();
    	panierLabel= new JLabel("PANIER");
    	panneauPanier.add(panierLabel);
    	panneauHaut.add(panneauPanier,"East");
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
	    JTable tableau = new JTable(new ModeleTableauCatalogue());     
	    this.getContentPane().add(new JScrollPane(tableau), BorderLayout.WEST);
	    
	    JTable panier = new JTable(new ModelePanier());     
	    this.getContentPane().add(new JScrollPane(panier), BorderLayout.EAST);
	        
	        
	    JPanel panneauBouton=new JPanel();
		JButton boutonValider=new JButton("Valider");
			
		boutonValider.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
				setVisible(false);
			}
		});
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
			
		panneauBouton.add(boutonValider);
		panneauBouton.add(retourBouton);
			
	
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }
	
	
	
}
