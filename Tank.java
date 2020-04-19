package tank;

import java.awt.*;
import java.io.IOException;

public class Tank extends Image {
    private int[] angle = new int[60];
    private int mapY = 40;
    private int mapX = 39;
    private int moveX, moveY, degree, rotation = 0;
    private int speed = 15;
    private Sprite tank;

    public Tank(String location) throws IOException {
        super(location);

        tank = new Sprite("src/resource/TankImage.png", 64);
    }

    public void setDegree() {
        for (int row = 0; row < angle.length; row++) {
            angle[row] = degree;
            degree += 6;
        }
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int[] getAngle() {
        return angle;
    }

    public int getRotation() {
        return rotation;
    }

    public int getMoveX() {
        return this.moveX;
    }

    public int getMoveY() {
        return this.moveY;
    }

    public boolean collidePowerUp(Wall powerUp) {
        try {
            powerUp.setWallMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle tank = new Rectangle(moveX, moveY, getWidth() - speed, getHeight() - speed);

        for (int row = 0; row < mapY; row++) {
            for (int column = 0; column < mapX; column++) {

                if (powerUp.getWall()[row][column].equals("5")) {

                    Rectangle walls = new Rectangle(column * powerUp.getWidth(), row * powerUp.getHeight(),
                            powerUp.getWidth(), powerUp.getHeight());

                    if (tank.intersects(walls)) {
                        powerUp.getWall()[row][column] = "0";
                        powerUp.setWall(powerUp.getWall());
                        return true;
                    }
                }

            }
        }

        return false;
    }

    public boolean collideTank(Tank tank) {
        Rectangle tankOne = new Rectangle(moveX, moveY, getWidth() - speed, getHeight() - speed);
        Rectangle tankTwo = new Rectangle(tank.getImageX(), tank.getImageY(), tank.getWidth() - speed, tank.getHeight() - speed);

        return tankOne.intersects(tankTwo);
    }

    public boolean collideWall(Wall wall) {
        try {           
            wall.setWallMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle tank = new Rectangle(moveX, moveY, getWidth() - speed, getHeight() - speed);

        for (int row = 0; row < mapY; row++) {
            for (int column = 0; column < mapX; column++) {

                if (wall.getWall()[row][column].equals("1") || wall.getWall()[row][column].equals("2")) {

                    Rectangle walls = new Rectangle(column * wall.getWidth(), row * wall.getHeight(),
                                    wall.getWidth() - speed, wall.getHeight() - speed);

                    if (tank.intersects(walls)) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    public void rotateLeft() {
        if (rotation < 59) {
            rotation++;
        } else {
            rotation = 0;
        }
        image = tank.getImage(rotation);
    }

    public void rotateRight() {
        if (rotation > 0) {
            rotation--;
        } else {
            rotation = 59;
        }
        image = tank.getImage(rotation);
    }

    public void moveForward() {
        int vx = (int) (Math.cos(Math.toRadians(angle[rotation])) * speed);
        int vy = (int) (Math.sin(Math.toRadians(angle[rotation])) * speed);
        moveX = this.getImageX() + vx;
        moveY = this.getImageY() - vy;
    }

    public void moveBackward() {
        int vx = (int) (Math.cos(Math.toRadians(angle[rotation])) * speed);
        int vy = (int) (Math.sin(Math.toRadians(angle[rotation])) * speed);
        moveX = this.getImageX() - vx;
        moveY = this.getImageY() + vy;
    }

    public int shootX() {
        return this.x + this.getWidth() / 2
                + (int) ((int) (this.getWidth() / 2 * (Math.cos(Math.toRadians(50))))
                * 2 * (Math.cos(Math.toRadians(this.angle[rotation]))));
    }

    public int shootY() {
        return this.y + this.getHeight() / 2
                - (int) ((int) (this.getHeight() / 2 * (Math.cos(Math.toRadians(50))))
                * 2 * (Math.sin(Math.toRadians(this.angle[rotation]))));
    }

    @Override   
    public void draw (Graphics graphics) {
        graphics.drawImage (tank.getImage(rotation), x, y, observer);
    }
}


