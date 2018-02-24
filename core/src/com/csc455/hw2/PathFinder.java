package com.csc455.hw2;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.PriorityQueue;

public class PathFinder implements ApplicationListener {


	@Override
	public void create() {
		Grid.handleFile();
		Cell[][] grid = Grid.getGrid();
		Cell c = Dijkstra.run(0,0,2,2);

		while(c != null){
			c.setPathSymbol("-");
			c = c.getPredecessor();
		}

		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				System.out.print(grid[i][j].getPathSymbol() + " ");
			}
			System.out.println();
		}
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
