package org.elmarsoft.main;

import org.newdawn.slick.*;

public class Main extends BasicGame {

    public Main(String title) {
        super(title);
    }

    public static void main(String[] args) {
        try {
            Main main = new Main("RPG");
            AppGameContainer app = new AppGameContainer(main);
            app.setDisplayMode(640, 400, false);
            app.setAlwaysRender(true);
            app.setShowFPS(true);
            app.setVSync(false);
            app.setVerbose(false);
            app.setMaximumLogicUpdateInterval(1);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
