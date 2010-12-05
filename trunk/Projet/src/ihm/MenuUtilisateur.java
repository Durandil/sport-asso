package ihm;
import ihm.FenetreCommandeArticle;
import ihm.FenetreContactVendeur;
import ihm.FenetreDialogGestionCompteClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



public class MenuUtilisateur extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuCompte = new JMenu("Mon Compte");
	private JMenu menuCatalogue = new JMenu("Catalogue");
	private JMenu menuPromotions = new JMenu("Promotions");
	private JMenu menuContact = new JMenu("Contact");
		
	
	private JMenuItem itemFermer = new JMenuItem("Fermer compte utilisateur");
	private JMenuItem itemMesInformations = new JMenuItem("Mes informations");
	private JMenuItem itemProgFidelite = new JMenuItem("Mon programme fid�lit�");
	private JMenuItem itemMessagerieClient = new JMenuItem("Messagerie");
	private JMenuItem itemContact = new JMenuItem("Nous Contacter");
	private JMenuItem itemInformations = new JMenuItem("Informations");
	private JMenuItem itemArticles = new JMenuItem("Articles");
	private JMenuItem itemPromotions = new JMenuItem("Promotions en cours");
	private JLabel icon;
	
	// Cette variable static contiendra normalement le client en cours d'utilisation de l'application
	// pour le moment elle sert � faire fonctionner la fen�tre
	//public static Client client = new Particulier("julien","pic","jpic@orange.fr","3 rue du Vercors","Anncey","74000","0472157898",true);

	public MenuUtilisateur(){
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Bienvenue : "+ FenetreDialogIdentification.clientUserIdentifiant);
		
		this.setLayout(new BorderLayout());
		icon = new JLabel(new ImageIcon("src/images/nba.jpg"));
		JPanel panImage = new JPanel();
		panImage.setBackground(Color.white);
		panImage.setLayout(new BorderLayout());
		panImage.add(icon);
		
		this.getContentPane().add(panImage,"Center");
		this.repaint();
		this.pack();
		//On initialise nos sous-menus (JMenuItem) avec leurs actions correspondantes
		//--------------------------
			
		itemFermer.addActionListener(this);
		
		itemMesInformations.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//ouvrir la fenetre permettant acc�s au compte
				FenetreDialogGestionCompteClient fenetreGestionCompte =new FenetreDialogGestionCompteClient(null,"Informations client",true,FenetreDialogIdentification.clientUserIdentifiant);
				fenetreGestionCompte.setVisible(true);
			}
		});
		
		itemProgFidelite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//ouvrir la fenetre permettant acc�s au informations sur programme de fidelite
				 FenetreFideliteClient fenetreProgrammeFidelite =new  FenetreFideliteClient(null,"programme fidelit�",true);
				 fenetreProgrammeFidelite.setVisible(true);
			}
		});
		
		itemContact.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FenetreContactVendeur contactVendeur= new FenetreContactVendeur(null,"Nous Contacter",true);
				contactVendeur.setVisible(true);
			}
		});
		
		itemInformations.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				@SuppressWarnings("unused")
				FenetreInformationsClient contactVendeur= new FenetreInformationsClient();
			}
		});
		
		itemArticles.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FenetreCommandeArticle catalogue = new FenetreCommandeArticle();
				catalogue.setVisible(true);
			}
		});
		
		itemPromotions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FenetrePromotions promotion = new FenetrePromotions();
				promotion.setVisible(true);
			}
		});
		
		// Ajout des sous-menus � leur menu respectif dans l'ordre descendant que l'on d�sire 
		// � l'affichage 
		this.menuCompte.add(itemMesInformations);
		this.menuCompte.add(itemProgFidelite);
		this.menuCompte.add(itemMessagerieClient);
		this.menuCompte.add(itemFermer);
		
		this.menuCatalogue.add(itemArticles);
		
		this.menuPromotions.add(itemPromotions);
		
		this.menuContact.add(itemInformations);
		this.menuContact.add(itemContact);
			
	    //L'ordre d'ajout va d�terminer l'ordre d'apparition dans le menu de gauche � droite
	    //Le premier ajout� sera tout � gauche de la barre de menu et inversement pour le dernier
		this.menuBar.add(menuCompte);
		this.menuBar.add(menuCatalogue);
		this.menuBar.add(menuPromotions);
		this.menuBar.add(menuContact);
		//-------------------------
		
		//Ajout de la barre de menu � notre objet MenuUtilisateur
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == itemFermer){
			this.dispose();
		}
	}
	
	
}


