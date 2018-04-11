package com.game.planetdefense.Utils.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.game.planetdefense.Utils.StaticUtils;

public class AssetsManager extends AssetManager {

    private BitmapFont game_font;

    private TextureAtlas atlas;

    private TextureRegion launcher_texture;

    private TextureRegion missile_texture;

    private TextureRegion rock_texture;
    private Animation<TextureRegion> meteor_animation;
    private Animation<TextureRegion> satellite_animation;

    public AssetsManager() {
        super();
    }

    public void setAssetsQueue(){
        fontLoad();
        loadTextureAtlas();
    }

    private void fontLoad(){
        FileHandleResolver resolver = new InternalFileHandleResolver();
        this.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        this.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter myFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        myFont.fontFileName = "Font/roboto.ttf";

        float width = Gdx.graphics.getWidth();
        float ratio = width / 1280f;
        float baseSize = StaticUtils.FONT_SIZE_BASE;
        int size = (int) (baseSize * ratio);
        myFont.fontParameters.size = size;
        this.load("Font/roboto.ttf", BitmapFont.class, myFont);
    }

    private void loadTextureAtlas(){
        this.load("Atlas/atlas", TextureAtlas.class);
    }

    public void getAssets(){
        game_font = this.get("Font/roboto.ttf");
        atlas = this.get("Atlas/atlas");

        //get atlas assets
        launcher_texture = atlas.findRegion("launcher");
        missile_texture = atlas.findRegion("missile");
        rock_texture = atlas.findRegion("rock");
        meteor_animation = new Animation<TextureRegion>(0.099f, atlas.findRegions("meteor"), Animation.PlayMode.LOOP);
        satellite_animation = new Animation<TextureRegion>(0.099f, atlas.findRegions("satellite"), Animation.PlayMode.LOOP);

    }

    public BitmapFont getGame_font() {
        return game_font;
    }

    public TextureRegion getLauncher_texture() {
        return launcher_texture;
    }

    public TextureRegion getMissile_texture() {
        return missile_texture;
    }

    public TextureRegion getRock_texture() {
        return rock_texture;
    }

    public Animation<TextureRegion> getMeteor_animation() {
        return meteor_animation;
    }

    public Animation<TextureRegion> getSatellite_animation() {
        return satellite_animation;
    }

    public TextureRegion getTitleImage(){
        return atlas.findRegion("game_title");
    }

    public TextureRegion getMenuBackground(){
        return atlas.findRegion("menu_background");
    }

    public TextureRegion getGameBackground(){
        return atlas.findRegion("game_background");
    }
}
