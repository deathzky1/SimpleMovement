import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame implements KeyListener {

  Draw drawing;

  public MyFrame(){
      this.drawing = new Draw();
  }

   public void keyPressed(KeyEvent e){
       if(e.getKeyCode() == KeyEvent.VK_UP){
           drawing.moveUp();
            System.out.println(drawing.x + " , " + drawing.y);
       }
      else if (e.getKeyCode() == KeyEvent.VK_DOWN){
          drawing.moveDown();
          System.out.println(drawing.x + " , " + drawing.y);
      }
      else if (e.getKeyCode() == KeyEvent.VK_LEFT){
          drawing.moveLeft();
          System.out.println(drawing.x + " , " + drawing.y);
      }
      else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
          drawing.moveRight();
          System.out.println(drawing.x + " , " + drawing.y);
      }
      else if(e.getKeyCode() == KeyEvent.VK_SPACE){
      drawing.attack();
      System.out.println("attack");
  }
    else if(e.getKeyCode() == KeyEvent.VK_K){
      drawing.bowAttack();
      System.out.println("bow");
}
else if(e.getKeyCode() == KeyEvent.VK_J){
    drawing.combo();
      System.out.println("Combo 1: Special Attack");

}

}
  public void keyReleased(KeyEvent e){

  }

  public void keyTyped(KeyEvent e){

 }




  public static void main(String args[]){

      MyFrame gameFrame = new MyFrame();
      Draw drawing = new Draw();
      gameFrame.setSize(600,600);
      gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gameFrame.setVisible(true);
      gameFrame.getContentPane().add(gameFrame.drawing);
      gameFrame.addKeyListener(gameFrame);

}

  }