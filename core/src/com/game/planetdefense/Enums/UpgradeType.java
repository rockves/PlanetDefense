package com.game.planetdefense.Enums;

import com.game.planetdefense.Utils.Singletons.UserData;

public enum UpgradeType {
    LaserUpgrade, DmgBonus;

    public float getPrice(){
        switch(this){
            case LaserUpgrade:
                return 1000f;
            case DmgBonus:
                return 50f;
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

    public float getUpgradeValue(){
        switch(this){
            case LaserUpgrade:
                return 1000f;
            case DmgBonus:
                return 1f;
            default:
                return 0f;
        }
    }
}
