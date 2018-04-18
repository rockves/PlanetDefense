package com.game.planetdefense.Utils.Managers;

import com.badlogic.gdx.Gdx;
import com.game.planetdefense.Actors.Asteroid;
import com.game.planetdefense.Enums.AsteroidType;
import com.game.planetdefense.Enums.UpgradeType;
import com.game.planetdefense.GameStage;
import com.game.planetdefense.PlanetDefense;
import com.game.planetdefense.Utils.StaticUtils;

import java.util.Random;

public class WaveManager {

    private GameStage stage;
    private AssetsManager assetsManager;
    private AsteroidType asteroid_type;

    private int wave;
    private boolean isFirst = true;
    private float start_difficulty_points;
    private float difficulty_points;
    private float time_to_next_object_spawn;
    private float difficulty_multiplier;

    public WaveManager(PlanetDefense planetDefense, GameStage stage){
        this.assetsManager = planetDefense.assets_manager;
        this.stage = stage;
        wave = UpgradeType.StageBonus.getUpgradeLvl();
        start_difficulty_points = 0;
        difficulty_points = 0;
        difficulty_multiplier = 1;
        difficulty_multiplier += wave * StaticUtils.DIFFICULTY_STATS_MULTIPLIER;
    }

    public void prepareWave(){
        wave++;
        if(isFirst){
            if(UpgradeType.StageBonus.getUpgradeLvl() > 0){
                start_difficulty_points = calculateDifficultyPoints(wave);
            }else {
                start_difficulty_points = StaticUtils.DIFFICULTY_POINTS;
                isFirst = false;
            }
        }
        else start_difficulty_points *= StaticUtils.DIFFICULTY_POINTS_MULTIPLIER;
        difficulty_points = start_difficulty_points;
        difficulty_multiplier += StaticUtils.DIFFICULTY_STATS_MULTIPLIER;
        Gdx.app.log("points", "" + difficulty_points);
    }

    public void spawnAsteroid(){
        Asteroid asteroid = stage.getAsteroid_pool().obtain();
        int range = AsteroidType.values().length;
        do {
            asteroid_type = AsteroidType.getRandomType(range);
            range -= 1;
            if(range == 0) break;
        }while(asteroid_type.getDifficultyPoints() > difficulty_points);

        difficulty_points -= asteroid_type.getDifficultyPoints();

        asteroid.setHp(asteroid_type.getHp() * difficulty_multiplier);
        asteroid.setSpeed(asteroid_type.getSpeed());
        asteroid.setMoneyDrop(asteroid_type.getMoneyDrop() * difficulty_multiplier);
        asteroid.setAnimation(asteroid_type.getAnimation(assetsManager));

        Random rand = new Random();
        float start_y = stage.getHeight() + (asteroid_type.getHeight());
        float start_x = rand.nextFloat() * (stage.getWidth());
        float target_x = (rand.nextFloat() * ((stage.getWidth()/6) * 4)) + stage.getWidth()/6;
        asteroid.setAsteroid(start_x, start_y, asteroid_type.getWidth(), asteroid_type.getHeight());
        asteroid.setTarget(target_x, 0);
        asteroid.rotateToTarget();

        stage.dropAsteroid(asteroid);
    }

    public void spawnNextObject(){
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

    private float calculateDifficultyPoints(int wave){
        if(wave == 1){
            return StaticUtils.DIFFICULTY_POINTS;
        }
        return StaticUtils.DIFFICULTY_POINTS_MULTIPLIER * calculateDifficultyPoints(wave - 1);
    }
}
