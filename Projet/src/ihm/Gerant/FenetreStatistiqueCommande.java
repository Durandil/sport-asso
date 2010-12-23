package ihm.Gerant;

import ihm.modeleTableau.ModeleTableauStatCommande;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import basededonnees.SGBD;

/**
 * Cette classe permettra d'afficher les détails d'une commande particulière pour le gérant
 * 
 * @author Utilisateur
 * 
 * @see FicheClient
 */
public class FenetreStatistiqueCommande extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * <b>Constructeur de la fenêtre {@link FenetreStatistiqueCommande}.</b>
	 * <p> Cette fenêtre est similaire à une facture dont on affiche les détails. </p>
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param title
	 *            String indiquant le titre de la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * @param numCommande
	 * 			  Identifiant de la commande dont on affiche les détails
	 */
	public FenetreStatistiqueCommande(JFrame parent, String title, boolean modal,String numCommande){
		super(parent,title,modal);
		this.setSize(700, 300);
        this.setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(numCommande);
		
	}
	/**
	 * <p> Initialisation des composants de la fenêtre : <ul>
	 * <li> un JPanel en haut de la fenêtre contenat les détails de la commande :
	 *  référence, date et montant.</li>
	 * <li> un tableau avec la liste des articles commandés avec leur quantité 
	 * commandée.</li>
	 * <li> un bouton permettant le retour à la fiche client.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param idCommande
	 * 				Identifiant de la commande dont on affiche les détails
	 * 
	 * @see FicheClient
	 * 
	 * @see ModeleTableauStatCommande
	 */
	private void initComponent(String idCommande) {
		
		// Définition du JPanel qui détaille les informations concernant la commande //
		//---------------------------------------------------------------------------//
		JPanel panneauHautInformations = new JPanel();
		panneauHautInformations.setLayout(new GridLayout(3,1,0,5));
		
		String date = SGBD.selectStringConditionString("COMMANDE", "DATECOMMANDE", "IDCOMMANDE", idCommande);
		JLabel dateCommandeLabel = new JLabel("Date : "+ date);
		JLabel referenceCommandeLabel = new JLabel("Référence commande : " + idCommande);
		JLabel montantCommandeLabel = new JLabel("Montant de la commande : "+ SGBD.selectStringConditionString("COMMANDE", "MONTANTCOMMANDE", "IDCOMMANDE", idCommande)+" €");
		panneauHautInformations.add(dateCommandeLabel);
		panneauHautInformations.add(referenceCommandeLabel);
		panneauHautInformations.add(montantCommandeLabel);
		
		// Récupération du tableau avec le détail des articles et des quantités commandées //
		// ------- de chacun des articles avec ModeleTableauStatCommande ------------------//
		ModeleTableauStatCommande modele = new ModeleTableauStatCommande(idCommande);
		JTable tableau = new JTable(modele);
		JScrollPane tableauCommande = new JScrollPane(tableau);
		tableau.setVisible(true);
		tableau.setEnabled(false);
		
		// Définition du bouton permettant le retour à la page précédente //
		//-------------- en fermant la page en cours -------------------- //
		JButton boutonRetour = new JButton("Retour à la fiche client");
		boutonRetour.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// Ajout de tous les composants au conteneur de la fenêtre //
		//---------------------------------------------------------//
		getContentPane().add(panneauHautInformations,"North");
		getContentPane().add(tableauCommande,"Center");
		getContentPane().add(boutonRetour,"South");
	}
	
}
