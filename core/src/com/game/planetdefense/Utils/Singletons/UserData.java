package com.game.planetdefense.Utils.Singletons;

class UserData {
    private static final UserData ourInstance = new UserData();

    static UserData getInstance() {
        return ourInstance;
    }

    private UserData() {
    }
}
