package org.elmarsoft.weather;

import org.elmarsoft.main.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 3/1/13
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class DayNight {
    float hours;
    float timePassed = 0;

    public void update(float deltaT) {
        timePassed += deltaT;
        if (timePassed > 1000) {
            timePassed -= 1000;
            if (hours < 23) {
                hours++;
            } else {
                hours = 0;
            }
        }
    }

    public void draw(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 1 - (hours / 24)));
        graphics.fillRect(0, 0, Game.SCREENWIDTH, Game.SCREENHEIGTH);
    }
}
