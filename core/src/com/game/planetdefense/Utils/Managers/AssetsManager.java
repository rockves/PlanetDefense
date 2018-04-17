package com.game.planetdefense.Utils.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

    private AudioManager audio_manager;

    private Animation<TextureRegion> laser_launcher_body_animation;
    private Animation<TextureRegion> shop_guy_animation;
    private Animation<TextureRegion> upgrade_button_animation;

    //asteroids
    private Animation<TextureRegion> explosion_animation;

    private Animation<TextureRegion> meteorite;
    private Animation<TextureRegion> ice_meteorite;
    private Animation<TextureRegion> metal_meteorite;
    private Animation<TextureRegion> lava_meteorite;
    private Animation<TextureRegion> gold_meteorite;
    private Animation<TextureRegion> crystal_meteorite;
    private Animation<TextureRegion> slime_meteorite;
    private Animation<TextureRegion> purple_meteorite;


    public AssetsManager() {
        super();
    }

    public void setAssetsQueue(){
        soundLoad();
        fontLoad();
        loadTextureAtlas();
    }

    private void fontLoad(){
        FileHandleResolver resolver = new InternalFileHandleResolver();
        this.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        this.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter myFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        myFont.fontFileName = "Font/kid.ttf";

        float width = Gdx.graphics.getWidth();
        float ratio = width / 1280f;
        float baseSize = StaticUtils.FONT_SIZE_BASE;
        int size = (int) (baseSize * ratio);
        myFont.fontParameters.size = size;
        this.load("Font/kid.ttf", BitmapFont.class, myFont);
    }

    private void soundLoad(){
        this.load("Sound/laser_sound.wav", Sound.class);
        this.load("Sound/buy_sound.wav", Sound.class);
        this.load("Sound/explosion_sound.wav", Sound.class);

        this.load("Music/menu_music.mp3", Music.class);
        this.load("Music/game_music.mp3", Music.class);
        //this.load("Music/upgrade_menu_music.mp3", Music.class);
    }

    private void loadTextureAtlas(){
        this.load("Atlas/atlas", TextureAtlas.class);
    }

    public void getAssets(){
        game_font = this.get("Font/kid.ttf");
        atlas = this.get("Atlas/atlas");
        //get atlas assets
        laser_launcher_body_animation = new Animation<TextureRegion>(0.099f, atlas.findRegions("laserlauncher_body"), Animation.PlayMode.LOOP);
        upgrade_button_animation = new Animation<TextureRegion>(0.2f, atlas.findRegions("upgrade_button"), Animation.PlayMode.LOOP);
        shop_guy_animation = new Animation<TextureRegion>(0.2f, atlas.findRegions("shopguy"), Animation.PlayMode.LOOP);

        explosion_animation = new Animation<TextureRegion>(0.04f, atlas.findRegions("explosion"), Animation.PlayMode.NORMAL);

        meteorite = new Animation<TextureRegion>(0.099f, atlas.findRegions("meteorite"), Animation.PlayMode.LOOP);
        ice_meteorite = new Animation<TextureRegion>(0.099f, atlas.findRegions("ice_meteorite"), Animation.PlayMode.LOOP);
        metal_meteorite = new Animation<TextureRegion>(0.099f, atlas.findRegions("metal_meteorite"), Animation.PlayMode.LOOP);
        lava_meteorite = new Animation<TextureRegion>(0.099f, atlas.findRegions("lava_meteorite"), Animation.PlayMode.LOOP);
        gold_meteorite = new Animation<TextureRegion>(0.099f, atlas.findRegions("gold_meteorite"), Animation.PlayMode.LOOP);
        crystal_meteorite = new Animation<TextureRegion>(0.099f, atlas.findRegions("crystal_meteorite"), Animation.PlayMode.LOOP);
        slime_meteorite = new Animation<TextureRegion>(0.099f, atlas.findRegions("slime_meteorite"), Animation.PlayMode.LOOP);
        purple_meteorite = new Animation<TextureRegion>(0.099f, atlas.findRegions("propablysomepurpleflame_meteorite"), Animation.PlayMode.LOOP);

        audio_manager = new AudioManager(this);
    }

    public BitmapFont getGame_font() {
        return game_font;
    }

    public AudioManager getAudio_manager(){
        return audio_manager;
    }

    public Animation<TextureRegion> getLaser_launcher_body_animation() {
        return laser_launcher_body_animation;
    }

    public TextureRegion getLaser_launcher_head() {
        return atlas.findRegion("laserlauncher_head");
    }

    public TextureRegion getRed_laser_texture() {
        return atlas.findRegion("red_laser");
    }

    public TextureRegion getGreen_laser_texture() {
        return atlas.findRegion("green_laser");
    }

    public TextureRegion getYellow_laser_texture() {
        return atlas.findRegion("yellow_laser");
    }

    public TextureRegion getBlue_laser_texture() {
        return atlas.findRegion("blue_laser");
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

    public TextureRegion getButton_about(){
        return atlas.findRegion("button_about");
    }

    public TextureRegion getButton_about_hover(){
        return atlas.findRegion("button_about_hover");
    }

    public TextureRegion getButton_exit(){
        return atlas.findRegion("button_exit");
    }

    public TextureRegion getButton_exit_hover(){
        return atlas.findRegion("button_exit_hover");
    }

    public TextureRegion getEarthTexture(){
        return atlas.findRegion("earth");
    }

    public TextureRegion getShieldTexture(){
        return atlas.findRegion("shield");
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

    public TextureRegion getDamageUpgradeIcon(){
        return atlas.findRegion("upgrade_dmg_icon");
    }

    public TextureRegion getLaserUpgradeIcon(){
        return atlas.findRegion("upgrade_laser_icon");
    }

    public TextureRegion getWaveUpUpgradeIcon(){
        return atlas.findRegion("upgrade_wave_icon");
    }

    public TextureRegion getShieldUpgradeIcon(){
        return atlas.findRegion("upgrade_def_icon");
    }

    ////////////////////////

    public Animation<TextureRegion> getExplosion_animation() {
        return explosion_animation;
    }

    public Animation<TextureRegion> getMeteorite() {
        return meteorite;
    }

    public Animation<TextureRegion> getIce_meteorite() {
        return ice_meteorite;
    }

    public Animation<TextureRegion> getMetal_meteorite() {
        return metal_meteorite;
    }

    public Animation<TextureRegion> getLava_meteorite() {
        return lava_meteorite;
    }

    public Animation<TextureRegion> getGold_meteorite() {
        return gold_meteorite;
    }

    public Animation<TextureRegion> getCrystal_meteorite() {
        return crystal_meteorite;
    }

    public Animation<TextureRegion> getSlime_meteorite() {
        return slime_meteorite;
    }

    public Animation<TextureRegion> getPurple_meteorite() {
        return purple_meteorite;
    }
}
