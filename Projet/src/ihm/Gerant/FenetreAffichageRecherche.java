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
		
		
		// D�finition d'un panneau JPanel qui va accueillir le tableau des r�sultats 
		// de la recherche au moyen du constructeur de la classe ModeleTableauClient
		
		JPanel panneauCentral =  new JPanel();
		final ModeleTableauClient modele = new ModeleTableauClient(idClient,nom,denom, ville);
		final JTable tableauRechercheClient = new JTable(modele);
		tableauRechercheClient.setVisible(true);
		
		// Dans le cas o� le tableau ne contient aucune ligne,c'est � dire qu'aucun client 
		// ne correspond � la recherche effectu�e, on lui affiche un message pour le lui
		// signifier
		if(modele.getRowCount()==0){
			dispose();
			JOptionPane.showMessageDialog(null, "Aucun client ne correspond aux crit�res de votre recherche !","Attention",JOptionPane.ERROR_MESSAGE);
		}
		
		panneauCentral.add(new JScrollPane(tableauRechercheClient));
		
		
		JPanel panneauBouton= new JPanel();
		
		// D�finition du premier bouton de la fen�tre qui permet l'acc�s � une fiche client 
		// 
		JButton validationRecherche = new JButton("Acc�s client");
		validationRecherche.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// nous recup�rons le num�ro de la ligne s�lectionn�e par le g�rant dans 
				// le tableau
				int ligne =  tableauRechercheClient.getSelectedRow();
				
				//si aucune ligne est s�lectionn�e, alors nous affichons un message pour lui 
				//signifier l'erreur 
				if(ligne==-1){
					
					JOptionPane.showMessageDialog(null, "Aucune ligne s�lectionn�e. Veuillez en s�lectionner une","Attention",JOptionPane.ERROR_MESSAGE);
				
				}
				else{
					// R�cup�ration de l'identifiant du client dans la premi�re colonne du tableau
					String identifiant = tableauRechercheClient.getValueAt(ligne,0).toString();
					
					// Affichage d'une boite de dialogue pour confirmer le client selectionn�
					int res= JOptionPane.showConfirmDialog(null, "confirmez la s�lection client de : "+ identifiant);
					
					if(res == JOptionPane.OK_OPTION){
						dispose();
						// on affiche la fiche client correspondante � l'identifiant saisi
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
