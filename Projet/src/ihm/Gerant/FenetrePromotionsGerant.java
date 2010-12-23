package ihm.Gerant;
import ihm.modeleTableau.ModelePromotion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import metier.Promotion;

/**
 * 
 * @author Utilisateur
 *
 */
public class FenetrePromotionsGerant extends JFrame {
	

	private static final long serialVersionUID = 1L;
	public static int ligneTableau = 0;
	
	/**
	 * 
	 */
	public FenetrePromotionsGerant(){
		super();
		this.setTitle("Promotions en cours");
		this.setSize(500, 600);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         

    /**
     *     
     */
    private void initComponent(){
    	
    	// Définition du tableau qui affichera l'ensemble des promotions en cours pour les différents clients
    	// (adhérents ou non adhérents) après interrogation de la base de données dans ModelePromotionClient
    	final ModelePromotion modele = new ModelePromotion();
    	final JTable tableauPromotions = new JTable(modele);     
	    this.getContentPane().add(new JScrollPane(tableauPromotions), BorderLayout.CENTER);
	    
	    //Création du panneau qui se situera en haut de la fenetre créée
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	// Création d'un "sous-panneau" accueillant tous les boutons relatifs aux actions
    	// que le gérant peut faire sur les promotions en cours : soit en ajouter, soit en modifier soit en supprimer
    	JPanel panneauTitle=new JPanel();
    	JButton boutonAjouter=new JButton("Ajouter");
    	JButton boutonSupprimer=new JButton("Supprimer");
    	JButton boutonModifier=new JButton("Modifier");
    	
    	if(modele.getRowCount()==0){
    		boutonSupprimer.setEnabled(false);
    		boutonModifier.setEnabled(false);
    	}
    	
    	boutonAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ajout d'une promotion
				FenetreFormulairePromotionsGerant nouvellePromo = new FenetreFormulairePromotionsGerant(null,"Ajout d'une nouvelle promotion",true);
				nouvellePromo.setVisible(true);
				dispose();
			}
		});
    	
    	boutonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Supprimer la promotion selectionnée dans la base de donnée
				ligneTableau = tableauPromotions.getSelectedRow();
				if(ligneTableau==-1){
					
					JOptionPane.showMessageDialog(null, "Aucune ligne sélectionnée. Veuillez en sélectionner une","Attention",JOptionPane.ERROR_MESSAGE);
				}
				else{
					String idPromo = tableauPromotions.getValueAt(ligneTableau, 0).toString();
					String descriptionPromo = tableauPromotions.getValueAt(ligneTableau, 1).toString();
					
					int res = JOptionPane.showConfirmDialog(null, "Confimez-vous la suppression de la promotion "+ idPromo +" : "+ descriptionPromo ,"Confirmation",JOptionPane.YES_NO_OPTION);
					
					if(res==JOptionPane.YES_OPTION){
						Promotion.supprimerListing_PromoBDD(idPromo);
						Promotion.supprimerPromoBDD(idPromo);
					}
				}
			}
		});
    	
    	boutonModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modifier la promotion sélectionée grâce à un formulaire
				ligneTableau = tableauPromotions.getSelectedRow();
				if(ligneTableau==-1){
					
					JOptionPane.showMessageDialog(null, "Aucune ligne sélectionnée. Veuillez en sélectionner une","Attention",JOptionPane.ERROR_MESSAGE);
				}
				else{
					String idPromo = tableauPromotions.getValueAt(ligneTableau, 0).toString();
					
					FenetreFormulairePromotionsGerant modifierPromo;
					try {
						modifierPromo = new FenetreFormulairePromotionsGerant(null,"Modification d'une nouvelle promotion",true,idPromo);
						modifierPromo.setVisible(true);
						dispose();
					} 
					catch (Exception e1) {	
						System.out.println(e1.getMessage());
					}	
					
					
									
				}
				
			}
		});
    	
    	// Ajout de ses boutons au "sous-panneau" et de celui au "panneau du haut"
    	panneauTitle.add(boutonAjouter);
    	panneauTitle.add(boutonSupprimer);
    	panneauTitle.add(boutonModifier);
    	panneauHaut.add(panneauTitle,"Center");
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
	    
	    
	    // Définition du panneau panneauBouton qui accueillera le bouton
	    // permettant de retourner à la page précédente 
	    JPanel panneauBouton=new JPanel();
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
				dispose();
			}			
		});
			
		panneauBouton.add(retourBouton);
	
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }
	
}

