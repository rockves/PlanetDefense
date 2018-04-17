package com.game.planetdefense.Utils.Managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.game.planetdefense.Utils.StaticUtils;

public class AudioManager {

    private Sound buy_sound;
    private long buy_sound_id = 0;
    private Sound explosion_sound;
    private long explosion_sound_id = 0;
    private Sound laser_sound;
    private long laser_sound_id = 0;

    private Music game_music;
    private Music menu_music;
    private Music upgrade_menu_music;

    public AudioManager(AssetsManager assetsManager) {
        buy_sound = assetsManager.get("Sound/buy_sound.wav", Sound.class);
        explosion_sound = assetsManager.get("Sound/explosion_sound.wav", Sound.class);
        laser_sound = assetsManager.get("Sound/laser_sound.wav", Sound.class);

        game_music = assetsManager.get("Music/game_music.mp3", Music.class);
        menu_music = assetsManager.get("Music/menu_music.mp3", Music.class);
        upgrade_menu_music = assetsManager.get("Music/menu_music.mp3", Music.class);
    }

    public void playBuySound(){
        if(buy_sound_id != 0){
            buy_sound.stop(buy_sound_id);
        }
        buy_sound_id = buy_sound.play(StaticUtils.BUY_SOUND_VOLUME);
    }

    public void playExplosionSound(){
        if(explosion_sound_id != 0){
            explosion_sound.stop(explosion_sound_id);
        }
        explosion_sound_id = explosion_sound.play(StaticUtils.EXPLOSION_SOUND_VOLUME);
    }

    public void playLaserSound(){
        if(laser_sound_id != 0){
            laser_sound.stop(laser_sound_id);
        }
        laser_sound_id = laser_sound.play(StaticUtils.LASER_SOUND_VOLUME);
    }

    public void playGameMusic(){
        game_music.setLooping(true);
        game_music.setVolume(StaticUtils.GAME_MUSIC_VOLUME);
        game_music.play();
    }
    public void stopGameMusic(){
        game_music.stop();
    }

    public void playMenuMusic(){
        menu_music.setLooping(true);
        menu_music.setVolume(StaticUtils.MENU_MUSIC_VOLUME);
        menu_music.play();
    }
    public void stopMenuMusic(){
        menu_music.stop();
    }

    public void playUpgradeMenuMusic(){
        upgrade_menu_music.setLooping(true);
        upgrade_menu_music.setVolume(StaticUtils.UPGRADE_MENU_MUSIC_VOLUME);
        upgrade_menu_music.play();
    }
    public void stopUpgradeMenuMusic(){
        upgrade_menu_music.stop();
    }
}
