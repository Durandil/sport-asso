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
 * <b>FenetreReapprovisionnement permet g�rer le r�approvisionnement des articles en quantit� faible </b>
 * 
 * <p>Elle permet d'afficher une fen�tre dans laquelle le g�rant
 * peut visualiser et commander les articles en quantite insuffisante par rapport � leur cat�gorie
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
	 * Constructeur de la fenetre permettant de r�approvisionner les articles en faible quantit�.
	 * Les composants de la fen�tre sont initialis�s dans 
	 * {@link FenetreReapprovisionnement#initComponent()}.
	 */
	public FenetreReapprovisionnement(){
		super();
		this.setTitle("Articles � commander");
		this.setSize(300,400);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         
	
	/**
	 * <p>Initialisation des composants de la fen�tre :<ul>
	 * <li> un JPanel pour mettre un texte de pr�sentation de la fen�tre.</li>
	 * <li> un JPanel contenant un tableau qui affiche les articles.</li>
	 * <li> un JPanel contenant les deux boutons pour r�approvisionner un article.</li>
	 * </ul>
	 * </p>
	 * 
	 * @see {@link ModeleTableauCatalogue}
	 * @see {@link ModeleTableauCatalogue#ModeleTableauCatalogue(boolean, boolean)}
	 */
    private void initComponent(){
    	
    	// Cr�ation d'un JPanel pour mettre un texte introductif � la fen�tre //
    	//--------------------------------------------------------------------//
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new GridLayout(2,1,5,5));
    	
    	titreLabel = new JLabel("R�approvisionnement des articles en rupture de stock ou en quantit� insuffisante");
    	panneauHaut.add(titreLabel);
    	
    	// Ajout d'une image entre le tableau et l'introduction dans le haut de la fenetre //
    	//---------------------------------------------------------------------------------//
    	image = new JLabel(new ImageIcon("src/images/catalogue.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(image);
    	
    	panneauHaut.add(panIcon);
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	//R�cup�ration du tableau avec l'ensemble ds articles en quantit� insuffisante //
    	// apr�s interrogation de la base de donn�es dans ModeleTableauCatalogue       //
    	//-----------------------------------------------------------------------------//
    	final ModeleTableauCatalogue modele = new ModeleTableauCatalogue(true,true);
	    final JTable tableau = new JTable(modele);
	    this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
	    
	    // Ajout d'un bouton permettant d'ouvrir une fen�tre de commande apr�s avoir cliqu� 
	    // sur un article dans le tableau
	    JButton commandeBouton = new JButton("R�approvisionner");
	    commandeBouton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ligneTableau = tableau.getSelectedRow();
				
				if(ligneTableau==-1){
					JOptionPane.showMessageDialog(null, "Aucune ligne s�lectionn�e, veuillez en s�lectionner une","Attention",JOptionPane.ERROR_MESSAGE,new ImageIcon("src/images/warning.png"));
				}
				else{
					String numeroArticle = tableau.getValueAt(ligneTableau, 0).toString();
					FenetreCommandeReapprovisionnement fen = new FenetreCommandeReapprovisionnement(null, "Commande", true, numeroArticle);
					fen.setVisible(true);
					dispose();
				}
							
			}
		});
	    
	    
	    // Ajout du bouton permettant de revenir � la page pr�c�dante gr�ce � l'impl�menation
	    // de la m�thode ActionPerformed
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}			
		});
		
		// Cr�ation d'un JPanel pour accueillir les deux boutons cr�es au dessus//
		//----------------------------------------------------------------------//
		JPanel panneauBoutons = new JPanel();
		
		// Ajout des boutons au JPanel //
		//-----------------------------//
		panneauBoutons.add(commandeBouton,"West");
		panneauBoutons.add(retourBouton,"East");
		
		// Ajout du JPanel des boutons en bas de la fen�tre
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
		
	    pack();
	       
        
        }


	
	
}
