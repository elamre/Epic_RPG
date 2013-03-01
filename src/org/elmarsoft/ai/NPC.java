package org.elmarsoft.ai;

import org.elmarsoft.ai.AIUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/28/13
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class NPC extends AIUnit {
    public NPC(int x, int y, String name) {
        super(x, y, name);
    }

    public abstract void action();
}
