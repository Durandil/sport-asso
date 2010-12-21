package ihm;
import ihm.Accueil.FenetreCompte;
import ihm.Accueil.FenetreDialogIdentification;
import ihm.Client.FenetreCommandeArticle;
import ihm.Client.FenetreContactVendeur;
import ihm.Client.FenetreDialogGestionCompteClient;
import ihm.Client.FenetreFideliteClient;
import ihm.Client.FenetreInformationsClient;
import ihm.Client.FenetrePromotions;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exception.Client.ExceptionMailsDifferents;



public class MenuUtilisateur extends JFrame {
	
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
	private JMenuItem itemProgFidelite = new JMenuItem("Mon programme fidélité");
	private JMenuItem itemMessagerieClient = new JMenuItem("Messagerie interne");
	private JMenuItem itemContact = new JMenuItem("Nous Contacter");
	private JMenuItem itemInformations = new JMenuItem("Informations");
	private JMenuItem itemArticles = new JMenuItem("Articles");
	private JMenuItem itemPromotions = new JMenuItem("Promotions en cours");
	private JLabel icon;

	
	public MenuUtilisateur(){
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
			
		itemFermer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				int res = JOptionPane.showConfirmDialog(null, "Confirmez-vous la déconnexion de votre compte ?","Confirmation",JOptionPane.YES_NO_OPTION);
				if(res==JOptionPane.OK_OPTION){
					dispose();
					try {
						FenetreCompte fen = new FenetreCompte();
					} catch (ExceptionMailsDifferents e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		itemMesInformations.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//ouvrir la fenetre permettant accès au compte
				FenetreDialogGestionCompteClient fenetreGestionCompte =new FenetreDialogGestionCompteClient(null,"Informations client",true,FenetreDialogIdentification.clientUserIdentifiant);
				fenetreGestionCompte.setVisible(true);
			}
		});
		
		itemProgFidelite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//ouvrir la fenetre permettant accès au informations sur programme de fidelite
				 FenetreFideliteClient fenetreProgrammeFidelite =new  FenetreFideliteClient(null,"programme fidelité",true);
				 fenetreProgrammeFidelite.setVisible(true);
			}
		});
		
		itemMessagerieClient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//ouvrir la fenetre permettant accès à la messagerie du client
				 FenetreMessagerie fenetre = new FenetreMessagerie(false);
				 fenetre.setVisible(true);
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
		
		// Ajout des sous-menus à leur menu respectif dans l'ordre descendant que l'on désire 
		// à l'affichage 
		this.menuCompte.add(itemMesInformations);
		this.menuCompte.add(itemProgFidelite);
		this.menuCompte.add(itemMessagerieClient);
		this.menuCompte.add(itemFermer);
		
		this.menuCatalogue.add(itemArticles);
		
		this.menuPromotions.add(itemPromotions);
		
		this.menuContact.add(itemInformations);
		this.menuContact.add(itemContact);
			
	    //L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
	    //Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier
		this.menuBar.add(menuCompte);
		this.menuBar.add(menuCatalogue);
		this.menuBar.add(menuPromotions);
		this.menuBar.add(menuContact);
		//-------------------------
		
		//Ajout de la barre de menu à notre objet MenuUtilisateur
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		}

}


