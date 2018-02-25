package com.csc455.hw2;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.PriorityQueue;

public class PathFinder extends ApplicationAdapter implements InputProcessor {

    private ShapeRenderer shapeRenderer;
    private Cell[][] grid;
    private OrthographicCamera camera;


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

		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Dijkstra.run(0,0,99,99);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				System.out.print(grid[i][j].getPathSymbol() + " ");
			}
			System.out.println();
		}

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		generateGrid();
    }

	private void generateGrid(){
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				shapeRenderer.setColor(grid[j][i].getColor());
				shapeRenderer.rect(i, j, 1,1);
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

    @Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        Vector3 tp = new Vector3();
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        camera.unproject(tp.set(screenX, screenY, 0));
        System.out.println("X: " + Math.floor(tp.x) + ", Y: " + Math.floor(tp.y));
        grid[(int)Math.floor(tp.y)][(int)Math.floor(tp.x)].setColor(new Color(0,0,0,1));
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
