package ihm.Gerant;
import ihm.modeleTableau.ModeleTableauCatalogue;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

/**
 * <b>FenetreReapprovisionnement permet gérer le réapprovisionnement des articles en quantité faible </b>
 * 
 * <p>Elle permet d'afficher une fenêtre dans laquelle le gérant
 * peut visualiser et commander les articles en quantite insuffisante par rapport à leur catégorie
 * ou en rupture de stock. La liste des articles est visible dans un tableau.</p>
 * @author Utilisateur
 *
 *@see {@link FenetreReapprovisionnement#FenetreReapprovisionnement()}
 *@see {@link FenetreReapprovisionnement#initComponent()}
 *
 */
public class FenetreReapprovisionnement extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel image;
	private JLabel titreLabel;
	public static int ligneTableau=0;
	
	/**
	 * Constructeur de la fenetre permettant de réapprovisionner les articles en faible quantité.
	 * Les composants de la fenêtre sont initialisés dans 
	 * {@link FenetreReapprovisionnement#initComponent()}.
	 */
	public FenetreReapprovisionnement(){
		super();
		this.setTitle("Articles à commander");
		this.setSize(300,400);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         
	
	/**
	 * <p>Initialisation des composants de la fenêtre :<ul>
	 * <li> un JPanel pour mettre un texte de présentation de la fenêtre.</li>
	 * <li> un JPanel contenant un tableau qui affiche les articles.</li>
	 * <li> un JPanel contenant les deux boutons pour réapprovisionner un article.</li>
	 * </ul>
	 * </p>
	 * 
	 * @see {@link ModeleTableauCatalogue}
	 * @see {@link ModeleTableauCatalogue#ModeleTableauCatalogue(boolean, boolean)}
	 */
    private void initComponent(){
    	
    	// Création d'un JPanel pour mettre un texte introductif à la fenêtre //
    	//--------------------------------------------------------------------//
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new GridLayout(2,1,5,5));
    	
    	titreLabel = new JLabel("Réapprovisionnement des articles en rupture de stock ou en quantité insuffisante");
    	panneauHaut.add(titreLabel);
    	
    	// Ajout d'une image entre le tableau et l'introduction dans le haut de la fenetre //
    	//---------------------------------------------------------------------------------//
    	image = new JLabel(new ImageIcon("src/images/catalogue.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(image);
    	
    	panneauHaut.add(panIcon);
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	//Récupération du tableau avec l'ensemble ds articles en quantité insuffisante //
    	// après interrogation de la base de données dans ModeleTableauCatalogue       //
    	//-----------------------------------------------------------------------------//
    	final ModeleTableauCatalogue modele = new ModeleTableauCatalogue(true,true);
	    final JTable tableau = new JTable(modele);
	    this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
	    
	    // Ajout d'un bouton permettant d'ouvrir une fenêtre de commande après avoir cliqué 
	    // sur un article dans le tableau
	    JButton commandeBouton = new JButton("Réapprovisionner");
	    commandeBouton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ligneTableau = tableau.getSelectedRow();
				
				if(ligneTableau==-1){
					JOptionPane.showMessageDialog(null, "Aucune ligne sélectionnée, veuillez en sélectionner une","Attention",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/images/warning.png"));
				}
				else{
					String numeroArticle = tableau.getValueAt(ligneTableau, 0).toString();
					FenetreCommandeReapprovisionnement fen = new FenetreCommandeReapprovisionnement(null, "Commande", true, numeroArticle);
					fen.setVisible(true);
					dispose();
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
		
		// Création d'un JPanel pour accueillir les deux boutons crées au dessus//
		//----------------------------------------------------------------------//
		JPanel panneauBoutons = new JPanel();
		
		// Ajout des boutons au JPanel //
		//-----------------------------//
		panneauBoutons.add(commandeBouton,"West");
		panneauBoutons.add(retourBouton,"East");
		
		// Ajout du JPanel des boutons en bas de la fenêtre
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
		
	    pack();
	       
        
        }


	
	
}
