package org.elmarsoft.entity;

import org.elmarsoft.main.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/27/13
 * Time: 1:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Footstep extends Entity {
    private Rectangle foot1, foot2;
    int duration = 0;
    private float alpha = 0.5f;

    public Footstep(float x, float y, int dir, int duration) {
        super(0, false, x, y);
        this.duration = duration;
        switch (dir) {
            case 1:          //up
                foot1 = new Rectangle(x, y + (0.25f * Game.TILESIZE), (0.25f * Game.TILESIZE), (0.5f * Game.TILESIZE));
                foot2 = new Rectangle(x + (0.75f * Game.TILESIZE), y + (0.5f * Game.TILESIZE), (0.25f * Game.TILESIZE), (0.5f * Game.TILESIZE));
                break;
            case 2:        //right
                foot1 = new Rectangle(x + (0.5f * Game.TILESIZE), y, (0.5f * Game.TILESIZE), (0.25f * Game.TILESIZE));
                foot2 = new Rectangle(x + (0.25f * Game.TILESIZE), y + (0.75f * Game.TILESIZE), (0.5f * Game.TILESIZE), (0.25f * Game.TILESIZE));
                break;
            case 3:        //down
                foot1 = new Rectangle(x, y + (0.5f * Game.TILESIZE), (0.25f * Game.TILESIZE), (0.5f * Game.TILESIZE));
                foot2 = new Rectangle(x + (0.75f * Game.TILESIZE), y + (0.25f * Game.TILESIZE), (0.25f * Game.TILESIZE), (0.5f * Game.TILESIZE));
                break;
            case 4:        //left
                foot1 = new Rectangle(x + (0.25f * Game.TILESIZE), y, (0.5f * Game.TILESIZE), (0.25f * Game.TILESIZE));
                foot2 = new Rectangle(x + (0.5f * Game.TILESIZE), y + (0.75f * Game.TILESIZE), (0.5f * Game.TILESIZE), (0.25f * Game.TILESIZE));
                break;
        }
    }

    public void update(float deltaT) {
        if (alpha > 0.05) {
            alpha -= deltaT / duration;
        } else {
            if (alpha > 0) {
                alpha -= deltaT / 5*duration;
            }
        }
    }

    public boolean finished(){
        return alpha <= 0;
    }

    @Override
    public void implementDraw(Graphics graphics, Rectangle screenPos) {
        graphics.setColor(new Color(0.5f, 0.5f, 0.5f, alpha));
        graphics.fill(foot1);
        graphics.fill(foot2);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
