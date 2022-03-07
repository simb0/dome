package de.dome;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static de.dome.GameStage.SCALE;

public class Defender extends Actor {

    private final Sprite sprite;
    private final World world;

    public Defender(Texture texture, final String actorName, World world) {
        this.world = world;
        setName(actorName);
        sprite = new Sprite(texture);
        spritePos(60, 8f);
    }

    public void spritePos(float x, float y){

        float width = (float) (sprite.getWidth() * SCALE * 0.3);
        float height = (float) (sprite.getHeight() * SCALE * 0.3);

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

    public void shootAt(int x, int y) {
        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
// Set our body's starting position in the world
        bodyDef.position.set(sprite.getX(), sprite.getY());

// Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);
        body.setLinearVelocity(-10,20);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(2f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit


// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);


// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }


}
