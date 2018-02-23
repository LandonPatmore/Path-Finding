package com.csc455.hw2;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.PriorityQueue;

public class PathFinder implements ApplicationListener {

	private final Cell[][] grid = Grid.getGrid();
	private PriorityQueue<Cell> cells = new PriorityQueue<Cell>();


	@Override
	public void create() {
		Grid.handleFile();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
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
