package org.elmarsoft.weather;

import org.elmarsoft.main.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 3/1/13
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class DayNight {
    final int CLOCK_DIAMETER = 50;
    int x = 0, y = 180;
    final float MS_TO_HOUR = 10000;
    float timePassed = 0;
    float hours = 20;
    float darkness = 0.2f;
    float targetDarkness = 0.2f;
    float clockTime = 0;
    boolean increment = true;
    int minutes = 0;
    Rectangle playerVisibility;
    Circle circle;

    public DayNight() {

    }

    public void update(float deltaT) {
        updateDarkness(deltaT);
        updateClock(deltaT);
        //  System.out.println("Increment: " + increment + " Hours: " + hours + " darkness: " + darkness + " Target darkness: " + targetDarkness + " Time passed: " + timePassed + " clockTime: " + clockTime);
    }

    private void updateClock(float deltaT) {
        clockTime += deltaT;
        if (clockTime > 24 * MS_TO_HOUR)
            clockTime -= 24 * MS_TO_HOUR;
        //To change body of created methods use File | Settings | File Templates.
    }

    private void updateDarkness(float deltaT) {
        if (increment) {
            timePassed += deltaT;
        } else {
            timePassed -= deltaT;
        }
        targetDarkness = timePassed / (12 * MS_TO_HOUR);
        if (timePassed > 12 * MS_TO_HOUR) {
            increment = false;
        } else if (timePassed < 1) {
            increment = true;
        }
        if (darkness > targetDarkness) {
            darkness -= (darkness - targetDarkness) / 200;
        } else if (darkness < targetDarkness) {
            darkness += (targetDarkness - darkness) / 200;
        }
    }

    public void draw(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, darkness));
        graphics.fillRect(0, 0, Game.SCREENWIDTH, Game.SCREENHEIGTH);
        int lineX = 0, lineY = 0;
        graphics.setColor(Color.red);

        graphics.drawOval(x, y, CLOCK_DIAMETER, CLOCK_DIAMETER);
        //System.out.println("X: " + x + " Y: " + y);
        lineX = (int) (Math.cos(Math.toRadians((clockTime / (12 * MS_TO_HOUR)) * 360 - 90)) * (CLOCK_DIAMETER / 2) + x + CLOCK_DIAMETER / 2);
        lineY = (int) (Math.sin(Math.toRadians((clockTime / (12 * MS_TO_HOUR)) * 360 - 90)) * (CLOCK_DIAMETER / 2) + y + CLOCK_DIAMETER / 2);
        graphics.drawLine(x + CLOCK_DIAMETER / 2, y + CLOCK_DIAMETER / 2, lineX, lineY);
    }
}
