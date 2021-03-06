import javax.swing.JComponent;
   import javax.swing.Timer;
   import java.awt.Color;
   import java.awt.Graphics;
   import java.io.IOException;
   import javax.imageio.ImageIO;
   import java.awt.image.BufferedImage;
   import java.net.URL;
   import java.util.Random;


public class Draw extends JComponent{

  private BufferedImage image;
  private BufferedImage backgroundImage;
  private URL resource = getClass().getResource("run/run0.png");


        public int x = 30;
        public int y = 30;
    
        public int state = 0;

        Monster monster1;
        Monster monster2;

        public Draw(){
          monster1 = new Monster(200, 200);
          monster2 = new Monster(150,200);
          try{
          image = ImageIO.read(resource);
          backgroundImage = ImageIO.read(getClass().getResource("background.png"));
          }

          catch(IOException e){
            e.printStackTrace();
          }
        }

        public void reloadImage(){
          state++;
          if(state == 0){
            resource = getClass().getResource("run/run0.png");
          }
          else if(state == 1){
            resource = getClass().getResource("run/run1.png");
          }
          else if(state == 2){
            resource = getClass().getResource("run/run2.png");
          }
          else if(state == 3){
            resource = getClass().getResource("run/run3.png");
          }
          else if(state == 4){
            resource = getClass().getResource("run/run4.png");

          }
          else if(state == 5){
            resource = getClass().getResource("run/run5.png");
            state = 0;
          }

          try{
          image = ImageIO.read(resource);
          }
          catch(IOException e){
            e.printStackTrace();
          }
        }

      
          
      

         public void attackAnimation(){
    Thread thread1 = new Thread(new Runnable(){
      public void run(){
        for(int ctr = 0; ctr < 18; ctr++){
          try {
            if(ctr==17){
              resource = getClass().getResource("idle/idle0.png");
            }
            else{
              resource = getClass().getResource("attack/attack" + ctr + ".png");
            }
            
         try{
              image = ImageIO.read(resource);
            }
            catch(IOException e){
              e.printStackTrace();
            }
                repaint();
                Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    thread1.start();
  }

  public void bowAnimation(){
    Thread thread2 = new Thread(new Runnable(){
      public void run(){
        for (int ctr =0; ctr < 10; ctr++) {
          try{
            if(ctr == 9){
               resource = getClass().getResource("idle/idle0.png");
           
             
            }
            else{
              resource = getClass().getResource("bow/bow"+ctr+".png");
               
             
            }

            try{
              image = ImageIO.read(resource);
            }
            catch(IOException e){
              e.printStackTrace();
            } 
            repaint();
            Thread.sleep(100);
          } catch(InterruptedException e){
            e.printStackTrace();
          }
        }
      }
    });
    thread2.start();
  }

   public void comboAnimation(){
    Thread thread3 = new Thread(new Runnable(){
      public void run(){
        for (int ctr =0; ctr < 14; ctr++) {

          try{
            if(ctr  == 13){
                resource = getClass().getResource("idle/idle0.png");
         
           }
            else{
            
        resource = getClass().getResource("combo/combo"+ctr+".png");
 
            }

            try{
              image = ImageIO.read(resource);
            }
            catch(IOException e){
              e.printStackTrace();
            }
            repaint();
            Thread.sleep(100);
          } catch(InterruptedException e){
            e.printStackTrace();
          }
        }
      }
    });
    thread3.start();
  }

  
      public void moveUP(){
      y = y - 5;  
      repaint();
      reloadImage();
      }

      public  void moveDOWN(){
      y = y + 5;
      repaint();
      reloadImage();
    
      }

       public void moveLEFT(){
      x = x - 5;
      repaint();
      reloadImage();
  
      }
        public void moveRIGHT(){
       x = x + 5;
       repaint();
       reloadImage();
      }

      public void attack(){
      attackAnimation();

  }
  public void bowAttack(){
    bowAnimation();
  }
  public void combo(){
    comboAnimation();

  }
  
public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(backgroundImage, 0, 0, this);
    g.drawImage(image, x, y, this);
    g.drawImage(monster1.image, monster1.xPos, monster1.yPos, this);
    g.drawImage(monster2.image, monster2.xPos, monster2.yPos, this);
    g.setColor(Color.BLACK);
    g.fillRect(6, 18, 264, 34);
    g.setColor(Color.DARK_GRAY);
    
    //HealthBar
    g.setColor(Color.GREEN);
    g.fillRect(50, 25, 200, 10);

   
   
   


}

}


