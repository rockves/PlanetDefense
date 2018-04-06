package com.game.planetdefense.Enums;

public enum AsteroidCategory {

        A,
        B,
        C,
        D;

        public float getSizeRatio(){
            if(this == A){
                return 0.5f;
            }else if(this == B){
                return 1f;
            }else if(this == C){
                return 1.5f;
            }else if(this == D){
                return 2f;
            }else {
                return 0;
            }
        }

        public float getHpRatio(){
            if(this == A){
                return 0.5f;
            }else if(this == B){
                return 1f;
            }else if(this == C){
                return 1.5f;
            }else if(this == D){
                return 2f;
            }else {
                return 0;
            }
        }

    public float getMoneyDropRatio(){
        if(this == A){
            return 1.25f;
        }else if(this == B){
            return 1f;
        }else if(this == C){
            return 1.5f;
        }else if(this == D){
            return 2f;
        }else {
            return 0;
        }
    }

        public float getSpeedRatio(){
            if(this == A){
                return 2f;
            }else if(this == B){
                return 1f;
            }else if(this == C){
                return 0.75f;
            }else if(this == D){
                return 0.5f;
            }else {
                return 0;
            }
        }
    }
