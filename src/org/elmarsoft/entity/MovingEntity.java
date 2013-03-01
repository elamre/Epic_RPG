package org.elmarsoft.entity;

import org.newdawn.slick.GameContainer;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MovingEntity extends Entity {
    protected float dx = 0;
    protected float dy = 0;
    protected float speed = 1f;
    protected float destinationX = 0;
    protected float destinationY = 0;

    public MovingEntity(int entityId, boolean solid) {
        super(entityId, solid);
    }

    public MovingEntity(int entityId, boolean solid, int x, int y) {
        super(entityId, solid, x, y);
    }

    public void update(float deltaT, GameContainer gameContainer) {
        this.x += dx * speed;
        this.y += dy * speed;
        updateImplement(deltaT, gameContainer);
    }

    public void setDest(int destionationX, int destinationY) {
        this.destinationX = destionationX;
        this.destinationY = destinationY;
    }

    protected abstract void updateImplement(float deltaT, GameContainer gameContainer);
}
