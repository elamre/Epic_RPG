package org.elmarsoft.image;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 9:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImageGetter {
    private static ImageGetter ourInstance;
    private Image blockImages;
    private SpriteSheet sheet;

    public static ImageGetter getInstance() {
        if (ourInstance == null)
            ourInstance = new ImageGetter();
        return ourInstance;
    }

    private ImageGetter() {
        try {
            blockImages = new Image("rsc/imgs/block_sht_1.png");
        } catch (SlickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        sheet = new SpriteSheet(blockImages, 16, 16, 1, 1);
    }

    public Image getImage(Point point) {
        return sheet.getSprite((int) point.getX(), (int) point.getY());
    }

    public Image getImage(int indexX, int indexY) {
        return sheet.getSprite(indexX, indexY);
    }
}
