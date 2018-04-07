package com.game.planetdefense.Utils.Singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class UserData {
    private static final UserData ourInstance = new UserData();
    private static final Preferences prefs = Gdx.app.getPreferences("userData");

    private static float money;

    public static UserData getInstance() {
        return ourInstance;
    }

    private UserData() {
        prefs.getFloat("money", 0);
    }

    public void updateUserData(){
        prefs.putFloat("money",money);
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        UserData.money = money;
    }

    public void addMoney(float money){
        UserData.money += money;
    }
}
