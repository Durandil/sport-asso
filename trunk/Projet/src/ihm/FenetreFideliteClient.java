package ihm;
import ihm.DialogInfo;
import ihm.FenetreDialogCreationCompte;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.Client;


public class FenetreFideliteClient extends JDialog {
	
	private JLabel fideliteLabel;
	private JComboBox fidelite;
	private JLabel pointsFideliteLabel;
	private JTextField pointsFidelite;
	
	/**
	 * Constructeur de la classe FenetreFideliteClient
	 * les paramètres sont définis pour faire fonctionner la classe JDialog
	 * @param parent : Appel éventuel à un JFrame (lors de l'affichage, utilisez parent=null)
	 * @param title :  Titre de la fenetre 
	 * @param modal : Booléen pour l'affichage de la fenetre
	 * @param client : Il permet de récupérer les informations d'un client pour les utiliser dans la fenêtre
	 * 				   
	 */
	public FenetreFideliteClient(JFrame parent, String title, boolean modal,Client client){
		super(parent, title, modal);
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(client);
	}
	
	
	private void initComponent(Client client){
		
		// Création du panneau de gestion de la demande d'une carte de fidelité
		JPanel panFidelite = new JPanel();
		panFidelite.setBackground(Color.white);
		panFidelite.setPreferredSize(new Dimension(220, 80));
		fideliteLabel = new JLabel("Desirez-vous une carte de fidelité ? : ");
		panFidelite.add(fideliteLabel);
		
		// Création du menu déroulant sur la demande de la carte de fidélité
		fidelite=new JComboBox();
		fidelite.addItem("Oui");
		fidelite.addItem("Non");
		
		//client.isEstFidele()==true
		if(FenetreDialogCreationCompte.itemFidelite=="Oui"){
			// si la personne a répondu oui lors de la création de compte, on ne lui permet pas de modifier 
			// le contenu du menu déroulant
			fidelite.setVisible(false);
		}
		else{
			// dans le cas contraire, il pourra le modifier
			fidelite.setVisible(true);
		}
		
		fidelite.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// si la valeur du menu déroulant est modifiée, on change pour l'individu la valeur de l'attribut du compte fidélité
				FenetreDialogCreationCompte.itemFidelite = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}	
		});
		
		panFidelite.add(fidelite);
		
		// Création d'un panneau permettant l'affichage du nombre de points
		JPanel panPointsFidelite = new JPanel();
		panPointsFidelite.setBackground(Color.white);
		panPointsFidelite.setPreferredSize(new Dimension(220, 80));
		pointsFideliteLabel = new JLabel("Nombre de points fidelité :");
		panPointsFidelite.add(pointsFideliteLabel);
		int points=client.getNbPointsFidelite(); // récupération du nombre de points du client entré en paramètre de initComponent()
		pointsFidelite= new JTextField(points);
		pointsFidelite.setPreferredSize(new Dimension(90, 25));
		pointsFidelite.setEnabled(false);
		panPointsFidelite.add(pointsFidelite);
		
		// Bouton affichage des bons d'achat disponible
		JButton boutonAffichageBons=new JButton("Afficher les bons d'achat disponibles");
		boutonAffichageBons.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// afficher dans une nouvelle fenetre ou dans un JTable les résultats
				
			}
		});
		
		// Création du panneau central qui accueillera les panneaux crées ci-dessus et qui seront affichés au centre
		// de la fenêtre
		JPanel panneauCentral = new JPanel();
        panneauCentral.add(panFidelite);
        panneauCentral.add(panPointsFidelite);
        panneauCentral.add(boutonAffichageBons);
        
        // Définition du panneau panneauBoutons qui va accueillir les boutons de confirmation et d'annulation
        JPanel panneauBoutons = new JPanel();
        JButton validationBouton = new JButton("Confirmer");
		validationBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO enregistrer éventuelle modification de programme fidelité 
				//(par ex : passage de non à oui,on ne peut pas passer de oui à non a priori)
				// et retourner menu utilisateur
			}			
		});
        
        
		JButton annulationBouton = new JButton("Annuler");
		annulationBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
		
		// ajout des boutons au panneau panneauBoutons
		panneauBoutons.add(validationBouton);
		panneauBoutons.add(annulationBouton);
		
		this.getContentPane().add(panneauCentral, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
	}
}
