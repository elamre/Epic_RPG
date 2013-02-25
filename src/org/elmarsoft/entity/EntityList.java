package org.elmarsoft.entity;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class EntityList {
    public static class Player extends EntityList {
        public Player() {
            super(1, 4, 0);
        }
    }

    Point point;
    int id = 0;

    public EntityList(int id, int x, int y) {
        point = new Point(x, y);
        this.id = id;
    }

    public Point getPoint() {
        return point;
    }

    public int getId() {
        return id;
    }
}
