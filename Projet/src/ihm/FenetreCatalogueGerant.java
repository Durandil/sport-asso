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
	// Creer la base de données correspondante aux articles 
	
	private JLabel icon;


	/**
	 * Création du constructeur de la classe FenetreCatalogueGerant
	 * la fenetre sera créée selon les instructions de la méthode initComponent()
	 * 
	 */
	public FenetreCatalogueGerant(){
		super();
		this.setTitle("Gestion du Catalogue Article");
		this.setSize(500, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         
	
        
    private void initComponent(){
    	
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new GridLayout(2,1,5,5));
    	
    	// Récupération et ajout de l'image sur un panneau de la fenetre principale
    	icon = new JLabel(new ImageIcon("src/images/catalogue.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(icon);
    	
		// Création du panneau qui accueille les boutons du haut permettant la gestion des articles
    	JPanel panneauBoutonHaut= new JPanel();
    	panneauBoutonHaut.setLayout(new GridLayout(1,3,5,5));

    	JButton boutonAjouter=new JButton("Ajouter");
    	JButton boutonSupprimer=new JButton("Supprimer");
    	JButton boutonModifier=new JButton("Modifier");
    	
    	// Ajout des boutons sur ce panneau
    	panneauBoutonHaut.add(boutonAjouter);
    	panneauBoutonHaut.add(boutonModifier);
    	panneauBoutonHaut.add(boutonSupprimer);
    	
    	panneauHaut.add(panIcon);
    	panneauHaut.add(panneauBoutonHaut);
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	// Définition du tableau qui accueillera l'ensemble des articles disponibles
    	// après interrogation de la base de données
	    JTable tableau = new JTable(new ModeleTableauCatalogue());
	    this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
	    
	    // Définition du panneau qui contiendra les boutons de confirmation et de retour à la page précédente
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
			
		// Ajout du panneau des boutons au "panneau principal" qui héberge tous les autres panneaux
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }
	
	
	
}

