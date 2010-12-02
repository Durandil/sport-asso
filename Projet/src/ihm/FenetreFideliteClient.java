package ihm;
import ihm.FenetreDialogCreationCompte;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import basededonnees.SGBD;



public class FenetreFideliteClient extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel fideliteLabel;
	private JComboBox fidelite;
	private JLabel pointsFideliteLabel;
	private JTextField pointsFidelite;
	private JOptionPane affichageBonAchat;
	
	/**
	 * Constructeur de la classe FenetreFideliteClient
	 * les param�tres sont d�finis pour faire fonctionner la classe JDialog
	 * @param parent : Appel �ventuel � un JFrame (lors de l'affichage, utilisez parent=null)
	 * @param title :  Titre de la fenetre 
	 * @param modal : Bool�en pour l'affichage de la fenetre
	 * @param client : Il permet de r�cup�rer les informations d'un client pour les utiliser dans la fen�tre
	 * 				   
	 */
	public FenetreFideliteClient(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(400, 500);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	
	private void initComponent(){
		
		// Cr�ation du panneau de gestion de la demande d'une carte de fidelit�
		JPanel panFidelite = new JPanel();
		panFidelite.setBackground(Color.white);
		panFidelite.setPreferredSize(new Dimension(220, 80));
		fideliteLabel = new JLabel("Desirez-vous une carte de fidelit� ? : ");
		panFidelite.add(fideliteLabel);
		
		// TODO r�cup�rer dans base de donnees fidelite SANS ERREUR !!!!
		ArrayList<String> fideliteClient= new ArrayList<String>();
		fideliteClient=SGBD.recupererInformationFideliteClient(FenetreDialogIdentification.clientUserIdentifiant);
		String estFidele=fideliteClient.get(0);
		
		if(estFidele.equals("false")){
			FenetreDialogCreationCompte.itemFidelite="Non";
		}
		else{
			FenetreDialogCreationCompte.itemFidelite="Oui";
		}
		
		// Cr�ation du menu d�roulant sur la demande de la carte de fid�lit�
		fidelite=new JComboBox();
		fidelite.addItem("Oui");
		fidelite.addItem("Non");
		fidelite.setSelectedItem(FenetreDialogCreationCompte.itemFidelite);
		
		//client.isEstFidele()==true
		if(FenetreDialogCreationCompte.itemFidelite=="Oui"){
			// si la personne a r�pondu oui lors de la cr�ation de compte, on ne lui permet pas de modifier 
			// le contenu du menu d�roulant
			fidelite.setEnabled(false);
		}
		else{
			// dans le cas contraire, il pourra le modifier
			fidelite.setEnabled(true);
		}
		
		fidelite.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// si la valeur du menu d�roulant est modifi�e, on change pour l'individu la valeur de l'attribut du compte fid�lit�
				FenetreDialogCreationCompte.itemFidelite = (String) ((JComboBox) e.getSource()).getSelectedItem();
				if(FenetreDialogCreationCompte.itemFidelite=="Oui"){
					// si la personne a r�pondu oui lors de la cr�ation de compte, on ne lui permet pas de modifier 
					// le contenu du menu d�roulant
					fidelite.setEnabled(false);
				}
				else{
					// dans le cas contraire, il pourra le modifier
					fidelite.setEnabled(true);
				}
			}	
		});
		
		fidelite.setVisible(true);
		panFidelite.add(fidelite);
		
		// Cr�ation d'un panneau permettant l'affichage du nombre de points
		JPanel panPointsFidelite = new JPanel();
		panPointsFidelite.setBackground(Color.white);
		panPointsFidelite.setPreferredSize(new Dimension(220, 80));
		pointsFideliteLabel = new JLabel("Nombre de points fidelit� :");
		
		final int points= Integer.parseInt(SGBD.selectStringConditionString("CARTE_FIDELITE", "NBPOINTS", "IDCLIENT", FenetreDialogIdentification.clientUserIdentifiant)) ; // r�cup�ration du nombre de points du client entr� en param�tre de initComponent()
		pointsFidelite= new JTextField(""+points+"");
		pointsFidelite.setPreferredSize(new Dimension(90, 25));
		pointsFidelite.setVisible(true);
		pointsFidelite.setEnabled(false);
		
		panPointsFidelite.add(pointsFideliteLabel);
		panPointsFidelite.add(pointsFidelite);
		
		// Bouton affichage des bons d'achat disponible
		JButton boutonAffichageBons=new JButton("Afficher les bons d'achat disponibles");
		boutonAffichageBons.addActionListener(new ActionListener() {
			
		
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				//Gestion des bons achat du client selon le nombre de points
				// sur sa carte de fidelite

				int bonAchat=0;
				
				if(points <= 0) {
					bonAchat=0;
				}
				else if(points<20) {
					bonAchat=10;
				}
				else if(points<60) bonAchat=30;
				
				affichageBonAchat = new JOptionPane();
				ImageIcon imageInformation = new ImageIcon("src/images/information.jpg");
				affichageBonAchat.showMessageDialog(null, "Vous disposez d'un bon d'achat de "+ bonAchat + " �", "Information sur les bons d'achat", JOptionPane.INFORMATION_MESSAGE, imageInformation);
				
			}
		});
		
		// Cr�ation du panneau central qui accueillera les panneaux cr�es ci-dessus et qui seront affich�s au centre
		// de la fen�tre
		JPanel panneauCentral = new JPanel();
        panneauCentral.add(panFidelite);
        panneauCentral.add(panPointsFidelite);
        panneauCentral.add(boutonAffichageBons);
        
        // D�finition du panneau panneauBoutons qui va accueillir les boutons de confirmation et d'annulation
        JPanel panneauBoutons = new JPanel();
        JButton validationBouton = new JButton("Confirmer");
		validationBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO enregistrer �ventuelle modification de programme fidelit� 
				//(par ex : passage de non � oui,on ne peut pas passer de oui � non a priori)
				// et retourner menu utilisateur
			}			
		});
        
        
		JButton annulationBouton = new JButton("Annuler");
		annulationBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
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
