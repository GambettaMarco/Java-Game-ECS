package com.core;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.systems.CollisionSystem;
import com.systems.MovementSystem;
import com.systems.PlayerSystem;
import com.systems.RenderSystem;

public class Core extends ApplicationAdapter {

	private static final String TAG = "AshleySample";

	private static final float SCENE_WIDTH = 12.8f;
	private static final float SCENE_HEIGHT = 7.2f;

	CreadorMundo mundo;

	private PooledEngine coreEngine;
	private OrthographicCamera camera;
	private FitViewport viewport;
	private long lastDropTime;


	@Override
	public void create () {
		camera = new OrthographicCamera();

		viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
		// Centers camera
		viewport.getCamera().position.set(
				viewport.getCamera().position.x + SCENE_WIDTH*0.5f,
				viewport.getCamera().position.y + SCENE_HEIGHT*0.5f,
				0);
		viewport.getCamera().update();
		viewport.update((int)SCENE_WIDTH, (int)SCENE_HEIGHT);

		camera.update();
		coreEngine = new PooledEngine();
		mundo = new CreadorMundo(coreEngine);


		//add to the engine
		coreEngine.addSystem(new MovementSystem());
		coreEngine.addSystem(new RenderSystem(camera));
		coreEngine.addSystem(new PlayerSystem());
		coreEngine.addSystem(new CollisionSystem());

		mundo.crear();


	}

	public void crearGota(){
		if(TimeUtils.timeSinceNanos(lastDropTime) > 1000000000){
			Entity gota = mundo.gota();
			lastDropTime = TimeUtils.nanoTime();
		}
	}
	public void removerEntitdades(){
		if(Gdx.input.isKeyPressed(Input.Keys.R)){
			coreEngine.removeAllEntities();
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		coreEngine.update(Gdx.graphics.getDeltaTime());
		removerEntitdades();
		crearGota();
	}

}
