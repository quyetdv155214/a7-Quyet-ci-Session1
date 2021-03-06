package controllers;

import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by apple on 12/3/16.
 */
public class PlaneController extends Controller implements Body {

    private static final int SPEED = 5;

    public KeySetting keySetting;

    public PlaneController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);

    }

    public void keyPressed(KeyEvent e) {
        if(keySetting != null && this.getModel().isAlive()) {
            int keyCode = e.getKeyCode();
            if(keyCode == keySetting.keyUp) {
                model.move(0, -SPEED);
            } else if (keyCode == keySetting.keyDown) {
                model.move(0, SPEED);
            } else if (keyCode == keySetting.keyLeft) {
                model.move(-SPEED, 0);
            } else if (keyCode == keySetting.keyRight) {
                model.move(SPEED, 0);
            }
        }
    }

    // Design pattern
    // Factory
    public static PlaneController createPlane(int x, int y) {
        PlaneController planeController = new PlaneController(
                new Model(x, y, 70, 50),
                new View(Utils.loadImage("resources/plane3.png"))
        );
        planeController.getModel().setHp(10);
        return planeController;
    }

    @Override
    public void onContact(Body orther) {
        if (orther instanceof EnemyBulletController)
        {
            this.getModel().decHp(1);

        }
    }
}
