package org.elmarsoft.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapOrganizer {
    TiledMap map;
    public MapOrganizer() {
        try {
            map = new TiledMap("rsc/maps/map_1.tmx","rsc/imgs");
        } catch (SlickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void draw(Graphics g) {
        map.render(0,0);
    }
}
