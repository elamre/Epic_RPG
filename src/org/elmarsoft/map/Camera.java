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
    private static final int CAMERA_SPEED = 1;
    private static Camera ourInstance = new Camera();
    private float x = 0;
    private float y = 0;
    private float targetX = 0;
    private float targetY = 0;

    public static Camera getInstance() {
        return ourInstance;
    }

    private Camera() {
    }

    public void update(float deltaT) {
        int cameraDelta = (int) deltaT * CAMERA_SPEED;
        if (Math.abs(targetX - x) > cameraDelta) {
            if (targetX > x)
                x += cameraDelta;
            else
                x -= cameraDelta;
        } else
            x = targetX;
        if (Math.abs(targetY - y) > cameraDelta) {
            if (targetY > y)
                y += cameraDelta;
            else
                y -= cameraDelta;
        } else
            y = targetY;
    }


    public void lockOnTarget(Entity entity) {
        this.targetX =  entity.getX()- (Game.SCREENWIDTH / Game.SCALE) / 2;
        this.targetY =  entity.getY()- (Game.SCREENHEIGTH / Game.SCALE) / 2;
    }

    public void lockScreen(Graphics graphics) {
        graphics.translate(-x * Game.SCALE, -y * Game.SCALE);
    }

    public void unlockScreen(Graphics graphics) {
        graphics.translate(x, y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
