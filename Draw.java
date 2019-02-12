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
  public URL resource = getClass().getResource("run0.png");

  // circle's position
  public int x = 75;
  public int y = 435;
  public int height = 0;
  public int width = 0;

  // animation states
  public int state = 0;

  // randomizer
  public Random randomizer;

  // enemy
  public int enemyCount;
  Monster[] monsters = new Monster[20];

  public Draw(){
    randomizer = new Random();
    spawnEnemy();
    
    try{
      image = ImageIO.read(resource);
      backgroundImage = ImageIO.read(getClass().getResource("background.png"));
    }
    catch(IOException e){
      e.printStackTrace();
    }

    height = image.getHeight();
    width = image.getWidth();

    startGame();
  }

  public void startGame(){
    Thread gameThread = new Thread(new Runnable(){
      public void run(){
        while(true){
          try{
            for(int c = 0; c < monsters.length; c++){
              if(monsters[c]!=null){
                monsters[c].moveTo(x,y);
                repaint();
              }
            }
            Thread.sleep(100);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
        }
      }
    });
    gameThread.start();
  }

  public void spawnEnemy(){
    if(enemyCount < 20){
      monsters[enemyCount] = new Monster(randomizer.nextInt(500), randomizer.nextInt(500), this);
      enemyCount++;
    }
  }

  public void reloadImage(){
    state++;

    if(state == 0){
      resource = getClass().getResource("run0.png");
    }
    else if(state == 1){
      resource = getClass().getResource("run1.png");
    }
    else if(state == 2){
      resource = getClass().getResource("run2.png");
    }
    else if(state == 3){
      resource = getClass().getResource("run3.png");
    }
    else if(state == 4){
      resource = getClass().getResource("run4.png");
    }
    else if(state == 5){
      resource = getClass().getResource("run5.png");
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
        for(int ctr = 0; ctr < 4; ctr++){
          try {
            if(ctr==3){
              resource = getClass().getResource("run0.png");
            }
            else{
              resource = getClass().getResource("attack"+ctr+".png");
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
               resource = getClass().getResource("idle0.png");
           
             
            }
            else{
              resource = getClass().getResource("bow"+ctr+".png");
               
             
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
        for (int ctr =0; ctr < 15; ctr++) {

          try{
            if(ctr  == 13){
                resource = getClass().getResource("idle0.png");
         
           }
            else{
            
        resource = getClass().getResource("combo"+ctr+".png");

              
                
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
  public void attack(){
      attackAnimation();

  }
 public void bowAttack(){
    bowAnimation();
  }
  public void combo(){
    comboAnimation();

  }

  public void moveUp(){
    y = y - 5;
    reloadImage();
    repaint();
    checkCollision();
  }

  public void moveDown(){
    y = y + 5;
    reloadImage();
    repaint();
    checkCollision();
  }

  public void moveLeft(){
    x = x - 5;
    reloadImage();
    repaint();
    checkCollision();
  }

  public void moveRight(){
    x = x + 5;
    reloadImage();
    repaint();
    checkCollision();
  }

  public void checkCollision(){
    int xChecker = x + width;
    int yChecker = y;

    for(int x=0; x<monsters.length; x++){
      boolean collideX = false;
      boolean collideY = false;

      if(monsters[x]!=null){
        monsters[x].contact = false;

        if(yChecker > monsters[x].yPos){
          if(yChecker-monsters[x].yPos < monsters[x].height){
            collideY = true;
          }
        }
        else{
          if(monsters[x].yPos - yChecker < monsters[x].height){
            collideY = true;
          }
        }

        if(xChecker > monsters[x].xPos){
          if(xChecker-monsters[x].xPos < monsters[x].width){
            collideX = true;
          }
        }
        else{
          if(monsters[x].xPos - xChecker < 5){
            collideX = true;
          }
        }
      }

      if(collideX && collideY){
        System.out.println("collision!");
        monsters[x].contact = true;
      }
    }
  }

  
  
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.YELLOW);
    g.drawImage(backgroundImage, 0, 0, this);

    // character grid for hero
    // g.setColor(Color.YELLOW);
    // g.fillRect(x, y, width, height);
    g.drawImage(image, x, y, this);
    
    for(int c = 0; c < monsters.length; c++){
      if(monsters[c]!=null){
        // character grid for monsters
        // g.setColor(Color.BLUE);
        // g.fillRect(monsters[c].xPos, monsters[c].yPos+5, monsters[c].width, monsters[c].height);
        g.drawImage(monsters[c].image, monsters[c].xPos, monsters[c].yPos, this);
        g.setColor(Color.GREEN);
        g.fillRect(monsters[c].xPos+7, monsters[c].yPos, monsters[c].life, 2);
      } 
    }
  }
}