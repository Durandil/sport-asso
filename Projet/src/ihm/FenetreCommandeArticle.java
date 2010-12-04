package ihm;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import metier.Commande;

import basededonnees.SGBD;


public class FenetreCommandeArticle extends JFrame{
	// Creer la base de données correspondante aux articles 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel catalogueLabel;
	private JLabel panierLabel;
	public static ArrayList<String[]> panierClient = Commande.preparerPanier();
	// pour conserver la derniere ligne modifiée du catalogue en cas ajout article panier
	private static int ligneCatalogue ;
	// pour conserver la dernière ligne modifiée du panier en cas de retrait article panier
	private static int lignePanier ;
	
	/*
	 * Définition du constructeur de la classe qui va initialiser la fenetre selon les instructions de la méthode
	 * initComponent(). Cette classe permet l'affichage simultané du catalogue et du panier du client.
	 */
	
	public FenetreCommandeArticle(){
		super();
		this.setTitle("Catalogue Article");
		this.setSize(500, 1000);
		this.setLocation(50,50);
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
    	
	    final JTable tableau = new JTable(new ModeleTableauCatalogue(false,false));
	    panneauTableauCatalogue.add(new JScrollPane(tableau),"North");
	    
	    this.getContentPane().add(panneauTableauCatalogue, BorderLayout.WEST);
	    
	    // Définition du panneau qui accueillera les aricles du panier
	    final ModelePanier modPan = new ModelePanier(panierClient);
	    final JTable panier = new JTable(modPan);     
	    this.getContentPane().add(new JScrollPane(panier), BorderLayout.EAST);
	        
	    // Définition du panneau des boutons permettant la confirmation ou l'annulation de la commande en cours    
	    JPanel panneauBouton=new JPanel();
	    
	    
	    JButton commanderArticle = new JButton("Chosir un article");
	    commanderArticle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ligneCatalogue = tableau.getSelectedRow();
				Object numeroArticle = tableau.getValueAt(ligneCatalogue, 0);
				String numArticle = numeroArticle.toString();
				
				int indiceQuantitePanier= Commande.rechercheArticleDansPanier(numArticle, panierClient);
				int quantitePanier= Integer.parseInt(panierClient.get(indiceQuantitePanier)[1]);
				
				String quantiteStock=SGBD.selectStringConditionString("ARTICLE", "STOCK", "IDARTICLE",numArticle);
				FenetreChoixCatalogue fen = new FenetreChoixCatalogue(null,"Achat d'article",true,Integer.parseInt(quantiteStock)-quantitePanier,numArticle);
				fen.setVisible(true);
			}
		});
	    
		JButton boutonValider=new JButton("Valider");
			
		boutonValider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// TODO il faudra modifier la base de données en fonction des quantités et articles achetés
				// et enregistrer la commande dans la table COMMANDE
				// puis vider le panier
				setVisible(false);
				
				
			}
		});
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// le bouton retour permet l'annulation de la commande en cours et le retour au menu utilisateur
				setVisible(false);
				// vider le panier une fois que le client a appuyé sur le bouton
				Commande.viderPanier(panierClient);
			}			
		});
		
		JButton rafraichirPanierBouton = new JButton("Rafraichir le panier");
		rafraichirPanierBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// le bouton permet de rafraichir le tableau panier
				modPan.actualiserLigne(ligneCatalogue, panierClient);
				modPan.fireTableDataChanged();
				System.out.println("Dernière Ligne modifiée : "+ligneCatalogue);
			}			
		});
		
		JButton retirerPanierBouton = new JButton("Retirer un article du panier");
		retirerPanierBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// le bouton permet d'afficher la fenetre de retrait article du panier
				lignePanier = tableau.getSelectedRow();
				Object numeroArticle = tableau.getValueAt(lignePanier, 0);
				String numArticle = numeroArticle.toString();
				
				String quantitePanier = panier.getValueAt(lignePanier, 1).toString();
				//FenetreSuppressionPanier fenetreRetrait = new FenetreSuppressionPanier(null, "Retrait d'article du panier", true, lignePanier);
				//fenetreRetrait.setVisible(true);
			}			
		});
		
		panneauBouton.add(commanderArticle);
		panneauBouton.add(boutonValider);
		panneauBouton.add(retourBouton);
		panneauBouton.add(rafraichirPanierBouton);
			
	
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
  
	    pack();

        }
	
	
}
