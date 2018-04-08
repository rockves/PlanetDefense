package com.game.planetdefense.Enums;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.planetdefense.Actors.Asteroid;
import com.game.planetdefense.Utils.Managers.AssetsManager;

import java.util.Random;

public enum AsteroidType {
    METEOR, SATELLITE;

    public float getHp(){
        switch(this){
            /*case ROCK:
                return 1;*/
            case SATELLITE:
                return 2;
            case METEOR:
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
            case METEOR:
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
            case METEOR:
                return 500;
            default:
                return 0;
        }
    }

    public Animation<TextureRegion> getAnimation(AssetsManager assets_manager){
        switch(this){
            /*case ROCK:
                return 150;*/
            case SATELLITE:
                return assets_manager.getSatellite_animation();
            case METEOR:
                return assets_manager.getMeteor_animation();
            default:
                return null;
        }
    }

    public static AsteroidType getRandomType(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
