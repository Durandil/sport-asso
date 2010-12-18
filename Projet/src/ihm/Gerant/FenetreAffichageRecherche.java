package ihm.Gerant;

import ihm.modeleTableau.ModeleTableauClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class FenetreAffichageRecherche extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public FenetreAffichageRecherche(JFrame parent, String title, boolean modal,String idClient,String nom,String denom,String ville){
		super(parent, title, modal);
		this.setSize(700,600);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent(idClient,nom,denom,ville);
	}
	
	private void initComponent(String idClient,String nom,String denom,String ville){
		
		
		// Définition d'un panneau JPanel qui va accueillir le tableau des résultats 
		// de la recherche au moyen du constructeur de la classe ModeleTableauClient
		
		JPanel panneauCentral =  new JPanel();
		final ModeleTableauClient modele = new ModeleTableauClient(idClient,nom,denom, ville);
		final JTable tableauRechercheClient = new JTable(modele);
		tableauRechercheClient.setVisible(true);
		
		// Dans le cas où le tableau ne contient aucune ligne,c'est à dire qu'aucun client 
		// ne correspond à la recherche effectuée, on lui affiche un message pour le lui
		// signifier
		if(modele.getRowCount()==0){
			dispose();
			JOptionPane.showMessageDialog(null, "Aucun client ne correspond aux critères de votre recherche !","Attention",JOptionPane.ERROR_MESSAGE);
		}
		
		panneauCentral.add(new JScrollPane(tableauRechercheClient));
		
		
		JPanel panneauBouton= new JPanel();
		
		// Définition du premier bouton de la fenêtre qui permet l'accès à une fiche client 
		// 
		JButton validationRecherche = new JButton("Accès client");
		validationRecherche.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// nous recupérons le numéro de la ligne sélectionnée par le gérant dans 
				// le tableau
				int ligne =  tableauRechercheClient.getSelectedRow();
				
				//si aucune ligne est sélectionnée, alors nous affichons un message pour lui 
				//signifier l'erreur 
				if(ligne==-1){
					
					JOptionPane.showMessageDialog(null, "Aucune ligne sélectionnée. Veuillez en sélectionner une","Attention",JOptionPane.ERROR_MESSAGE);
				
				}
				else{
					// Récupération de l'identifiant du client dans la première colonne du tableau
					String identifiant = tableauRechercheClient.getValueAt(ligne,0).toString();
					
					// Affichage d'une boite de dialogue pour confirmer le client selectionné
					int res= JOptionPane.showConfirmDialog(null, "confirmez la sélection client de : "+ identifiant);
					
					if(res == JOptionPane.OK_OPTION){
						dispose();
						// on affiche la fiche client correspondante à l'identifiant saisi
						FicheClient ficheDuClient = new FicheClient(null, "Fiche du client : "+ identifiant, true, identifiant);
						ficheDuClient.setVisible(true);
					
					}
										
				}
			}

		});
		
		panneauBouton.add(validationRecherche);
		
		JButton retourRecherche = new JButton("Retour");
		retourRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panneauBouton.add(retourRecherche);
		
		this.getContentPane().add(panneauCentral,"Center");
		this.getContentPane().add(panneauBouton,"South");
		

		
	}
	
}
