package com.game.planetdefense.Utils.Singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class UserData {
    private static final UserData ourInstance = new UserData();
    private Preferences prefs = null;

    private float money;
    private boolean hasPlaying;

    public static UserData getInstance() {
        return ourInstance;
    }

    private UserData() {
        prefs = Gdx.app.getPreferences("userData");
        money = prefs.getFloat("money", 0);
        hasPlaying = prefs.getBoolean("hasPlaying", false);
    }

    public void updateUserData(){
        prefs.putFloat("money",money);
        prefs.putBoolean("hasPlaying", hasPlaying);
        prefs.flush();
    }

    public void resetUserData(){
        prefs.clear();
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

    public boolean isHasPlaying() {
        return hasPlaying;
    }

    public void setHasPlaying(boolean hasPlaying) {
        this.hasPlaying = hasPlaying;
    }
}
