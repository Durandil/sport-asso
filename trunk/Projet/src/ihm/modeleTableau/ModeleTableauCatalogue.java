package ihm.modeleTableau;
import ihm.Client.FenetreCommandeArticle;
import ihm.Gerant.FenetreCatalogueGerant;
import ihm.Gerant.FenetreReapprovisionnement;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import basededonnees.SGBD;

import metier.Article;
import metier.LigneCommande;

/**
 * La classe ModeleTableauCatalogue h�rite de la classe AbstractTableModel permettant l'utilisation
 * de tableau de donn�es. Nous allons utiliser cette classe pour afficher diff�rents types de 
 * catalogue d'articles en fonction de l'utilisateur
 * 
 * Pour plus d'informations, voir le constructeur de la classe {@link ModeleTableauCatalogue#ModeleTableauCatalogue(boolean, boolean)}
 * 
 * @see FenetreReapprovisionnement
 * @see FenetreCommandeArticle
 * @see FenetreCatalogueGerant
 *
 */
public class ModeleTableauCatalogue extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	/**
	 * donnees contenues � l'int�rieur du tableau
	 * 
	 * @see ModeleTableauCatalogue#getRowCount()
	 * @see ModeleTableauStatCommande
	 * @see ModeleTableauCatalogue#getValueAt(int, int)
	 * 
	 */
	private final Object[][] donnees;
	
	/**
	 * liste des noms de colonnes du tableau
	 * 
	 * @see ModeleTableauCatalogue#getColumnCount()
	 * @see ModeleTableauCatalogue#getColumnName(int)
	 */
    private final String[] entetes={"R�f�rence","Denomination","Quantite en stock","Prix Initial"} ;
	
    
	/**
	 * D�finition d'un constructeur qui retourne un tableau vide avec 1000 lignes et 5 colonnes
	 */
	public ModeleTableauCatalogue() {
		super();
		// TODO Auto-generated constructor stub
		donnees= new Object[1000][5];
	}
	
	/**
	 * <p>Ce constructeur permet de cr�er 3 types de tableaux :<ul>
	 * <li> le tableau des articles � r�approvisionner dans {@link FenetreReapprovisionnement} </li>
	 * <li> le catalogue des articles qui sont dans l'�tat en stock pour le client dans
	 * 	{@link FenetreCommandeArticle} </li>
	 * <li> le catalogue de l'ensemble des articles en stock et en rupture de stock que le g�rant va
	 * pouvoir g�rer (ajout,modification,suppression d'articles) dans {@link FenetreCatalogueGerant} </li>
	 * </ul>
	 * </p>
	 * 
	 * @param pourReapprovisionnement
	 * 			Bool�en indiquant si le tableau sert � afficher les articles ayant besoin d'�tre r�approvisionn�s
	 * 	
	 * @param pourTableauGerant
	 * 			Bool�en indiquant si le catalogue affich� est destin� au g�rant (true) ou au client (false)
	 */
	public ModeleTableauCatalogue(boolean pourReapprovisionnement,boolean pourTableauGerant){
		super();
		if( pourReapprovisionnement == false){
			
			if(pourTableauGerant == true){
				// tableau qui accueillera le catalogue  pour le g�rant et qui doit afficher 
				// tous les articles contrairement au client
				
				//Quatre listes sont cr��es pour r�cup�rer les informations de la table ARTICLE
				ArrayList<String> listeIdentifiants = SGBD.selectListeStringOrdonneCondition("ARTICLE","IDARTICLE","IDARTICLE","ETATARTICLE !='Supprim�'");
				ArrayList<String> listeDescriptions = SGBD.selectListeStringOrdonneCondition("ARTICLE", "DESCRIPTION","IDARTICLE","ETATARTICLE !='Supprim�'");
				ArrayList<Integer> listeStocks = SGBD.selectListeIntOrdonneCondition("ARTICLE", "STOCK","IDARTICLE","ETATARTICLE !='Supprim�'");
				ArrayList<Integer> listeEtats = SGBD.selectListeIntOrdonneCondition("ARTICLE", "PRIXINITIAL","IDARTICLE","ETATARTICLE !='Supprim�'");
			
				
				donnees = new Object[listeIdentifiants.size()][5];
	
				//On ajoute les informations dans l'objet donnees
				for(int i=0;i<listeIdentifiants.size();i++){
					donnees[i][0] = listeIdentifiants.get(i);
					donnees[i][1] = listeDescriptions.get(i);
					donnees[i][2] = listeStocks.get(i);
					donnees[i][3] = listeEtats.get(i);
				
				}
				
			}
			else{ 
				// tableau qui  accueillera le catalogue  pour les clients et qui doit afficher 
				// que les articles sont stock > 0
				
				//Quatre listes sont cr��es pour r�cup�rer les informations de la table ARTICLE
				ArrayList<String> listeIdentifiants = SGBD.selectListeStringOrdonneCondition("ARTICLE","IDARTICLE","IDARTICLE","STOCK>0 and ETATARTICLE !='Supprim�'");
				ArrayList<String> listeDescriptions = SGBD.selectListeStringOrdonneCondition("ARTICLE", "DESCRIPTION","IDARTICLE","STOCK>0 AND ETATARTICLE !='Supprim�'");
				ArrayList<Integer> listeStocks = SGBD.selectListeIntOrdonneCondition("ARTICLE", "STOCK","IDARTICLE","STOCK>0 AND ETATARTICLE !='Supprim�'");
				ArrayList<Integer> listeEtats = SGBD.selectListeIntOrdonneCondition("ARTICLE", "PRIXINITIAL","IDARTICLE","STOCK>0 AND ETATARTICLE !='Supprim�'");
			
				
				donnees = new Object[listeIdentifiants.size()][5];
	
				//On ajoute les informations dans l'objet donnees
				for(int i=0;i<listeIdentifiants.size();i++){
					donnees[i][0] = listeIdentifiants.get(i);
					donnees[i][1] = listeDescriptions.get(i);
					donnees[i][2] = listeStocks.get(i);
					donnees[i][3] = listeEtats.get(i);
				
				}
			}
			
		}
		else{
			// cr�ation des listes pour r�cup�rer les informations des articles qui ont besoin d'�tre
			// reapprovisionn�s
			ArrayList<ArrayList<String>> listeArticles = new ArrayList<ArrayList<String>>() ;
			listeArticles = SGBD.selectArticlesReapprovisionnement();
			ArrayList<String >listeIdentifiants = listeArticles.get(0);
			ArrayList<String >listeDescriptions = listeArticles.get(1);
			ArrayList<String >listeStocks = listeArticles.get(2);
			ArrayList<String >listeEtats = listeArticles.get(3);
			
			donnees = new Object[listeIdentifiants.size()][4];
			
			// on ajoute les informations dans l'objet
			for(int i=0;i< listeIdentifiants.size();i++){
					donnees[i][0] = listeIdentifiants.get(i);
					donnees[i][1] = listeDescriptions.get(i);
					donnees[i][2] = listeStocks.get(i);
					donnees[i][3] = listeEtats.get(i);
			}
			
		
		}
	}
	

	/**
	 * @return Indique le nombre de colonnes dans le tableau
	 */
	public int getColumnCount() {
		return entetes.length;
	}
	
	/**
	 * @return Indique  le nombre de lignes dans le	tableau
	 */
	public int getRowCount() {
		return donnees.length;
	}
	
	/**
	 * @param rowIndex 
	 * 			integer indiquant le num�ro de la ligne de l'�l�ment choisi	
	 * @param colIndex
	 * 			integer indiquant le num�ro de la colonne de l'�l�ment choisi 
	 * 
	 * @return l'objet situ� dans la rowIndex �me ligne et la colIndex �me colonne du tableau  
	 * 			correspondant � la liste des articles en stock dans le catalogue
	 */
	public Object getValueAt(int rowIndex, int colIndex) {
		return donnees[rowIndex][colIndex];
	}
	
	/**
	 * @return le nom de la colonne � l'index columnIndex
	 * 
	 * @param columnIndex
	 * 				l'index de la colonne dans le tableau
	 */
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    

}

