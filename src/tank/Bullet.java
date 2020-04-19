package tank;


import java.awt.*;
import java.io.IOException;


public class Bullet extends Animation {
    private final int speed = 30;
    private int index, x, y;
    private int[] angle;
    private Sprite explosion, bullet;
    private Tank tankOne, tankTwo;
    private Wall wall;
    private int mapHeight = 40;
    private int mapWidth = 39;
    private int rectangleWidth1 = 5;
    private int rectangleWidth2 = 10;
    private int rectangleHeight1 = 5;
    private int rectangleHeight2 = 10;
    private GameWorld game;
    private Player playerOne, playerTwo;

    public Bullet(GameWorld game, Tank tankOne, Tank tankTwo, Player playerOne, Player playerTwo,
                  Wall wall, Sprite bulletDirection, int x, int y, int delay, boolean loop) {
        super(bulletDirection, x, y, delay, loop);
       
        this.game = game;
        this.x = x;
        this.y = y;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.bullet = bulletDirection;
        this.angle = tankOne.getAngle();
        this.index = tankOne.getRotation();
        this.tankOne = tankOne;
        this.tankTwo = tankTwo;
        this.wall = wall;

        try {            
           explosion = new Sprite("src/resource/Explosion.png", 24);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean collideWall() {
        try {           
            wall.setWallMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle bullets = new Rectangle(x, y, rectangleWidth2, rectangleHeight2);

        for (int row = 0; row < mapHeight; row++) {
            for (int column = 0; column < mapWidth; column++) {

                if ( wall.getWall()[row][column].equals("1") || wall.getWall()[row][column].equals("2")){
                    Rectangle walls = new Rectangle(column * wall.getWidth(), row * wall.getHeight(),
                                      wall.getWidth(), wall.getHeight());

                    if (bullets.intersects(walls)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean collideTank() {
        Rectangle bullets = new Rectangle(x, y, rectangleWidth1, rectangleHeight1);
        Rectangle tank_aRec = new Rectangle(tankOne.getImageX(), tankOne.getImageY(), tankOne.getWidth() - 10, tankOne.getHeight()-10);
        Rectangle tank_bRec = new Rectangle(tankTwo.getImageX(), tankTwo.getImageY(), tankTwo.getWidth() - 10, tankTwo.getHeight()-10);

        return (bullets.intersects(tank_aRec) || bullets.intersects(tank_bRec));
    }

    public void hitWall() {
        try {            
            wall.setWallMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle bullets = new Rectangle(x, y, rectangleWidth2, rectangleHeight2);

        for (int row = 0; row < mapHeight; row++) {
            for (int column = 0; column < mapWidth; column++) {
                
                if (wall.getWall()[row][column].equals("2")) {
                    Rectangle walls = new Rectangle(column * wall.getWidth(), row * wall.getHeight(),
                                            wall.getWidth(), wall.getHeight());

                    if (bullets.intersects(walls)) {
                        wall.getWall()[row][column] = "0";
                        wall.setWall(wall.getWall());
                    }
                }
            }
        }
    }

    public void hitTank() {
        Rectangle bullets = new Rectangle(x, y, rectangleWidth1, rectangleHeight1);
        Rectangle tank_aRec = new Rectangle(tankOne.getImageX(), tankOne.getImageY(), tankOne.getWidth()-10, tankOne.getHeight()-10);
        Rectangle tank_bRec = new Rectangle(tankTwo.getImageX(), tankTwo.getImageY(), tankTwo.getWidth()-10, tankTwo.getHeight()-10);

        if (bullets.intersects(tank_aRec)) {
            playerOne.attack();

            if (playerOne.loseHealth()) {
                playerTwo.addScore();
                explosion(tankOne);
                playerOne.setPosition();
                tankOne.setRotation(0);
            }
        }

        if (bullets.intersects(tank_bRec)) {
            playerTwo.attack();

            if (playerTwo.loseHealth()) {
                playerOne.addScore();
                explosion(tankTwo);
                playerTwo.setPosition();
                tankTwo.setRotation(0);
            }
        }
    }

    public void shootForward() {
        x += (int) (Math.cos(Math.toRadians(angle[index])) * speed);
        y -= (int) (Math.sin(Math.toRadians(angle[index])) * speed);
    } 
    
    public void explosion(Tank tankExplosion) {
        game.animate(new Animation(this.explosion, tankExplosion.getImageX(), tankExplosion.getImageY(), 5, false));
    }

    
    @Override
    public void draw(Graphics graphics) {
  
        if(!stop) {
            shootForward();
            graphics.drawImage(bullet.getImage(index), x, y, null);

            if (collideWall()) {
                hitWall();
                stop = true;
            }
            if (collideTank()) {
                hitTank();
                stop = true;
            }
        }
    }
}
