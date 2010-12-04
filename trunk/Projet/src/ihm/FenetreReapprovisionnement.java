package ihm;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;


public class FenetreReapprovisionnement extends JFrame {
	// Creer la base de donn�es correspondante aux articles 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel image;
	private JLabel titreLabel;


	public FenetreReapprovisionnement(){
		super();
		this.setTitle("Articles � commander");
		this.setSize(300,400);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         

        
    private void initComponent(){
    	
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new GridLayout(2,1,5,5));
    	
    	titreLabel = new JLabel("R�approvisionnement des articles en rupture de stock ou en quantit� insuffisante");
    	panneauHaut.add(titreLabel);
    	
    	// Ajout d'une image entre le tableau et l'introduction dans le haut de la fenetre
    	image = new JLabel(new ImageIcon("src/images/catalogue.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(image);
    	
    	panneauHaut.add(panIcon);
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	// TODO R�cup�ration du tableau avec l'ensemble ds articles en quantit� insuffisante 
    	// apr�s interroagtion de la base de donn�es dans ModeleTableauCatalogue
	    final JTable tableau = new JTable(new ModeleTableauCatalogue(true,true));
	    this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
	    
	    // Ajout d'un bouton permettant d'ouvrir une fenetre de commande apr�s avoir cliqu� 
	    // sur un article dans le tableau
	    JButton commandeBouton = new JButton("R�approvisionner");
	    commandeBouton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int ligne = tableau.getSelectedRow();
				String numeroArticle = tableau.getValueAt(ligne, 0).toString();
				FenetreCommandeReapprovisionnement fen = new FenetreCommandeReapprovisionnement(null, "Commande", true, numeroArticle);
				fen.setVisible(true);
				tableau.setEnabled(false);// emepecher le gerant de vouloir recommander imm�diatement un autre article
				// ou celui qu'il vient de r�approvisionner
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
		
		JPanel panneauBoutons = new JPanel();
		panneauBoutons.add(commandeBouton,"West");
		panneauBoutons.add(retourBouton,"East");
		
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }


	
	
}
