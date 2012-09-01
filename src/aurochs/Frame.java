/**
 * 
 */
package aurochs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Sam
 *
 */
public class Frame extends JPanel implements Runnable{
	int desty;
	JFrame frame;
	int currentX;
	int currentY;
	int[] previousDirection = {1,1};
	
	public Frame() {
		super();
		desty = 100;
		currentY = 100;
		currentX = 100;
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.red);
    	try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Random generator = new Random();
		
		int randomIndex = generator.nextInt( 5 );
		int[] nextDirection = {0,0};
		
		switch (randomIndex) {
			case 0: case 1:
				nextDirection = previousDirection;
				break;
			case 2:
				nextDirection[0] = 1;
				nextDirection[1] = 1;
				break;
			case 3:
				nextDirection[0] = 1;
				nextDirection[1] = -1;
				break;
			case 4:
				nextDirection[0] = -1;
				nextDirection[1] = 1;
				break;
		}
		
		int newX = currentX + nextDirection[0];
		int newY = currentY + nextDirection[1];
		
		g2d.drawLine(currentX, currentY, newX, newY); 
		
		currentX = newX;
		currentY = newY;
		
		previousDirection = nextDirection;

		desty++;
		repaint();
		 
	}
	
	public void run() {
		frame = new JFrame("Frame");
		
		Frame panel = new Frame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
