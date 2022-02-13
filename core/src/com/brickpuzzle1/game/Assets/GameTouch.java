package com.brickpuzzle1.game.Assets;

import com.badlogic.gdx.Gdx;

public class GameTouch {
    //private static final String TAG = GameTouch.class.getName();
    public static float[] touchX;
    public static float[] touchY;
    public static boolean  istouch;
    public static int touchnum=5;
    public GameTouch() {
    }

    public static boolean IsTouch() {
        if (Gdx.input.isTouched()){
            touchX = new float[touchnum];
            touchY = new float[touchnum];
            istouch = false;
            for (int i = 0 ; i<touchnum; i++)
            {
                if (Gdx.input.isTouched(i)){
                    touchX[i]=Gdx.input.getX(i);
                    touchX[i]=touchX[i]*GameConstant.S_WIDTH;
                    touchX[i]=touchX[i]/GameConstant.V_WIDTH;

                    touchY[i]=Gdx.graphics.getHeight() - Gdx.input.getY(i);
                    touchY[i]=touchY[i]*GameConstant.S_HEIGHT;
                    touchY[i]=touchY[i]/ GameConstant.V_HEIGHT;
                    //Gdx.app.log("touch ", "Cor X "+Float.toString(touchX[i])+", Cor Y " +Float.toString(touchY[i]));
                    istouch = true;
                }
            }
            return istouch;
        }
        else {
            return false;
        }
    }
}
