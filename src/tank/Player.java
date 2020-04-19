package tank;

import java.awt.*;

public class Player {

    private boolean gameOver = false;
    private int tankX, tankY;
    private int health, maxHealth;
    private int lives = 3;
    private int score = 0;
    private int mapY = 40;
    private int mapX = 39;
    private int damageDealt = 15;
    private PowerUp powerUp;
    private String position;
    private Tank tank;
    private Wall wall;

    public Player(Tank tank, Wall wall, PowerUp powerUp, String position) {
        this.tank = tank;
        this.wall = wall;
        this.powerUp = powerUp;
        this.position = position;
        maxHealth = 300;
        health = 300;
    }

    public void displayHealth (Graphics graphic, int x, int y) {
        if (health > 250){
            graphic.setColor(Color.green);
            graphic.fill3DRect(x, y, health,35, true);
        }
        else if (health > 200 && health <= 250) {
            graphic.setColor(Color.cyan);
            graphic.fill3DRect(x, y, health,35, true);
        }
        else if (health > 150 && health <= 200) {
            graphic.setColor(Color.yellow);
            graphic.fill3DRect(x, y, health,35, true);
        }
        else if (health > 100 && health <= 150) {
            graphic.setColor(Color.orange);
            graphic.fill3DRect(x, y, health,35, true);
        }
        else if (health > 50 && health <= 100) {
            graphic.setColor(Color.red);
            graphic.fill3DRect(x, y, health,35, true);
        }
        else if (health > 0 && health <= 50){
            graphic.setColor(Color.pink);
            graphic.fill3DRect(x, y, health,35, true);
        }
        else {
            lives--;
            health = 300;
        }
    }

    public void displayLives (Graphics graphics, int x, int y) {
        graphics.setColor(Color.white);

        for (int i = 0; i < lives; i++) {
            graphics.fillOval(x + i*30, y, 25, 25);
        }

        if (lives == 0) {
            this.gameOver = true;
            graphics.setColor(Color.black);
            Font display = graphics.getFont().deriveFont(120.0f);
            graphics.setFont(display);
            graphics.drawString("GAME OVER!", 280, 350);
        }
    }

    public void displayScore (Graphics graphics, int x, int y) {
        graphics.setColor(Color.darkGray);
        Font scores = graphics.getFont().deriveFont(30.0f);
        graphics.setFont(scores);
        graphics.drawString("Scores: " + this.score, x, y);
    }

    public void setPosition() {
        for (int row = 0; row < mapY; row++) {
            for (int column = 0; column < mapX; column++) {

                if (wall.getWall()[row][column].equals(position)) {
                    tankX = column * wall.getWidth();
                    tankY = row * wall.getHeight();
                }
            }
        }
        tank.setImageX(tankX);
        tank.setImageY(tankY);
    }

    public void addScore() {
        score++;
    }

    public boolean gameOver() {
        return gameOver;
    }

    public void attack() {
        this.health -= damageDealt;
    }

    public boolean loseHealth() {
        Sound explosion = new Sound();
        explosion.playOnce("src/resource/explosive.wav");
        return health == 0;
    }

    public void powerUpEffect() {
        String power = powerUp.randomPowerUp();

        switch(power) {

            case "Increase Damage by 120%":
                damageDealt *= 1.2;
                System.out.println("Damage increased by 120%");
                break;

            case "Increase Damage by 140%":
                damageDealt *= 1.4;
                System.out.println("Damage increased by 140%");
                break;

            case "Increase Lives":
                lives++;
                System.out.println("One life increased");
                break;

            case "Increase Health by 110%":
                maxHealth *= 1.1;
                health = maxHealth;
                System.out.println("Health increased by 110%");
                break;

            case "Increase Health by 130%":
                maxHealth *= 1.3;
                health = maxHealth;
                System.out.println("Health increased by 130%");
                break;


        }
    }

}



