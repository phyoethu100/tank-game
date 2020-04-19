package tank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class GameWorld extends JPanel implements Runnable {
  
    protected final String backgroundMap = "src/resource/sky.bmp";
    protected final String tank = "src/resource/TankImage.png";
    protected final int WIDTH = 1250;
    protected final int HEIGHT = 1280;    
    protected final int PANEL_HEIGHT = 700;    
    protected Dimension dimension;
    protected Image background;
    protected Wall breakableWall;
    protected Tank tankOne, tankTwo;
    protected Player playerOne, playerTwo;
    protected ArrayList<Animation> animation;
    protected BufferedImage miniMap = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    protected BufferedImage tankOneImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    protected BufferedImage tankTwoImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    protected Graphics miniGame;
    protected PowerUp powerUps;

    public GameWorld() {
        try {
          this.background = new Image(backgroundMap);
          this.tankOne = new Tank(tank);
          this.tankTwo = new Tank(tank);
          this.breakableWall = new Wall("src/resource/cyan.gif");
          this.breakableWall.setWallMap();
          this.powerUps = new PowerUp();
          playerOne = new Player(tankOne, breakableWall, powerUps, "3");
          playerTwo = new Player(tankTwo, breakableWall, powerUps, "4");
          playerOne.setPosition();
          playerTwo.setPosition();
          
        } catch(IOException exception) {
          System.err.println("Error in loading images!");
          exception.printStackTrace();
        }
        this.animation = new ArrayList<>();
        this.dimension = new Dimension(WIDTH, PANEL_HEIGHT);
    }

    @Override
    public Dimension getPreferredSize() {
         return this.dimension;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        miniGame = miniMap.createGraphics();

        for( int x = 0; x < WIDTH; x += background.getWidth() ) {
            for( int y = 0; y < HEIGHT; y+= background.getHeight() ) {
                background.setImageX( x );
                background.setImageY( y );              
                background.draw(miniGame);
            }
        }

        Animation animation;

        for(int counter = 0; counter < this.animation.size(); counter++) {
            animation = this.animation.get(counter);

            if(animation.stop()) {
                this.animation.remove(counter);
            } else {               
                animation.draw(miniGame);
            }
        }
      
        breakableWall.draw(miniGame);
        tankOne.draw(miniGame);
        tankTwo.draw(miniGame);
        miniGame = graphics;

        tankOneImage = miniMap.getSubimage(getTankPositionX(tankOne), getTankPositionY(tankOne), WIDTH/2, PANEL_HEIGHT);
        tankTwoImage = miniMap.getSubimage(getTankPositionX(tankTwo), getTankPositionY(tankTwo), WIDTH/2, PANEL_HEIGHT);

        miniGame.drawImage(tankOneImage,0,0,WIDTH/2-1,PANEL_HEIGHT,this);
        miniGame.drawImage(tankTwoImage,WIDTH/2,0,WIDTH/2,PANEL_HEIGHT,this);
        miniGame.drawImage(miniMap, HEIGHT/2-175, 225, 300, 230,this);
              
        playerOne.displayHealth(miniGame, 5, PANEL_HEIGHT-35);
        playerTwo.displayHealth(miniGame, WIDTH/2+10, PANEL_HEIGHT-35);
      
        playerOne.displayLives(miniGame, 480, PANEL_HEIGHT-35);
        playerTwo.displayLives(miniGame, WIDTH/2+480, PANEL_HEIGHT-35);
                
        playerOne.displayScore(miniGame,WIDTH/2-170, PANEL_HEIGHT-650);
        playerTwo.displayScore(miniGame,WIDTH-170, PANEL_HEIGHT-650);
        
    }
   
    public int getTankPositionX(Tank tanks) {
        int positionX = tanks.getImageX() - WIDTH / 4;

        if (positionX < 0) {
            positionX = 0;
        }
        if (positionX > (WIDTH/2)) {
            positionX = (WIDTH/2);
        }
        return positionX;
    }

   
    public int getTankPositionY(Tank tanks) {
        int positionY = tanks.getImageY() - PANEL_HEIGHT / 2;

        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > (HEIGHT - PANEL_HEIGHT)) {
            positionY = (HEIGHT - PANEL_HEIGHT);
        }
        return positionY;
    }
        
    public void animate (Animation animation)
    {
        this.animation.add (animation);
    }

}
