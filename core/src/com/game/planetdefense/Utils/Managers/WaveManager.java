package com.game.planetdefense.Utils.Managers;

import com.game.planetdefense.Actors.Asteroid;
import com.game.planetdefense.Enums.AsteroidType;
import com.game.planetdefense.GameStage;
import com.game.planetdefense.PlanetDefense;
import com.game.planetdefense.Utils.StaticUtils;

import java.util.Random;

public class WaveManager {

    private GameStage stage;
    private AssetsManager assetsManager;
    private AsteroidType asteroid_type;

    private int wave;
    private float start_difficulty_points;
    private float difficulty_points;
    private float time_to_next_object_spawn;

    public WaveManager(PlanetDefense planetDefense, GameStage stage){
        this.assetsManager = planetDefense.assets_manager;
        this.stage = stage;
        wave = 0;
        start_difficulty_points = 0;
        difficulty_points = 0;
    }

    public void prepareWave(){
        wave++;
        if(wave == 1){start_difficulty_points = StaticUtils.DIFFICULTY_POINTS;}
        else start_difficulty_points *= StaticUtils.DIFFICULTY_POINTS_MULTIPLIER;
        difficulty_points = start_difficulty_points;
    }

    public void spawnAsteroid(){
        Asteroid asteroid = stage.getAsteroid_pool().obtain();

       // do {
            asteroid_type = AsteroidType.getRandomType();
        //}while(asteroid_type.getHp() > difficulty_points);

        difficulty_points -= asteroid_type.getHp();

        asteroid.setHp((int)asteroid_type.getHp());
        asteroid.setSpeed(asteroid_type.getSpeed());
        asteroid.setMoneyDrop(asteroid_type.getMoneyDrop());
        asteroid.setAnimation(asteroid_type.getAnimation(assetsManager));

        Random rand = new Random();
        float start_y = stage.getHeight() + (com.game.planetdefense.Utils.StaticUtils.ASTEROID_HEIGHT * 2);
        float start_x = rand.nextFloat() * (stage.getWidth());
        float target_x = rand.nextFloat() * (stage.getWidth());
        asteroid.setAsteroid(start_x, start_y, asteroid_type.getWidth(), asteroid_type.getHeight());
        asteroid.setTarget(target_x, 0);
        asteroid.rotateToTarget();

        stage.dropAsteroid(asteroid);
    }

    public void spawnNextObject(){
        //TODO: Random difficulty object
        spawnAsteroid();
    }

    public float getTime_to_next_object_spawn() {
        return time_to_next_object_spawn;
    }

    public void setTime_to_next_object_spawn(float time_to_next_object_spawn) {
        this.time_to_next_object_spawn = time_to_next_object_spawn;
    }

    public boolean isEndOfWave(){
        return difficulty_points <= 0;
    }

    public int getWave() {
        return wave;
    }
}
