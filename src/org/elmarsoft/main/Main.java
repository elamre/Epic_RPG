package org.elmarsoft.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main{

    public static void main(String[] args) {
        try {
            Game game = new Game("RPG");
            AppGameContainer app = new AppGameContainer(game);
            app.setDisplayMode(Game.SCREENWIDTH, Game.SCREENHEIGTH, false);
            app.setAlwaysRender(true);
            app.setShowFPS(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
