package de.dome;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Grid {
    private static int gridWidth = 2;
    private static int gridHeight = 2;

    public final int xPos;
    public final int yPos;
    private final int top, left, right, bottom;

    public Grid(int x, int y){
        xPos = x;
        yPos = y;

        left = xPos * gridWidth;
        right = left + gridWidth;
        bottom = yPos * gridHeight;
        top= bottom + gridHeight;
    }

    public void drawOutLine(ShapeRenderer shapeRenderer){

        shapeRenderer.line(left , top, left, bottom);
        shapeRenderer.line(right , top, right , bottom);
        shapeRenderer.line(left , top, right , top);
        shapeRenderer.line(left , bottom, right , bottom);

    }

    public void drawCoords(BitmapFont font, Batch batch) {
        font.draw(batch, xPos+","+yPos, xPos * gridWidth, yPos * gridHeight);
    }
}
