package org.elmarsoft.ai;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/28/13
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestNPC extends NPC {

    public QuestNPC(int x, int y, String name) {
        super(x, y, name);
    }

    @Override
    public void action() {
        showText();
    }

    public void showText() {
    }

    @Override
    protected void updateImplement(float deltaT, GameContainer gameContainer) {

    }

    @Override
    public void implementDraw(Graphics graphics, Rectangle screenPos) {

    }
}
