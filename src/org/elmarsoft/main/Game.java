package org.elmarsoft.main;

import org.elmarsoft.entity.Player;
import org.elmarsoft.weather.DayNight;
import org.elmarsoft.weather.Weather;
import org.elmarsoft.map.Camera;
import org.elmarsoft.map.MapOrganizer;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 8:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends BasicGame {
    public static final float SCREENWIDTH = 640;
    public static final float SCREENHEIGTH = 480;
    public static final int TILESIZE = 16;
    public static final int SCALE = 2;
    private Player player;
    private Weather weather = new Weather();
    private DayNight day= new DayNight();

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
        MapOrganizer.getMapOrganizer();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @see org.newdawn.slick.Game#update(org.newdawn.slick.GameContainer, int)
     */
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        player.update(delta, container);
        Camera.getInstance().update(delta);
        weather.update(delta);
        day.update(delta);
        if (container.getInput().isKeyPressed(Input.KEY_1)) {
            weather.setWeather(Weather.CLEARWEATHER);
        } else if (container.getInput().isKeyPressed(Input.KEY_2)) {
            weather.setWeather(Weather.CLOUDY);
        } else if (container.getInput().isKeyPressed(Input.KEY_3)) {
            weather.setWeather(Weather.RAINY);
        } else if (container.getInput().isKeyPressed(Input.KEY_4)) {
            weather.setWeather(Weather.STORM);
        }
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
        Rectangle screenPos = new Rectangle(0 + Camera.getInstance().getX(), 0 + Camera.getInstance().getY(), Game.SCREENWIDTH / Game.SCALE + 1, Game.SCREENHEIGTH / Game.SCALE + 1);
        Camera.getInstance().lockScreen(g);
        g.scale(Game.SCALE, Game.SCALE);
        MapOrganizer.getMapOrganizer().draw(g);
        player.draw(g, screenPos);
        weather.draw(g, screenPos);
        Camera.getInstance().unlockScreen(g);
        day.draw(g);
    }
}
