package de.dome;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private GameStage gameStage;
	private ShapeRenderer shapeRenderer;
	private Radar radar;
	private GridActor gridActor;
	private FrameRate frameRate;

	@Override
	public void create () {
		gameStage = new GameStage();
		for(Actor a : gameStage.getActors()) {
			if(a.getName().equalsIgnoreCase("radar")) {
				radar = (Radar) a;
			} else if(a.getName().equalsIgnoreCase("grid")) {
				gridActor = (GridActor)a;
			}
		}
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.setProjectionMatrix(gameStage.getViewport().getCamera().combined);

		frameRate = new FrameRate();
	}

	@Override
	public void render () {
		frameRate.update();
		ScreenUtils.clear(0, 0, 1f, 1);
		gameStage.act(Gdx.graphics.getDeltaTime());
		gameStage.draw();

		shapeRenderer.begin();
		radar.drawShape(shapeRenderer);
		gridActor.drawShape(shapeRenderer);
		shapeRenderer.end();
		frameRate.render();
	}



	@Override
	public void dispose () {
		gameStage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		gameStage.getViewport().update(width, height, true);
	}
}
