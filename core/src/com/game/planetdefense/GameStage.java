package com.game.planetdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.planetdefense.Actors.Asteroid;
import com.game.planetdefense.Actors.Launcher;
import com.game.planetdefense.Actors.Missile;
import com.game.planetdefense.Utils.Managers.WaveManager;
import com.game.planetdefense.Utils.Singletons.UserData;
import com.game.planetdefense.Utils.StaticUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameStage extends Stage {

    private PlanetDefense planetDefense;

    private List<Asteroid> active_asteroids;
    private Pool<Asteroid> asteroid_pool;
    private List<Missile> active_missiles;
    private Pool<Missile> missile_pool;
    private WaveManager wave_manager;
    private Launcher launcher;
    //private Table ui_table;
    private Container stage_screen;
    //private Label money_label;
    private boolean isPause = false;

    public GameStage(Viewport viewport, PlanetDefense planetDefense) {
        super(viewport);
        if(!UserData.getInstance().isHasPlaying()) UserData.getInstance().setHasPlaying(true);
        this.planetDefense = planetDefense;
        this.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(isPause){
                    wave_manager.prepareWave();
                    toggleWaveScreen();
                    return super.touchDown(event, x, y, pointer, button);
                }
                launcherLaunch(x,y);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        loadGameStage();
        toggleWaveScreen();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        if(isPause) return;
        checkCollisions();
        super.act(delta);
        if(!wave_manager.isEndOfWave()) {
            if (wave_manager.getTime_to_next_object_spawn() <= 0) {
                wave_manager.setTime_to_next_object_spawn(StaticUtils.ASTEROID_MAX_DROP_INTERVAL);
                wave_manager.spawnNextObject();
            } else {
                wave_manager.setTime_to_next_object_spawn(wave_manager.getTime_to_next_object_spawn() - delta);
            }
        }else if(active_asteroids.isEmpty()){
            endWave();
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
                return new Missile(planetDefense.assets_manager);
            }
        };
        //list create
        active_asteroids = new ArrayList<Asteroid>();
        active_missiles = new ArrayList<Missile>();
        //Wave manager load
        wave_manager = new WaveManager(planetDefense, this);
        //build launcher
        buildLauncher();
        //load UI
        loadUi();
        //load background image
        createBackground();
    }

    private void loadUi(){
        stage_screen = new Container(new Label("", new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.BLACK)));
        stage_screen.setFillParent(true);
        stage_screen.debugAll();
        stage_screen.setVisible(false);
        this.addActor(stage_screen);
    }

    private void createBackground(){
        Image background = new Image(planetDefense.assets_manager.getGameBackground());
        background.setFillParent(true);
        this.addActor(background);
    }

    private void checkCollisions(){
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
                    asteroid.setHp(asteroid.getHp() - missile.getDamage());
                    if(asteroid.getHp() > 0){
                        break;
                    }
                    UserData.getInstance().addMoney(asteroid.getMoneyDrop());
                    missile_pool.free(missile);
                    asteroid_pool.free(asteroid);
                    missile_iterator.remove();
                    asteroid_iterator.remove();
                    break;
                }
                //missile flight too long
                if(missile.flight_time >= com.game.planetdefense.Utils.StaticUtils.MISSILE_TIME_TO_DESTROY){
                    missile_pool.free(missile);
                    missile_iterator.remove();
                }
            }
        }
    }

    public void dropAsteroid(Asteroid asteroid){
        active_asteroids.add(asteroid);
        this.addActor(asteroid);
    }

    private void launcherLaunch(float target_X, float target_Y){
        if(isPause) return;
        Missile missile = launcher.launchMissile(missile_pool.obtain(),target_X,target_Y);
        if(missile == null) return;
        this.addActor(missile);
        this.active_missiles.add(missile);
        Gdx.app.log("Active missiles", "" + active_missiles.size());
        Gdx.app.log("Missiles pool", "" + missile_pool.getFree());
    }

    private void buildLauncher(){
        launcher = new Launcher(planetDefense.assets_manager);
        launcher.setLauncher(this.getWidth()/2 - com.game.planetdefense.Utils.StaticUtils.LAUNCHER_WIDTH/2, 0, com.game.planetdefense.Utils.StaticUtils.LAUNCHER_WIDTH, com.game.planetdefense.Utils.StaticUtils.LAUNCHER_HEIGHT);
        this.addActor(launcher);
        Gdx.app.log("Launcher position", " " + launcher.getX() + " " + launcher.getY());
    }

    private void toggleWaveScreen(){
        isPause = !isPause;
        stage_screen.setVisible(!stage_screen.isVisible());
        if(stage_screen.isVisible()){
            Label label = (Label)stage_screen.getActor();
            label.setText("Wave " + (wave_manager.getWave() + 1));
            label.setAlignment(Align.center);
        }
    }

    private void endWave(){
        toggleWaveScreen();
        Iterator<Missile> missile_iterator = active_missiles.iterator();
        while (missile_iterator.hasNext()){
            Missile missile = missile_iterator.next();
            missile_pool.free(missile);
            missile_iterator.remove();
        }

        if(UserData.getInstance().getHigh_wave() < wave_manager.getWave()) UserData.getInstance().setHigh_wave(wave_manager.getWave());
    }

    public List<Asteroid> getActive_asteroids() {
        return active_asteroids;
    }
    public Pool<Asteroid> getAsteroid_pool() {
        return asteroid_pool;
    }
    public List<Missile> getActive_missiles() {
        return active_missiles;
    }
    public Pool<Missile> getMissile_pool() {
        return missile_pool;
    }
}
