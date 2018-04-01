package com.game.planetdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.planetdefense.Actors.Asteroid;
import com.game.planetdefense.Actors.Launcher;
import com.game.planetdefense.Actors.Missile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameStage extends Stage {

    private List<Asteroid> active_asteroids;
    private Pool<Asteroid> asteroid_pool;
    private List<Missile> active_missiles;
    private Pool<Missile> missile_pool;
    private Launcher launcher;
    private float time_to_asteroid_drop;

    public GameStage() {
        loadGameStage();
    }

    public GameStage(Viewport viewport) {
        super(viewport);
        loadGameStage();
    }

    public GameStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
        loadGameStage();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        checkCollisions();
        super.act(delta);
        if(time_to_asteroid_drop <= 0){
            time_to_asteroid_drop = StaticUtils.ASTEROID_DROP_INTERVAL;
            dropAsteroid();
        }else {
            time_to_asteroid_drop -= delta;
        }
    }

    private void loadGameStage(){
        //pool create
        asteroid_pool = new Pool<Asteroid>() {
            @Override
            protected Asteroid newObject() {
                return new Asteroid();
            }
        };
        missile_pool = new Pool<Missile>() {
            @Override
            protected Missile newObject() {
                return new Missile();
            }
        };
        //list create
        active_asteroids = new ArrayList<Asteroid>();
        active_missiles = new ArrayList<Missile>();
        //build launcher
        buildLauncher();
        time_to_asteroid_drop = StaticUtils.ASTEROID_DROP_INTERVAL;
    }

    private void checkCollisions(){
        //TODO: Make asteroids collision with delete of missiles and asteroids
        Iterator<Asteroid> asteroid_iterator = active_asteroids.iterator();
        //asteroid iteration
        while (asteroid_iterator.hasNext()) {
            Asteroid asteroid = asteroid_iterator.next();
            //check is asteroid touch ground
            if(asteroid.getY() < 0){
                asteroid_pool.free(asteroid);
                asteroid_iterator.remove();
            }
            Iterator<Missile> missile_iterator = active_missiles.iterator();
            //missile iteration
            while(missile_iterator.hasNext()){
                Missile missile = missile_iterator.next();
                //collision missile with asteroid
                if(asteroid.getRectangle().overlaps(missile.getRectangle())){
                    missile_pool.free(missile);
                    asteroid_pool.free(asteroid);
                    missile_iterator.remove();
                    asteroid_iterator.remove();
                    break;
                }
                //missile flight too long
                if(missile.flight_time >= StaticUtils.MISSILE_TIME_TO_DESTROY){
                    missile_pool.free(missile);
                    missile_iterator.remove();
                }
            }
        }
    }

    private void dropAsteroid(){
        Random rand = new Random();
        float start_y = this.getHeight() + (StaticUtils.ASTEROID_HEIGHT * 2);
        float start_x = rand.nextFloat() * (this.getWidth());
        float target_x = rand.nextFloat() * (this.getWidth());
        Asteroid asteroid = asteroid_pool.obtain();
        asteroid.setAsteroid(start_x, start_y);
        asteroid.setTarget(target_x, 0);
        asteroid.rotateToTarget();
        active_asteroids.add(asteroid);
        this.addActor(asteroid);
    }

    public void launcherLaunch(float target_X, float target_Y){
        Missile missile = launcher.launchMissile(missile_pool.obtain(),target_X,target_Y);
        if(missile == null) return;
        this.addActor(missile);
        this.active_missiles.add(missile);
        Gdx.app.log("Active missiles", "" + active_missiles.size());
        Gdx.app.log("Missiles pool", "" + missile_pool.getFree());
    }

    private void buildLauncher(){
        launcher = new Launcher();
        launcher.setLauncher(this.getWidth()/2 - StaticUtils.LAUNCHER_WIDTH/2, 0, StaticUtils.LAUNCHER_WIDTH, StaticUtils.LAUNCHER_HEIGHT);
        this.addActor(launcher);
        Gdx.app.log("Launcher position", " " + launcher.getX() + " " + launcher.getY());
    }
}
