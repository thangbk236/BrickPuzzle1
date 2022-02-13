package com.brickpuzzle1.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.brickpuzzle1.game.Assets.GameConstant;
import com.brickpuzzle1.game.Assets.GameJson;
import com.brickpuzzle1.game.BrickPuzzle1;
import com.brickpuzzle1.game.Screen.MainMenuScreenLayer.GridLayer;
import com.brickpuzzle1.game.Screen.MainMenuScreenLayer.MainMenuButtonLayer;

public class MainMenuScreen implements Screen {
    protected final Game game;
    GridLayer gridLayer;
    MainMenuButtonLayer mainMenuButtonLayer;
    protected static MainMenuScreen mainMenuScreen;// single stone

    private OrthographicCamera camera;
    SpriteBatch batch;

    public static boolean firstTimePlay=true;
    private boolean timeToShowAdsLatch;
    private float timeToShowAds;
    public boolean onSaveToJson;

    public MainMenuScreen(Game game){
        this.game=game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstant.S_WIDTH, GameConstant.S_HEIGHT);
        camera.update();
        batch = new SpriteBatch();

        gridLayer = new GridLayer(0);
        mainMenuButtonLayer = new MainMenuButtonLayer(game);
    }

    public static MainMenuScreen getInstance(Game game,boolean newscreen){
        if (newscreen){
            mainMenuScreen=new MainMenuScreen(game);
        }
        else {
            if(mainMenuScreen==null){
                mainMenuScreen=new MainMenuScreen(game);
            }
        }
        mainMenuScreen.timeToShowAds=0.0f;
        mainMenuScreen.timeToShowAdsLatch=false;
        return mainMenuScreen;
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gridLayer.GameUpdate(delta,batch);
        mainMenuButtonLayer.GameUpdate(delta,batch);

        // sau 3s thi cho hien thi ads
        if (!firstTimePlay){
            timeToShowAds+=delta;
            if (timeToShowAds>3.0f){//sau 3s thi cho hien thi ads
                timeToShowAds=3.0f;
                if (!timeToShowAdsLatch){
                    timeToShowAdsLatch=true;
                    int i = MathUtils.random(1,5);
                    if (i==5) {
                        BrickPuzzle1.playservices.showBannerAd();
                        Gdx.app.log("ADS","banner");
                    }
                    else {
                        if(i==2||i==4){
                            Gdx.app.log("ADS","Interstitial");
                            BrickPuzzle1.playservices.showInterstitialAd(new Runnable() {
                                @Override
                                public void run() {
                                    //Gdx.app.exit();
                                }
                            });
                        }
                        else {
                            Gdx.app.log("ADS","RewardedVideo");
                            BrickPuzzle1.playservices.showRewardedVideoAd();
                        }
                    }
                }
            }
        }
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
        gridLayer.dispose();
        mainMenuButtonLayer.dispose();
    }
}
