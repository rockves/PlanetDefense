package com.game.planetdefense.Utils.Singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class UserData {
    private static final UserData ourInstance = new UserData();
    private Preferences prefs = null;

    private static float money;

    public static UserData getInstance() {
        return ourInstance;
    }

    private UserData() {
        prefs = Gdx.app.getPreferences("userData");
        money = prefs.getFloat("money", 0);
    }

    public void updateUserData(){
        prefs.putFloat("money",money);
        prefs.flush();
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
