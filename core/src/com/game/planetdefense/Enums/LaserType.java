package com.game.planetdefense.Enums;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.planetdefense.Utils.Managers.AssetsManager;

public enum LaserType {
    RED;

    public float getWidth(){
        switch (this){
            case RED:
                return 5f;
            default:
                return 0f;
        }
    }

    public float getHeight(){
        switch (this){
            case RED:
                return 120f;
            default:
                return 0f;
        }
    }

    public float getSpeed(){
        switch (this){
            case RED:
                return 1000f;
            default:
                return 0f;
        }
    }

    public TextureRegion getTexture(AssetsManager assets_manager){
        switch(this){
            case RED:
                return assets_manager.getRed_laser_texture();
            default:
                return null;
        }
    }
}
