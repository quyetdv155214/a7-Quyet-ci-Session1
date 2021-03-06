package models;

import controllers.ExplosionController;
import controllers.GameVector;
import controllers.manangers.ControllerManager;
import utils.Utils;
import views.Animation;

import java.awt.*;

/**
 * Created by apple on 12/7/16.
 */
public class Model {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isAlive = true;
    private int hp ;


    public Model(int x, int y, int width, int height, int hp) {
        this.hp = hp;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Model(int x, int y, int width, int height) {
        hp =1;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void move(GameVector gameVector)
    {
        this.move(gameVector.dx, gameVector.dy);
    }

    public GameVector subtract(Model model)
    {
        return new GameVector(this.x - model.x, this.y - model.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getMidX() {
        return this.x + this.width / 2;
    }

    public int getMidY() {
        return this.y + this.height / 2;
    }

    public int getBottom() {
        return this.y + this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    public void decHp(int decHp){
        hp-=decHp;
        if ( hp <=0 )
            isAlive = false;
    }
    //
    public void destroy() {
        isAlive = false;
        System.out.println("chet");
        ExplosionController explosionController = new ExplosionController(
                new Model(x, y, 32, 32),
                new Animation(Utils.loadSheet("resources/explosion.png", 32, 32, 1, 6))
        );
        Utils.playSound("resources/enemyExoploding.wav", false);
        ControllerManager.explosion.add(explosionController);
    }

    public boolean intersects(Model other) {
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = other.getRect();
        return rect1.intersects(rect2);
    }
}
