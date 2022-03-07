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
        spritePos(0, 5);
    }

    public Sprite getSprite() {
        return sprite;
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
        MassData massData = new MassData();
        massData.mass = 1;

        body.setMassData(massData);
        body.setType(BodyDef.BodyType.DynamicBody);
        body.setTransform(getX(), getY(), 0);
        body.applyForce(new Vector2(600,1300), new Vector2(25, 0),true);
        body.setAngularVelocity(-1);
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
