package org.elmarsoft.entity;

import org.elmarsoft.image.ImageGetter;
import org.elmarsoft.main.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 8:40 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity {
    private Image image;
    private boolean solid;
    protected float x;
    protected float y;
    protected float width = Game.TILESIZE;
    protected float heigth = Game.TILESIZE;

    public Entity(int entityId, boolean solid, int x, int y) {
        this.x = x;
        this.y = y;
        this.solid = solid;
    }

    public Entity(int entityId, boolean solid, float x, float y) {
        this.x = x;
        this.y = y;
        this.solid = solid;
    }

    public Entity(int entityId, boolean solid) {
        this.solid = solid;
    }

    public void draw(Graphics graphics, Rectangle screenPos) {
        if (x + width > screenPos.getX() && x < screenPos.getMaxX()) {
            if (y + heigth > screenPos.getY() && y < screenPos.getMaxY()) {
                if (image != null) {
                    image.draw(x, y);
                }
                implementDraw(graphics, screenPos);
            }
        }
    }

    public boolean getSolid() {
        return solid;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public abstract void implementDraw(Graphics graphics, Rectangle screenPos);
}
