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
    private Sprite ballSprite;
    private Sprite terrainSprite;
    private Body body;
    private Body body2;
    private Body ball;

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture("voiture.png"));
        ballSprite = new Sprite(new Texture("balle.png"));
        terrainSprite = new Sprite(new Texture("terrain.png"));

        // Physics
        Box2D.init();

        this.world = new World(new Vector2(0, 0), true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(100, 500);

        body = world.createBody(bodyDef);

        PolygonShape circle = new PolygonShape();
        circle.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

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
        circle.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

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
        bodyDef.position.set(0, -git stat50);

        circle = new PolygonShape();
        circle.setAsBox(Gdx.graphics.getWidth(), 1);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 1f;

        world.createBody(bodyDef).createFixture(fixtureDef);

        bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(Gdx.graphics.getWidth() - 50, 0);

        circle = new PolygonShape();
        circle.setAsBox(1, Gdx.graphics.getHeight());

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 1f;

        world.createBody(bodyDef).createFixture(fixtureDef);

        bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(0, Gdx.graphics.getHeight() - 50);

        circle = new PolygonShape();
        circle.setAsBox(Gdx.graphics.getWidth(), 1);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 1f;

        world.createBody(bodyDef).createFixture(fixtureDef);

        bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(-50, 0);

        circle = new PolygonShape();
        circle.setAsBox(1, Gdx.graphics.getHeight());

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.restitution = 1f;

        world.createBody(bodyDef).createFixture(fixtureDef); bodyDef = new BodyDef();

        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(1000, 50);

        CircleShape theCircle = new CircleShape();
        theCircle.setRadius(ballSprite.getWidth() / 2);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = theCircle;
        fixtureDef.restitution = 2.0f;
        fixtureDef.density = 0.0000001f;

        ball = world.createBody(bodyDef);
        ball.createFixture(fixtureDef);

        ball.setLinearDamping(0.25f);
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
        batch.draw(terrainSprite, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(sprite, body.getPosition().x, body.getPosition().y/*, 0, 0, sprite.getWidth(), sprite.getHeight(), 1, 1, this.body.getAngle()*/);
        batch.draw(sprite, body2.getPosition().x, body2.getPosition().y/*, 0, 0, sprite.getWidth(), sprite.getHeight(), 1, 1, this.body2.getAngle()*/);
        batch.draw(ballSprite, ball.getPosition().x, ball.getPosition().y/*, 0, 0, ballSprite.getWidth(), ballSprite.getHeight(), 1, 1, ball.getAngle()*/);
        batch.end();
    }
}
