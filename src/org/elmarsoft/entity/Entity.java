package org.elmarsoft.entity;

import org.elmarsoft.image.ImageGetter;
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

    public Entity(int entityId, boolean solid) {
        this.solid = solid;
        switch (entityId) {
            case 1:
                image = ImageGetter.getInstance().getImage(4,0);
                break;
            default:
                image = ImageGetter.getInstance().getImage(0, 0);
                break;
        }
    }

    public void draw(Graphics graphics, Rectangle screenPos) {
        if (x + 16 > screenPos.getX() && x < screenPos.getMaxX()) {
            if (y + 16 > screenPos.getY() && y < screenPos.getMaxX()) {
                if (image != null)
                    image.draw(x, y);
                implementDraw(graphics);
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

    public abstract void implementDraw(Graphics graphics);
}
