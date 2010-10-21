package ihm;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Panneau extends JPanel {
	
	public void paintComponent(Graphics g){
        
		g.setFont(new Font("Courier", Font.BOLD, 20));
		g.setColor(Color.GREEN);
        g.drawString("SPORT'ASSOC ", 10, 20);
        
        g.setFont(new Font("Times", Font.BOLD, 13));
        g.setColor(Color.red);
        g.drawString("Cr�er l'envie et rendre accessible au plus grand nombre le plaisir et les bienfaits du sport",20,40);
        g.drawString("Telle est la promesse que SPORT'ASSOC propose � ses clients depuis ses d�buts.",20,60);
        
        g.setFont(new Font("Times", Font.BOLD, 10));
        g.setColor(Color.black);
        g.drawString("Regroupant tous les sports sous un m�me toit, SPORT'ASSOC n a cess� depuis son origine de favoriser l accessibilit� � la pratique du sport : ", 20, 80);
        
        g.drawString("  � Accessibilit� par le prix tout en gardant une exigeance forte sur le d�veloppement de la qualit� de nos produits.", 20, 95);
        g.drawString(" 	  Notre organisation nous permet encore de baisser r�guli�rement le prix de nos produits.",25,110);
        
        g.drawString("  � Accessibilit� par une large gamme de produits sportifs. ",20,130);
        g.drawString("	  Cette gamme est compos�e � la fois de nos marques propres et des produits de marques Internationales.", 25, 145);
        
        g.drawString("Au del� de ces produits, SPORT'ASSOC propose �galement de nombreux services qui permettent �galement de faciliter ",20,170);
        g.drawString("la pratique du sport : nos ateliers, nos avantages clients, les bons d'achat.", 20, 190);
        
        g.drawString("Et depuis quelques ann�es, SPORT'ASSOC est all� plus loin dans sa relation avec ses clients en partageant le sport",20,210);
        g.drawString("autour d'�v�nements clients acteurs. ",20,230);
        
        g.setColor(Color.gray);
        g.setFont(new Font("Times", Font.BOLD, 16));
        g.drawString("Alexis Louvel, directeur de SPORT'ASSOC", 250, 270);
        
        
	}  
}
