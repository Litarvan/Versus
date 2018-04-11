package fr.litarvan.versus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;

public class Versus extends Game
{
    @Override
    public void create()
    {
        this.setScreen(new GameFootball());
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        super.render();
    }
}
