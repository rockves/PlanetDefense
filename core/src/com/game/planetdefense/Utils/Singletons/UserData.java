package com.game.planetdefense.Utils.Singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.game.planetdefense.Enums.UpgradeType;

public class UserData {
    private static final UserData ourInstance = new UserData();
    private Preferences prefs = null;

    private float money;
    private int high_wave;
    private long destroyed_asteroids;
    private long laser_fired;
    private boolean hasPlaying;

    public static UserData getInstance() {
        return ourInstance;
    }

    private UserData() {
        getUserData();
    }

    private void getUserData(){
        prefs = Gdx.app.getPreferences("userData");
        money = prefs.getFloat("money", 0);
        high_wave = prefs.getInteger("highWave", 0);
        hasPlaying = prefs.getBoolean("hasPlaying", false);
        destroyed_asteroids = prefs.getLong("destroyedAsteroids", 0);
        laser_fired = prefs.getLong("laserFired", 0);
    }

    public void updateUserData(){
        prefs.putFloat("money",money);
        prefs.putInteger("highWave", high_wave);
        prefs.putBoolean("hasPlaying", hasPlaying);
        prefs.putLong("destroyedAsteroids", destroyed_asteroids);
        prefs.putLong("laserFired", laser_fired);
        prefs.flush();
    }

    public void resetUserData(){
        prefs.clear();
        prefs.flush();
        getUserData();
        prefs.flush();
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void addMoney(float money){
        this.money += money;
    }

    public int getHigh_wave() {
        return high_wave;
    }

    public void setHigh_wave(int high_wave) {
        this.high_wave = high_wave;
    }

    public boolean isHasPlaying() {
        return hasPlaying;
    }

    public void setHasPlaying(boolean hasPlaying) {
        this.hasPlaying = hasPlaying;
    }

    public void addDestroyedAsteroid(){
        destroyed_asteroids += 1;
    }

    public long getDestroyed_asteroids(){
        return destroyed_asteroids;
    }

    public void addLaserShoot(){
        laser_fired += 1;
    }

    public long getLaserShoots(){
        return laser_fired;
    }

    public void addUpgradeLvl(UpgradeType upgradeType){
        prefs.putInteger(upgradeType.name(), prefs.getInteger(upgradeType.name(), 0) + 1);
        prefs.flush();
    }

    public int getUpgradeLvl(UpgradeType upgradeType){
        return prefs.getInteger(upgradeType.name(), 0);
    }

    public void addUpgradeNumber(){
        prefs.putInteger("UpgradeNumber", prefs.getInteger("UpgradeNumber", 0) + 1);
        prefs.flush();
    }

    public int getUpgradeNumber(){
        return prefs.getInteger("UpgradeNumber", 0);
    }

    public void setMusicOn(boolean on){
        prefs.putBoolean("MusicOn", on);
        prefs.flush();
    }

    public boolean getIsMusicOn(){
        return prefs.getBoolean("MusicOn", true);
    }

    public void setSoundOn(boolean on){
        prefs.putBoolean("SoundOn", on);
        prefs.flush();
    }

    public boolean getIsSoundOn(){
        return prefs.getBoolean("SoundOn", true);
    }

    public void setSplashScreenOn(boolean on){
        prefs.putBoolean("SplashScreenOn", on);
        prefs.flush();
    }

    public boolean getIsSplashScreenOn(){
        return prefs.getBoolean("SplashScreenOn", true);
    }
}
