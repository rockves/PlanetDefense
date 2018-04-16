package com.game.planetdefense.Actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.planetdefense.Enums.LaserType;
import com.game.planetdefense.Enums.UpgradeType;
import com.game.planetdefense.Utils.Managers.AssetsManager;
import com.game.planetdefense.Utils.Singletons.UserData;
import com.game.planetdefense.Utils.StaticUtils;

public class Launcher extends Actor {

    private LaserType laserType;
    private Animation<TextureRegion> body_animation;
    private TextureRegion laser_texture;
    private Sprite launcher_body;
    private Sprite launcher_head;
    private Rectangle position;
    private float timer;
    private float state_time;

    public Launcher(AssetsManager assets_manager) {
        laserType = checkUserLaserType();
        laser_texture = laserType.getTexture(assets_manager);
        this.state_time = 0;
        this.body_animation = assets_manager.getLaser_launcher_body_animation();
        position = new Rectangle(0,0,0,0);
        this.launcher_body = new Sprite(body_animation.getKeyFrame(state_time, true));
        this.launcher_head = new Sprite(assets_manager.getLaser_launcher_head());
        this.launcher_head.setBounds(position.getX(),position.getY(),position.getWidth(),position.getHeight());
        this.launcher_body.setBounds(position.getX(),position.getY(),position.getWidth(),position.getHeight());
        timer = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        this.launcher_body.draw(batch,parentAlpha);
        this.launcher_head.draw(batch,parentAlpha);
    }

    @Override
    public void act(float delta) {
        timer += delta;
        state_time += delta;
        this.launcher_body.setRegion(body_animation.getKeyFrame(state_time,true));
        super.act(delta);
    }

    public void setLauncher(float x, float y, float width, float height){
        this.position.set(x,y,width,height);
        this.launcher_body.setBounds(x,y,width,height * 0.7f);
        this.launcher_head.setBounds(x - ((width * 0.3f)/2),y + (height * 0.55f),width + (width * 0.3f),height  * 0.3f);
        this.launcher_body.setOrigin(this.launcher_body.getWidth()/2, this.launcher_body.getHeight()/2);
        this.launcher_head.setOrigin(this.launcher_head.getWidth()/2, 0);
    }

    public Missile launchMissile(Missile missile, float target_x, float target_y){
        rotate(target_x,target_y);
        if(timer < StaticUtils.LAUNCHER_DELAY_TIME){
            return null;
        }
        missile.setMissile(this.launcher_head.getX() + this.launcher_head.getOriginX() - laserType.getWidth()/2, this.launcher_head.getY(), laserType, laser_texture);
        missile.setTarget(target_x,target_y);
        missile.rotateToTarget();
        missile.moveForward(this.launcher_head.getHeight());
        timer = 0;
        return missile;
    }

    private void rotate(float target_x, float target_y){
        Vector2 temp = new Vector2(0,0);
        float angle = temp.set(target_x,target_y).sub(this.launcher_head.getX() + this.launcher_head.getOriginX(),this.launcher_head.getY() + this.launcher_head.getOriginY()).angle();
        this.launcher_head.setRotation(angle - 90);
    }


    private LaserType checkUserLaserType(){
        return LaserType.values()[UserData.getInstance().getUpgradeLvl(UpgradeType.LaserUpgrade)];
    }

    @Override
    public float getX() {
        return position.getX();
    }
    @Override
    public float getY() {
        return position.getY();
    }
    public float getBodyOriginX() {
        return launcher_body.getOriginX();
    }
    public float getHeadOriginX() {
        return launcher_head.getOriginX();
    }
    public float getBodyOriginY() {
        return launcher_body.getOriginY();
    }
    public float getHeadOriginY() {
        return launcher_head.getOriginY();
    }
}
