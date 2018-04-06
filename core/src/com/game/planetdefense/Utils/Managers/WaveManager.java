package com.game.planetdefense.Utils.Managers;

import com.game.planetdefense.Actors.Asteroid;
import com.game.planetdefense.Enums.AsteroidType;
import com.game.planetdefense.Utils.StaticUtils;

public class WaveManager {
//TODO: Przniesc pozycjonowanie asteroidy do waveManagera albo enumeratora typu aby pozniej moc zrobic inne przeszkody niz asteroidy na przyklad latajacych horyzontalnie kosmitow
    AsteroidType asteroid_type;
    private int wave;
    private int asteroid_in_wave;
    private int asteroid_to_drop;
    private float drop_interval;
    private float difficult_ratio;

    public WaveManager(){
        wave = 0;
        asteroid_in_wave = 0;
        asteroid_to_drop = 0;
        drop_interval = 0;
        difficult_ratio = 1;
    }

    public void prepareWave(){
        wave++;
        asteroid_in_wave += StaticUtils.COUNT_OF_ASTEROID_IN_NEXT_WAVE;
        drop_interval = StaticUtils.ASTEROID_MAX_DROP_INTERVAL;
        asteroid_to_drop = asteroid_in_wave;
    }

    public void generateAsteroid(Asteroid asteroid){
        --asteroid_to_drop;
        asteroid_type = AsteroidType.getRandomType();
        asteroid.setHp((int)asteroid_type.getHp());
        asteroid.setSpeed(asteroid_type.getSpeed());
        asteroid.setMoneyDrop(asteroid_type.getMoneyDrop());
        asteroid.setAnimation(asteroid_type.getAsteroidAnimation());
    }

    public boolean isAsteroidToDrop(){
        return asteroid_to_drop > 0;
    }

    public int getWave() {
        return wave;
    }
}
