package com.csc455.hw2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.io.IOException;

public class PathFinder extends ApplicationAdapter implements InputProcessor {

    private ShapeRenderer shapeRenderer;
    private Cell[][] grid;
    private OrthographicCamera camera;
    private Vector2 cell1;

    private Thread runner;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();

        try {
            GridGenerator.generateFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Grid.handleFile();
        grid = Grid.getGrid();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(Grid.getX(), Grid.getY() * (h / w));
        camera.setToOrtho(true, Grid.getX(), Grid.getY() * (h / w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        handleInput();
        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        generateGrid();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if(runner != null) {
                runner.interrupt();
            }
            try {
                GridGenerator.generateFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Grid.handleFile();
            grid = Grid.getGrid();
        }
    }

    private void generateGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                shapeRenderer.setColor(grid[i][j].getColor());
                shapeRenderer.rect(j, i, 1, 1);
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
        grid[(int) Math.floor(tp.y)][(int) Math.floor(tp.x)].setColor(new Color(1, 0, 0, 1));

        if (cell1 == null) {
            cell1 = con;
        } else {
            runner = new Thread(new Dijkstra((int) Math.floor(cell1.x), (int) Math.floor(cell1.y), (int) Math.floor(con.x), (int) Math.floor(con.y)));
            runner.start();
            cell1 = null;
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
}
