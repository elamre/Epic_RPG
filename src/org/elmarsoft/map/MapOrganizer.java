package org.elmarsoft.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapOrganizer {
    private static MapOrganizer mapOrganizer;
    ArrayList<Integer> solidBlocks = new ArrayList<Integer>();
    TiledMap map;

    public static MapOrganizer getMapOrganizer() {
        if (mapOrganizer == null)
            mapOrganizer = new MapOrganizer();
        return mapOrganizer;
    }

    public boolean isSolid(int id) {
        if (solidBlocks.contains(id)) {
            return true;
        }
        return false;
    }

    public int getWidth() {
        return map.getWidth();
    }

    public int getHeight() {
        return map.getHeight();
    }

    public MapOrganizer() {
        try {
            map = new TiledMap("rsc/maps/map_1.tmx", "rsc/imgs");
        } catch (SlickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        solidBlocks.add(0);
        solidBlocks.add(2);
        solidBlocks.add(3);
        solidBlocks.add(7);
        solidBlocks.add(8);
    }

    public void draw(Graphics g) {
        map.render(0, 0);
    }

    public int getTile(int x, int y) {
        if (x >= 0 && y >= 0 && x < map.getWidth() && y < map.getHeight())
            return map.getTileId(x, y, 0);
        return 0;
    }
}
