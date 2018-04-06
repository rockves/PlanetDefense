package com.game.planetdefense.Utils.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GraphicManager {

    @SuppressWarnings("FieldCanBeLocal")
    private static TextureAtlas atlas;

    public static TextureRegion launcher_texture;

    public static TextureRegion missile_texture;

    public static TextureRegion rock_texture;
    public static Animation<TextureRegion> meteor_animation;
    public static Animation<TextureRegion> satellite_animation;

    public static void loadAssets(){
        atlas = new TextureAtlas(Gdx.files.internal("Atlas/atlas"));
        launcher_texture = atlas.findRegion("launcher");
        missile_texture = atlas.findRegion("missile");
        rock_texture = atlas.findRegion("rock");
        meteor_animation = new Animation<TextureRegion>(0.099f, atlas.findRegions("meteor"), Animation.PlayMode.LOOP);
        satellite_animation = new Animation<TextureRegion>(0.099f, atlas.findRegions("satellite"), Animation.PlayMode.LOOP);
    }
}
