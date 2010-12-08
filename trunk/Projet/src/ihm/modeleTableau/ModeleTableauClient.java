package ihm.modeleTableau;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

public class ModeleTableauClient extends AbstractTableModel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Object[][] donnees;

    private final String[] entetes={"Identifiant","Nom","Prénom","Dénomination"} ;
	
    
	
	public ModeleTableauClient() {
		super();
		// TODO Auto-generated constructor stub
		donnees = new Object[1000][4];
		
	}


	public ModeleTableauClient(String idClient,String nom,String denom,String ville){
		super();
		//Quatre listes sont créées pour récupérer les informations de la table ARTICLES
		ArrayList<ArrayList<String>> listeClients = SGBD.recupererInformationRechercheClient(idClient,nom,denom,ville);
		
		donnees = new Object[1000][4];
		System.out.println(listeClients.get(0).size());
		if (listeClients.size()>0){
			ArrayList<String> listeIdentifiants = listeClients.get(0);
			ArrayList<String> listeNom = listeClients.get(1);
			ArrayList<String> listePrenom = listeClients.get(2);
			ArrayList<String> listeDenomination = listeClients.get(3);
			
		
			
		
			//On ajoute les informations dans l'objet donnees
			for(int i=0;i<listeIdentifiants.size();i++){
			
				donnees[i][0] = listeIdentifiants.get(i);	
				donnees[i][1] = listeNom.get(i);
				donnees[i][2] = listePrenom.get(i);
				donnees[i][3] = listeDenomination.get(i);
				
				System.out.println(donnees[i][0] + " " + donnees[i][1] +  " " + donnees[i][2] + " " + donnees[i][3]);

			}
		}
		
	}
	

	
	
	
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return donnees.length;
	}

	public Object getValueAt(int rowIndex, int colIndex) {
		// TODO Auto-generated method stub
		return donnees[rowIndex][colIndex];
	}
	
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }


	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		
	}
    

}