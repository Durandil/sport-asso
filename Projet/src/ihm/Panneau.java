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
        g.drawString("Créer l'envie et rendre accessible au plus grand nombre le plaisir et les bienfaits du sport",20,40);
        g.drawString("Telle est la promesse que SPORT'ASSOC propose à ses clients depuis ses débuts.",20,60);
        
        g.setFont(new Font("Times", Font.BOLD, 10));
        g.setColor(Color.black);
        g.drawString("Regroupant tous les sports sous un même toit, SPORT'ASSOC n a cessé depuis son origine de favoriser l accessibilité à la pratique du sport : ", 20, 80);
        
        g.drawString("  ¤ Accessibilité par le prix tout en gardant une exigeance forte sur le développement de la qualité de nos produits.", 20, 95);
        g.drawString(" 	  Notre organisation nous permet encore de baisser régulièrement le prix de nos produits.",25,110);
        
        g.drawString("  ¤ Accessibilité par une large gamme de produits sportifs. ",20,130);
        g.drawString("	  Cette gamme est composée à la fois de nos marques propres et des produits de marques Internationales.", 25, 145);
        
        g.drawString("Au delà de ces produits, SPORT'ASSOC propose également de nombreux services qui permettent également de faciliter ",20,170);
        g.drawString("la pratique du sport : nos ateliers, nos avantages clients, les bons d'achat.", 20, 190);
        
        g.drawString("Et depuis quelques années, SPORT'ASSOC est allé plus loin dans sa relation avec ses clients en partageant le sport",20,210);
        g.drawString("autour d'événements clients acteurs. ",20,230);
        
        g.setColor(Color.gray);
        g.setFont(new Font("Times", Font.BOLD, 16));
        g.drawString("Alexis Louvel, directeur de SPORT'ASSOC", 250, 270);
        
        
	}  
}
