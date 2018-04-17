package com.game.planetdefense.Utils.Singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.game.planetdefense.Enums.UpgradeType;

public class UserData {
    private static final UserData ourInstance = new UserData();
    private Preferences prefs = null;

    private float money;
    private int high_wave;
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
    }

    public void updateUserData(){
        prefs.putFloat("money",money);
        prefs.putInteger("highWave", high_wave);
        prefs.putBoolean("hasPlaying", hasPlaying);
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

    public void addUpgradeLvl(UpgradeType upgradeType){
        prefs.putInteger(upgradeType.name(), prefs.getInteger(upgradeType.name(), 0) + 1);
        prefs.flush();
    }

    public int getUpgradeLvl(UpgradeType upgradeType){
        return prefs.getInteger(upgradeType.name(), 0);
    }
}
