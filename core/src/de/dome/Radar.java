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
        this.setPosition(43, 7.7f);
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
        if (circle.radius > 30)
            circle.radius = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(reg, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void drawShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(circle.x, circle.y, circle.radius);
    }

    public void checkCol(Rocket rocket) {
        if(circleRectCollision(new Vector2(circle.x, circle.y), new Vector2(rocket.getSprite().getBoundingRectangle().x, rocket.getSprite().getBoundingRectangle().y),
                circle.radius, rocket.getSprite().getBoundingRectangle().width, rocket.getSprite().getBoundingRectangle().height)) {
            System.out.println("Help rocket incoming!");
        }
    }

    private Boolean circleRectCollision(Vector2 circleCenter, Vector2 rectCenter,
                                        float radius, float width, float height) {
        float distanceY = Math.abs(circleCenter.y - rectCenter.y);
        if (distanceY > (height / 2 + radius)) return false;

        float distanceX = Math.abs(circleCenter.x - rectCenter.x);
        if (distanceX > (width / 2 + radius)) return false;

        if (distanceX <= (width / 2)) return true;
        if (distanceY <= (height / 2)) return true;

        float a = distanceX - width / 2;
        float b = distanceY - height / 2;
        float cSqr = a * a + b * b;
        return (cSqr <= (radius * radius));
    }
}
