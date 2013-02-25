package org.elmarsoft.main;

import org.elmarsoft.entity.Player;
import org.elmarsoft.map.Camera;
import org.elmarsoft.map.MapOrganizer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 8:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends BasicGame {
    public static final int SCREENWIDTH = 640;
    public static final int SCREENHEIGTH = 480;
    public static final int SCALE = 1;
    private Player player;
    private MapOrganizer map;

    /**
     * Create a new basic game
     *
     * @param title The title for the game
     */
    public Game(String title) {
        super(title);
    }

    /**
     * @see org.newdawn.slick.Game#init(org.newdawn.slick.GameContainer)
     */
    @Override
    public void init(GameContainer container) throws SlickException {
        player = new Player(false);
        map = new MapOrganizer();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @see org.newdawn.slick.Game#update(org.newdawn.slick.GameContainer, int)
     */
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        player.update(delta, container);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Render the game's screen here.
     *
     * @param container The container holing this game
     * @param g         The graphics context that can be used to render. However, normal rendering
     *                  routines can also be used.
     * @throws org.newdawn.slick.SlickException
     *          Throw to indicate a internal error
     */
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        Camera.getInstance().lockOnTarget(player);
        Rectangle screenPos = new Rectangle(0 + Camera.getInstance().getX(), 0 + Camera.getInstance().getY(), Game.SCREENWIDTH, Game.SCREENHEIGTH);
        Camera.getInstance().lockScreen(g);
        map.draw(g);
        Camera.getInstance().unlockScreen(g);
    }
}
