package tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Wall extends Image {

    private BufferedImage unbreakable;
    private int mapX = 39;
    private int mapY = 40;
    private static BufferedReader map;
    private Sprite powerUp;
    private String[][] wall = new String[mapY][mapX];

    public Wall(String wall) throws IOException {
        super(wall);
               
        map = new BufferedReader(new FileReader("src/resource/GameMap.txt"));
        powerUp = new Sprite("src/resource/fire.png", 32);
        unbreakable = ImageIO.read(new File("src/resource/blue.gif"));

    }

    public String[][] getWall() {
        return wall;
    }

    public void setWall(String[][] wall) {
        this.wall = wall;
    }

    public void setWallMap() throws IOException {
         int row = 0;
         String mapInfo;

         while ((mapInfo = map.readLine()) != null) {
            for (int column = 0; column < wall[row].length; column++) {
                wall[row][column] = String.valueOf(mapInfo.charAt(column));
            }
            row++;
        }
    }

    @Override  
    public void draw (Graphics graphics) {
        for (x = 0; x < mapX; x++) {
            for (y = 0; y < mapY; y++) {

                if (wall[y][x].equals("1")) {
                    graphics.drawImage(unbreakable, x * unbreakable.getWidth(), y * unbreakable.getHeight(), observer);
                }
                else if (wall[y][x].equals("2")) {
                    graphics.drawImage(image, x * image.getWidth(), y * image.getHeight(), observer);
                }
                else if (wall[y][x].equals("5")) {
                    graphics.drawImage(powerUp.getImage(3), x * powerUp.getImage(3).getWidth(), y * powerUp.getImage(3).getHeight(), observer);
                }
            }
        }
    }

}

