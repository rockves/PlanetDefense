package com.game.planetdefense.Utils.Singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class UserData {
    private static final UserData ourInstance = new UserData();
    private Preferences prefs = null;

    private float money;
    private int high_wave;
    private boolean hasPlaying;

    private float shoot_speed_multiplier;
    private float bonus_shoot_damage;

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
        shoot_speed_multiplier = prefs.getFloat("shoot_speed_multiplier", 1);
        bonus_shoot_damage = prefs.getFloat("bonus_shoot_damage", 0);
    }

    public void updateUserData(){
        prefs.putFloat("money",money);
        prefs.putInteger("highWave", high_wave);
        prefs.putBoolean("hasPlaying", hasPlaying);
        prefs.putFloat("shoot_speed_multiplier", shoot_speed_multiplier);
        prefs.putFloat("bonus_shoot_damage", bonus_shoot_damage);
        prefs.flush();
    }

    public void resetUserData(){
        prefs.clear();
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

    public float getShoot_speed_multiplier() {
        return shoot_speed_multiplier;
    }

    public void setShoot_speed_multiplier(float shoot_speed_multiplier) {
        this.shoot_speed_multiplier = shoot_speed_multiplier;
    }

    public float getBonus_shoot_damage() {
        return bonus_shoot_damage;
    }

    public void setBonus_shoot_damage(float bonus_shoot_damage) {
        this.bonus_shoot_damage = bonus_shoot_damage;
    }
}
