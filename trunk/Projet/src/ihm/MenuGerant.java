package ihm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import basededonnees.SGBD;


public class MenuGerant extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuClientele = new JMenu("Client�le");
	private JMenu menuStock = new JMenu("Stock");

	private JMenuItem itemGestionCompte = new JMenuItem("Gestion Compte Client");
	private JMenuItem itemMessagerie = new JMenuItem("Messagerie");
	private JMenuItem itemFermer = new JMenuItem("Fermer");
	private JMenuItem itemArticles = new JMenuItem("Articles");
	private JMenuItem itemCommandes = new JMenuItem("Commandes");
	private JMenuItem itemPromotions = new JMenuItem("Promotions");

	private JLabel icon;

	public MenuGerant(){
		this.setSize(420, 330);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		icon = new JLabel(new ImageIcon("src/images/ligue.jpg"));
		JPanel panImage = new JPanel();
		panImage.setBackground(Color.white);
		panImage.setLayout(new BorderLayout());
		panImage.add(icon);
		
		this.getContentPane().add(panImage,"Center");
		
		// il faut, pour chaque item, lui impl�menter son actionPerformed correspondant
		// � la fenetre qu'il faudra ouvrir 
		
		itemGestionCompte.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre de recherche d'un client
				FenetreRechercheClient recherche = new FenetreRechercheClient(null, "Recherche de clients", true);
				recherche.setVisible(true);
				ArrayList<String> liste = new ArrayList<String>();
				
				//liste = SGBD.recupererAttributClient("qpuc@ensai.fr");
				//for (String element : liste) {
				//	System.out.println(element);
				//}
				
			}
		});
		
		itemMessagerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre contenant la boite de reception interne du g�rant
				FenetreMessagerie message = new FenetreMessagerie();
				message.setVisible(true);
			}
		});
		
		itemFermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}				
		});
		
		itemArticles.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre avec la derni�re version du catalogue client
				FenetreCatalogueGerant fenetreCatalogue = new FenetreCatalogueGerant();
				fenetreCatalogue.setVisible(true);
			}
		});
		
		itemCommandes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre contenant le tableau des articles en rupture de stock 
				// et/ou � commander 
				FenetreReapprovisionnement fenetreStock = new FenetreReapprovisionnement();
				fenetreStock.setVisible(true);
			}
		});
		
		itemPromotions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				//ouvrir la fenetre permettant acc�s au compte
				FenetrePromotionsGerant fenetrePromo = new FenetrePromotionsGerant();
				fenetrePromo.setVisible(true);
			}
		});
		
		// Ajout des diff�rents sous-menus � leur menu correspondant comme cela a �t� d�fini
		// dans la maquette graphique des interfaces
		this.menuClientele.add(itemGestionCompte);
		this.menuClientele.add(itemMessagerie);
		this.menuClientele.add(itemFermer);
		this.menuStock.add(itemArticles);
		this.menuStock.add(itemCommandes);
		this.menuStock.add(itemPromotions);
		
		// Ajout des menus � l'objet barre de menus qui contient tous les menus
		this.menuBar.add(menuClientele);
		this.menuBar.add(menuStock);
		
		//Ajout de la barre de menus � notre objet MenuGerant
		this.setJMenuBar(menuBar);
		// Enfin on rend visible l'objet
		this.setVisible(true);
		
			
	}
}
