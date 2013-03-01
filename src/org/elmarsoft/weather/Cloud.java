package org.elmarsoft.weather;

import org.elmarsoft.entity.MovingEntity;
import org.elmarsoft.main.Game;
import org.elmarsoft.map.MapOrganizer;
import org.elmarsoft.weather.Weather;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.Random;

public class Cloud extends MovingEntity {
    private static final int cloudSize = (int) Game.TILESIZE * 3;
    private Weather.WeatherSettings weather = Weather.CLEARWEATHER;
    Random random = new Random();
    private boolean alive = true;

    boolean[][] cloudAtlas = {
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
    };

    public Cloud(int x, int y) {
        super(0, false, x, y);
        System.out.println("Creating cloud at: " + x + " ," + y);
        changeWeather(Weather.STORM);
        calculateDxDy();
        createParts();
        this.width = cloudAtlas[0].length * cloudSize;
        this.heigth = cloudAtlas.length * cloudSize;
        speed = 0.05f;
    }

    public boolean isAlive() {
        return alive;
    }

    private void createParts() {
        addPart(5, 5, 1, 1);
        addPart(5, 5, 2, 1);
        addPart(5, 5, 3, 1);
        addPart(5, 5, 4, 1);
        for (int x = 1; x < cloudAtlas.length - 1; x++) {
            for (int y = 1; y < cloudAtlas[0].length - 1; y++) {
                if (cloudAtlas[x + 1][y - 1])
                    cloudAtlas[x][y] = true;

                //System.out.print(Boolean.compare(cloudAtlas[x][y], true) + ", ");
            }
            // System.out.println();
        }
    }

    private void addPart(int gridX, int gridY, int dir, int amount) {
        Random random = new Random();
        amount += 2;
        if (gridY < cloudAtlas.length && gridX < cloudAtlas[0].length - 1 && gridX > -1 && gridY > -1) {
            //System.out.println("dir: " + dir + " adding cloud: " + gridX + "  " + gridY);
            cloudAtlas[gridX][gridY] = true;
            if (random.nextInt(50) < (200 / amount)) {
                switch (dir) {
                    case 1:
                        addPart(gridX, gridY + 1, dir, amount);
                        break;
                    case 2:
                        addPart(gridX + 1, gridY, dir, amount);
                        break;
                    case 3:
                        addPart(gridX, gridY - 1, dir, amount);
                        break;
                    case 4:
                        addPart(gridX - 1, gridY, dir, amount);
                        break;
                }
            }
        }
    }

    public void changeWeather(Weather.WeatherSettings weather) {
        this.weather = weather;
        this.speed = weather.getSpeed();
    }

    private void calculateDxDy() {
        int rDx = 0;
        int rDy = 0;
        while ((rDx == rDy) && (rDx == 0)) {
            rDx = random.nextInt(3);
            rDy = random.nextInt(3);
        }
        switch (rDx) {
            case 0:
                dx = 0;
                break;
            case 1:
                dx = -1;
                break;
            case 2:
                dx = 1;
                break;
        }

        switch (rDy) {
            case 0:
                dy = 0;
                break;
            case 1:
                dy = -1;
                break;
            case 2:
                dy = 1;
                break;
        }
    }

    @Override
    protected void updateImplement(float deltaT, GameContainer gameContainer) {
        if (x + this.width < 0 || y + this.heigth < 0) {
            alive = false;
            System.out.println("Destroying cloud");
        } else if (x > MapOrganizer.getMapOrganizer().getWidth() * Game.TILESIZE || y > MapOrganizer.getMapOrganizer().getHeight() * Game.TILESIZE) {
            alive = false;
            System.out.println("Destroying cloud");
        }
    }

    @Override
    public void implementDraw(Graphics graphics, Rectangle screenPos) {
        for (int xOffset = 0; xOffset < cloudAtlas.length; xOffset++) {
            for (int yOffset = 0; yOffset < cloudAtlas.length; yOffset++) {
                if (cloudAtlas[xOffset][yOffset]) {
                    graphics.setColor(weather.getShadowColor());
                    graphics.fillRect(x + xOffset * cloudSize, y + yOffset * cloudSize, cloudSize, cloudSize);
                }
            }
        }
        for (int xOffset = 0; xOffset < cloudAtlas.length; xOffset++) {
            for (int yOffset = 0; yOffset < cloudAtlas.length; yOffset++) {
                if (cloudAtlas[xOffset][yOffset]) {
                    graphics.setColor(weather.getCloudColor());
                    graphics.fillRect(x + xOffset * cloudSize + 16, y + yOffset * cloudSize + 16, cloudSize, cloudSize);
                }
            }
        }

        //To change body of implemented methods use File | Settings | File Templates.
    }


}