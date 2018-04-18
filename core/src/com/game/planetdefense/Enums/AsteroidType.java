package com.game.planetdefense.Enums;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.planetdefense.Utils.Managers.AssetsManager;

import java.util.Random;

public enum AsteroidType {
    METEORITE, ICE_METEORITE, METAL_METEORITE, LAVA_METEORITE, GOLD_METEORITE, CRYSTAL_METEORITE, SLIME_METEORITE, PURPLE_METEORITE;

    public float getHp(){
        switch(this){
            case METEORITE:
                return 3f;
            case ICE_METEORITE:
                return 5f;
            case METAL_METEORITE:
                return 9f;
            case LAVA_METEORITE:
                return 13f;
            case GOLD_METEORITE:
                return 29f;
            case CRYSTAL_METEORITE:
                return 35f;
            case SLIME_METEORITE:
                return 40f;
            case PURPLE_METEORITE:
                return 50f;
            default:
                return 0f;
        }
    }

    public float getDifficultyPoints(){
        switch(this){
            case METEORITE:
                return 5f;
            case ICE_METEORITE:
                return 10f;
            case METAL_METEORITE:
                return 20f;
            case LAVA_METEORITE:
                return 30f;
            case GOLD_METEORITE:
                return 40f;
            case CRYSTAL_METEORITE:
                return 50f;
            case SLIME_METEORITE:
                return 60f;
            case PURPLE_METEORITE:
                return 70f;
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
            case METAL_METEORITE:
                return 10f;
            case LAVA_METEORITE:
                return 20f;
            case GOLD_METEORITE:
                return 30f;
            case CRYSTAL_METEORITE:
                return 40f;
            case SLIME_METEORITE:
                return 50f;
            case PURPLE_METEORITE:
                return 60f;
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
            case METAL_METEORITE:
                return 250f;
            case LAVA_METEORITE:
                return 250f;
            case GOLD_METEORITE:
                return 250f;
            case CRYSTAL_METEORITE:
                return 250f;
            case SLIME_METEORITE:
                return 250f;
            case PURPLE_METEORITE:
                return 350f;
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
            case METAL_METEORITE:
                return assets_manager.getMetal_meteorite();
            case LAVA_METEORITE:
                return assets_manager.getLava_meteorite();
            case GOLD_METEORITE:
                return assets_manager.getGold_meteorite();
            case CRYSTAL_METEORITE:
                return assets_manager.getCrystal_meteorite();
            case SLIME_METEORITE:
                return assets_manager.getSlime_meteorite();
            case PURPLE_METEORITE:
                return assets_manager.getPurple_meteorite();
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
            case METAL_METEORITE:
                return 125f;
            case LAVA_METEORITE:
                return 125f;
            case GOLD_METEORITE:
                return 125f;
            case CRYSTAL_METEORITE:
                return 125f;
            case SLIME_METEORITE:
                return 125f;
            case PURPLE_METEORITE:
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
            case METAL_METEORITE:
                return 50f;
            case LAVA_METEORITE:
                return 50f;
            case GOLD_METEORITE:
                return 50f;
            case CRYSTAL_METEORITE:
                return 50f;
            case SLIME_METEORITE:
                return 50f;
            case PURPLE_METEORITE:
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
