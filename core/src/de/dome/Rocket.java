package de.dome;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.codeandweb.physicseditor.PhysicsShapeCache;

import static de.dome.GameStage.SCALE;

public class Rocket extends Actor {

    private final PhysicsShapeCache physicsBodies;
    private final World world;
    private Sprite sprite;
    private Body body;


    public Rocket(Texture texture, final String actorName, PhysicsShapeCache physicsBodies, World world) {
        setName(actorName);
        this.physicsBodies = physicsBodies;
        this.world = world;
        sprite = new Sprite(texture);

        spritePos(10, 5);
    }

    public void spritePos(float x, float y){

        float width = sprite.getWidth() * SCALE;
        float height = sprite.getHeight() * SCALE;

        sprite.setSize(width, height);
        sprite.setOrigin(0, 0);

        sprite.setPosition(x, y);
        setHeight(sprite.getHeight());
        setWidth(sprite.getWidth());
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        body = physicsBodies.createBody("rocket", world, SCALE, SCALE);
        body.setBullet(false);
        body.setType(BodyDef.BodyType.DynamicBody);
        body.setTransform(getX(), getY(), 0);
        body.applyForce(100.0f, 100.0f, 10 * SCALE, 0, true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        sprite.setRotation((float) Math.toDegrees(body.getAngle()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
