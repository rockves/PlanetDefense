package com.game.planetdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.planetdefense.Actors.Asteroid;
import com.game.planetdefense.Actors.Explosion;
import com.game.planetdefense.Actors.Launcher;
import com.game.planetdefense.Actors.Missile;
import com.game.planetdefense.Enums.UpgradeType;
import com.game.planetdefense.Utils.Managers.AudioManager;
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
    private List<Explosion> active_explosion;
    private Pool<Explosion> explosion_pool;

    private WaveManager wave_manager;
    private AudioManager audio_manager;
    private Launcher launcher;
    private Container stage_screen;
    private MoveToAction move_action;
    private Container fail_screen;
    private Label shield_counter;
    private float shield_points;
    private Image earth;
    private boolean isPause = false;
    private boolean changeToUpgradeScreen = false;
    private boolean fail = false;

    public GameStage(Viewport viewport, PlanetDefense planetDefense) {
        super(viewport);
        if(!UserData.getInstance().isHasPlaying()) UserData.getInstance().setHasPlaying(true);
        this.planetDefense = planetDefense;
        this.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(stage_screen.getActions().size > 0){
                    return super.touchDown(event, x, y, pointer, button);
                }
                if(isPause){
                    if(fail){
                        if(!(fail_screen.getActions().size > 0)){
                            changeToUpgradeScreen = true;
                        }
                        return super.touchDown(event, x, y, pointer, button);
                    }
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
        this.audio_manager = planetDefense.assets_manager.getAudio_manager();
        this.audio_manager.playGameMusic();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        simulateExplosion();
        if(isPause || stage_screen.getActions().size > 0) return;
        checkCollisions();
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
        explosion_pool = new Pool<Explosion>() {
            @Override
            protected Explosion newObject() {
                return new Explosion(planetDefense.assets_manager);
            }
        };
        //list create
        active_asteroids = new ArrayList<Asteroid>();
        active_missiles = new ArrayList<Missile>();
        active_explosion = new ArrayList<Explosion>();
        //Wave manager load
        wave_manager = new WaveManager(planetDefense, this);
        //load background image
        createBackground();
        //set earth
        setEarth();
        //build launcher
        buildLauncher();
        //load UI
        loadUi();
    }

    private void loadUi() {
        stage_screen = new Container<Label>(new Label("", new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.WHITE)));
        stage_screen.setBackground(new TextureRegionDrawable(planetDefense.assets_manager.getButtonsBackground()));
        stage_screen.setSize(this.getWidth(), this.getWidth() * 0.10f);
        stage_screen.setPosition(0 - stage_screen.getWidth(), this.getHeight() / 2 - stage_screen.getHeight() / 2);
        //stage_screen.debugAll();
        stage_screen.setVisible(true);
        this.addActor(stage_screen);
        ///////////////////////////////
        fail_screen = new Container<Label>(new Label("YOU FAILED!\nGO ARM YOURSELF BETTER", new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.WHITE)));
        fail_screen.setBackground(new TextureRegionDrawable(planetDefense.assets_manager.getButtonsBackground()));
        fail_screen.setSize(this.getWidth(), this.getWidth() * 0.10f);
        fail_screen.setPosition(0, this.getHeight() + fail_screen.getHeight());
        //stage_screen.debugAll();
        fail_screen.setVisible(true);
        fail_screen.align(Align.center);
        this.addActor(fail_screen);
        ///////////////////////////////
        if(UpgradeType.ShieldBonus.getUpgradeLvl() != 0){
            Image shield_counter_image = new Image(planetDefense.assets_manager.getEarthTexture());
            shield_counter_image.setSize(StaticUtils.SHIELD_COUNTER_SIZE, StaticUtils.SHIELD_COUNTER_SIZE);
            shield_counter_image.setPosition(this.getWidth() * 0.02f, this.getHeight() - shield_counter_image.getHeight() - (this.getWidth() * 0.02f));
            this.addActor(shield_counter_image);

            Image shield_image = new Image(planetDefense.assets_manager.getShieldTexture());
            shield_image.setSize(shield_counter_image.getWidth() * 0.5f, shield_counter_image.getHeight() * 0.5f);
            shield_image.setPosition(shield_counter_image.getX() + shield_counter_image.getWidth() / 2 - shield_image.getWidth() / 2, shield_counter_image.getY() + shield_counter_image.getHeight() / 2 - shield_image.getHeight() / 2);
            this.addActor(shield_image);

            shield_points = (UpgradeType.ShieldBonus.getUpgradeLvl() * UpgradeType.ShieldBonus.getUpgradeValue());
            shield_counter = new Label("" + (int) shield_points, new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.WHITE));
            shield_counter.setPosition(shield_counter_image.getX() + shield_counter_image.getWidth() + 10, shield_counter_image.getY() + shield_counter_image.getHeight() / 2 - shield_counter.getHeight() / 2);
            shield_counter.setAlignment(Align.center);
            this.addActor(shield_counter);
        }
    }

    private void createBackground(){
        Image background = new Image(planetDefense.assets_manager.getStarBackground());
        background.setFillParent(true);
        this.addActor(background);
    }

    private void createExplosion(Asteroid asteroid){
        Explosion explosion = explosion_pool.obtain();
        explosion.setExplosion(asteroid.getX(), asteroid.getY(), asteroid.getWidth(), asteroid.getWidth());
        this.addActor(explosion);
        active_explosion.add(explosion);
        audio_manager.playExplosionSound();
    }

    private void simulateExplosion(){
        Iterator<Explosion> explosion_iterator = active_explosion.iterator();
        while(explosion_iterator.hasNext()){
            Explosion explosion = explosion_iterator.next();
            if(explosion.isAnimationEnd()){
                explosion_pool.free(explosion);
                explosion_iterator.remove();
            }
        }
    }

    private void checkCollisions(){
        Iterator<Asteroid> asteroid_iterator = active_asteroids.iterator();
        //asteroid iteration
        while (asteroid_iterator.hasNext()) {
            Asteroid asteroid = asteroid_iterator.next();
            //check is asteroid touch ground
            if(asteroid.getY() < 0){
                if(shield_points != 0){
                    createExplosion(asteroid);
                    asteroid_pool.free(asteroid);
                    asteroid_iterator.remove();
                    shield_points -= 1;
                    updateShieldCounter();
                    break;
                }
                asteroid_pool.free(asteroid);
                asteroid_iterator.remove();
                toggleFailScreen();
                UserData.getInstance().updateUserData();
                break;
            }
            Iterator<Missile> missile_iterator = active_missiles.iterator();
            //missile iteration
            while(missile_iterator.hasNext()){
                Missile missile = missile_iterator.next();
                //collision missile with asteroid
                if(Intersector.overlapConvexPolygons(asteroid.getPolygon(), missile.getPolygon())){
                    asteroid.setHp(asteroid.getHp() - missile.getDamage());
                    if(asteroid.getHp() > 0){
                        missile_pool.free(missile);
                        missile_iterator.remove();
                        break;
                    }
                    //explosion set
                    createExplosion(asteroid);
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
        launcher.setLauncher(this.getWidth()/2 - com.game.planetdefense.Utils.StaticUtils.LAUNCHER_WIDTH/2, this.getHeight() * 0.1f, com.game.planetdefense.Utils.StaticUtils.LAUNCHER_WIDTH, com.game.planetdefense.Utils.StaticUtils.LAUNCHER_HEIGHT);
        this.addActor(launcher);
        Gdx.app.log("Launcher position", " " + launcher.getX() + " " + launcher.getY());
    }

    private void toggleWaveScreen(){
        isPause = !isPause;
        move_action = new MoveToAction();
        if(isPause){
            Label label = (Label)stage_screen.getActor();
            label.setText("Wave " + (wave_manager.getWave() + 1));
            label.setAlignment(Align.center);
            //set move
            stage_screen.setPosition(0 - stage_screen.getWidth(),stage_screen.getY());
            move_action.reset();
            move_action.setDuration(0.7f);
            move_action.setPosition(0,stage_screen.getY());
            stage_screen.addAction(move_action);
        }else {
            move_action.reset();
            move_action.setDuration(0.7f);
            move_action.setPosition(this.getWidth(), stage_screen.getY());
            stage_screen.addAction(move_action);
        }
    }

    private void toggleFailScreen(){
        isPause = true;
        fail = true;
        move_action = new MoveToAction();
        move_action.reset();
        move_action.setPosition(0, this.getHeight()/2 - fail_screen.getHeight()/2);
        move_action.setDuration(0.8f);
        fail_screen.addAction(move_action);

        Iterator<Missile> missile_iterator = active_missiles.iterator();
        while (missile_iterator.hasNext()){
            Missile missile = missile_iterator.next();
            missile_pool.free(missile);
            missile_iterator.remove();
        }
        Iterator<Asteroid> asteroidIterator = active_asteroids.iterator();
        while(asteroidIterator.hasNext()){
            Asteroid asteroid = asteroidIterator.next();
            asteroid_pool.free(asteroid);
            asteroidIterator.remove();
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

    private void setEarth(){
        earth = new Image(planetDefense.assets_manager.getEarthTexture()){

            @Override
            public void act(float delta) {
                this.setRotation((this.getRotation() + (5 * delta)) % 360);
                super.act(delta);
            }
        };
        earth.setSize(StaticUtils.EARTH_SIZE, StaticUtils.EARTH_SIZE);
        earth.setOrigin(StaticUtils.EARTH_SIZE/2, StaticUtils.EARTH_SIZE/2);
        earth.setPosition(this.getWidth()/2 - earth.getWidth()/2, 0 - earth.getHeight() * 0.70f);
        this.addActor(earth);
    }

    private void updateShieldCounter(){
        shield_counter.setText("" + (int)shield_points);
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
    public boolean makeChangeToUpgradeScreen(){
        return changeToUpgradeScreen;
    }
}
