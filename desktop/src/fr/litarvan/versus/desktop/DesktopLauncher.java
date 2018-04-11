package fr.litarvan.versus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.litarvan.versus.Versus;

public class DesktopLauncher
{
    public static void main(String[] arg)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Versus";
        config.width = 1250;
        config.height = 925;
        config.resizable = true;
        config.useGL30 = true;
        //config.fullscreen = true;

        new LwjglApplication(new Versus(), config);
    }
}
