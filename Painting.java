import java.awt.*;
import javax.swing.*;

public class Painting extends JPanel{

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.setColor(Color.BLUE);
		g.fillOval(20, 20, 100, 100);
		g.setColor(Color.MAGENTA);
		g.fillRect(100,100,100,100);
		g.setColor(Color.GREEN);
		g.fillRoundRect(200,200,200,200,200,200);

		g.setColor(Color.BLACK);
		int [] x = {50,50,90,90,150,90,90};
        int [] y = {55,85,85,110,70,30,55};
        g.fillPolygon(x, y, 7);

 
		

	}

	public static void main(String args[]){
		JFrame myFrame = new JFrame("Painting");
		// JPanel panel1 = new JPanel();

		Painting myPainting = new Painting();

		// panel1.setBackground(Color.GREEN);

		myFrame.add(myPainting);

		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(700, 500);
		myFrame.setVisible(true);
	}
}