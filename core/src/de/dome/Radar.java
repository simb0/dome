package de.dome;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Radar extends Actor {

    private final Circle circle;
    private Animation<TextureRegion> walkAnimation;
    private TextureRegion reg;
    private float stateTime;

    public Radar(Texture texture, final String actorName) {
        setName(actorName);
        createIdleAnimation(texture);

        circle = new Circle();
        circle.set(getX(), getY(), 10);
    }

    private void createIdleAnimation(Texture walkSheet) {

        int FRAME_COLS = 49;
        int FRAME_ROWS = 1;
        setHeight(64);
        setWidth(64);
        this.setPosition(43,7.7f);
        this.setScale(GameStage.SCALE, GameStage.SCALE);

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
        stateTime = 0f;
        reg = walkAnimation.getKeyFrame(0);

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        stateTime += delta;
        reg = walkAnimation.getKeyFrame(stateTime, true);

        circle.radius += 0.8;
        if(circle.radius > 50)
            circle.radius = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(reg, getX(), getY(), getOriginX(), getOriginY(),getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void drawShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(circle.x, circle.y, circle.radius);
    }
}
