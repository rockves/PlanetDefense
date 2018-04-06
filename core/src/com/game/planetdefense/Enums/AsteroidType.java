package com.game.planetdefense.Enums;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.planetdefense.Utils.Managers.GraphicManager;

import java.util.Random;

public enum AsteroidType {
    //ROCK,
    SATELLITE,
    FIREBALL;

    public float getHp(){
        switch(this){
            /*case ROCK:
                return 1;*/
            case SATELLITE:
                return 2;
            case FIREBALL:
                return 3;
            default:
                return 0;
        }
    }

    public float getMoneyDrop(){
        switch(this){
            /*case ROCK:
                return 10;*/
            case SATELLITE:
                return 20;
            case FIREBALL:
                return 30;
            default:
                return 0;
        }
    }

    public float getSpeed(){
        switch(this){
            /*case ROCK:
                return 150;*/
            case SATELLITE:
                return 500;
            case FIREBALL:
                return 500;
            default:
                return 0;
        }
    }

    public Animation<TextureRegion> getAsteroidAnimation(){
        switch(this){
            /*case ROCK:
                return GraphicManager.satellite_animation;*/
            case SATELLITE:
                return GraphicManager.satellite_animation;
            case FIREBALL:
                return GraphicManager.meteor_animation;
            default:
                return null;
        }
    }

    public static AsteroidType getRandomType(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
