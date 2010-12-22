package ihm;

import ihm.modeleTableau.ModeleMessagerie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import metier.Message;

/**
 * La classe FenetreMessagerie permet aux diff�rents utilisateurs d'avoir acc�s � une boite 
 * de r�ception pour pallier au fait que JavaMail ne fonctionne pas � l'Ensai. De cette
 * boite de r�ception, l'utilisateur peut acc�der � d'autres fen�tres permettant de
 * lire ses messages, les supprimer ou d'y r�pondre.
 * 
 * @see FenetreLectureMessage
 * @see FenetreReponseMessage
 * 
 * @author Utilisateur
 *
 */
public class FenetreMessagerie extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de la classe {@link FenetreMessagerie} initialis�e gr�ce � la m�thode  
	 * @link {@link FenetreMessagerie#initComponent(boolean)}
	 * 
	 * @param boiteMessagerieGerant
	 * 				Bool�en indiquant quel utilisateur utilise la fen�tre (true) pour le g�rant
	 * 				et (false) pour les clients
	 * 
	 */
	public FenetreMessagerie(boolean boiteMessagerieGerant){
		super();
		this.setTitle("Boite de R�ception ");
		this.setSize(500, 900);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(boiteMessagerieGerant);
	}         

        
    private void initComponent(boolean messagerieGerant){
    	final boolean messagerieDuGerant = messagerieGerant;
    	
    	// Cr�ation d'une table contenant tous les messages envoy�s par les clients au g�rant
    	// apr�s interrogation de la base de donn�es
    	final ModeleMessagerie modele =  new ModeleMessagerie(messagerieGerant);
	    final JTable tableauMessage = new JTable(modele);  
	    final JScrollPane tab = new JScrollPane(tableauMessage);
	    this.getContentPane().add(tab, BorderLayout.CENTER);
    	
    	
    	//Cr�ation du panneau qui se situera en haut de la fenetre cr��e
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	/**
    	 * Cr�ation d'un "sous-panneau" accueillant tous les boutons relatifs aux actions
    	 * que le g�rant peut faire sur les messages de ses clients, qui sera ins�r� dans panneauHaut
    	 */
    	JPanel panneauTitle=new JPanel();
    	JButton boutonLire=new JButton("Lire");
    	JButton boutonSupprimerTout=new JButton("Supprimer tout");
    	
    	// si le tableau est vide, on d�sactive les boutons afin d'�viter aux utilisateurs de vouloir 
    	// faire des actions dans un tableau vide
    	if(modele.getRowCount() == 0){
    		boutonLire.setEnabled(false);
    		boutonSupprimerTout.setEnabled(false);
    	}
    	
    	// D�finition de l'action du bouton permettant de lire un message //
    	//----------------------------------------------------------------//
    	boutonLire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// R�cup�ration de la ligne selctionn�e et des attributs du message
				int ligne = tableauMessage.getSelectedRow();
				String identifiantMail = tableauMessage.getValueAt(ligne,0).toString();
				String expediteur = tableauMessage.getValueAt(ligne,1).toString();
				String sujet = tableauMessage.getValueAt(ligne,2).toString();
				String contenu = tableauMessage.getValueAt(ligne,3).toString();
				String date = tableauMessage.getValueAt(ligne,4).toString();
				
				
				// Ouverture de la fenetre de lecture de message en passant en param�tre
				// du constructeur les attributs du message selectionn�
				FenetreLectureMessage fenMessage = new FenetreLectureMessage(null, "Message : "+ sujet, true, expediteur, sujet, contenu, date,identifiantMail,messagerieDuGerant);
				fenMessage.setVisible(true);
				
				// Fermeture de la fen�te
				dispose();
				
			}
		});
    	
    	boutonSupprimerTout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Suppression de tous les messages de la Base de Donn�es correspondants � l'utilisateur
				// courant de l'application
				Message.supprimerAllBDD(); 

			}
		});
    		
    	// Ajout de ses boutons au sous-panneau << panneauTitle>>  et de celui-ci au "panneau du haut"
    	panneauTitle.add(boutonLire);
    	panneauTitle.add(boutonSupprimerTout);
    	panneauHaut.add(panneauTitle,"Center");
   
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
	    // D�finition du panneau panneauBouton qui accueillera le bouton
	    // permettant de retourner � la page pr�c�dente 
	    JPanel panneauBouton=new JPanel();
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			// l'instruction permet de fermer la fenetre en cours	
			dispose();
			}			
		});
			
		panneauBouton.add(retourBouton);
			
	
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
  
	    pack();      
        
        }
    
    
	
}
