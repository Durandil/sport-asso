package ihm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;


public class FenetreReapprovisionnement extends JFrame {
	// Creer la base de données correspondante aux articles 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel image;
	private JLabel titreLabel;
	public static boolean doitReactualiserTableau = false;
	public static int ligneTableau=0;

	public FenetreReapprovisionnement(){
		super();
		this.setTitle("Articles à commander");
		this.setSize(300,400);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         

        
    private void initComponent(){
    	
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new GridLayout(2,1,5,5));
    	
    	titreLabel = new JLabel("Réapprovisionnement des articles en rupture de stock ou en quantité insuffisante");
    	panneauHaut.add(titreLabel);
    	
    	// Ajout d'une image entre le tableau et l'introduction dans le haut de la fenetre
    	image = new JLabel(new ImageIcon("src/images/catalogue.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(image);
    	
    	panneauHaut.add(panIcon);
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	// TODO Récupération du tableau avec l'ensemble ds articles en quantité insuffisante 
    	// après interroagtion de la base de données dans ModeleTableauCatalogue
    	final ModeleTableauCatalogue modele = new ModeleTableauCatalogue(true,true);
	    final JTable tableau = new JTable(modele);
	    this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
	    
	    // Ajout d'un bouton permettant d'ouvrir une fenetre de commande après avoir cliqué 
	    // sur un article dans le tableau
	    JButton commandeBouton = new JButton("Réapprovisionner");
	    commandeBouton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(doitReactualiserTableau==false){
					ligneTableau = tableau.getSelectedRow();
					String numeroArticle = tableau.getValueAt(ligneTableau, 0).toString();
					FenetreCommandeReapprovisionnement fen = new FenetreCommandeReapprovisionnement(null, "Commande", true, numeroArticle);
					fen.setVisible(true);
				}
				
				
			}
		});
	    
	    
	    // Ajout du bouton permettant de revenir à la page précédante grâce à l'implémenation
	    // de la méthode ActionPerformed
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}			
		});
		
		
		JButton reactualiserBouton = new JButton("Réactualiser tableau");
		reactualiserBouton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(doitReactualiserTableau==true){
					// TODO méthode pour reactualiser le tableau
					modele.updateLigne(modele.getRowCount(),true);
					// change la valeur du booleen doitReactualiserTableau car la reactualisation
					// a été effectuée
					doitReactualiserTableau=false;
				}
			}
		});
		
		
		JPanel panneauBoutons = new JPanel();
		panneauBoutons.add(commandeBouton,"West");
		panneauBoutons.add(retourBouton,"East");
		panneauBoutons.add(reactualiserBouton,"Center");
		
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }


	
	
}
