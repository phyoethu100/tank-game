package tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Control extends GameWorld implements KeyListener {

    private boolean pressA, pressD, pressS, pressW, pressSpace; // Tank 1
    private boolean pressUp, pressDown, pressLeft, pressRight, pressEnter; // Tank 2
    private Sound sound;
    private Sprite bullet, explosion;

    public Control() {
        super();

        this.setFocusable(true);
        this.addKeyListener(this);
        new Thread(this).start();

        try {
            this.bullet = new Sprite("src/resource/Shells.png", 24);
            this.explosion = new Sprite("src/resource/Explosion.png", 32);
            this.sound = new Sound();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        tankOne.setDegree();
        tankTwo.setDegree();

        do {
            if (!(playerOne.gameOver() || playerTwo.gameOver())) {

                if (pressA) {
                    tankOne.rotateLeft();
                }

                if (pressD) {
                    tankOne.rotateRight();
                }

                if (pressW) {
                    tankOne.moveForward();

                    if (!((tankOne.collideWall(breakableWall)) || (tankOne.collideTank(tankTwo)))) {
                        if (tankOne.collidePowerUp(breakableWall)) {
                            playerOne.powerUpEffect();
                        }
                        tankOne.setImageX(tankOne.getMoveX());
                        tankOne.setImageY(tankOne.getMoveY());
                    }
                }

                if (pressS) {
                    tankOne.moveBackward();

                    if (!((tankOne.collideWall(breakableWall)) || (tankOne.collideTank(tankTwo)))) {
                        if(tankOne.collidePowerUp(breakableWall)) {
                            playerOne.powerUpEffect();
                        }
                        tankOne.setImageX(tankOne.getMoveX());
                        tankOne.setImageY(tankOne.getMoveY());
                    }
                }

                if (pressSpace) {
                    animate(new Animation(this.explosion, tankOne.shootX(), tankOne.shootY(), 1, false));
                    animate(new Bullet(this, tankOne, tankTwo, playerOne, playerTwo,
                            breakableWall, this.bullet, tankOne.shootX(), tankOne.shootY(), 5, false));

                    sound.playOnce("src/resource/shotgun.wav");
                }

                if (pressLeft) {
                    tankTwo.rotateLeft();
                }
                if (pressRight) {
                    tankTwo.rotateRight();
                }

                if (pressUp) {
                    tankTwo.moveForward();

                    if (!((tankTwo.collideWall(breakableWall)) || (tankTwo.collideTank(tankOne)))) {
                        if(tankTwo.collidePowerUp(breakableWall)) {
                            playerTwo.powerUpEffect();
                        }
                        tankTwo.setImageX(tankTwo.getMoveX());
                        tankTwo.setImageY(tankTwo.getMoveY());
                    }
                }

                if (pressDown) {
                    tankTwo.moveBackward();

                    if (!((tankTwo.collideWall(breakableWall)) || (tankTwo.collideTank(tankOne)))) {
                        if(tankTwo.collidePowerUp(breakableWall)) {
                            playerTwo.powerUpEffect();
                        }
                        tankTwo.setImageX(tankTwo.getMoveX());
                        tankTwo.setImageY(tankTwo.getMoveY());
                    }
                }

                if (pressEnter) {
                    animate (new Animation(this.explosion, tankTwo.shootX(), tankTwo.shootY(), 1, false));
                    animate(new Bullet(this, tankTwo, tankOne, playerTwo, playerOne,
                            breakableWall, this.bullet, tankTwo.shootX(), tankTwo.shootY(), 5, false));

                    sound.playOnce("src/resource/gun.wav");
                }

            }

            this.repaint();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (true);

    }
    
    @Override
    public void keyPressed(KeyEvent k) {

        int keyPressed = k.getKeyCode();

        if (keyPressed == KeyEvent.VK_A) {
            pressA = true;
        }
        else if (keyPressed == KeyEvent.VK_D) {
            pressD = true;
        }
        else if (keyPressed == KeyEvent.VK_S) {
            pressS = true;
        }
        else if (keyPressed == KeyEvent.VK_W) {
            pressW = true;
        }
        else if (keyPressed == KeyEvent.VK_SPACE) {
            pressSpace = true;
        }

        else if (keyPressed == KeyEvent.VK_UP) {
            pressUp = true;
        }
        else if (keyPressed == KeyEvent.VK_DOWN) {
            pressDown = true;
        }
        else if (keyPressed == KeyEvent.VK_LEFT) {
            pressLeft = true;
        }
        else if (keyPressed == KeyEvent.VK_RIGHT) {
            pressRight = true;
        }
        else if (keyPressed == KeyEvent.VK_ENTER) {
            pressEnter = true;
        }
       
    }

    @Override
    public void keyTyped(KeyEvent k) {}

    @Override
    public void keyReleased(KeyEvent k) {

        int keyReleased = k.getKeyCode();

        if (keyReleased == KeyEvent.VK_A) {
            pressA = false;
        }
        else if (keyReleased == KeyEvent.VK_D) {
            pressD = false;
        }
        else if (keyReleased == KeyEvent.VK_S) {
            pressS = false;
        }
        else if (keyReleased == KeyEvent.VK_W) {
            pressW = false;
        }
        else if (keyReleased == KeyEvent.VK_SPACE) {
            pressSpace = false;
        }

        else if (keyReleased == KeyEvent.VK_UP) {
            pressUp = false;
        }
        else if (keyReleased == KeyEvent.VK_DOWN) {
            pressDown = false;
        }
        else if (keyReleased == KeyEvent.VK_LEFT) {
            pressLeft = false;
        }
        else if (keyReleased == KeyEvent.VK_RIGHT) {
            pressRight = false;
        }
        else if (keyReleased == KeyEvent.VK_ENTER) {
            pressEnter = false;
        }

    }

}



