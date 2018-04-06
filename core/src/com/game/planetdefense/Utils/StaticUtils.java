package com.game.planetdefense.Utils;

public class StaticUtils {
    private StaticUtils(){}
    //STAGE UTILS
    public static int MONEY;
    public static float ASTEROID_MAX_DROP_INTERVAL;
    ////////////////
    //WAVE UTILS
    public static short COUNT_OF_ASTEROID_IN_NEXT_WAVE;
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
    ////////////////

    public static void loadData() {

        MONEY = 0;
        ASTEROID_MAX_DROP_INTERVAL = 1f;

        COUNT_OF_ASTEROID_IN_NEXT_WAVE = 1;

        LAUNCHER_WIDTH = 100;
        LAUNCHER_HEIGHT = 100;
        LAUNCHER_DELAY_TIME = 0;

        MISSILE_WIDTH = 90;
        MISSILE_HEIGHT = 45;
        MISSILE_SPEED = 700;
        MISSILE_TIME_TO_DESTROY = 4;

        ASTEROID_WIDTH = 200;
        ASTEROID_HEIGHT = 100;
    }
}
