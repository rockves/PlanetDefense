package com.game.planetdefense.Enums;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.planetdefense.Utils.Managers.AssetsManager;

public enum LaserType {
    RED, YELLOW, BLUE, GREEN;

    public float getWidth(){
        switch(this){
            case RED:
                return 5f;
            case YELLOW:
                return 5f;
            case BLUE:
                return 5f;
            case GREEN:
                return 5f;
            default:
                return 0f;
        }
    }

    public float getHeight(){
        switch(this){
            case RED:
                return 120f;
            case YELLOW:
                return 120f;
            case BLUE:
                return 120f;
            case GREEN:
                return 120f;
            default:
                return 0f;
        }
    }

    public float getSpeed(){
        switch(this){
            case RED:
                return 700f;
            case YELLOW:
                return 1000f;
            case BLUE:
                return 1500f;
            case GREEN:
                return 2000f;
            default:
                return 0f;
        }
    }

    public float getDamageMultiplier(){
        switch(this){
            case RED:
                return 1f;
            case YELLOW:
                return 5f;
            case BLUE:
                return 15f;
            case GREEN:
                return 30f;
            default:
                return 0f;
        }
    }

    public TextureRegion getTexture(AssetsManager assets_manager){
        switch(this){
            case RED:
                return assets_manager.getRed_laser_texture();
            case YELLOW:
                return assets_manager.getYellow_laser_texture();
            case BLUE:
                return assets_manager.getBlue_laser_texture();
            case GREEN:
                return assets_manager.getGreen_laser_texture();
            default:
                return null;
        }
    }
}
