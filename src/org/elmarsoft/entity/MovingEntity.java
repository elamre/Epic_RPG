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
    protected float speed = 0.5f;

    public MovingEntity(int entityId, boolean solid) {
        super(entityId, solid);
    }

    public void update(float deltaT, GameContainer gameContainer) {
        this.x += deltaT * dx * speed;
        this.y += deltaT * dy * speed;
        updateImplement(deltaT, gameContainer);
    }

    protected abstract void updateImplement(float deltaT, GameContainer gameContainer);
}
