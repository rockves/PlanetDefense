package com.game.planetdefense.Enums;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.planetdefense.Utils.Managers.AssetsManager;

import java.util.Random;

public enum AsteroidType {
    METEORITE, ICE_METEORITE, LAVA_METEORITE, GOLD_METEORITE, CRYSTAL_METEORITE;

    public float getHp(){
        switch(this){
            case METEORITE:
                return 2f;
            case ICE_METEORITE:
                return 10f;
            case LAVA_METEORITE:
                return 30f;
            case GOLD_METEORITE:
                return 50f;
            case CRYSTAL_METEORITE:
                return 100f;
            default:
                return 0f;
        }
    }

    public float getDifficultyPoints(){
        switch(this){
            case METEORITE:
                return 2f;
            case ICE_METEORITE:
                return 100f;
            case LAVA_METEORITE:
                return 150f;
            case GOLD_METEORITE:
                return 200f;
            case CRYSTAL_METEORITE:
                return 300f;
            default:
                return 0f;
        }
    }

    public float getMoneyDrop(){
        switch(this){
            case METEORITE:
                return 5f;
            case ICE_METEORITE:
                return 10f;
            case LAVA_METEORITE:
                return 20f;
            case GOLD_METEORITE:
                return 50f;
            case CRYSTAL_METEORITE:
                return 100f;
            default:
                return 0f;
        }
    }

    public float getSpeed(){
        switch(this){
            case METEORITE:
                return 250f;
            case ICE_METEORITE:
                return 250f;
            case LAVA_METEORITE:
                return 250f;
            case GOLD_METEORITE:
                return 250f;
            case CRYSTAL_METEORITE:
                return 250f;
            default:
                return 0f;
        }
    }

    public Animation<TextureRegion> getAnimation(AssetsManager assets_manager){
        switch(this){
            case METEORITE:
                return assets_manager.getMeteorite();
            case ICE_METEORITE:
                return assets_manager.getIce_meteorite();
            case LAVA_METEORITE:
                return assets_manager.getLava_meteorite();
            case GOLD_METEORITE:
                return assets_manager.getGold_meteorite();
            case CRYSTAL_METEORITE:
                return assets_manager.getCrystal_meteorite();
            default:
                return null;
        }
    }

    public float getWidth(){
        switch(this){
            case METEORITE:
                return 125f;
            case ICE_METEORITE:
                return 125f;
            case LAVA_METEORITE:
                return 125f;
            case GOLD_METEORITE:
                return 125f;
            case CRYSTAL_METEORITE:
                return 125f;
            default:
                return 0f;
        }
    }

    public float getHeight(){
        switch(this){
            case METEORITE:
                return 50f;
            case ICE_METEORITE:
                return 50f;
            case LAVA_METEORITE:
                return 50f;
            case GOLD_METEORITE:
                return 50f;
            case CRYSTAL_METEORITE:
                return 50f;
            default:
                return 0f;
        }
    }

    public static AsteroidType getRandomType(int range){
        Random random = new Random();
        return values()[random.nextInt(range)];
    }
}
