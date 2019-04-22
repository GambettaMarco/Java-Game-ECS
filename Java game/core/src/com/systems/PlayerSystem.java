package com.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.components.PlayerComponent;
import com.components.TransformComponent;

public class PlayerSystem extends IteratingSystem {


    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    public PlayerSystem() {
        super(Family.all(PlayerComponent.class).get());
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);


        if(transform.pos.x < 0) transform.pos.x = 0;
        if(transform.pos.x > 800 - 64) transform.pos.x = 800 - 64;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) transform.pos.x -= 3 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) transform.pos.x += 3 * Gdx.graphics.getDeltaTime();
    }
}

