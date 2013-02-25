package org.elmarsoft.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 9:22 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AIUnit extends MovingEntity {
    String name = "";

    public AIUnit(int entityId, boolean solid, String name) {
        super(entityId, solid);
        this.name = name;
    }


}
