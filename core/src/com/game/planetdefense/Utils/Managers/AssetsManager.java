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
    private BitmapFont small_game_font;

    private TextureAtlas atlas;

    private Animation<TextureRegion> laser_launcher_body_animation;
    private TextureRegion rock_texture;
    private Animation<TextureRegion> meteor_animation;
    private Animation<TextureRegion> satellite_animation;
    private Animation<TextureRegion> shop_guy_animation;
    private Animation<TextureRegion> upgrade_button_animation;

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
        laser_launcher_body_animation = new Animation<TextureRegion>(0.099f, atlas.findRegions("laserlauncher_body"), Animation.PlayMode.LOOP);
        meteor_animation = new Animation<TextureRegion>(0.099f, atlas.findRegions("meteor"), Animation.PlayMode.LOOP);
        satellite_animation = new Animation<TextureRegion>(0.099f, atlas.findRegions("satellite"), Animation.PlayMode.LOOP);
        upgrade_button_animation = new Animation<TextureRegion>(0.2f, atlas.findRegions("upgrade_button"), Animation.PlayMode.LOOP);
        shop_guy_animation = new Animation<TextureRegion>(0.2f, atlas.findRegions("shopguy"), Animation.PlayMode.LOOP);

    }

    public BitmapFont getGame_font() {
        return game_font;
    }

    public BitmapFont getSmall_game_font(){
        return game_font;
    }

    public Animation<TextureRegion> getLaser_launcher_body_animation() {
        return laser_launcher_body_animation;
    }

    public TextureRegion getLaser_launcher_head() {
        return atlas.findRegion("laserlauncher_head");
    }

    public TextureRegion getRed_laser_texture() {
        return atlas.findRegion("laser");
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

    public TextureRegion getStarBackground(){
        return atlas.findRegion("stars_background");
    }

    public TextureRegion getButtonsBackground(){
        return atlas.findRegion("menu_buttons_background");
    }

    public TextureRegion getButton_continue(){
        return atlas.findRegion("button_continue");
    }

    public TextureRegion getButton_continue_hover(){
        return atlas.findRegion("button_continue_hover");
    }

    public TextureRegion getButton_newGame(){
        return atlas.findRegion("button_newgame");
    }

    public TextureRegion getButton_newGame_hover(){
        return atlas.findRegion("button_newgame_hover");
    }

    public TextureRegion getButton_options(){
        return atlas.findRegion("button_options");
    }

    public TextureRegion getButton_options_hover(){
        return atlas.findRegion("button_options_hover");
    }

    public TextureRegion getButton_highscore(){
        return atlas.findRegion("button_highscore");
    }

    public TextureRegion getButton_highscore_hover(){
        return atlas.findRegion("button_highscore_hover");
    }

    public TextureRegion getEarthTexture(){
        return atlas.findRegion("earth");
    }

    public TextureRegion getMoneyCounterTexture(){
        return atlas.findRegion("money_count");
    }

    public Animation<TextureRegion> getShopGuyAnimation(){
        return shop_guy_animation;
    }

    public Animation<TextureRegion> getUpgrade_button_animation(){
        return upgrade_button_animation;
    }
}
