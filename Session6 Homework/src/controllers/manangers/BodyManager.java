package controllers.manangers;

import controllers.Body;
import controllers.enemy.EnemyController;
import models.Model;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by q on 12/14/2016.
 */
public class BodyManager {
    private Vector<Body> bodies;
    public static  final BodyManager instance = new BodyManager();
    private BodyManager() {
        //
        this.bodies =  new Vector<>();
    }
    public  void register(Body body){
        this.bodies.add(body);
    }
    public void remove(){
        Iterator<Body> iterator = bodies.iterator();
        while(iterator.hasNext()){
            Model model = iterator.next().getModel();
            if(model.isAlive()==false){
                iterator.remove();
            }
        }
    }
    public void remove(Body body)
    {
        bodies.remove(body);
    }
    public int getNumEnemy(){
        int count = 0 ;
        for (Body b: bodies
             ) {
            if (b instanceof EnemyController){
                count ++;
            }

        }
        return  count;
    }

    public void checkContact(){
        for (int i = 0; i < bodies.size()-1; i++) {
            for (int j = 0; j <bodies.size(); j++) {
                Body bodyi = bodies.get(i);
                Body bodyj = bodies.get(j);

                Model modeli  = bodyi.getModel();
                Model modelj = bodyj.getModel();
                if(modeli.intersects(modelj)){
                    bodyi.onContact(bodyj);
                    bodyj.onContact(bodyi);

                }
            }
        }
    }
}
