package ihm.Gerant;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class FenetreStatistiqueCommande extends JFrame {
	// cette classe permettra d'afficher les détails de commandes particulières pour le gérant
	
	public FenetreStatistiqueCommande(){
		super();
		this.setSize(700, 300);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); 
		this.initComponent();
		
	}

	private void initComponent() {
		// TODO Auto-generated method stub
		
	}
	
}
