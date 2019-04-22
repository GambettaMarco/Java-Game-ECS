package com.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.components.CollisionComponent;
import com.components.PlayerComponent;
import com.components.TransformComponent;

public class CollisionSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<PlayerComponent> pc = ComponentMapper.getFor(PlayerComponent.class);
    private ComponentMapper<CollisionComponent> cc = ComponentMapper.getFor(CollisionComponent.class);

    public CollisionSystem() {
        super(Family.all(CollisionComponent.class, PlayerComponent.class).get());
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        PlayerComponent player = pc.get(entity);
        CollisionComponent collision = cc.get(entity);

        if(collision.rectangle.contains(Gdx.input.getX(),Gdx.input.getY())){
            System.out.println("Mouse encima");
        }
    }
}
