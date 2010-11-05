package ihm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;


public class FenetreCatalogueGerant extends JFrame{
	// Creer la base de donn�es correspondante aux articles 
	
	private JLabel icon;

	/**
	 * Cr�ation du constructeur de la classe FenetreCatalogueGerant
	 * la fenetre sera cr��e selon les instructions de la m�thode initComponent()
	 * 
	 */
	public FenetreCatalogueGerant(){
		super();
		this.setTitle("Gestion du Catalogue Article");
		this.setSize(500, 700);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         
	
        
    private void initComponent(){
    	
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new GridLayout(2,1,5,5));
    	
    	// R�cup�ration et ajout de l'image sur un panneau de la fenetre principale
    	icon = new JLabel(new ImageIcon("src/images/catalogue.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(icon);
    	
		// Cr�ation du panneau qui accueille les boutons du haut permettant la gestion des articles
    	JPanel panneauBoutonHaut= new JPanel();
    	panneauBoutonHaut.setLayout(new GridLayout(1,3,5,5));

    	JButton boutonAjouter=new JButton("Ajouter");
    	boutonAjouter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// ouverture du formulaire d'ajout d'un article dans la base de donnees
				FenetreFormulaireArticleGerant formulaire = new FenetreFormulaireArticleGerant(null,"Ajout d'article",true);
			}
		});
    	
    	JButton boutonSupprimer=new JButton("Supprimer");
    	boutonSupprimer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// suppression de l'article selectionn� dans le catalogue
				
			}
		});
    	
    	JButton boutonModifier=new JButton("Modifier");
    	boutonModifier.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// modification de l'article selectionn� dans la catalogue
				
			}
		});
    	
    	
    	// Ajout des boutons sur ce panneau
    	panneauBoutonHaut.add(boutonAjouter);
    	panneauBoutonHaut.add(boutonModifier);
    	panneauBoutonHaut.add(boutonSupprimer);
    	
    	panneauHaut.add(panIcon);
    	panneauHaut.add(panneauBoutonHaut);
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	// D�finition du tableau qui accueillera l'ensemble des articles disponibles
    	// apr�s interrogation de la base de donn�es
	    JTable tableau = new JTable(new ModeleTableauCatalogue(false));
	    this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
	    
	    // D�finition du panneau qui contiendra les boutons de confirmation et de retour � la page pr�c�dente
	    JPanel panneauBouton=new JPanel();
		JButton boutonValider=new JButton("Confirmer");
			
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
			
		// Ajout du panneau des boutons au "panneau principal" qui h�berge tous les autres panneaux
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }
	
	
	
}

