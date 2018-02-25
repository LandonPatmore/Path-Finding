package com.csc455.hw2;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.PriorityQueue;

public class PathFinder extends ApplicationAdapter implements InputProcessor {

    private ShapeRenderer shapeRenderer;
    private Cell[][] grid;
    private OrthographicCamera camera;
    private Vector2 cell1;
    private Vector2 cell2;


    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(100, 100 * (h / w));
        camera.setToOrtho(true, 100, 100 * (h / w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        Grid.handleFile();
        grid = Grid.getGrid();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        handleInput();
        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        generateGrid();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            Grid.handleFile();
            grid = Grid.getGrid();
        }
    }

    private void generateGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                shapeRenderer.setColor(grid[j][i].getColor());
                shapeRenderer.rect(i, j, 1, 1);
            }
        }
        shapeRenderer.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        Vector3 tp = new Vector3();
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        camera.unproject(tp.set(screenX, screenY, 0));
        Vector2 con = new Vector2(tp.x, tp.y);
        grid[(int) Math.floor(tp.y)][(int) Math.floor(tp.x)].setColor(new Color(0, 1, 1, 1));

        if (cell1 == null) {
            cell1 = con;
        } else {
            cell2 = con;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        new Dijkstra().run((int) Math.floor(cell1.x), (int) Math.floor(cell1.y), (int) Math.floor(cell2.x), (int) Math.floor(cell2.y));
                        cell1 = null;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
