package piłka;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ball {

	public static Image ball = new ImageIcon("ball.png").getImage();
	
	public Image getBall() {
		return ball;
	}
	

	int xPliki = ball.getWidth(null);
	int yPliki = ball.getHeight(null);
	int x = 0;
	int y = 0;
	int dx = 1;
	int dy = 1;
	int iloscOdbic = 0;
	public void ruszPilka(JPanel panel) {

		int xPanelu = (int) panel.getBounds().getMaxX();
		int yPanelu = (int) panel.getBounds().getMaxY();
		
		x += dx;
		y += dy;

		if (x >= xPanelu - xPliki) {
			x = xPanelu - xPliki;
			dx = dx * (-1);
			iloscOdbic++;
		}

		if (y >= yPanelu - yPliki) {
			y = yPanelu - yPliki;
			dy = dy * (-1);
			iloscOdbic++;
		}
		if (x == 0) {
			dx = dx * (-1);
			iloscOdbic++;
		}
		if (y == 0) {
			dy = dy * (-1);
			iloscOdbic++;
		}
	}
}
