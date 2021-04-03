package de.dome;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import static de.dome.GameStage.SCALE;

public class City extends Actor {

    private Sprite sprite;

    public City(Texture texture, final String actorName) {
        setName(actorName);
        sprite = new Sprite(texture);

        spritePos(35, 1.5f);
    }

    public void spritePos(float x, float y){

        float width = (float) ((sprite.getWidth() * SCALE) + (10));
        float height = (float) ((sprite.getHeight() * SCALE) + (10));

        sprite.setSize(width, height);
        sprite.setOrigin(0, 0);

        sprite.setPosition(x, y);
        setHeight(sprite.getHeight());
        setWidth(sprite.getWidth());
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
