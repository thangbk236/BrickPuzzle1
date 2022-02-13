package com.brickpuzzle1.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.brickpuzzle1.game.Assets.GameAsset;
import com.brickpuzzle1.game.Assets.GameConstant;
import com.brickpuzzle1.game.Assets.GameJson;
import com.brickpuzzle1.game.Screen.PlayScreenLayer.PlayScreenLayer;

public class PlayScreen implements Screen {
    protected final Game game;
    private static PlayScreen playScreen;
    PlayScreenLayer playScreenLayer;
    private OrthographicCamera camera;
    SpriteBatch batch;

    public boolean onSaveToJson;
    private int gameId;
    public float time;

    public PlayScreen(Game game){
        this.game=game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstant.S_WIDTH, GameConstant.S_HEIGHT);
        camera.update();
        batch = new SpriteBatch();

        playScreenLayer = new PlayScreenLayer(game);
        onSaveToJson=false;
    }
    public static PlayScreen getInstance(Game game,boolean newscreen){
        if (newscreen){
            playScreen=new PlayScreen(game);
            playScreen.playScreenLayer.Set_State(0);
        }
        else {
            if(playScreen==null){
                playScreen=new PlayScreen(game);
            }
            playScreen.playScreenLayer.Set_State(4);// khi vao man choi thi phai cho dem lui set lai state
        }
        return playScreen;
    }
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        playScreenLayer.render(delta,batch);
    }
    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        batch.setProjectionMatrix(camera.combined);
    }
    @Override
    public void show(){

    }
    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        if (!onSaveToJson){
            onSaveToJson=true;
            GameJson.save();
        }
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        onSaveToJson=false;

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        batch.dispose();
        playScreen.dispose();
    }
}
