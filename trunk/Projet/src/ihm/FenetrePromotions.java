package ihm;
import ihm.ModelePromotionClient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class FenetrePromotions extends JFrame {
	
	private JLabel promotionsLabel;


	public FenetrePromotions(){
		super();
		this.setTitle("Promotions en cours");
		this.setSize(500, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}         

        
    private void initComponent(){
    	
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	JPanel panneauTitle=new JPanel();
    	promotionsLabel= new JLabel("Decouvrez les promotions en cours dans le tableau ci-dessous");
    	panneauTitle.add(promotionsLabel);
    	panneauHaut.add(panneauTitle,"Center");
    	
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
	    JTable tableauPromotions = new JTable(new ModelePromotionClient());     
	    this.getContentPane().add(new JScrollPane(tableauPromotions), BorderLayout.CENTER);
	    
	    
	    JPanel panneauBouton=new JPanel();
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
			
		panneauBouton.add(retourBouton);
			
	
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
			
	        
	        
	    pack();
	       
        
        }
	
}

