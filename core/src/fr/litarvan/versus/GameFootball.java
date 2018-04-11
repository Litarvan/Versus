package fr.litarvan.versus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class GameFootball extends VersusGame
{
    // Camera
    private SpriteBatch batch;

    // Physics
    private float acc = 0;
    private World world;

    private Sprite sprite;
    private Body body;
    private Body body2;

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture("voiture.png"));

        // Physics
        Box2D.init();

        this.world = new World(new Vector2(0, 0), true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(100, 500);

        body = world.createBody(bodyDef);

        PolygonShape circle = new PolygonShape();
        circle.setAsBox(sprite.getWidth(), sprite.getHeight());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 1f;
        fixtureDef.density = 0.00001f;

        body.createFixture(fixtureDef);
        body.setLinearDamping(2);

        circle.dispose();

        bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(300, 500);

        body2 = world.createBody(bodyDef);

        circle = new PolygonShape();
        circle.setAsBox(sprite.getWidth(), sprite.getHeight());

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 1f;
        fixtureDef.density = 0.00001f;

        body2.createFixture(fixtureDef);
        body2.setLinearDamping(2);

        circle.dispose();

        bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(0, 0);

        circle = new PolygonShape();
        circle.setAsBox(Gdx.graphics.getWidth(), 1);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 1f;

        world.createBody(bodyDef).createFixture(fixtureDef);

        bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(Gdx.graphics.getWidth(), 0);

        circle = new PolygonShape();
        circle.setAsBox(1, Gdx.graphics.getHeight());

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 1f;

        world.createBody(bodyDef).createFixture(fixtureDef);

        bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(0, Gdx.graphics.getHeight());

        circle = new PolygonShape();
        circle.setAsBox(Gdx.graphics.getWidth(), 1);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 1f;

        world.createBody(bodyDef).createFixture(fixtureDef);

        bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(0, 0);

        circle = new PolygonShape();
        circle.setAsBox(1, Gdx.graphics.getHeight());

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 1f;

        world.createBody(bodyDef).createFixture(fixtureDef);
    }

    @Override
    public void render(float delta)
    {
        delta = Math.min(delta, 0.25f);
        acc += delta;

        float step = 1/275f;

        while (acc >= step)
        {
            Vector2 vel = this.body.getLinearVelocity();
            Vector2 pos = this.body.getPosition();

            if (Gdx.input.isKeyPressed(Keys.Q)) {
                this.body.applyLinearImpulse(-1, 0, pos.x, pos.y, true);
            }

            if (Gdx.input.isKeyPressed(Keys.D)) {
                this.body.applyLinearImpulse(1, 0, pos.x, pos.y, true);
            }

            if (Gdx.input.isKeyPressed(Keys.Z)) {
                this.body.applyLinearImpulse(0, 1, pos.x, pos.y, true);
            }

            if (Gdx.input.isKeyPressed(Keys.S)) {
                this.body.applyLinearImpulse(0, -1, pos.x, pos.y, true);
            }

            world.step(step, 6, 2);
            acc -= step;
        }

        batch.begin();
        batch.draw(sprite, body.getPosition().x, body.getPosition().y, 0, 0, sprite.getWidth() * 2, sprite.getHeight() * 2, 1, 1, this.body.getAngle());
        batch.draw(sprite, body2.getPosition().x, body2.getPosition().y, 0, 0, sprite.getWidth() * 2, sprite.getHeight() * 2, 1, 1, this.body2.getAngle());
        batch.end();
    }
}
