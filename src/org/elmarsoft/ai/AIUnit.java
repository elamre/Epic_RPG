package org.elmarsoft.ai;

import org.elmarsoft.entity.MovingEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 9:22 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AIUnit extends MovingEntity {
    String name = "";
    String text = "";

    public AIUnit(int x, int y, String name) {
        super(0, true, x, y);
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }


}
