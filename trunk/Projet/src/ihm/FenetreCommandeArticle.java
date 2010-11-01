package ihm;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JComboBox comboBoxTri;

	
	/*
	 * Définition du constructeur de la classe qui va initialiser la fenetre selon les instructions de la méthode
	 * initComponent(). Cette classe permet l'affichage simultané du catalogue et du panier du client.
	 */
	
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
    	
    	// Définition du panneau qui ne contiendra que l'affichage de part et d'autre de la fenetre
    	// des intitulés des tableaux catalogie e panier
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
    	
    	
    	// Ajout du panneau au panneau "principal" en haut de la fenetre
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	// Définition du panneau qui accueillera le catalogue et un JComboBox de tri du tableau
    	// selon divers critères
    	JPanel panneauTableauCatalogue = new JPanel();
    	panneauTableauCatalogue.setLayout(new GridLayout(1,2,5,5));
    	
	    JTable tableau = new JTable(new ModeleTableauCatalogue());
	    tableau.setAutoCreateRowSorter(true); // permet de trier un tableau en cliquant sur la colonne
	    panneauTableauCatalogue.add(new JScrollPane(tableau));
	    
	    /*comboBoxTri = new JComboBox();
	    comboBoxTri.addItem("Trier par Prix");
	    comboBoxTri.addItem("Trier par sport");
	    comboBoxTri.addItem("Trier par nom d'article");
	    panneauTableauCatalogue.add(comboBoxTri);
	    */
	    
	    this.getContentPane().add(panneauTableauCatalogue, BorderLayout.WEST);
	    
	    // Définition du panneau qui accueillera les aricles du panier
	    JTable panier = new JTable(new ModelePanier());     
	    this.getContentPane().add(new JScrollPane(panier), BorderLayout.EAST);
	        
	    // Définition du panneau des boutons permettant la confirmation ou l'annulation de la commande en cours    
	    JPanel panneauBouton=new JPanel();
		JButton boutonValider=new JButton("Valider");
			
		boutonValider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// TODO il faudra modifier la base de données en fonction des quantités et articles achetés
				// et enregistrer la commande dans la table COMMANDE
				setVisible(false);
			}
		});
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// le bouton retour permet l'annulation de la commande en cours et le retour au menu utilisateur
				setVisible(false);
			}			
		});
			
		panneauBouton.add(boutonValider);
		panneauBouton.add(retourBouton);
			
	
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }
	
	
	
}
