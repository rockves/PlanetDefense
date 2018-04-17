package com.game.planetdefense.Enums;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.planetdefense.Utils.Managers.AssetsManager;
import com.game.planetdefense.Utils.Singletons.UserData;

public enum UpgradeType {
    LaserUpgrade, DmgBonus, ShieldBonus, StageBonus;

    public float getPrice(){
        switch(this){
            case LaserUpgrade:
                return 1000f;
            case DmgBonus:
                return 100f;
            case ShieldBonus:
                return 500f;
            case StageBonus:
                return 1000f;
            default:
                return 0f;
        }
    }

    public void addUpgradeLvl(){
        UserData.getInstance().addUpgradeLvl(this);
    }

    public int getUpgradeLvl(){
        return UserData.getInstance().getUpgradeLvl(this);
    }

    public int getUpgradeMaxLvl(){
        switch(this){
            case LaserUpgrade:
                return LaserType.values().length - 1;
            case DmgBonus:
                return -1;
            case ShieldBonus:
                return -1;
            case StageBonus:
                return -1;
            default:
                return 0;
        }
    }

    public float getUpgradeValue(){
        switch(this){
            case LaserUpgrade:
                return 1000f;
            case DmgBonus:
                return 1f;
            case ShieldBonus:
                return 1f;
            case StageBonus:
                return 1f;
            default:
                return 0f;
        }
    }

    public TextureRegion getUpgradeIcon(AssetsManager assetsManager){
        switch(this){
            case LaserUpgrade:
                return assetsManager.getLaserUpgradeIcon();
            case DmgBonus:
                return assetsManager.getDamageUpgradeIcon();
            case ShieldBonus:
                return assetsManager.getShieldUpgradeIcon();
            case StageBonus:
                return assetsManager.getWaveUpUpgradeIcon();
            default:
                return null;
        }
    }
}
