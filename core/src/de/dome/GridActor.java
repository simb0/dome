package de.dome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GridActor extends Actor {

    private Grid[][] grid;
    private BitmapFont font;


    public GridActor(final String actorName) {
        setName(actorName);
        
        font = new BitmapFont();
        font.setUseIntegerPositions(false);
        font.getData().setScale(0.05f);


        grid = new Grid[50][50];
        for(int x = 0; x < 50; x++) {
            for(int y = 0; y < 50; y++) {
                grid[x][y] = new Grid(x,y);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y].drawCoords(font, batch);
            }
        }
    }

    public void drawShape(ShapeRenderer shapeRenderer) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y].drawOutLine(shapeRenderer);
            }
        }
    }

}
