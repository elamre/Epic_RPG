package org.elmarsoft.map;

import org.elmarsoft.entity.Entity;
import org.elmarsoft.main.Game;
import org.newdawn.slick.Graphics;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 8:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class Camera {
    private static Camera ourInstance = new Camera();
    private int x = 0;
    private int y = 0;

    public static Camera getInstance() {
        return ourInstance;
    }

    private Camera() {
    }

    public void lockOnTarget(Entity entity) {
        if (entity.getX() - Game.SCREENWIDTH / 2 > 0)
            this.x = (int) entity.getX() - Game.SCREENWIDTH / 2;
        if (entity.getY() - Game.SCREENHEIGTH / 2 > 0)
            this.y = (int) entity.getY() - Game.SCREENHEIGTH / 2;
    }

    public void lockScreen(Graphics graphics) {
        graphics.translate(-x, -y);
    }

    public void unlockScreen(Graphics graphics) {
        graphics.translate(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
