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


public class FenetreFideliteClient extends JDialog {
	
	
	private DialogInfo infoClient = new DialogInfo();
	private boolean sendData;
	private JLabel fideliteLabel;
	private JComboBox fidelite;
	private JLabel pointsFideliteLabel;
	private JTextField pointsFidelite;
	
	/**
	 * Constructeur
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public FenetreFideliteClient(JFrame parent, String title, boolean modal,DialogInfo client){
		super(parent, title, modal);
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(client);
	}
	
	/**
	 * Méthode appelée pour utiliser la boîte 
	 * @return infoClient
	 */
	public DialogInfo showClientFidelite(){
		this.sendData = false;
		this.setVisible(true);		
		return this.infoClient;		
	}
	
	private void initComponent(DialogInfo client){
		
		// La demande de carte de fidelité
		JPanel panFidelite = new JPanel();
		panFidelite.setBackground(Color.white);
		panFidelite.setPreferredSize(new Dimension(220, 80));
		fideliteLabel = new JLabel("Desirez-vous une carte de fidelité ? : ");
		panFidelite.add(fideliteLabel);
		
		fidelite=new JComboBox();
		fidelite.addItem("Oui");
		fidelite.addItem("Non");
		
		if(FenetreDialogCreationCompte.itemFidelite=="Oui"){
			fidelite.setVisible(false);
		}
		else{
			fidelite.setVisible(true);
		}
		
		fidelite.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FenetreDialogCreationCompte.itemFidelite = (String) ((JComboBox) e.getSource()).getSelectedItem();
			}	
		});
		
		panFidelite.add(fidelite);
		
		// Le nombre de points
		JPanel panPointsFidelite = new JPanel();
		panPointsFidelite.setBackground(Color.white);
		panPointsFidelite.setPreferredSize(new Dimension(220, 80));
		pointsFideliteLabel = new JLabel("Nombre de points fidelité :");
		panPointsFidelite.add(pointsFideliteLabel);
		int points=client.getPointFidelite();
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
		
		JPanel panneauCentral = new JPanel();
        panneauCentral.add(panFidelite);
        panneauCentral.add(panPointsFidelite);
        panneauCentral.add(boutonAffichageBons);
        
        JPanel panneauBoutons = new JPanel();
        JButton validationBouton = new JButton("OK");
		validationBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO enregistrer éventuelle modification et retourner menu utilisateur
			}			
		});
        
        
		JButton annulationBouton = new JButton("Annuler");
		annulationBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
		
		panneauBoutons.add(validationBouton);
		panneauBoutons.add(annulationBouton);
		
		this.getContentPane().add(panneauCentral, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
	}
}
