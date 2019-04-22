package com.core;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.components.*;
import com.badlogic.gdx.math.MathUtils;

public class CreadorMundo {
    PooledEngine engine;

    private static final float SCENE_WIDTH = 12.8f;
    private static final float SCENE_HEIGHT = 7.2f;


    public CreadorMundo (PooledEngine engine) {
        this.engine = engine;
    }

    public void crear(){
        Entity player1 = player1();
        Entity gota = gota();
    }

    public void crearGota(){
        Entity asd = gota();
    }


    public Entity player1(){
        Entity entity = engine.createEntity();

        // creating components
        TextureComponent texture = new TextureComponent();
        TransformComponent transform = new TransformComponent();
        SizeComponent size = new SizeComponent();
        MovementComponent movement = new MovementComponent();
        PlayerComponent player = new PlayerComponent();
        CollisionComponent collision = new CollisionComponent();

        // initComponent
        texture.region = new TextureRegion(new Texture(Gdx.files.internal("bucket.png")));
        size.width = 1f;
        size.height = 1f;
        transform.pos.set(SCENE_WIDTH * .5f - size.width * .5f, SCENE_HEIGHT * .5f - size.height * .5f);
        collision.rectangle.x = transform.pos.x;
        collision.rectangle.y = transform.pos.y;
        collision.rectangle.width = 1;
        collision.rectangle.height = 1;

        // add Components to entities
        entity.add(transform);
        entity.add(movement);
        entity.add(texture);
        entity.add(size);
        entity.add(player);
        entity.add(collision);

        engine.addEntity(entity);

        return entity;
    }

    public Entity gota(){
        Entity entity = engine.createEntity();

        TextureComponent texture = new TextureComponent();
        TransformComponent transform = new TransformComponent();
        SizeComponent size = new SizeComponent();
        MovementComponent movement = new MovementComponent();
        CollisionComponent collision = new CollisionComponent();

        texture.region = new TextureRegion(new Texture(Gdx.files.internal("1.png")));
        size.width = 1f;
        size.height = 1f;
        transform.pos.set(MathUtils.random(0, SCENE_WIDTH) ,SCENE_HEIGHT + 1 - size.height * 0.5f);
        collision.rectangle.width = 3f;
        collision.rectangle.height = 3f;
        movement.velocity.y = -1;


        // add Components to entities
        entity.add(transform);
        entity.add(movement);
        entity.add(texture);
        entity.add(size);
        entity.add(collision);

        engine.addEntity(entity);

        return entity;
    }
}
