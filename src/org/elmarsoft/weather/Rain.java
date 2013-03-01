package org.elmarsoft.weather;

import org.elmarsoft.map.Camera;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/27/13
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Rain {
    private ArrayList<RainDrup> rainDrups = new ArrayList<RainDrup>();
    private float rainTimer = 10;
    private int max = 250;
    boolean rain = false;

    public void setMax(int max) {
        this.max = max;
    }

    public void start() {
        rain = true;
    }

    public void stop() {
        rain = false;
    }

    public void update(float deltaT) {
        ArrayList<RainDrup> removalList = new ArrayList<RainDrup>();
        if (rain) {
            rainTimer -= deltaT;
            int offset = 200;
            if (rainDrups.size() < max) {
                if (rainTimer <= 0) {
                    Random random = new Random();
                    rainDrups.add(new RainDrup((int) Camera.getInstance().getX() + random.nextInt(640 + offset) - offset, (int) Camera.getInstance().getY() - 9, offset));
                    rainTimer += 10;
                }
            }
        }
        for (int i = 0; i < rainDrups.size(); i++) {
            rainDrups.get(i).update(deltaT);
            if (!rainDrups.get(i).isAlive()) {
                removalList.add(rainDrups.get(i));
            }
        }
        for (int i = 0; i < removalList.size(); i++) {
            rainDrups.remove(removalList.get(i));
        }
        removalList.clear();
    }

    public void draw(Graphics graphics, Rectangle screenPos) {
        graphics.setColor(new Color(0, 0, 1, 0.5f));
        for (int i = 0; i < rainDrups.size(); i++) {
            rainDrups.get(i).draw(graphics, screenPos);
        }
    }

    private class RainDrup {
        private boolean alive;
        float x, y;
        int length;
        int offset = 50;
        float speed = 7;

        public RainDrup(int x, int y, int offset) {
            this.offset = offset;
            this.x = x;
            this.y = y;
            this.length = new Random().nextInt(7) + 3;
            this.speed = 7 + new Random().nextFloat() * 2;
            alive = true;
        }

        public void update(float deltaT) {
            this.y += deltaT / speed;
            this.x += deltaT / (speed * 5);
        }

        public void draw(Graphics graphics, Rectangle screenPos) {
            boolean draw = false;
            if (this.x + length / 3 > screenPos.getX() && this.x < screenPos.getMaxX()) {
                if (this.y + 10 > screenPos.getY() && this.y - 10 < screenPos.getMaxY()) {
                    draw = true;
                }
            }
            if (this.x + offset > screenPos.getX() && this.x - offset < screenPos.getMaxX()) {
                if (this.y + 10 > screenPos.getY() && this.y - 10 < screenPos.getMaxY()) {
                    if (draw == true)
                        graphics.drawLine(x, y, x + (length / 3), y + length);
                } else {
                    alive = false;
                }
            } else {
                alive = false;
            }
        }

        public boolean isAlive() {
            return alive;
        }
    }
}
