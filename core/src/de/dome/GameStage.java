package de.dome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.codeandweb.physicseditor.PhysicsShapeCache;

public class GameStage extends Stage {

    public static float SCALE = 0.022f;

    private Image backGroundImage;
    private Radar radar;
    private City city;
    private World world;

    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;

    private boolean pause = false;

    float accumulator = 0;
    private Box2DDebugRenderer debugRenderer;
    private PhysicsShapeCache physicsBodies;
    private Rocket rocket;
    private Defender defender;

    public GameStage() {
        super(new ExtendViewport(50,50));
        setupWorld();

    }

    private void setupWorld() {
        Gdx.input.setInputProcessor(this);
        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        setupBackGround();
        setupCity();
        setupRadar();
        setupGrid();
        setupDefender();

        physicsBodies = new PhysicsShapeCache("rocket.xml");
        setupRocket(physicsBodies, world);
    }

    private void setupDefender() {
        Texture backGroundTexture = new Texture("flak.png");
        backGroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        defender = new Defender(backGroundTexture, "flak", world);
        addActor(defender);
        defender.shootAt(11,10);
    }

    @Override
    public void act(float delta) {
        if(pause)
            return;
        super.act(delta);
    }

    @Override
    public void draw() {
        super.draw();
        if(!pause) {
            checkCollision();
            stepWorld();
        }
        debugRenderer.render(world, this.getCamera().combined);
    }



    private void checkCollision() {
//        if(radar.checkCol(rocket))
//            pause = true;
    }

    private void stepWorld() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= STEP_TIME) {
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            accumulator -= STEP_TIME;
        }
    }

    private void setupBackGround() {
        Texture backGroundTexture = new Texture("land.png");
        backGroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        backGroundImage = new Image(backGroundTexture);
        backGroundImage.setName("bg");
        backGroundImage.setScale(SCALE,SCALE);
        addActor(backGroundImage);
    }

    private void setupRadar() {
        Texture backGroundTexture = new Texture("radar.png");
        backGroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        radar = new Radar(backGroundTexture, "radar");
        addActor(radar);
    }

    private void setupGrid() {
        addActor(new GridActor("grid"));
    }

    private void setupCity() {
        Texture backGroundTexture = new Texture("city.png");
        backGroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        city = new City(backGroundTexture, "city");
        addActor(city);
    }

    private void setupRocket(PhysicsShapeCache physicsBodies, World world) {
        Texture backGroundTexture = new Texture("rocket.png");
        backGroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        rocket = new Rocket(backGroundTexture, "rocket", physicsBodies, world);
        addActor(rocket);
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        debugRenderer.dispose();
    }
}
