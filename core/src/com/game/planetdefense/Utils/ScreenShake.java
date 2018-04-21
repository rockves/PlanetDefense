package com.game.planetdefense.Utils;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.Random;

public class ScreenShake {

    private float elapsed;
    private float duration;
    private float intensity;
    private Random random;
    private float base_x;
    private float base_y;

    public ScreenShake(OrthographicCamera camera) {
        this.elapsed = 0;
        this.duration = 0;
        this.intensity = 0;
        this.random = new Random();
        this.base_x = camera.position.x;
        this.base_y = camera.position.y;
    }

    public void shake(float intensity, float duration){
        this.intensity = intensity;
        this.duration = duration / 1000f;
        this.elapsed = 0;
    }

    public void update(float delta, OrthographicCamera camera) {

        // Only shake when required.
        if(elapsed < duration) {

            // Calculate the amount of shake based on how long it has been shaking already
            float currentPower = intensity * camera.zoom * ((duration - elapsed) / duration);
            float x = (random.nextFloat() - 0.5f) * currentPower;
            float y = (random.nextFloat() - 0.5f) * currentPower;
            camera.translate(-x, -y);

            // Increase the elapsed time by the delta provided.
            elapsed += delta;
        }
    }

    public void returnToBase(OrthographicCamera camera){
        camera.position.x = base_x;
        camera.position.y = base_y;
    }
}
