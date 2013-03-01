package org.elmarsoft.entity;

import org.elmarsoft.main.Game;
import org.elmarsoft.map.Camera;
import org.elmarsoft.map.MapOrganizer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/25/13
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Player extends MovingEntity {
    enum Direction {
        UP(1, 0, -1), RIGHT(2, 1, 0), DOWN(3, 0, 1), LEFT(4, -1, 0);
        private int dir, dx, dy;

        Direction(int dir, int dx, int dy) {
            this.dir = dir;
            this.dx = dx;
            this.dy = dy;
        }

        public int getDir() {
            return dir;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }
    }

    Direction dir = Direction.DOWN;
    private ArrayList<Footstep> footSteps = new ArrayList<Footstep>();
    private float timer = 0;
    //TODO get proper movespeed
    private int moveSpeed = 130;
    private int destX = 0, destY = 0;
    private boolean canMove = true;
    private int direction = 1;

    public Player(boolean solid) {
        super(1, solid);
        //  this.x = 24 * Game.TILESIZE;
        // destX = (int) x;
        //  this.y = 19 * Game.TILESIZE;
        //  destY = (int) y;
        speed = 1f;
    }

    boolean keyPress = false;

    @Override
    protected void updateImplement(float deltaT, GameContainer gameContainer) {
        ArrayList<Footstep> removalList = new ArrayList<Footstep>();
        for (int i = 0; i < footSteps.size(); i++) {
            footSteps.get(i).update(deltaT);
            if (footSteps.get(i).finished()) {
                removalList.add(footSteps.get(i));
            }
        }
        for (int i = 0; i < removalList.size(); i++) {
            footSteps.remove(removalList.get(i));
        }
        removalList.clear();
        if (gameContainer.getInput().isKeyDown(Input.KEY_W)) {
            keyPress = true;
            dir = Direction.UP;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_S)) {
            keyPress = true;
            dir = Direction.DOWN;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_D)) {
            keyPress = true;
            dir = Direction.RIGHT;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_A)) {
            keyPress = true;
            dir = Direction.LEFT;
        }
        if (timer < moveSpeed + 1) {
            timer += deltaT;
        }
        if (canMove) {
            if (keyPress) {
                if (timer > moveSpeed) {
                    int comingBlock = 0;
                    comingBlock = MapOrganizer.getMapOrganizer().getTile((int) ((x / Game.TILESIZE) + dir.getDx()), (int) ((y / Game.TILESIZE) + dir.getDy()));
                    if (!MapOrganizer.getMapOrganizer().isSolid(comingBlock)) {
                        destX = (int) (x + Game.TILESIZE * dir.getDx());
                        destY = (int) (y + Game.TILESIZE * dir.getDy());
                        dx += speed * dir.getDx();
                        dy += speed * dir.getDy();
                        int duration = 0;
                        switch (MapOrganizer.getMapOrganizer().getTile((int) (x / 16), (int) (y / 16))) {
                            case 4:
                                duration = 2000;
                                break;
                            case 5:
                                duration = 1000;
                                break;
                        }
                        footSteps.add(new Footstep(x, y, dir.getDir(), duration));
                        timer -= moveSpeed;
                    }
                }
            }
            keyPress = false;
            canMove = false;
        }

        if (x == destX && y == destY) {
            canMove = true;
            dx = 0;
            dy = 0;
            timer = moveSpeed - 5;
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementDraw(Graphics graphics, Rectangle screenPos) {
        for (int i = 0; i < footSteps.size(); i++) {
            footSteps.get(i).draw(graphics, screenPos);
        }
        graphics.setColor(new Color(0.753f, 1f, 0.753f));
        graphics.fillRect(x, y, Game.TILESIZE, Game.TILESIZE);
        graphics.setColor(new Color(0.653f, 0.9f, 0.653f));
        graphics.drawRect(x, y, Game.TILESIZE, Game.TILESIZE);
        graphics.setColor(Color.white);
        graphics.drawString("ID: " + MapOrganizer.getMapOrganizer().getTile((int) (x / 16), (int) (y / 16)), Camera.getInstance().getX(), Camera.getInstance().getY());
    }
}
