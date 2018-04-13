package com.game.planetdefense.Enums;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.planetdefense.Utils.Managers.AssetsManager;

import java.util.Random;

public enum AsteroidType {
    METEOR, SATELLITE;

    public float getHp(){
        switch(this){
            /*case ROCK:
                return 1;*/
            case SATELLITE:
                return 15f;
            case METEOR:
                return 5f;
            default:
                return 0f;
        }
    }

    public float getMoneyDrop(){
        switch(this){
            /*case ROCK:
                return 10;*/
            case SATELLITE:
                return 30f;
            case METEOR:
                return 20f;
            default:
                return 0f;
        }
    }

    public float getSpeed(){
        switch(this){
            /*case ROCK:
                return 150;*/
            case SATELLITE:
                return 200f;
            case METEOR:
                return 400f;
            default:
                return 0f;
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

    public float getWidth(){
        switch(this){
            /*case ROCK:
                return 150;*/
            case SATELLITE:
                return 100f;
            case METEOR:
                return 100f;
            default:
                return 0f;
        }
    }

    public float getHeight(){
        switch(this){
            /*case ROCK:
                return 150;*/
            case SATELLITE:
                return 100f;
            case METEOR:
                return 50f;
            default:
                return 0f;
        }
    }

    public static AsteroidType getRandomType(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
