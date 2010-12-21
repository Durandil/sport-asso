package ihm.Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Initialisation du panneau qui accueillera le message de présentation du magasin sport'asso
 * cette classe sera réutilisée dans le fenêtre d'informations {@link FenetreInformationsClient}
 * @author Utilisateur
 *
 */
public class Panneau extends JPanel {
	

	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialiation du message qui servira de présentation de l'entreprise
	 */
	public void paintComponent(Graphics g){

        g.setFont(new Font("Times", Font.BOLD, 13));
        g.setColor(Color.red);
        g.drawString("Créer l'envie et rendre accessible au plus grand nombre le plaisir et les bienfaits du sport",20,40);
        g.drawString("Telle est la promesse que SPORT'ASSO propose à ses clients depuis ses débuts.",20,60);
        
        g.setFont(new Font("Times", Font.BOLD, 10));
        g.setColor(Color.black);
        g.drawString("Regroupant tous les sports sous un même toit, SPORT'ASSO n'a cessé depuis son origine de favoriser l accessibilité à la pratique du sport : ", 20, 80);
        
        g.drawString("  ¤ Accessibilité par le prix tout en gardant une exigeance forte sur le développement de la qualité de nos produits.", 20, 95);
        g.drawString(" 	  Notre organisation nous permet encore de baisser régulièrement le prix de nos produits en fonction des quantités achetées.",25,110);
        
        g.drawString("  ¤ Accessibilité par une large gamme de produits sportifs. ",20,130);
        g.drawString("	  Cette gamme est composée à la fois de nos marques propres et des produits de marques reconnues.", 25, 145);
        
        
	}  
}
