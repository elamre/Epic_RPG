package org.elmarsoft.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Player extends MovingEntity {

    public Player( boolean solid) {
        super(1, solid);
    }

    @Override
    protected void updateImplement(float deltaT, GameContainer gameContainer) {
        dx = 0;
        dy = 0;
        if (gameContainer.getInput().isKeyDown(Input.KEY_W)) {
            dy -= 1;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_S)) {
            dy += 1;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_D)) {
            dx += 1;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_A)) {
            dx -= 1;
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementDraw(Graphics graphics) {
       // graphics.fillRect(x, y, 16, 16);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
