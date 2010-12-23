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
 * Cette classe permettra d'afficher les d�tails d'une commande particuli�re pour le g�rant
 * 
 * @author Utilisateur
 * 
 * @see FicheClient
 */
public class FenetreStatistiqueCommande extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * <b>Constructeur de la fen�tre {@link FenetreStatistiqueCommande}.</b>
	 * <p> Cette fen�tre est similaire � une facture dont on affiche les d�tails. </p>
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param title
	 *            String indiquant le titre de la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
	 * @param numCommande
	 * 			  Identifiant de la commande dont on affiche les d�tails
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
	 * <p> Initialisation des composants de la fen�tre : <ul>
	 * <li> un JPanel en haut de la fen�tre contenat les d�tails de la commande :
	 *  r�f�rence, date et montant.</li>
	 * <li> un tableau avec la liste des articles command�s avec leur quantit� 
	 * command�e.</li>
	 * <li> un bouton permettant le retour � la fiche client.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param idCommande
	 * 				Identifiant de la commande dont on affiche les d�tails
	 * 
	 * @see FicheClient
	 * 
	 * @see ModeleTableauStatCommande
	 */
	private void initComponent(String idCommande) {
		
		// D�finition du JPanel qui d�taille les informations concernant la commande //
		//---------------------------------------------------------------------------//
		JPanel panneauHautInformations = new JPanel();
		panneauHautInformations.setLayout(new GridLayout(3,1,0,5));
		
		String date = SGBD.selectStringConditionString("COMMANDE", "DATECOMMANDE", "IDCOMMANDE", idCommande);
		JLabel dateCommandeLabel = new JLabel("Date : "+ date);
		JLabel referenceCommandeLabel = new JLabel("R�f�rence commande : " + idCommande);
		JLabel montantCommandeLabel = new JLabel("Montant de la commande : "+ SGBD.selectStringConditionString("COMMANDE", "MONTANTCOMMANDE", "IDCOMMANDE", idCommande)+" �");
		panneauHautInformations.add(dateCommandeLabel);
		panneauHautInformations.add(referenceCommandeLabel);
		panneauHautInformations.add(montantCommandeLabel);
		
		// R�cup�ration du tableau avec le d�tail des articles et des quantit�s command�es //
		// ------- de chacun des articles avec ModeleTableauStatCommande ------------------//
		ModeleTableauStatCommande modele = new ModeleTableauStatCommande(idCommande);
		JTable tableau = new JTable(modele);
		JScrollPane tableauCommande = new JScrollPane(tableau);
		tableau.setVisible(true);
		tableau.setEnabled(false);
		
		// D�finition du bouton permettant le retour � la page pr�c�dente //
		//-------------- en fermant la page en cours -------------------- //
		JButton boutonRetour = new JButton("Retour � la fiche client");
		boutonRetour.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// Ajout de tous les composants au conteneur de la fen�tre //
		//---------------------------------------------------------//
		getContentPane().add(panneauHautInformations,"North");
		getContentPane().add(tableauCommande,"Center");
		getContentPane().add(boutonRetour,"South");
	}
	
}
