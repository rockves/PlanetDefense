package com.game.planetdefense;

public class StaticUtils {
    //STAGE UTILS
    public static float ASTEROID_DROP_INTERVAL;
    ////////////////
    //LAUNCHER UTILS
    public static int LAUNCHER_WIDTH;
    public static int LAUNCHER_HEIGHT;
    public static float LAUNCHER_DELAY_TIME;
    ////////////////
    //MISSILE UTILS
    public static int MISSILE_WIDTH;
    public static int MISSILE_HEIGHT;
    public static float MISSILE_SPEED;
    public static float MISSILE_TIME_TO_DESTROY;
    ////////////////
    //ASTEROID UTILS
    public static int ASTEROID_WIDTH;
    public static int ASTEROID_HEIGHT;
    public static float ASTEROID_SPEED;
    ////////////////

    public static void loadData() {
        ASTEROID_DROP_INTERVAL = 2.5f;

        LAUNCHER_WIDTH = 100;
        LAUNCHER_HEIGHT = 100;
        LAUNCHER_DELAY_TIME = 1;

        MISSILE_WIDTH = 90;
        MISSILE_HEIGHT = 45;
        MISSILE_SPEED = 700;
        MISSILE_TIME_TO_DESTROY = 4;

        ASTEROID_WIDTH = 100;
        ASTEROID_HEIGHT = 100;
        ASTEROID_SPEED = 200;
    }
}
