package ihm.Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Panneau extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){

        g.setFont(new Font("Times", Font.BOLD, 13));
        g.setColor(Color.red);
        g.drawString("Cr�er l'envie et rendre accessible au plus grand nombre le plaisir et les bienfaits du sport",20,40);
        g.drawString("Telle est la promesse que SPORT'ASSO propose � ses clients depuis ses d�buts.",20,60);
        
        g.setFont(new Font("Times", Font.BOLD, 10));
        g.setColor(Color.black);
        g.drawString("Regroupant tous les sports sous un m�me toit, SPORT'ASSO n a cess� depuis son origine de favoriser l accessibilit� � la pratique du sport : ", 20, 80);
        
        g.drawString("  � Accessibilit� par le prix tout en gardant une exigeance forte sur le d�veloppement de la qualit� de nos produits.", 20, 95);
        g.drawString(" 	  Notre organisation nous permet encore de baisser r�guli�rement le prix de nos produits.",25,110);
        
        g.drawString("  � Accessibilit� par une large gamme de produits sportifs. ",20,130);
        g.drawString("	  Cette gamme est compos�e � la fois de nos marques propres et des produits de marques Internationales.", 25, 145);
        
        
	}  
}
