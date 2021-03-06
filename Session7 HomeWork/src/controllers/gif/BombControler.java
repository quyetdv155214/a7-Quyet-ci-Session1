package controllers.gif;

import controllers.*;
import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.Animation;
import views.View;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by quyet on 12/25/2016.
 */
public class BombControler extends Controller implements Body {
    private static final int WIDTH = 35;
    private static final int HEIGHT = 30;
    private int scope;
//    private ControllerManager bulletManager;
    BombBehavior bombBehavior ;
    public BombControler(Model model, View view, BombBehavior bombBehavior) {
        super(model, view);
        this.bombBehavior = bombBehavior;
        BodyManager.instance.register(this);
    }

    @Override
    public void run() {
        super.run();
        this.getModel().move(0, 1);

//        bulletManager.run();

    }


    public static BombControler create(int x, int y, BombType bombType) {

        Vector<BufferedImage> images ;
        switch (bombType) {
            case BIG:
                images = new Vector<>();
                images.add(Utils.loadImage("resources/nuclear_bomb.png"));
                return new BombControler(
                        new Model(x, y, WIDTH, HEIGHT),
                        new Animation(images),
                        new ExplodingBomb(1000)

                );
            case SMALL:
                images = new Vector<>();
                images.add(Utils.loadImage("resources/bomb.png"));
                return new BombControler(
                        new Model(x, y, WIDTH, HEIGHT),
                        new Animation(images),
                        new ExplodingBomb(300)
                );
            default:
                return null;
        }


    }

    @Override
    public void onContact(Body other) {
        if (other instanceof PlaneController) {
            this.decHp(1);
            this.bombBehavior.effect();
        }
    }
}
