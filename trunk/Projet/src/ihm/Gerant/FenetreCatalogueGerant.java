package ihm.Gerant;
import ihm.modeleTableau.ModeleTableauCatalogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import metier.Article;


public class FenetreCatalogueGerant extends JFrame{
	// Creer la base de données correspondante aux articles 
	
	private JLabel icon;
	private static ArrayList<Integer> numerosLignesInserees = new ArrayList<Integer>();
	private static ArrayList<Integer> numerosLignesSupprimees = new ArrayList<Integer>();
	public ModeleTableauCatalogue modTabCatalogue = new ModeleTableauCatalogue();
	public JTable tableau = new JTable();
	public static boolean modificationTableau=false;
	public JPanel panneauTableau= new JPanel();
	
	/**
	 * Création du constructeur de la classe FenetreCatalogueGerant
	 * la fenetre sera créée selon les instructions de la méthode initComponent()
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
    	
    	final JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new GridLayout(2,1,5,5));
    	
    	// Récupération et ajout de l'image sur un panneau de la fenetre principale
    	icon = new JLabel(new ImageIcon("src/images/catalogue.jpg"));
		final JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(icon);
    	
		// Définition du tableau qui accueillera l'ensemble des articles disponibles
    	// après interrogation de la base de données
		
		modTabCatalogue = new ModeleTableauCatalogue(false,true);
	    tableau = new JTable(modTabCatalogue);

	    final JScrollPane tab = new JScrollPane(tableau);
	    panneauTableau.add(tab);
	    this.getContentPane().add(panneauTableau, BorderLayout.CENTER);
		
		
		// Création du panneau qui accueille les boutons du haut permettant la gestion des articles
    	final JPanel panneauBoutonHaut= new JPanel();
    	panneauBoutonHaut.setLayout(new GridLayout(1,4,5,5));
    	
    	final JButton boutonAjouter=new JButton("Ajouter");
    	boutonAjouter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ouverture du formulaire d'ajout d'un article dans la base de donnees
				
				FenetreFormulaireArticleGerant formulaire = new FenetreFormulaireArticleGerant(null,"Ajout d'article",true);
				formulaire.setVisible(true);
				System.out.println("nombre lignes tableau avant ajout "+modTabCatalogue.getRowCount());
				
				modTabCatalogue.ajouterLigne();
				modTabCatalogue.fireTableRowsInserted(modTabCatalogue.getRowCount(),modTabCatalogue.getRowCount());
				
				/** solution alternative si on ne résoud pas le pb de l'affichage :
				
				setVisible(false);
				
				**/
				
//				if(modificationTableau==true){
//					panneauTableau.remove(tab);
//					modTabCatalogue = new ModeleTableauCatalogue(false,true);
//				    tableau = new JTable(modTabCatalogue);
//				    final JScrollPane tab = new JScrollPane(tableau);
//				    panneauTableau.add(tab);
//				}
				
				
			}
		});
    	

    	JButton boutonSupprimer=new JButton("Supprimer");
    	boutonSupprimer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// suppression de l'article selectionné dans le catalogue
				
				int ligne = tableau.getSelectedRow();
				String numArticle= tableau.getValueAt(ligne, 0).toString();

				Article.supprimerArticleBDD(numArticle);
//				
				//modTabCatalogue = new ModeleTableauCatalogue();
				modTabCatalogue.actualiserTableau(false);
//				modTabCatalogue.fireTableRowsDeleted(ligne, ligne);
//				modTabCatalogue.fireTableDataChanged();
//				numerosLignesSupprimees.add(ligne);
				
//				JOptionPane supprime = new JOptionPane();
//				ImageIcon image = new ImageIcon("src/images/information.png");
//				supprime.showMessageDialog(null, "L'article sera supprimé quand vous aurez fermé la fenêtre", "Information", JOptionPane.INFORMATION_MESSAGE, image);
//				tableau.removeRowSelectionInterval(ligne, ligne);

				/** solution alternative si on ne résoud pas le pb de l'affichage :
				
				setVisible(false);
				
				**/
				
				repaint();
				
			}
		});
    	
    	JButton boutonModifier=new JButton("Modifier");
    	boutonModifier.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// modification de l'article selectionné dans la catalogue
				int ligne = tableau.getSelectedRow();
				String numArticle = "";
				int numeroArticle=0;
				
				try{
					numeroArticle=Integer.parseInt(tableau.getValueAt(ligne, 0).toString());
					
					if(numeroArticle<10){
						 numArticle= "ART0000" + numeroArticle;
					}
					if(numeroArticle<100 && numeroArticle>=10){
						numArticle= "ART000" + tableau.getValueAt(ligne, 0).toString();
					}
					if(numeroArticle<1000 && numeroArticle>=100){
						numArticle= "ART00" + tableau.getValueAt(ligne, 0).toString();
					}
					if(numeroArticle<10000 && numeroArticle>=1000){
						numArticle= "ART0" + tableau.getValueAt(ligne, 0).toString();
					}
					if(numeroArticle<100000 && numeroArticle>=10000){
						numArticle= "ART" + tableau.getValueAt(ligne, 0).toString();
					}
					
				}
				catch(NumberFormatException event){
					numArticle=tableau.getValueAt(ligne, 0).toString();
				}
				finally{
					FenetreFormulaireArticleGerant formulaire = new FenetreFormulaireArticleGerant(null,"Modifier l'article "+numArticle,true,numArticle);
					formulaire.setVisible(true);
					/** solution alternative si on ne résoud pas le pb de l'affichage :
					
					setVisible(false);
					
					**/
				}
				
			}
			
		});
    	
    	
    	// Ajout des boutons sur ce panneau
    	panneauBoutonHaut.add(boutonAjouter);
    	panneauBoutonHaut.add(boutonModifier);
    	panneauBoutonHaut.add(boutonSupprimer);
    	
    	panneauHaut.add(panIcon);
    	panneauHaut.add(panneauBoutonHaut);
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
	    
	    // Définition du panneau qui contiendra les boutons de reactualisation du tableau et de retour à la page précédente
	    JPanel panneauBouton=new JPanel();
	    JButton boutonRafraichirTableau= new JButton("Rafraichir le catalogue");
			
		boutonRafraichirTableau.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				//modTabCatalogue.actualiserTableau(false);
				//modTabCatalogue.fireTableDataChanged();
				//modTabCatalogue.fireTableStructureChanged();
				
				// on met à jour toutes les lignes qui ont été insérées
//				for(int i=0; i<numerosLignesInserees.size();i++){
//					modTabCatalogue.fireTableRowsInserted(numerosLignesInserees.get(i), numerosLignesInserees.get(i));
//				}
//				// puis on met à jour toutes les lignes supprimées
//				for(int i=0; i<numerosLignesSupprimees.size();i++){
//					modTabCatalogue.fireTableRowsDeleted(numerosLignesSupprimees.get(i), numerosLignesSupprimees.get(i));
//				}
//				// on vide les vecteurs quand on a mis à jour le tableau
//				for(int i=0; i<numerosLignesInserees.size();i++){
//					numerosLignesInserees.remove(i);
//				}
//				
//				for(int i=0; i<numerosLignesSupprimees.size();i++){
//					numerosLignesSupprimees.remove(i);
//				}
//			
			}
		});
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}			
		});
			
		//panneauBouton.add(boutonRafraichirTableau);
		panneauBouton.add(retourBouton);
			
		// Ajout du panneau des boutons au "panneau principal" qui héberge tous les autres panneaux
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }
	
	
	
}

